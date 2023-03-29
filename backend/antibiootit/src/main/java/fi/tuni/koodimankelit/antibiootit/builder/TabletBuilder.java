package fi.tuni.koodimankelit.antibiootit.builder;


import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

public class TabletBuilder extends AntibioticTreatmentBuilder {

    private final Tablet antibiotic;

    public TabletBuilder(Tablet antibiotic, double weight) {
        super(antibiotic, weight);

        this.antibiotic = antibiotic;
    }

    @Override
    protected DosageFormula buildFormula() {
        return new DosageFormula(
            new StrengthMeasurement(strength.getUnit(), strength.getValue(), strength.getText()),
            new Measurement(antibiotic.getDosagePerDayUnit(), antibiotic.getTabletsPerDose() * strength.getValue())
        );
    }

    @Override
    protected DosageResult buildResult() {
        String resultUnit = "kpl";
        int dosageResult = antibiotic.getTabletsPerDose();
        
        return new DosageResult(
            new Measurement(resultUnit, dosageResult),
            new Measurement(resultUnit, dosageResult)
        );
    }
    

}
