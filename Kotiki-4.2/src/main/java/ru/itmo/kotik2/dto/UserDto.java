package ru.itmo.kotik2.dto;

import ru.itmo.kotik2.entitites.Role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
