package by.softarex.collectdata.controller;


import by.softarex.collectdata.model.Answer;
import by.softarex.collectdata.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/questionnaire")
    public ResponseEntity<Answer> writeAnswer(@RequestBody Answer answer) {
        return null;
    }
}
