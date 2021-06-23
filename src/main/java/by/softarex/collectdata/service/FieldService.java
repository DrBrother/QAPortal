package by.softarex.collectdata.service;

import by.softarex.collectdata.model.Field;
import by.softarex.collectdata.model.Option;
import by.softarex.collectdata.repositories.FieldRepository;
import by.softarex.collectdata.repositories.OptionRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository, OptionRepository optionRepository) {
        this.fieldRepository = fieldRepository;
        this.optionRepository = optionRepository;
    }

    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    public Field save(String field) {
        Field newField = new Field();
        JSONObject updatedFieldJson = new JSONObject(field);
        newField.setLabel(updatedFieldJson.getString("label"));
        newField.setType(updatedFieldJson.getString("type"));
        newField.setRequired(updatedFieldJson.getBoolean("required"));
        newField.setActive(updatedFieldJson.getBoolean("active"));
        if (!updatedFieldJson.isNull("option")) {
            JSONArray jsonArray = updatedFieldJson.getJSONArray("option");
            for (int i = 0; i < jsonArray.length(); i++) {
                newField.getOptionList().add(new Option(jsonArray.getString(i)));
            }
        }
        return fieldRepository.save(newField);
    }

    public Field updateField(String updatedField, Long fieldId) {
        JSONObject updatedFieldJson = new JSONObject(updatedField);
        Field existingField = fieldRepository.getById(fieldId);
        existingField.setLabel(updatedFieldJson.getString("label"));
        existingField.setType(updatedFieldJson.getString("type"));
        existingField.setRequired(updatedFieldJson.getBoolean("required"));
        existingField.setActive(updatedFieldJson.getBoolean("active"));

        if (!updatedFieldJson.isNull("option")) {
            existingField.getOptionList().clear();
            JSONArray jsonArray = updatedFieldJson.getJSONArray("option");
            for (int i = 0; i < jsonArray.length(); i++) {
                Option option = new Option(jsonArray.getString(i));
                existingField.getOptionList().add(option);
                optionRepository.save(option);
            }
        }
        return fieldRepository.save(existingField);
    }

    public void deleteField(Long fieldId) {
        Field deleteField = fieldRepository.getById(fieldId);
        fieldRepository.delete(deleteField);
    }
}
