package by.softarex.collectdata.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

//    костыль
    private int questionnaireId;


    @ManyToMany
    @JoinColumn(name = "field_id")
    private List<Field> fieldList;

    public Answer(String answer) {
        this.answer = answer;
    }

    public int randomId(){
        int min = 0;
        int max = 1000;
        return min + (int) (Math.random()*max);
    }

    public Answer() {
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }
}
