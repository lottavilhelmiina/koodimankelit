package fi.tuni.koodimankelit.antibiootit.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InfectionSelection {

    @NotBlank(message = "id is mandatory")
    private final String id;

    @NotNull(message = "value is mandatory")
    private final Boolean value;


    public InfectionSelection(String id, Boolean value) {
        this.id = id;
        this.value = value;
    }


    public String getId() {
        return this.id;
    }


    public Boolean getValue() {
        return this.value;
    }


}
