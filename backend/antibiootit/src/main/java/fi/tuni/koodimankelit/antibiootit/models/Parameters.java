package fi.tuni.koodimankelit.antibiootit.models;

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
    private final Boolean ebv;


    public Parameters(String diagnosisID, Double weight, Boolean penicillinAllergic, Boolean ebv) {
        this.diagnosisID = diagnosisID;
        this.weight = weight;
        this.penicillinAllergic = penicillinAllergic;
        this.ebv = ebv;
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


    public boolean getEbv() {
        return this.ebv;
    }



    @Override
    // For testing purposes
    public String toString() {
        return String.format("Parameters{%s %.1f PenicillinAllergic: %s Ebv: %s}", diagnosisID, weight, penicillinAllergic, ebv);
    }
    

}
