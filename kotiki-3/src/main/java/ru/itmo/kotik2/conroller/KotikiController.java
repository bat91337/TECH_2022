package ru.itmo.kotik2.conroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.dto.KotikDto;
import ru.itmo.kotik2.service.KotikiService;
import ru.itmo.kotik2.service.OwnerService;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kotiki")
public class KotikiController {
    @Autowired
    private KotikiService kotikiService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<Kotiki> SaveKotiki(@RequestBody Kotiki kotik) {
        return ResponseEntity.ok().body(kotikiService.create(kotik));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/SearchById")
    public ResponseEntity<KotikDto> SearchByID(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(kotikiService.readById(id), HttpStatus.OK);
//        return ResponseEntity.ok().body(kotikiService.readById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Kotiki> UpdateKotiki(@RequestBody Kotiki kotik) {
        return ResponseEntity.ok().body(kotikiService.update(kotik));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<Kotiki> DeleteKotiki(@RequestParam(value = "id") Long id) {
        kotikiService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addFriend")
    public ResponseEntity<Kotiki> AddFriend(@RequestBody Kotiki kotik, Long id) {
        return ResponseEntity.ok().body(kotikiService.addFriend(kotik, id));
    }

    @GetMapping("/getByColor")
    public ResponseEntity<Collection<Kotiki>> GetByColor(@RequestParam (value = "color") Colors color) {
        return ResponseEntity.ok().body(kotikiService.GetKotikByColor(color));
    }

    @GetMapping("/getByBreed")
    public ResponseEntity<Collection<Kotiki>> GetByBreed(@RequestParam (value = "breed")String breed) {
        return ResponseEntity.ok().body(kotikiService.GetKotikByBreed(breed));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getByOwner")
    public ResponseEntity<Collection<KotikDto>> GetByOwner(@RequestParam (value = "owner") Owner owner) {
        return new ResponseEntity<>(kotikiService.GetKotikByOwner(owner).stream()
                .map(this::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getAllByOwner")
    public ResponseEntity<Collection<KotikDto>> GetAllByOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Owner owner1 = ownerService.getOwnerByUsername(authentication.getName());
        return new ResponseEntity<>(kotikiService.GetKotikByOwner(owner1).stream()
                .map(this::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<KotikDto>> GetAll2() {
        return new ResponseEntity<>(
                kotikiService.GetAllAdmin().stream().
                        map(this::toDto).
                        collect(Collectors.toList()), HttpStatus.OK);
    }

    public Kotiki toEntity(KotikDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Kotiki.class);
    }

    public KotikDto toDto(Kotiki entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, KotikDto.class);
    }
}
