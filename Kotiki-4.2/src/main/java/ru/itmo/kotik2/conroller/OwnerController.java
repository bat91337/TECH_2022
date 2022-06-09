package ru.itmo.kotik2.conroller;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.Service.OwnerServiceImpl;
import ru.itmo.kotik2.dto.*;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.tools.KafkaTemplateTool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/owner")
public class OwnerController {
    @Autowired
    private OwnerServiceImpl ownerService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private KafkaTemplateTool kafkaTemplateTool;

    @PostMapping("/create")
    public ResponseEntity<OwnerDto> CreateOwner(@RequestBody OwnerDto owner) {
        kafkaTemplateTool.kafkaOwnerTemplate.send("createOwner", owner);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/searchById")
    public ResponseEntity<OwnerDto> SearchOwner(@RequestParam(value = "id") Long id) {
        OwnerDto owner = new OwnerDto();
        owner.setId(id);
        kafkaTemplateTool.kafkaOwnerTemplate.send("searchOwnerById", owner);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(ownerService.getOwnerDto());
    }

    @PostMapping("/update")
    public ResponseEntity<OwnerDto> Update(@RequestBody OwnerDto owner) {
        kafkaTemplateTool.kafkaOwnerTemplate.send("updateOwner", owner);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<OwnerDto> Delete(@RequestParam(value = "id") Long id) {
        OwnerDto owner = new OwnerDto();
        owner.setId(id);
        kafkaTemplateTool.kafkaOwnerTemplate.send("deleteOwner", owner);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addUser")
    public ResponseEntity<OwnerDto> addUser(@RequestParam(value = "idowner") Long owner,
                                            @RequestParam(value = "iduser")Long user) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner);
        UserDto userDto =new UserDto();
        userDto.setId(user);
        OwnerUserDto ownerUserDto = new OwnerUserDto(userDto, ownerDto);
        kafkaTemplateTool.kafkaOwnerUserKafkaTemplate.send("addOwnerToUser", ownerUserDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addKotiki")
    public ResponseEntity<OwnerDto> AddKotiki(@RequestParam(value = "idowner") Long owner,
                                              @RequestParam(value = "idkotik")Long kotik) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner);
        KotikDto kotikDto =new KotikDto();
        kotikDto.setId(kotik);
        OwnerKotikDto ownerKotikDto =new OwnerKotikDto(ownerDto, kotikDto);
        kafkaTemplateTool.kafkaOwnerKotikTemplate.send("addKotikToUser", ownerKotikDto);
        return ResponseEntity.ok().build();
    }
    @SneakyThrows
    @GetMapping("/getByDate")
    public ResponseEntity<Collection<OwnerDto>>GetByDate(@RequestParam (value = "date")String date) {
        OwnerDto ownerDto =new OwnerDto();
        ownerDto.setDate(date);
        kafkaTemplateTool.kafkaOwnerTemplate.send("getOwnerByDate", ownerDto);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(ownerService.getOwnersDto());
    }

    @SneakyThrows
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<OwnerDto>> GetAll() {
        List<OwnerDto> ownersDto = new ArrayList<>();
        kafkaTemplateTool.kafkaOwnersTemplate.send("getAllOwners", ownersDto);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(ownerService.getOwnersDto());
    }
    public Owner toEntity(OwnerDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Owner.class);
    }

    public OwnerDto toDto(Owner entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OwnerDto.class);
    }
}
