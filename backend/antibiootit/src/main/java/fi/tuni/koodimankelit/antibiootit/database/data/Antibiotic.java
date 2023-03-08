package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public class Antibiotic {
    private final String antibiotic;
    private final String format;
    private final String info;
    private final String dosagePerDayUnit;
    private final int dosagePerDay;
    private final int maxDosePerDay;
    private final List<Strength> strength;
    private final String weightUnit;
    private final String resultUnit;
    private final int days;
    private final int dosesPerDay;
    private final int tabletsPerDose;

    public Antibiotic(String antibiotic, String format, String info, String dosagePerDayUnit, int dosagePerDay, 
        int maxDosePerDay, List<Strength> strength, String weightUnit, String resultUnit, int days,
        int dosesPerDay, int tabletsPerDose) {
            this.antibiotic = antibiotic;
            this.format = format;
            this.info = info;
            this.dosagePerDayUnit = dosagePerDayUnit;
            this.dosagePerDay = dosagePerDay;
            this.maxDosePerDay = maxDosePerDay;
            this.strength = strength;
            this.days = days;
            this.dosesPerDay = dosesPerDay;
            this.weightUnit = weightUnit;
            this.resultUnit = resultUnit;
            this.tabletsPerDose = tabletsPerDose;
    }

    public String getAntibiotic() {
        return this.antibiotic;
    }

    public String getFormat() {
        return this.format;
    }

    public String getInfo() {
        return this.info;
    }

    public String getDosagePerDayUnit() {
        return this.dosagePerDayUnit;
    }

    public int getDosagePerDay() {
        return this.dosagePerDay;
    }

    public int getMaxDosePerDay() {
        return this.maxDosePerDay;
    }

    public List<Strength> getStrength() {
        return this.strength;
    }

    public String getWeightUnit() {
        return this.weightUnit;
    }

    public String getResultUnit() {
        return this.resultUnit;
    }

    public int getDays() {
        return this.days;
    }

    public int getDosesPerDay() {
        return this.dosesPerDay;
    }

    public int getTabletsPerDose() {
        return this.tabletsPerDose;
    }
}
