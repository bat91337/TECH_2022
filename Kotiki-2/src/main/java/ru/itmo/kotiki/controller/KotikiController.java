package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.service.KotikiService;

import java.util.Collection;

@RestController
@RequestMapping("/kotiki")
public class KotikiController {

    @Autowired
    private KotikiService kotikiService;

    @PostMapping("/create")
    public ResponseEntity<Kotiki>SaveKotiki(@RequestBody Kotiki kotik)
    {
        return ResponseEntity.ok().body(kotikiService.create(kotik));
    }
    @GetMapping("/SearchById")
    public ResponseEntity<Kotiki> SearchByID(@RequestParam(value = "id") Long id)
    {
        return new ResponseEntity<>(kotikiService.readById(id), HttpStatus.OK);
//        return ResponseEntity.ok().body(kotikiService.readById(id));
    }
    @PostMapping("/update")
    public ResponseEntity<Kotiki> UpdateKotiki(@RequestBody Kotiki kotik)
    {
        return ResponseEntity.ok().body(kotikiService.update(kotik));
    }
    @PostMapping("/delete")
    public ResponseEntity<Kotiki> DeleteKotiki(@RequestBody Kotiki kotik)
    {
        kotikiService.delete(kotik);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addFriend")
    public ResponseEntity<Kotiki> AddFriend(@RequestBody Kotiki kotik, Long id)
    {
        return ResponseEntity.ok().body(kotikiService.addFriend(kotik, id));
    }
    @GetMapping("/getByColor")
    public ResponseEntity<Collection<Kotiki>> GetByColor(@RequestParam (value = "color")Colors color)
    {
        return ResponseEntity.ok().body(kotikiService.GetKotikByColor(color));
    }
    @GetMapping("/getByBreed")
    public ResponseEntity<Collection<Kotiki>> GetByBreed(@RequestParam (value = "breed")String breed)
    {
        return ResponseEntity.ok().body(kotikiService.GetKotikByBreed(breed));
    }
    @GetMapping("/getByOwner")
    public ResponseEntity<Collection<Kotiki>> GetByOwner(@RequestParam (value = "owner")Owner owner)
    {
        return ResponseEntity.ok().body(kotikiService.GetKotikByOwner(owner));
    }





}
