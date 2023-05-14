package kg.marsbekov.springdemo.controller;


import kg.marsbekov.springdemo.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    @Qualifier("storageService")
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStrings() {
        try {
            return new ResponseEntity<>(storageService.getList(), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addToList(@RequestParam String str) {
        storageService.addToList(str);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(value = "/delete/{str}")
    public ResponseEntity<Boolean> deleteFromList(@PathVariable String str) {
        return new ResponseEntity<>(storageService.deleteStringFromList(str), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{str}")
    public ResponseEntity<Boolean> isExist(@PathVariable String str) {
        return new ResponseEntity<>(storageService.isExistInList(str), HttpStatus.OK);
    }

    @GetMapping(value = "/cats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCats(){
        return new ResponseEntity<>(storageService.getCats(), HttpStatus.OK);
    }


}
