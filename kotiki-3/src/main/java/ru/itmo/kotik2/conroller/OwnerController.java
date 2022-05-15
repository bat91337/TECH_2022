package ru.itmo.kotik2.conroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.dto.OwnerDto;
import ru.itmo.kotik2.service.KotikiService;
import ru.itmo.kotik2.service.OwnerService;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private KotikiService kotikiService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<Owner> CreateOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.create(owner));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/searchById")
    public ResponseEntity<OwnerDto> SearchOwner(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(ownerService.readById(id), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Owner>Update(@RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.update(owner));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<Owner>Delete(@RequestParam(value = "id") Long id) {
        ownerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addUser")
    public ResponseEntity<Owner> addUser(@RequestParam(value = "idowner") Long owner,
                                         @RequestParam(value = "iduser")Long user) {
        return new ResponseEntity<>(ownerService.addUser(owner, user), HttpStatus.OK);
    }

    @PostMapping("/addKotiki")
    public ResponseEntity<Owner>AddKotiki(@RequestParam(value = "idowner") Long owner,
                                          @RequestParam(value = "idkotik")Long kotik) {
        return ResponseEntity.ok().body(ownerService.addKotik(owner, kotik));
    }
    @GetMapping("/getByDate")
    public ResponseEntity<Collection<Owner>>GetByDate(@RequestParam (value = "date")String date) {
        return ResponseEntity.ok().body(ownerService.GetOwnerByDate(date));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<OwnerDto>> GetAll() {
        return new ResponseEntity<>(ownerService.getAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    public Owner toEntity(OwnerDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Owner.class);
    }

    public OwnerDto toDto(Owner entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OwnerDto.class);
    }
}
