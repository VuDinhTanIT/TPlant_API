package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.AdminAccount;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {

	AdminAccount findByEmail(String email);

	boolean existsByEmail(String email);

}
	