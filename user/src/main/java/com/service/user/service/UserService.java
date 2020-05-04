package com.service.user.service;

import java.util.List;

import com.service.user.model.User;

public interface UserService {
	public List<User> getAllUser() throws Exception;
	public User authentication(User user) throws Exception;
	public String addUser(User user) throws Exception;
	public User getUserById(Integer user_id) throws Exception;
	public User getUserByName(String user_name) throws Exception;
	
}
