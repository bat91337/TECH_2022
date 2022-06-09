package ru.itmo.kotiki.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.UserDto;
import ru.itmo.kotiki.entitites.Users;
import ru.itmo.kotiki.repository.UserRepository;
import ru.itmo.kotiki.tools.KafkaTemplateTool;

import java.util.Collection;
import java.util.List;
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private KafkaTemplateTool kafkaTemplateTool;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Users create(Users user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String oldPass = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPass));
        return userRepository.save(user);
    }

    @Override
    @KafkaListener(topics = "getAllUsers")
    public Collection<Users> getAll(List<UserDto> userDtos) {
        List<Users> users = userRepository.findAll();
        for(Users user : users) {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDtos.add(userDto);
        }
        kafkaTemplateTool.kafkaUsersTemplate.send("sendUsers", userDtos);
        return userRepository.findAll();
    }

    @Override
    public void delete(Long key) {
        Users user = getById(key);
        userRepository.delete(user);
    }

    @Override
    public Users getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    @KafkaListener(topics = "loadUserByUsername")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUserByName(username);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        kafkaTemplateTool.kafkaUserTemplate.send("sendUser", userDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user == null){
            throw new UsernameNotFoundException("user not found:"+username);
        }
        UserDetails userDetails = User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        return userDetails;
    }
    @Override
    @KafkaListener(topics = "getUserByUsername")
    public Users getUsersByUsername(UserDto user) {
        Users users = userRepository.findUserByName(user.getName());
        UserDto userDto = modelMapper.map(users, UserDto.class);
        kafkaTemplateTool.kafkaUserTemplate.send("sendUser", userDto);
        return userRepository.findUserByName(user.getName());
    }
}
