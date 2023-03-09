package fi.tuni.koodimankelit.antibiootit.builder;


import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;

public class TabletBuilder extends AntibioticTreatmentBuilder {

    private final Tablet antibiotic;

    public TabletBuilder(Tablet antibiotic, double weight) {
        super(antibiotic, weight);

        this.antibiotic = antibiotic;
    }

    @Override
    protected DosageFormula buildFormula() {
        return new DosageFormula(
            new Measurement(strength.getUnit(), strength.getValue()),
            new Measurement(antibiotic.getDosagePerDayUnit(), antibiotic.getTabletsPerDose() * strength.getValue())
        );
    }

    @Override
    protected DosageResult buildResult() {
        return new DosageResult(
            new Measurement("kpl", antibiotic.getTabletsPerDose())
        );
    }
    

}
