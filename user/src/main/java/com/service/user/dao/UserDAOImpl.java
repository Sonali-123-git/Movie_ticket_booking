package com.service.user.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.user.entity.UserEntity;
import com.service.user.model.Response;
import com.service.user.model.User;


@Repository
public class UserDAOImpl implements UserDAO{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<User> getAllUser() throws Exception {
		
		Query query = entityManager.createQuery("select user FROM UserEntity user");
		List<User> users = null;			
		
		List<UserEntity> userEntities = query.getResultList();		
		users = new ArrayList<User>();

		for (UserEntity userEntity  : userEntities) {
			User user = new User();
			user.setUser_id(userEntity.getUser_id());
			user.setUser_name(userEntity.getUser_name());
			//user.setPassword(userEntity.getPassword());
			user.setRole(userEntity.getRole());
			user.setEmail(userEntity.getEmail());
			user.setPhone_no(userEntity.getPhone_no());
			users.add(user);
		}
		return users;
	}
	
	@Override
	public User getUserLoginByLoginName(String loginName) {
		User login = null;
        UserEntity userEntity=entityManager.createQuery("SELECT u from UserEntity u WHERE u.user_name= :user_name",UserEntity.class).
        		setParameter("user_name",loginName).getSingleResult();
        
        if(userEntity !=null) {
            
            login=new User();
            login.setUser_name(userEntity.getUser_name());            
            login.setUser_id(userEntity.getUser_id());
            login.setPassword(userEntity.getPassword());
            login.setEmail(userEntity.getEmail());
            login.setPhone_no(userEntity.getPhone_no());
            login.setRole(userEntity.getRole());            
        	}
        return login;
       
	}
	
	@Override
    public String addUser(User user){
       
        String message = "";
        UserEntity entity = new UserEntity();       
        entity.setPassword(user.getPassword());
        entity.setUser_name(user.getUser_name());
        entity.setRole("customer");
        entity.setPhone_no(user.getPhone_no());
        entity.setEmail(user.getEmail());
        entityManager.persist(entity);   
        message = "Customer registered successfully";       
        return message;
    }
	
	@Override
	public User getUserById(Integer user_id) throws Exception{
		User searched_user=null;
		UserEntity userEntity=entityManager.find(UserEntity.class,user_id);
        System.out.println("find");

        if(userEntity !=null) {
            System.out.println("if not null");

            searched_user=new User();
            searched_user.setUser_name(userEntity.getUser_name()); 
            searched_user.setUser_id(userEntity.getUser_id());
            searched_user.setPassword(userEntity.getPassword());
            searched_user.setEmail(userEntity.getEmail());
            searched_user.setRole(userEntity.getRole());
        }
        System.out.println("dao");

        return searched_user;
	}
	public User getUserByName(String user_name) throws Exception{
		User searched_user=null;
		UserEntity userEntity=entityManager.createQuery("SELECT u from UserEntity u WHERE u.user_name= :user_name",UserEntity.class).
        		setParameter("user_name",user_name).getSingleResult();
        if(userEntity !=null) {            
            searched_user=new User();
            searched_user.setUser_name(userEntity.getUser_name()); 
            searched_user.setUser_id(userEntity.getUser_id());
            searched_user.setPassword(userEntity.getPassword());
            searched_user.setEmail(userEntity.getEmail());
            searched_user.setRole(userEntity.getRole());
            searched_user.setPhone_no(userEntity.getPhone_no());

        }
        return searched_user;
	}
	
	
	
}
