package com.projeto.sistema1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.sistema1.dto.UserDto;
import com.projeto.sistema1.modelos.User;
import com.projeto.sistema1.repositorios.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	 	@Autowired
		private PasswordEncoder passwordEncoder;
		@Autowired
		private UserRepository userRepository;



		@Override
		public User save(UserDto userDto) {//passwordEncoder.encode( userDto.getPassword())
			User user= new User(userDto.getEmail(),passwordEncoder.encode( userDto.getPassword()), userDto.getRole(), userDto.getFullname());
			return userRepository.save(user);
		}

}
