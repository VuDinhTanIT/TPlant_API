package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	boolean existsByEmail(String email);

	Account findByEmail(String email);

}
	