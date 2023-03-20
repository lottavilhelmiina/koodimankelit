package fi.tuni.koodimankelit.antibiootit.models;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents all the info texts saved in the database
 */
@Schema(description = "List of information texts")
public class InfoTexts {

    private final List<InfoText> texts;

    /**
     * Default constructor
     * @param List<InfoText> Info texts
     */
    public InfoTexts(List<InfoText> texts) {
        this.texts = texts;
    }

    /**
     * Returns the list of all info texts
     * @return List<InfoText> Info texts
     */
    public List<InfoText> getInfoTexts() {
        return this.texts;
    }
}
