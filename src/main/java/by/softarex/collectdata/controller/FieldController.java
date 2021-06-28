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

    @GetMapping("/fields/{fieldId}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long fieldId){
        return ResponseEntity.ok(fieldService.getFieldById(fieldId));
    }

    @GetMapping("/fieldslabel")
    public ResponseEntity<List<String>> getFieldLabel(){
        return ResponseEntity.ok(fieldService.getFieldLabel());
    }

    @PostMapping("/fields")
    public ResponseEntity<Field> save(@RequestBody String field) {
        return ResponseEntity.ok(fieldService.save(field));
    }

    @PutMapping("/fields/{fieldId}")
    public ResponseEntity<Field> updateField(@RequestBody String updatedField, @PathVariable Long fieldId) {
            return ResponseEntity.ok(fieldService.updateField(updatedField, fieldId));
    }

    @DeleteMapping("/fields/{fieldId}")
    public ResponseEntity<Field> deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
