package fi.tuni.koodimankelit.antibiootit.builder;


import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;

public class TabletBuilder extends AntibioticTreatmentBuilder {

    private final Tablet antibiotic;

    public TabletBuilder(Tablet antibiotic, double weight) {
        super(antibiotic, weight);

        this.antibiotic = antibiotic;
    }

    @Override
    public AntibioticTreatment build() {
        
        Instructions instructions = new Instructions(antibiotic.getDays(), antibiotic.getDosesPerDay());
        DosageFormula dosageFormula = new DosageFormula(
            new Measurement(strength.getUnit(), strength.getValue()),
            new Measurement(antibiotic.getDosagePerDayUnit(), antibiotic.getTabletsPerDose() * strength.getValue())
        );
        DosageResult dosageResult = new DosageResult(
            new Measurement("kpl", antibiotic.getTabletsPerDose())
        );

        return new AntibioticTreatment(
            antibiotic.getFormat(),
            antibiotic.getInfo(),
            antibiotic.getAntibiotic(),
            instructions,
            dosageFormula,
            dosageResult
        );
    }
    

}
