package ru.itmo.kotik2.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.service.UserService;
import ru.itmo.kotik2.entitites.Users;

import java.util.Collection;

@RestController
@RequestMapping
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String Hello() {
        return "hello";
    }
    @PostMapping("/create")
    public ResponseEntity<Users> create(@RequestBody Users user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/getAll")
    public ResponseEntity<Collection<Users>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/admin/delete")
    public ResponseEntity<Users> delete(@RequestParam (value = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/getById")
    public ResponseEntity<Users> getById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
}
