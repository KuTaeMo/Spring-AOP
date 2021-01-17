package com.cos.person.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;

@RestController
public class UserController {

	private UserRepository userRepository;
	
	//DI = 의존성 주입
	public UserController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	// http://localhost:8080/user
	@GetMapping("/user")
	public CommonDto<List<User>> findAll() {
		System.out.println("findAll()");
		return new CommonDto<>(HttpStatus.OK.value(),userRepository.findAll());	//MessageConverter (javaObject -> Json String)
	}
	
	// http://localhost:8080/user/1
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById() : id : "+id);
		return new CommonDto<>(HttpStatus.OK.value(),userRepository.findById(id));
	}
	
	@PostMapping("/user")
	// x-www-form-urlencoded -> requset.getParameter()
	// text/plain -> @RequestBody 어노테이션
	// application/json -> @RequestBody 어노테이션 + 오브젝트로 받기
	public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {
		
		System.out.println("bindingResult = "+bindingResult.getErrorCount()); //에러 개수 츨력
		
		
		
		System.out.println("save()");
		System.out.println("User : "+dto);
		userRepository.save(dto);
		
		return new CommonDto<>(HttpStatus.OK.value(),"ok");
//		System.out.println("data : "+data);
//		System.out.println("username :"+username);
//		System.out.println("password :"+password);
//		System.out.println("phone :"+phone);
	}
	@DeleteMapping("/user/{id}")
	public CommonDto<String> delete(@PathVariable int id){
		System.out.println("delete()");
		userRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value(),null);
	}
	@PutMapping("/user/{id}")
	public CommonDto<String> update(@PathVariable int id,@RequestBody UpdateReqDto dto) {
		System.out.println("update()");
		userRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value(),null);
	}
}
