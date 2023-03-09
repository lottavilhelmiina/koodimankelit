package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public class Tablet extends Antibiotic {
    private final int tabletsPerDose;
    private final int dosagePerDay;
    private final String dosagePerDayUnit;

    public Tablet(String antibiotic, String format, String info, String dosagePerDayUnit,
        int dosagePerDay, int maxDosePerDay, List<Strength> strength, String weightUnit,
        int days, int dosesPerDay, int tabletsPerDose) {
        super(antibiotic, format, info, maxDosePerDay, strength, weightUnit, days, dosesPerDay);
        this.dosagePerDayUnit = dosagePerDayUnit;
        this.dosagePerDay = dosagePerDay;
        this.tabletsPerDose = tabletsPerDose;
    }

    public int getTabletsPerDose() {
        return this.tabletsPerDose;
    }


    public int getDosagePerDay() {
        return this.dosagePerDay;
    }


    public String getDosagePerDayUnit() {
        return this.dosagePerDayUnit;
    }

}
