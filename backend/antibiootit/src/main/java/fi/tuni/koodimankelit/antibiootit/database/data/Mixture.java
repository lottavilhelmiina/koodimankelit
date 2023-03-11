package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class Mixture extends Antibiotic {
    @Field("resultUnit")
    private final String resultUnit;
    @Field("dosagePerWeightPerDay")
    private final int dosagePerWeightPerDay;
    @Field("dosagePerWeightPerDayUnit")
    private final String dosagePerWeightPerDayUnit;

    public Mixture(String antibiotic, String format, String info, 
    int maxDosePerDay, List<Strength> strength, String weightUnit, int days,
    int dosesPerDay, String resultUnit, int dosagePerWeightPerDay, String dosagePerWeightPerDayUnit, List<DoseMultiplier> doseMultipliers) {
        super(antibiotic, format, info, maxDosePerDay, strength, weightUnit, days, dosesPerDay, doseMultipliers);
        this.resultUnit = resultUnit;
        this.dosagePerWeightPerDay = dosagePerWeightPerDay;
        this.dosagePerWeightPerDayUnit = dosagePerWeightPerDayUnit;
    }

    public String getResultUnit() {
        return this.resultUnit;
    }

    public int getDosagePerWeightPerDay() {
        return this.dosagePerWeightPerDay;
    }

    public String getDosagePerWeightPerDayUnit() {
        return this.dosagePerWeightPerDayUnit;
    }
}
