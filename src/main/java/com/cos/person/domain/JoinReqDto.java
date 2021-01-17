package com.cos.person.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JoinReqDto {
	
	@NotNull(message = "유저네임 키값이 없습니다.")
	@NotBlank(message = "유저네임이 없습니다.")
	@Size(message = "유저네임이 너무 큽니다.")
	private String username;
	
	@NotBlank(message = "패스워드가 없습니다.")
	private String password;
	private String phone;
}
