package com.sqrfactor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqrfactor.bean.Login;

/**
 * 
 * @author Angad Gill
 *
 */
@RestController
public class LoginController {

	public LoginController(){
		
	}
	
	@RequestMapping(value = "/login/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Login getLoginById(@PathVariable int id){
		List<Login> listOfProfiles = createLoginList();
		for (Login login : listOfProfiles){
			if(login.getId() == id){
				return login;
			}
		}
		return null;
	}
	
	// Utiliy method to create login list.  
	 public List<Login> createLoginList()  
	 {  
		 Login angadLogin = new Login(1, "angad.cec@gmail.com", "Angad");
		 Login agnimLogin = new Login(2, "agnimgupta11@gmail.com", "Agnim");
		 Login akshayLogin = new Login(3, "aksh.loomba@gmail.com", "Akshay");
		 Login venkatLogin = new Login(4, "venkatsudhir92@hotmail.com", "Venkat");
		 
		  List<Login> listOflogins = new ArrayList<Login>();
		  listOflogins.add(angadLogin);
		  listOflogins.add(agnimLogin);
		  listOflogins.add(akshayLogin);
		  listOflogins.add(venkatLogin);
		  
		 return listOflogins;  
	 }
	
}
