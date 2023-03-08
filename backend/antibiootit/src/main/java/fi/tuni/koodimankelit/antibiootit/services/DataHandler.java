package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;

public interface DataHandler {

    // Returns null if diagnose is not found
    public Diagnose getDiagnoseById(String id);

    public List<DiagnoseInfo> getAllDiagnoseInfos();

    public DiagnoseInfo getDiagnosisInfoById(String id);
    
    public List<InfoText> getAllInfoTexts();
}
