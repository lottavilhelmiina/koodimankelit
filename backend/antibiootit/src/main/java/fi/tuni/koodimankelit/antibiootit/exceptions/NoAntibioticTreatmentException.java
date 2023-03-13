package fi.tuni.koodimankelit.antibiootit.exceptions;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;

public class NoAntibioticTreatmentException extends RuntimeException {

    private final Diagnosis diagnosis;


    public NoAntibioticTreatmentException(Diagnosis diagnosis) {
        super(String.format("No antibiotic treatment for diagnosis: %s", diagnosis.getId()));
        this.diagnosis = diagnosis;
    }


    public Diagnosis getDiagnosis() {
        return this.diagnosis;
    }

}
