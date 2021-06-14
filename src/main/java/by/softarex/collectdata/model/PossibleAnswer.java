package by.softarex.collectdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "possible_answer")
public class PossibleAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ig;

    @Column(name = "possible_answer")
    private String possibleAnswer;

}
