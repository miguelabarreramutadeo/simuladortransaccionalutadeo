package com.utadeo.miguelabarreram.simuladortransaccional.repository;

import org.springframework.data.repository.CrudRepository;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;

public interface IUserRepository extends CrudRepository<User, Integer>{

	public Iterable<User> findByUserIdGreaterThan(int userId);
	public User findByUserName(String userName);
	public User findByEmail(String email);
}
