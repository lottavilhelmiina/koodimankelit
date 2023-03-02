package fi.tuni.koodimankelit.antibiootit.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InfectionSelection {

    @NotBlank(message = "Infection ID is mandatory")
    private final String id;

    @NotNull(message = "Selection is mandatory")
    private final boolean value;


    public InfectionSelection(String id, boolean value) {
        this.id = id;
        this.value = value;
    }


    public String getId() {
        return this.id;
    }


    public boolean getValue() {
        return this.value;
    }


}
