package com.projeto.sistema1.service;


import com.projeto.sistema1.dto.UserDto;
import com.projeto.sistema1.modelos.User;



public interface UserService {
	User save(UserDto userDto);

}
