package fi.tuni.koodimankelit.antibiootit.models.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Parameters {


    @NotBlank(message = "DiagnosisID is mandatory")
    private final String diagnosisID;

    @NotNull(message = "Weight is mandatory")
    private final Double weight;

    @NotNull(message = "PenicillinAllergic is mandatory")
    private final Boolean penicillinAllergic;

    @NotNull(message = "Ebv is mandatory")
    private final List<InfectionSelection> checkBoxes;


    public Parameters(String diagnosisID, Double weight, Boolean penicillinAllergic, List<InfectionSelection> checkBoxes) {
        this.diagnosisID = diagnosisID;
        this.weight = weight;
        this.penicillinAllergic = penicillinAllergic;
        this.checkBoxes = checkBoxes;
    }


    public String getDiagnosisID() {
        return this.diagnosisID;
    }


    public double getWeight() {
        return this.weight;
    }


    public boolean getPenicillinAllergic() {
        return this.penicillinAllergic;
    }
    
    
    public List<InfectionSelection> getCheckBoxes() {
        return this.checkBoxes;
    }
}
