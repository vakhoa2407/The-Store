package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.User;

public interface UserDAO extends JpaRepository<User, String>{
	@Query("Select o From User o Where o.id LIKE ?1 Or o.fullname LIKE ?2")
	List<User> findByIdOrName(String id, String fullname);

}
