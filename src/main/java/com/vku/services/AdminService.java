package com.vku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vku.dtos.AccountDTO;
import com.vku.dtos.LoginDto;
import com.vku.models.AdminAccount;
import com.vku.repositories.AdminAccountRepository;

@Service
public class AdminService {

    @Autowired
    private AdminAccountRepository adminAccountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void register(AccountDTO userDto) throws Exception {
        // Check if email already exists
        if (adminAccountRepository.existsByEmail(userDto.getEmail())) {
            throw new Exception("Email already exists");
        }

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

        // Create and save new AdminAccount
        AdminAccount adminAccount = new AdminAccount();
        // Map fields from AccountDTO to AdminAccount appropriately
        // ...
        adminAccount.setPassword(encryptedPassword);
        // ...
        adminAccountRepository.save(adminAccount);
    }

   
    public AdminAccount login(LoginDto loginDto) throws Exception {
        // Find the admin account with the given email
        AdminAccount adminAccount = adminAccountRepository.findByEmail(loginDto.getEmail());

        if (adminAccount == null) {
            throw new Exception("Email not found");
        }

        // Check if password matches
        if (!passwordEncoder.matches(loginDto.getPassword(), adminAccount.getPassword())) {
            throw new Exception("Incorrect password");
        }

        return adminAccount;
    }

    public void changePassword(Long id, String newPass) throws Exception {
        // Find the admin account
        AdminAccount adminAccount = adminAccountRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        // Check if current password matches
        if (!passwordEncoder.matches(newPass, adminAccount.getPassword())) {
            throw new Exception("Incorrect current password");
        }

        // Encrypt the new password
        String encryptedPassword = passwordEncoder.encode(newPass);

        // Update the password
        adminAccount.setPassword(encryptedPassword);
        adminAccountRepository.save(adminAccount);
    }

    
    public void updateInfo(AdminAccount currentUser, AdminAccount updateInfoDto) throws Exception {
        // Map fields from updateInfoDto to currentUser appropriately
        // ...
    	currentUser.setId(updateInfoDto.getId());
    	currentUser.setFullName(updateInfoDto.getFullName());
    	currentUser.setPhoneNumber(updateInfoDto.getPhoneNumber());
    	currentUser.setEmail(updateInfoDto.getEmail());
//    	currentUser.set(updateInfoDto.getFullName());
        adminAccountRepository.save(currentUser);
    }

    
   



}
