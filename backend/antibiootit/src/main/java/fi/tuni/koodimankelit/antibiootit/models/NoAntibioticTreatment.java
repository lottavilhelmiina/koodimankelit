package fi.tuni.koodimankelit.antibiootit.models;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;

public class NoAntibioticTreatment {

    private final String text;
    private final String id;
    
    public NoAntibioticTreatment(Diagnosis diagnosis) {
        this.text = "No antibiotic treatment for diagnosis: " + diagnosis.getId();
        this.id = diagnosis.getId();
    }


    public String getText() {
        return this.text;
    }

    public String getId() {
        return this.id;
    }

}
