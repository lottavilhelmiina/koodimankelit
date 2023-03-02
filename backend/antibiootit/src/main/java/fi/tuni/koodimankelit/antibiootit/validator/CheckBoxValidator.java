package fi.tuni.koodimankelit.antibiootit.validator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import fi.tuni.koodimankelit.antibiootit.database.data.CheckBoxInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.models.request.InfectionSelection;

@Component
public class CheckBoxValidator {
    public void validate(List<CheckBoxInfo> checkBoxInfos, List<InfectionSelection> infectionSelections) {
        Set<String> requestIDs = new TreeSet<>();
        Set<String> requiredIDs = new TreeSet<>();

        for (CheckBoxInfo checkBoxInfo : checkBoxInfos) {
            requiredIDs.add(checkBoxInfo.getId());
        }

        for(InfectionSelection infectionSelection : infectionSelections) {
            requestIDs.add(infectionSelection.getId());
        }

        if(!requestIDs.equals(requiredIDs)) {
            throw new InvalidParameterException("All required chekcBoxes not found");
        }
    }
}
