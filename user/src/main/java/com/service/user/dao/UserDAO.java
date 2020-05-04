package com.service.user.dao;
import java.util.List;

import com.service.user.model.Response;
import com.service.user.model.User;

public interface UserDAO {
	public List<User> getAllUser() throws Exception;
	public User getUserLoginByLoginName(String loginName);
	public String addUser(User user) throws Exception;
	public User getUserById(Integer user_id) throws Exception;
	public User getUserByName(String user_name) throws Exception;

	
}