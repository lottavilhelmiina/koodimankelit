package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Document;

import nonapi.io.github.classgraph.json.Id;

@Document(collection = "infoTexts")
public class InfoText {
    
    @Id
    private final String id;
    private final String text;

    public InfoText(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return this.id;
    }


    public String getText() {
        return this.text;
    }
}
