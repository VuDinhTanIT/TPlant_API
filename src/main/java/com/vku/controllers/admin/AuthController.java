package com.vku.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vku.dtos.AccountDTO;
import com.vku.dtos.LoginDto;
import com.vku.models.AdminAccount;
import com.vku.services.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AuthController {

    @Autowired
    private AdminService authService;

    @PostMapping("/createAccount")
    public ResponseEntity<?> register(@RequestBody AccountDTO userDto) {
        try {
            authService.register(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest) throws Exception {
        AdminAccount adminAccount = authService.login(loginDto);

        if(adminAccount != null) {
        	
        	// Lưu trữ thông tin người dùng vào session
        	HttpSession session = httpServletRequest.getSession();
        	
        	session.setAttribute("currentManager", adminAccount);
        	
        	return ResponseEntity.ok().body(adminAccount);
        }else {
            return ResponseEntity.badRequest().body(null);

        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest httpServletRequest, String newPass) {
        try {
            // Truy cập thông tin người dùng từ session
            HttpSession session = httpServletRequest.getSession();
            AdminAccount currentManager = (AdminAccount) session.getAttribute("currentManager");

            authService.changePassword(currentManager.getId(), newPass);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-info")
    public ResponseEntity<?> updateInfo(HttpServletRequest httpServletRequest, @RequestBody AdminAccount updateInfoDto) {
        try {
        	// Truy cập thông tin người dùng từ session
            HttpSession session = httpServletRequest.getSession();
            AdminAccount currentManager = (AdminAccount) session.getAttribute("currentManager");
            authService.updateInfo(currentManager, updateInfoDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword( String email) {
//        try {
//            authService.forgotPassword(email);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
