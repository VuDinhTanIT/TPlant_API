package com.vku.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vku.dtos.AccountDTO;
import com.vku.dtos.ChangePasswordDto;
import com.vku.dtos.LoginDto;
import com.vku.models.Account;
import com.vku.services.AdminService;
import com.vku.services.AuthUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AuthUserController {

	@Autowired
	private AuthUserService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AccountDTO userDto) {
		try {
			authService.register(userDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest) {

		Account account;
		try {
			account = authService.login(loginDto);
			if (account != null) {

				// Lưu trữ thông tin người dùng vào session
				HttpSession session = httpServletRequest.getSession();

				session.setAttribute("currentUser", account);

				return ResponseEntity.ok().body(account);
			} else {
				return ResponseEntity.badRequest().body(null);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());

		}

	}

	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(HttpServletRequest httpServletRequest, String newPass) {
		try {
			// Truy cập thông tin người dùng từ session
			HttpSession session = httpServletRequest.getSession();
			Account currentUser = (Account) session.getAttribute("currentUser");

			authService.changePassword(currentUser.getAccountId(), newPass);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update-info")
	public ResponseEntity<?> updateInfo(HttpServletRequest httpServletRequest, @RequestBody Account updateInfoDto) {
		try {
			// Truy cập thông tin người dùng từ session
			HttpSession session = httpServletRequest.getSession();
			Account currentUser = (Account) session.getAttribute("currentUser");
			authService.updateInfo(currentUser, updateInfoDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(String email) {
		try {
			authService.forgotPassword(email);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
