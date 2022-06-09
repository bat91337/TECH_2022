package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.entitites.Role;

public class UserDto {
    private Long id;
    private String name;
    private Role role;
    private String password;


    public UserDto(Long id, String name, Role role, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public UserDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
