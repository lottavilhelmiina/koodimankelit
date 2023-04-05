package fi.tuni.koodimankelit.antibiootit.builder;


import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;
import fi.tuni.koodimankelit.antibiootit.models.TabletDosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.TabletDosageResult;
import fi.tuni.koodimankelit.antibiootit.models.TabletTreatment;

public class TabletBuilder {

    private final Tablet antibiotic;
    private final AntibioticTreatmentBuilder antibioticBuilder;

    public TabletBuilder(Tablet antibiotic, double weight) {
        this.antibiotic = antibiotic;
        this.antibioticBuilder = new AntibioticTreatmentBuilder(antibiotic, weight);
    }

    public TabletTreatment buildTablet() {
        // Does not exceed minimum weight on any strength
        if(antibioticBuilder.getStrength() == null) {
            // TODO change to custom exception
            throw new RuntimeException("Selected antibiotic has no suitable strength");
        }

        return new TabletTreatment(
            antibiotic.getFormat(),
            antibiotic.getAntibiotic(), 
            antibioticBuilder.buildInstructions(), 
            buildTabletFormula(), 
            buildTabletResult());
    }

    protected TabletDosageFormula buildTabletFormula() {
        Strength strength = antibioticBuilder.getStrength();
        return new TabletDosageFormula(
            new StrengthMeasurement(strength.getUnit(), strength.getValue(), strength.getText())
        );
    }

    protected TabletDosageResult buildTabletResult() {
        String resultUnit = "kpl";
        int dosageResult = antibiotic.getTabletsPerDose();
        
        return new TabletDosageResult(
            new Measurement(resultUnit, dosageResult)
        );
    }
}
