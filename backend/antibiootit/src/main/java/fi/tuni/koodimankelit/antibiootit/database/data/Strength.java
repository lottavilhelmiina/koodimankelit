package fi.tuni.koodimankelit.antibiootit.database.data;

public class Strength {
    private final int value;
    private final int weight;

    public Strength(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return this.value;
    }

    public int getWeight() {
        return this.weight;
    }
}
