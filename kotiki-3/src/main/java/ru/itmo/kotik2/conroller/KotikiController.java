package ru.itmo.kotik2.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.service.KotikiService;
import ru.itmo.kotik2.service.OwnerService;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.wrappers.KotikiWrapper;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/kotiki")
public class KotikiController {
    @Autowired
    private KotikiService kotikiService;
    @Autowired
    private OwnerService ownerService;
    // TODO: 30.04.2022 Make Autowried by Constructor

    @PostMapping("/create")
    public ResponseEntity<Kotiki> SaveKotiki(@RequestBody Kotiki kotik) {
        return ResponseEntity.ok().body(kotikiService.create(kotik));
    }

    @GetMapping("/SearchById")
    public ResponseEntity<Kotiki> SearchByID(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(kotikiService.readById(id), HttpStatus.OK);
//        return ResponseEntity.ok().body(kotikiService.readById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Kotiki> UpdateKotiki(@RequestBody Kotiki kotik) {
        return ResponseEntity.ok().body(kotikiService.update(kotik));
    }

//    @GetMapping("/getAll")
//    public ResponseEntity<Collection<Kotiki>> GetAll() {
//        return new ResponseEntity<>(kotikiService.GetAllAdmin(), HttpStatus.OK);
//    }

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

    @GetMapping("/getByOwner")
    public ResponseEntity<Collection<Kotiki>> GetByOwner(@RequestParam (value = "owner") Owner owner) {
        return ResponseEntity.ok().body(kotikiService.GetKotikByOwner(owner));
    }

    @GetMapping("/getAllByOwner")
    public ResponseEntity<Collection<Kotiki>> GetAllByOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Owner owner1 = ownerService.getOwnerByUsername(authentication.getName());
        return ResponseEntity.ok().body(kotikiService.GetKotikByOwner(owner1));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<KotikiWrapper>> GetAll2() {
        return new ResponseEntity<>(
                kotikiService.GetAllAdmin().stream().
                        map((kotiki -> kotiki.kotikiWrapper())).
                        collect(Collectors.toList()), HttpStatus.OK);

    }
}
