package by.softarex.collectdata.service;


import by.softarex.collectdata.model.Answer;
import by.softarex.collectdata.model.Field;
import by.softarex.collectdata.repositories.AnswerRepository;
import by.softarex.collectdata.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, FieldRepository fieldRepository) {
        this.answerRepository = answerRepository;
        this.fieldRepository = fieldRepository;
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public void save(Answer[] answers) {
        Answer newAnswer = new Answer();
        List<Field> fieldList = fieldRepository.findAll();
        int questionnaireId = newAnswer.randomId();

        if (answerRepository.findAllByQuestionnaireId(questionnaireId) != null) {
            questionnaireId = newAnswer.randomId();

        }
        for (int i = 0; i < answers.length; i++) {
            answers[i].setFieldList(new ArrayList<>());
            answers[i].getFieldList().add(fieldList.get(i));
            answers[i].setQuestionnaireId(questionnaireId);
            answerRepository.save(answers[i]);
        }
    }
}
