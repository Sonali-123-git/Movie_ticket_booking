package com.service.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.service.user.dao.UserDAO;
import com.service.user.model.Response;
import com.service.user.model.User;
import com.service.user.service.UserService;

	@Service(value="UserServiceImpl")
	@Transactional
	public class UserServiceImpl implements UserService {

		@Autowired
		private UserDAO userDao; 

		@Override
		public List<User> getAllUser() throws Exception {
			return userDao.getAllUser();
		}
		
		
		@Override
        public User authentication(User user) throws Exception {
                        User loginuser=null;
                        User user1=userDao.getUserLoginByLoginName(user.getUser_name());                 
                        if(user1.getPassword().equals(user.getPassword())){
                                        loginuser=user1;
                        }                        
                        // TODO Auto-generated method stub
                        return loginuser;
        }

		
		
		@Override
	    public String addUser(User user)throws Exception{			
	           return userDao.addUser(user);
	    }
		
		@Override
		public User getUserById(Integer user_id) throws Exception {
			User searched_user=null;
	        searched_user=userDao.getUserById(user_id);
	        if (searched_user == null) {
	            throw new Exception("Service.USER_NOT_FOUND");
	        }
            System.out.println("service");

	        return searched_user;
		}
		@Override
		public User getUserByName(String user_name) throws Exception {
			User searched_user=null;
	        searched_user=userDao.getUserByName(user_name);
	        if (searched_user == null) {
	            throw new Exception("Service.USER_NOT_FOUND");
	        }

	        return searched_user;
		}
		
		
		
}
