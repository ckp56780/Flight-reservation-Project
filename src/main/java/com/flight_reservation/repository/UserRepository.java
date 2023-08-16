package com.flight_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight_reservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User  findByEmail(String emailId);
	//User  getByEmail(String emailId);
	//User readByEmail(String emailId);
	// User ByEmail(String emailId); //can't create

}
