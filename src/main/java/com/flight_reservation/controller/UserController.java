package com.flight_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight_reservation.entity.User;
import com.flight_reservation.repository.UserRepository;

@Controller
public class UserController {
	//This is used to save the user into databases and use its all method to save
	@Autowired
	private UserRepository  userRepo;
	
	
	//http://localhost:8080/flights/
	@RequestMapping("/showLoginPage")
	public String showLoginPage() {
		return "login/login";
	}
	
	//http://localhost:8080/flights/showReg
	@RequestMapping("/showReg")
	public String showReg() {
		return "login/showReg";	
	}
	//This below line will save the data into databases-
	//it will redirect from saveReg post method from showReg.jsp file
	@RequestMapping("/saveReg")
	public String saveReg(@ModelAttribute("user") User user) {
		userRepo.save(user);
		return "login/login";
	}
	//http://localhost:8080/flights
	//username=c@gmail.com & pass-testing
	@RequestMapping("/verifyLogin")
	public String verifyLogin(@RequestParam("emailId") String emailId,@RequestParam("password") String password,ModelMap modelMap) {
		User user = userRepo.findByEmail(emailId);
		//User user = userRepo.getByEmail(String emailId);
		//User user = userRepo.readByEmail(String emailId);
		// User user = userRepo.ByEmail(String   emailId); //can't create
		
//		System.out.println(user.getEmail());
//		System.out.println(user.getPassword());
		
		if(user!=null) {
			if(user.getEmail().equals(emailId) && user.getPassword().equals(password)) {
				return "findFlights";
			}
			else {
				modelMap.addAttribute("error","invalid username/passsword");
				return "login/login";
			}
		}
		else {
			modelMap.addAttribute("error","invalid username/passsword");
			return "login/login";
		}
		
		
		
	}

}
