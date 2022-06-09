package ru.itmo.kotik2.conroller;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotik2.Service.KotikiServiceImpl;
import ru.itmo.kotik2.dto.KotikDto;
import ru.itmo.kotik2.dto.OwnerDto;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.tools.KafkaTemplateTool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kotiki")
public class KotikiController {
    @Autowired
    private KotikiServiceImpl kotikiService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private KafkaTemplateTool kafkaTemplateTool;

    @PostMapping("/create")
    public ResponseEntity<KotikDto> SaveKotiki(@RequestBody KotikDto kotik) {
        kafkaTemplateTool.kafkaKotikTemplate.send("createKotik", kotik);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/SearchById")
    @SneakyThrows
    public ResponseEntity<KotikDto> SearchByID(@RequestParam(value = "id") Long id) {
        KotikDto kotik = new KotikDto();
        kotik.setId(id);
        kafkaTemplateTool.kafkaKotikTemplate.send("SearchByIdKotik", kotik);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(kotikiService.getKotikDto());
    }

    @PostMapping("/update")
    public ResponseEntity<KotikDto> UpdateKotiki(@RequestBody KotikDto kotik) {
        kafkaTemplateTool.kafkaKotikTemplate.send("updateKotik", kotik);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<KotikDto> DeleteKotiki(@RequestParam(value = "id") Long id) {
        KotikDto kotik = new KotikDto();
        kotik.setId(id);
        kafkaTemplateTool.kafkaKotikTemplate.send("deleteKotik", kotik);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addFriend")
    public ResponseEntity<KotikDto> AddFriend(@RequestBody KotikDto kotik, Long id) {
        KotikDto kotik1 = new KotikDto();
        kotik.setId(id);
        List<KotikDto> kotiki = new ArrayList<>();
        kotiki.add(kotik);
        kotiki.add(kotik1);
        kafkaTemplateTool.kafkaKotikiTemplate.send("addFriend", kotiki);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/getByColor")
    public ResponseEntity<Collection<KotikDto>> GetByColor(@RequestParam(value = "color") Colors color) {
        KotikDto kotik = new KotikDto();
        kotik.setColor(color);
        kafkaTemplateTool.kafkaKotikTemplate.send("getByColor", kotik);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(kotikiService.getKotiksDto());

    }

    @SneakyThrows
    @GetMapping("/getByBreed")
    public ResponseEntity<Collection<KotikDto>> GetByBreed(@RequestParam(value = "breed") String breed) {
        KotikDto kotik = new KotikDto();
        kotik.setBreed(breed);
        kafkaTemplateTool.kafkaKotikTemplate.send("getByBreed", kotik);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(kotikiService.getKotiksDto());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getByOwner")
    @SneakyThrows
    public ResponseEntity<Collection<KotikDto>> GetByOwner(@RequestParam(value = "owner") OwnerDto owner) {
        kafkaTemplateTool.kafkaOwnerTemplate.send("getByOwner", owner);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
//        List<KotikDto> kotiki = kotikiService.getKotiksDto();
        return ResponseEntity.ok().body(kotikiService.getKotiksDto());
    }

    @GetMapping("/getAllByOwner")
    @SneakyThrows
    public ResponseEntity<Collection<KotikDto>> GetAllByOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OwnerDto owner = new OwnerDto();
        owner.setName(authentication.getName());
        kafkaTemplateTool.kafkaOwnerTemplate.send("getAllByOwner", owner);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(kotikiService.getKotiksDto());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAll")
    @SneakyThrows
    public ResponseEntity<Collection<KotikDto>> GetAll2() {
        List<KotikDto> kotikiDto = new ArrayList<>();
        kafkaTemplateTool.kafkaKotikiTemplate.send("getAllAdmin", kotikiDto);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(kotikiService.getKotiksDto());
    }

    public Kotiki toEntity(KotikDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Kotiki.class);
    }

    public KotikDto toDto(Kotiki entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, KotikDto.class);

    }
}
