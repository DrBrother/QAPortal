package by.softarex.collectdata.repositories;

import by.softarex.collectdata.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    Field getFieldById(Long fieldId);
}
