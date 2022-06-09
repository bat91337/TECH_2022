package ru.itmo.kotiki.dto;

public class OwnerUserDto {
    private UserDto userDto;
    private OwnerDto ownerDto;

    public OwnerUserDto(UserDto userDto, OwnerDto ownerDto) {
        this.userDto = userDto;
        this.ownerDto = ownerDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public OwnerDto getOwnerDto() {
        return ownerDto;
    }

    public void setOwnerDto(OwnerDto ownerDto) {
        this.ownerDto = ownerDto;
    }
}
