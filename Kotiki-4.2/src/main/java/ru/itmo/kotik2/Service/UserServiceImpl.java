package ru.itmo.kotik2.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.kotik2.Service.UserService;
import ru.itmo.kotik2.dto.UserDto;
import ru.itmo.kotik2.entitites.Users;
import ru.itmo.kotik2.repository.OwnerRepository;
import ru.itmo.kotik2.repository.UserRepository;
import ru.itmo.kotik2.tools.KafkaTemplateTool;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDto userDto;
    private List<UserDto> usersDto;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplateTool kafkaTemplate;
    public UserServiceImpl() {
    }

    @Override
    @SneakyThrows
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = new UserDto();
//        user.setName(username);
//        System.out.println("ya uebok");
        kafkaTemplate.KafkaStringTemplate.send("loadUserByUsername", username);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        user = userDto;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user == null){
            throw new UsernameNotFoundException("user not found:"+username);
        }
        UserDetails userDetails = User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
        return userDetails;
    }

    @Override
    @KafkaListener(topics = "sendUser")
    public void getUser(UserDto userDto) {
        this.userDto=userDto;
    }

    @Override
    @KafkaListener(topics = "sendUsers")
    public void getUsers(List<UserDto> usersDto) {
        this.usersDto = usersDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<UserDto> getUsersDto() {
        return usersDto;
    }

    public void setUsersDto(List<UserDto> usersDto) {
        this.usersDto = usersDto;
    }
}
