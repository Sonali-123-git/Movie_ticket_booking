package com.service.user.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.service.user.model.User;
import com.service.user.model.Response;
import com.service.user.service.UserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserAPI {
	
	@Autowired
	Environment environment;
	@Autowired
	UserServiceImpl serviceImpl;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() throws Exception {
		List<User> users=new ArrayList<User>();
		try {
			users=serviceImpl.getAllUser();
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		} catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}
	}
	//authenticate
	@RequestMapping(value = "authenticate",method=RequestMethod.POST)
    public ResponseEntity<User> authenticateUser(@RequestBody User user) throws Exception{
    try{
                    
                    User user1= serviceImpl.authentication(user);
                    if(user1!=null){
                                    ResponseEntity<User> response= new ResponseEntity<User>(user1, HttpStatus.OK);
                                     //return "Valid credentials";
                                    return response;
                                    }
                    else{
                                    ResponseEntity<User> response= new ResponseEntity<User>(user1, HttpStatus.NOT_FOUND);
                                    //return "Invalid credentials";
                                    return response;
                    }
                    
    }
    catch(Exception e){
                                    System.out.println(e);
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
                    }              
}

	
	
	// Registering new user
	@RequestMapping(value="/",method=RequestMethod.POST)
    public ResponseEntity<Response> addUser(@RequestBody User user) throws Exception{
        String message = "User added successfully";
      serviceImpl.addUser(user);
      Response resp = new Response();
		resp.setResp(message);
		ResponseEntity<Response> response = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
		return response;
	}	
	//find user by id
//	@RequestMapping(value="/{user_id}",method=RequestMethod.GET)
//		public ResponseEntity <User> getUserById(@PathVariable Integer user_id) throws Exception{
//			try {
//	            User user=null;
//	            user=serviceImpl.getUserById(user_id);
//	            ResponseEntity<User> response=new ResponseEntity<User>(user, HttpStatus.OK);
//	            return response;
//				}
//			catch(Exception e) {
//	            System.out.println(e);
//	            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
//				}
//		}
	//find user by name
	@RequestMapping(value="/{user_name}",method=RequestMethod.GET)
	public ResponseEntity <User> getUserByName(@PathVariable String user_name) throws Exception{
		try {
            User user=null;
            user=serviceImpl.getUserByName(user_name);
            ResponseEntity<User> response=new ResponseEntity<User>(user, HttpStatus.OK);
            System.out.println("API");
            return response;
			}
		catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
			}
	}
          
    

}