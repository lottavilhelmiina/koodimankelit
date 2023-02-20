package fi.tuni.koodimankelit.antibiootit.calculator.data;

import java.util.ArrayList;
import java.util.List;

public class Treatment {
    private final ArrayList<AntibioticTreatment> antibioticChoises;


    public Treatment() {
        this.antibioticChoises = new ArrayList<>();
    }

    public void addAntibiotic(AntibioticTreatment choise) {
        this.antibioticChoises.add(choise);
    }


    public List<AntibioticTreatment> getAntibioticChoises() {
        return this.antibioticChoises;
    }



}
