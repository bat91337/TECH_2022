package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.service.OwnerService;

import java.util.Collection;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/create")
    public ResponseEntity<Owner>CreateOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.create(owner));
    }

    @GetMapping("/searchById")
    public ResponseEntity<Owner> SearchOwner(Long id) {
        return ResponseEntity.ok().body(ownerService.readById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Owner>Update(@RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.update(owner));
    }
    @PostMapping("/delete")
    public ResponseEntity<Owner>Delete(@RequestBody Owner owner) {
        ownerService.delete(owner);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addKotiki")
    public ResponseEntity<Owner>AddKotiki(Long owner, Long kotik)
    {
        return ResponseEntity.ok().body(ownerService.addKotik(owner, kotik));
    }
    @GetMapping("/getByDate")
    public ResponseEntity<Collection<Owner>>GetByDate(@RequestParam (value = "date")String date)
    {
        return ResponseEntity.ok().body(ownerService.GetOwnerByDate(date));
    }

}
