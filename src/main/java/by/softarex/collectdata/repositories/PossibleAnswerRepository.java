package by.softarex.collectdata.repositories;

import by.softarex.collectdata.model.PossibleAnswer;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {
}
