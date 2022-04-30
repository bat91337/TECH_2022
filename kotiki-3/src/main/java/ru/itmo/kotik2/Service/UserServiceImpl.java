package ru.itmo.kotik2.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.kotik2.entitites.Users;
import ru.itmo.kotik2.repository.OwnerRepository;
import ru.itmo.kotik2.repository.UserRepository;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    public UserServiceImpl(UserRepository userRepository, OwnerRepository ownerRepository) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }


    @Override
    public Users create(Users user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String oldPass = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPass));
        return userRepository.save(user);
    }

    @Override
    public Collection<Users> getAll() {
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUserByName(username);
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
    public Users getUsersByUsername(String user) {
        return userRepository.findUserByName(user);
    }
}
