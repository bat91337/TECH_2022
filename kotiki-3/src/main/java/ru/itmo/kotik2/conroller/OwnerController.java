package ru.itmo.kotik2.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.service.KotikiService;
import ru.itmo.kotik2.service.OwnerService;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;

@RestController
@RequestMapping("/user/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private KotikiService kotikiService;

    @PostMapping("/create")
    public ResponseEntity<Owner> CreateOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.create(owner));
    }

    @GetMapping("/searchById")
    public ResponseEntity<Owner> SearchOwner(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok().body(ownerService.readById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Owner>Update(@RequestBody Owner owner) {
        return ResponseEntity.ok().body(ownerService.update(owner));
    }
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
    @GetMapping("/getAll")
    public ResponseEntity<Collection<Owner>> GetAll() {
        return new ResponseEntity<>(ownerService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/getByOwner")
    public ResponseEntity<Collection<Kotiki>> GetByOwner(@RequestParam (value = "owner") Owner owner) {
        return ResponseEntity.ok().body(kotikiService.GetKotikByOwner(owner));
    }
}
