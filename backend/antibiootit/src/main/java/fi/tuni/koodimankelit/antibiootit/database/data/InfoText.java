package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Document;

import nonapi.io.github.classgraph.json.Id;

/**
 * Database representation of info texts
 */
@Document(collection = "infoTexts")
public class InfoText {
    
    @Id
    private final String id;
    private final String text;

    /**
     * Default constructor
     * @param id text's id
     * @param text info text
     */
    public InfoText(String id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * Returns info text's id
     * @return String id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns info text
     * @return String text
     */
    public String getText() {
        return this.text;
    }
}
