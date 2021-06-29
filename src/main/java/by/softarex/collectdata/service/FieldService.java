package by.softarex.collectdata.service;

import by.softarex.collectdata.dto.FieldDTO;
import by.softarex.collectdata.model.Field;
import by.softarex.collectdata.model.Option;
import by.softarex.collectdata.repositories.FieldRepository;
import by.softarex.collectdata.repositories.OptionRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void save(FieldDTO fieldDTO) {
        Field newField = new Field();
        newField.setLabel(fieldDTO.getLabel());
        newField.setType(fieldDTO.getType());
        newField.setRequired(fieldDTO.isRequired());
        newField.setActive(fieldDTO.isActive());
        fieldDTO.getOptions().forEach(option -> newField.getOptionList().add(new Option(option)));
        fieldRepository.save(newField);
    }

    public void updateField(FieldDTO fieldDTO, Long fieldId) {
        Field existingField = fieldRepository.getById(fieldId);
        existingField.setLabel(fieldDTO.getLabel());
        existingField.setType(fieldDTO.getType());
        existingField.setRequired(fieldDTO.isRequired());
        existingField.setActive(fieldDTO.isActive());
        existingField.getOptionList().clear();
        fieldDTO.getOptions().forEach(option -> existingField.getOptionList().add(new Option(option)));
        existingField.getOptionList().forEach(optionRepository::save);
        fieldRepository.save(existingField);
    }

    public void deleteField(Long fieldId) {
        Field deleteField = fieldRepository.getById(fieldId);
        fieldRepository.delete(deleteField);
    }

    public FieldDTO getFieldById(Long fieldId) {
        Field field = fieldRepository.getFieldById(fieldId);
        return new FieldDTO(field);
    }

//    public List<String> getFieldLabel() {
//        JSONArray labelJSONarr = new JSONArray(fieldRepository.findAll());
//        List<String> fieldLabel = new ArrayList<>();
//        for (int i = 0; i < labelJSONarr.length(); i++) {
//            fieldLabel.add(labelJSONarr.getJSONObject(i).getString("label"));
//        }
//        return fieldLabel;
//    }

}
