package com.estockmarket.authentication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.estockmarket.authentication.model.Logout;

public interface LogoutRepository extends JpaRepository<Logout, String>{

	List<Logout> findByToken(String token);

	@Transactional
	@Modifying
	@Query("delete from Logout where date < :olderDate" )
	void deleteOlder(LocalDateTime olderDate);

}
