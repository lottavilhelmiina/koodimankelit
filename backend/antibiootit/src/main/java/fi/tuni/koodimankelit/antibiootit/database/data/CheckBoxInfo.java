package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Database representation of checkboxes
 */
public class CheckBoxInfo {

    @Field("id")
    private final String id;
    private final String name;

    /**
     * Default constructor
     * @param id checkbox's id 
     * @param name name of checkbox
     */
    public CheckBoxInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns id of checkbox
     * @return String id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns name of checkbox
     * @return String name
     */
    public String getName() {
        return this.name;
    }
}
