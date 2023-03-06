package fi.tuni.koodimankelit.antibiootit.exceptions;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;

public class NoAntibioticTreatmentException extends RuntimeException {

    private final Diagnose diagnose;


    public NoAntibioticTreatmentException(Diagnose diagnose) {
        super(String.format("No antibiotic treatment for diagnose: %s", diagnose.getId()));
        this.diagnose = diagnose;
    }


    public Diagnose getDiagnose() {
        return this.diagnose;
    }

}
