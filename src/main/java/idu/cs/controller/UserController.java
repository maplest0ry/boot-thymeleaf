package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Controller  //Component, @service, @Repository
// Spring Framework에게 이 클래스로부터 작성된 객체는 Controller 역할을 함을 알려줌
// Spring 이 이 클래스로 부터 Bean 객체를 생성해서 등록할 수 있음
public class UserController {
	@Autowired UserRepository userRepo; // Dependency Injection
	@GetMapping("/")
	public String home(Model model) { 
		return "index";
	}
	@GetMapping("/user-login-form")
	public String getLoginForm(Model model) {
		return "login";
	}
	@PostMapping("/login")
	public String loginUser(@Valid User user, HttpSession session) {
		System.out.println("login process : ");
		User sessionUser = userRepo.findByUserId(user.getUserId());
		if(sessionUser == null) {
			System.out.println("id error : ");
			return "redirect:/user-login-form";
		}
		if(!sessionUser.getUserPw().equals(user.getUserPw())) {
			System.out.println("pw error : ");
			return "redirect:/user-login-form";
		}
		session.setAttribute("user", sessionUser);
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logoutUser (HttpSession session) {
		session.removeAttribute("user");
		//session.invalidate();  세션에 있는 데이터를 다 날려버린다.
		return "redirect:/";
	}
	@GetMapping("/user-register-form")
	public String getRegForm(Model model) {
		return "register";
	}
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}
	@PostMapping("/users")
	public String createUser(@Valid User user, Model model) {	//Valid 하면 유효성 검증이 필요한 객체일때. 꼼꼼하게 해줘	
		if(userRepo.save(user) != null)
			System.out.println("Database 등록 성공");
		else
			System.out.println("Database 등록 실패");
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
	@GetMapping("/users/{id}") // 업데이트 폼으로 이동
	public String getUserById(@PathVariable(value = "id") Long id, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(id).get();//.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		model.addAttribute("user", user);
		return "update";
		//return ResponseEntity.ok().body(user);
	}
	@GetMapping("/users/fn")
	public String getUserByName(@Param(value="name") String name, Model model)
			throws ResourceNotFoundException {
		List<User> users = userRepo.findByName(name);//.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		model.addAttribute("users", users);
		return "userlist";
		//return ResponseEntity.ok().body(user);
	}
	@PutMapping("/users/{id}") // 회원정보 수정, @PatchMapping
	public String updateUser(@PathVariable(value = "id") Long id, 
			@Valid User userDetails, Model model) {		
		User user = userRepo.findById(id).get(); 
		// user는 DB로 부터 읽어온 객체
		//user.setUserId(userDetails.getUserId());
		user.setUserPw(userDetails.getUserPw());
		user.setName(userDetails.getName());
		user.setCompany(userDetails.getCompany());
		userRepo.save(user);
		return "redirect:/users";
	}
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(value = "id") Long userId, Model model) {		
		User user = userRepo.findById(userId).get();
		userRepo.delete(user);
		model.addAttribute("name", user.getName());
		return "user-deleted";
	}
}