package com.vku.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vku.dtos.AccountDTO;
import com.vku.dtos.LoginDto;
import com.vku.models.Account;
import com.vku.repositories.AccountRepository;

@Service
public class AuthUserService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailSenderService emailSenderService;

	public void register(AccountDTO userDto) throws Exception {
		// Check if email already exists
		if (accountRepository.existsByEmail(userDto.getEmail())) {
			throw new Exception("Email already exists");
		}

		// Encrypt the password
		String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

		// Create and save new Account
		Account account = new Account();
		// Map fields from AccountDTO to Account appropriately
		// ...
		account.setPassword(encryptedPassword);
		// ...
		accountRepository.save(account);
	}

	public Account login(LoginDto loginDto) throws Exception {
		// Find the admin account with the given email
		Account account = accountRepository.findByEmail(loginDto.getEmail());

		if (account == null) {
			throw new Exception("Email not found");
		}

		// Check if password matches
		if (!passwordEncoder.matches(loginDto.getPassword(), account.getPassword())) {
			throw new Exception("Incorrect password");
		}

		return account;
	}

	public void changePassword(Long id, String newPass) throws Exception {
		// Find the admin account
		Account account = accountRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

		// Check if current password matches
		if (!passwordEncoder.matches(newPass, account.getPassword())) {
			throw new Exception("Incorrect current password");
		}

		// Encrypt the new password
		String encryptedPassword = passwordEncoder.encode(newPass);

		// Update the password
		account.setPassword(encryptedPassword);
		accountRepository.save(account);
	}

	public void updateInfo(Account currentUser, Account updateInfoDto) throws Exception {
		// Map fields from updateInfoDto to currentUser appropriately
		// ...
		currentUser.setAccountId(updateInfoDto.getAccountId());
		currentUser.setFullName(updateInfoDto.getFullName());
		currentUser.setPhoneNumber(updateInfoDto.getPhoneNumber());
		currentUser.setEmail(updateInfoDto.getEmail());
//    	currentUser.set(updateInfoDto.getFullName());
		accountRepository.save(currentUser);
	}

	private static final int CODE_LENGTH = 5;
	private static final int EXPIRATION_MINUTES = 60;

	private Map<String, VerificationCode> verificationCodes = new HashMap<>();

	public void forgotPassword(String email) throws Exception {
		// Tìm tài khoản admin với email đã cho
		Account account = accountRepository.findByEmail(email);

		if (account == null) {
			throw new Exception("Email not found");
		}

		// Tạo mã xác nhận ngẫu nhiên
		String code = generateConfirmationCode();

		// Lưu trữ mã xác nhận với thông tin hết hạn
		VerificationCode verificationCode = new VerificationCode(code,
				LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));
		verificationCodes.put(email, verificationCode);

		// Tạo nội dung email
		String subject = "Khôi phục mật khẩu";
		String content = "Mã xác nhận của bạn là: <b>" + code +"</b>";

		// Gửi email chứa mã xác nhận
//		sendEmail(email, subject, content);
		try {
	        boolean isEmailSent = emailSenderService.sendMail("Password Reset", "Content", email);
	        if (isEmailSent) {
	        } else {
	        	throw new Exception ("Failed to send mail");
	        }
	    } catch (Exception e) {
	    }
	}

	public boolean verifyCode(String email, String code) {
		VerificationCode verificationCode = verificationCodes.get(email);

		if (verificationCode != null && verificationCode.getCode().equals(code)) {
			// Kiểm tra mã xác nhận còn hợp lệ hay không
			if (verificationCode.getExpirationDateTime().isAfter(LocalDateTime.now())) {
				// Mã xác nhận hợp lệ
				verificationCodes.remove(email); // Xóa mã xác nhận sau khi sử dụng
				return true;
			}
		}

		return false;
	}

	private String generateConfirmationCode() {
		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder();

		for (int i = 0; i < CODE_LENGTH; i++) {
			int digit = random.nextInt(10);
			codeBuilder.append(digit);
		}

		return codeBuilder.toString();
	}


	private static class VerificationCode {
		private String code;
		private LocalDateTime expirationDateTime;

		public VerificationCode(String code, LocalDateTime expirationDateTime) {
			this.code = code;
			this.expirationDateTime = expirationDateTime;
		}

		public String getCode() {
			return code;
		}

		public LocalDateTime getExpirationDateTime() {
			return expirationDateTime;
		}
	}
}
