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


    @ManyToMany
    @JoinColumn(name = "field_id")
    private List<Field> fieldList;


}
