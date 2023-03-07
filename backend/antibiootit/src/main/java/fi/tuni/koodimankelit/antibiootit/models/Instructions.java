package fi.tuni.koodimankelit.antibiootit.models;

/**
 * Represents when to take the antibiotic
 */
public class Instructions {
    private final int days;
    private final int dosesPerDay;


    /**
     * Default constructor
     * @param days amount how many days the treatment lasts
     * @param dosesPerDay amount how many times the antibiotic needs to be taken in a day
     */
    public Instructions(int days, int dosesPerDay) {
        this.days = days;
        this.dosesPerDay = dosesPerDay;
    }


    
    /** 
     * Returns how many days the treatment lasts
     * @return int days
     */
    public int getDays() {
        return this.days;
    }


    
    /** Returns how many times a day the antibiotic needs to be taken
     * @return int times in a day
     */
    public int getDosesPerDay() {
        return this.dosesPerDay;
    }


}
