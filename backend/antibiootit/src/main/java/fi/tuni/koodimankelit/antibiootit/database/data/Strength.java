package fi.tuni.koodimankelit.antibiootit.database.data;

public class Strength {
    public int value;
    public int weight;

    public Strength() {}

    public Strength(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }
}
