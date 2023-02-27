package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;

@Service
public interface DataHandler {

    // Returns null if diagnose is not found
    public Diagnose getDiagnoseById(String id);

    public List<DiagnoseInfo> getAllDiagnoseInfos();

    public void addDiagnose(Diagnose diagnose);
    
}
