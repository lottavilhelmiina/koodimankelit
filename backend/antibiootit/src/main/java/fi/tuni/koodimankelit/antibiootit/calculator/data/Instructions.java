package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class Instructions {
    private final int days;
    private final int dosesPerDay;


    public Instructions(int days, int dosesPerDay) {
        this.days = days;
        this.dosesPerDay = dosesPerDay;
    }


    public int getDays() {
        return this.days;
    }


    public int getDosesPerDay() {
        return this.dosesPerDay;
    }


}
