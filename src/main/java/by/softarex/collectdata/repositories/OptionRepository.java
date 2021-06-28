package by.softarex.collectdata.repositories;

import by.softarex.collectdata.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    Option deleteAllByFieldId(Long fieldId);
}
