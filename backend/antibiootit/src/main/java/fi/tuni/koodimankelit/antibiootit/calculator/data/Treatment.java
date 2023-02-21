package fi.tuni.koodimankelit.antibiootit.calculator.data;

import java.util.ArrayList;
import java.util.List;

public class Treatment {
    private final ArrayList<AntibioticChoise> antibioticChoises;


    public Treatment() {
        this.antibioticChoises = new ArrayList<>();
    }

    public void addAntibiotic(AntibioticChoise choise) {
        this.antibioticChoises.add(choise);
    }


    public List<AntibioticChoise> getAntibioticChoises() {
        return this.antibioticChoises;
    }



}
