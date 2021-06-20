package by.softarex.collectdata.service;

import by.softarex.collectdata.model.Field;
import by.softarex.collectdata.model.Option;
import by.softarex.collectdata.model.User;
import by.softarex.collectdata.repositories.FieldRepository;
import by.softarex.collectdata.repositories.OptionRepository;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
//    private final OptionRepository optionRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
//        this.optionRepository = optionRepository;
    }

    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    public Field save(Field field) {
        Field newField = new Field();
//        Option newOption = new Option();
        newField.setLabel(field.getLabel());
        newField.setActive(field.getActive());
        newField.setRequired(field.getRequired());
        newField.setType(field.getType());
//        if(option.getOption() != null){
//            newOption.setField(option.getField());
//            optionRepository.save(newOption);
//        }
        return fieldRepository.save(newField);
    }

    public Field updateField(String updatedField, Long fieldId) {
        JSONObject updatedFieldJson = new JSONObject(updatedField);
        Field existingField = fieldRepository.getById(fieldId);
        existingField.setLabel(updatedFieldJson.getString("label"));
        existingField.setType(updatedFieldJson.getString("type"));
        existingField.setRequired(updatedFieldJson.getBoolean("required"));
        existingField.setActive(updatedFieldJson.getBoolean("active"));
        return fieldRepository.save(existingField);
    }

    public void deleteField(Long fieldId) {
        Field deleteField = fieldRepository.getById(fieldId);
        fieldRepository.delete(deleteField);
    }
}
