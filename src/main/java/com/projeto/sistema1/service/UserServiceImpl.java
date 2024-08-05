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
    public User save(UserDto userDto) {
        User user;

        if (userDto.getId() != null) {
            // Atualiza um usuário existente
            user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            user.setFullname(userDto.getFullname());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());

            user.setFuncionario(userDto.getFuncionario());
            
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
        } else {
            // Cria um novo usuário
            user = new User();
            user.setFullname(userDto.getFullname());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            user.setFuncionario(userDto.getFuncionario());
            
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }


}
