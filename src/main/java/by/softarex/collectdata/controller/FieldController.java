package by.softarex.collectdata.controller;

import by.softarex.collectdata.dto.FieldDTO;
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
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable Long fieldId) {
        return ResponseEntity.ok(fieldService.getFieldById(fieldId));
    }

    @PostMapping("/fields")
    public void save(@RequestBody FieldDTO fieldDTO) {
        fieldService.save(fieldDTO);
    }

    @PutMapping("/fields/{fieldId}")
    public void updateField(@RequestBody FieldDTO fieldDTO, @PathVariable Long fieldId) {
        fieldService.updateField(fieldDTO, fieldId);
    }

    @DeleteMapping("/fields/{fieldId}")
    public ResponseEntity<Field> deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/fieldslabel")
//    public ResponseEntity<List<String>> getFieldLabel() {
//        return ResponseEntity.ok(fieldService.getFieldLabel());
//    }
}
