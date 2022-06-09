package ru.itmo.kotik2.conroller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.Service.UserServiceImpl;
import ru.itmo.kotik2.dto.UserDto;
import ru.itmo.kotik2.tools.KafkaTemplateTool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private KafkaTemplateTool kafkaTemplateTool;
    @GetMapping("/")
    @SneakyThrows
    public String Hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = new UserDto();
        userDto.setName(authentication.getName());
        kafkaTemplateTool.kafkaUserTemplate.send("hello", userDto);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return "hello " + userService.getUserDto().getName();
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        kafkaTemplateTool.kafkaUserTemplate.send("createUser", user);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/getAll")
    @SneakyThrows
    public ResponseEntity<Collection<UserDto>> getAll() {
        List<UserDto> usersDto = new ArrayList<>();
        kafkaTemplateTool.kafkaUsersTemplate.send("getAllUsers", usersDto);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(userService.getUsersDto());
    }

    @PostMapping("/admin/delete")
    public ResponseEntity<UserDto> delete(@RequestParam (value = "id") Long id) {
        UserDto userDto =new UserDto();
        userDto.setId(id);
        kafkaTemplateTool.kafkaUserTemplate.send("deleteUser", userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/getById")
    @SneakyThrows
    public ResponseEntity<UserDto> getById(@RequestParam(value = "id") Long id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        kafkaTemplateTool.kafkaUserTemplate.send("getUserById", userDto);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(userService.getUserDto());
    }
}
