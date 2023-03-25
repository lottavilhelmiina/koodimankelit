package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Strength measurement")
public class StrengthMeasurement extends Measurement {

    private final String text;

        /**
     * Default constructor
     * @param unit unit of measurement
     * @param value value of measurement
     * @param text value and unit in text form
     */
    public StrengthMeasurement(String unit, double value, String text) {
        super(unit, value);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
