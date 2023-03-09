package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public class Mixture extends Antibiotic {
    private final String resultUnit;
    private final int dosagePerWeightPerDay;
    private final String dosagePerWeightPerDayUnit;

    public Mixture(String antibiotic, String format, String info, 
    int maxDosePerDay, List<Strength> strength, String weightUnit, int days,
    int dosesPerDay, String resultUnit, int dosagePerWeightPerDay, String dosagePerWeightPerDayUnit) {
        super(antibiotic, format, info, maxDosePerDay, strength, weightUnit, days, dosesPerDay);
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
