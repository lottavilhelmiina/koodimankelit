package fi.tuni.koodimankelit.antibiootit.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.CheckBoxInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.models.request.InfectionSelection;

public class CheckBoxValidatorTest {
    
    private CheckBoxValidator validator = new CheckBoxValidator();

    private List<CheckBoxInfo> checkBoxInfos;
    private List<InfectionSelection> infectionSelections;


    @Test
    public void bothEmptyIsValid() {
        checkBoxInfos = new ArrayList<>();
        infectionSelections = new ArrayList<>();

        assertDoesNotThrow(() -> validator.validate(checkBoxInfos, infectionSelections));
    }

    @Test
    public void oneSameIsValid() {
        checkBoxInfos = new ArrayList<>();
        checkBoxInfos.add(new CheckBoxInfo("id1", "name1"));

        infectionSelections = new ArrayList<>();
        infectionSelections.add(new InfectionSelection("id1", false));

        assertDoesNotThrow(() -> validator.validate(checkBoxInfos, infectionSelections));
    }

    @Test
    public void multipleSameIsValid() {
        checkBoxInfos = new ArrayList<>();
        checkBoxInfos.add(new CheckBoxInfo("id1", "name1"));
        checkBoxInfos.add(new CheckBoxInfo("id2", "name2"));
        checkBoxInfos.add(new CheckBoxInfo("id3", "name3"));

        infectionSelections = new ArrayList<>();
        infectionSelections.add(new InfectionSelection("id1", false));
        infectionSelections.add(new InfectionSelection("id2", true));
        infectionSelections.add(new InfectionSelection("id3", false));

        assertDoesNotThrow(() -> validator.validate(checkBoxInfos, infectionSelections));
    }

    @Test
    public void onlyCheckBoxEmptyIsInvalid() {
        checkBoxInfos = new ArrayList<>();

        infectionSelections = new ArrayList<>();
        infectionSelections.add(new InfectionSelection("id1", false));

        assertThrows(InvalidParameterException.class, () -> validator.validate(checkBoxInfos, infectionSelections));
    }

    @Test
    public void onlyInfectionSelectionEmptyIsInvalid() {
        checkBoxInfos = new ArrayList<>();
        checkBoxInfos.add(new CheckBoxInfo("id1", "name1"));

        infectionSelections = new ArrayList<>();

        assertThrows(InvalidParameterException.class, () -> validator.validate(checkBoxInfos, infectionSelections));
    }

    @Test
    public void partiallySameIsInvalid() {
        checkBoxInfos = new ArrayList<>();
        checkBoxInfos.add(new CheckBoxInfo("id1", "name1"));
        checkBoxInfos.add(new CheckBoxInfo("id2", "name1"));

        infectionSelections = new ArrayList<>();
        infectionSelections.add(new InfectionSelection("id1", false));
        infectionSelections.add(new InfectionSelection("id3", true));

        assertThrows(InvalidParameterException.class, () -> validator.validate(checkBoxInfos, infectionSelections));
    }

    @Test
    public void VoidResultsToException() {

        // CheckBoxInfos null
        checkBoxInfos = null;
        infectionSelections = new ArrayList<>();
        assertThrows(RuntimeException.class, () -> validator.validate(checkBoxInfos, infectionSelections));

        // InfectionSelections null
        checkBoxInfos = new ArrayList<>();
        infectionSelections = null;
        assertThrows(RuntimeException.class, () -> validator.validate(checkBoxInfos, infectionSelections));

        // Both null
        checkBoxInfos = null;
        assertThrows(RuntimeException.class, () -> validator.validate(checkBoxInfos, infectionSelections));
    }
}
