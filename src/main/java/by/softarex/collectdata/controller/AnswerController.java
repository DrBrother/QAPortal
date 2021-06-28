package by.softarex.collectdata.controller;

import by.softarex.collectdata.model.Answer;
import by.softarex.collectdata.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/questionnaires")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        return ResponseEntity.ok(answerService.findAll());
    }

    @PostMapping("/questionnaires")
    public ResponseEntity<Answer> save(@RequestBody Answer[] answer) {
        answerService.save(answer);
        return new ResponseEntity(HttpStatus.OK);
    }
}
