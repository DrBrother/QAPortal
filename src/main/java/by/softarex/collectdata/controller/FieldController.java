package by.softarex.collectdata.controller;

import by.softarex.collectdata.model.Field;
import by.softarex.collectdata.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class FieldController {

    private FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/fields")
    public ResponseEntity<List<Field>> getAllFields() {
        return ResponseEntity.ok(fieldService.findAll());
    }

    @PostMapping("/fields")
    public ResponseEntity<Field> save(@RequestBody String field) {
        return ResponseEntity.ok(fieldService.save(field));
    }

    @PutMapping("/fields/{fieldId}")
    public ResponseEntity<Field> updateField(@RequestBody String updatedField, @PathVariable Long fieldId) {
        Field field = fieldService.updateField(updatedField, fieldId);
        if (field != null) {
            return ResponseEntity.ok(field);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/fields/{fieldId}")
    public ResponseEntity<Field> deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
