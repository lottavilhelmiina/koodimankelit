package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Field;

public class CheckBoxInfo {

    @Field("id")
    private final String id;
    private final String name;

    public CheckBoxInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
