package by.softarex.collectdata.repositories;

import by.softarex.collectdata.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAll();

    List<Answer> findAllByQuestionnaireId(int a);

}
