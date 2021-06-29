package by.softarex.collectdata.dto;

import by.softarex.collectdata.model.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FieldDTO implements Serializable {

    private Long id;

    private String label;

    private String type;

    private boolean required;

    private boolean active;

    private List<String> options;

    public FieldDTO(Field field) {
        this.id = field.getId();
        this.label = field.getLabel();
        this.type = field.getType();
        this.active = field.getActive();
        this.required = field.getRequired();
        this.options = new ArrayList<>();
        field.getOptionList().forEach(option -> this.options.add(option.getOption()));
    }

    public FieldDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
