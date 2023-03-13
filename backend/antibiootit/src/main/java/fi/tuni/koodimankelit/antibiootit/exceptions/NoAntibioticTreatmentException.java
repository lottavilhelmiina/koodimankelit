package fi.tuni.koodimankelit.antibiootit.exceptions;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;

public class NoAntibioticTreatmentException extends RuntimeException {

    private final Diagnose diagnosis;


    public NoAntibioticTreatmentException(Diagnose diagnosis) {
        super(String.format("No antibiotic treatment for diagnosis: %s", diagnosis.getId()));
        this.diagnosis = diagnosis;
    }


    public Diagnose getDiagnosis() {
        return this.diagnosis;
    }

}
