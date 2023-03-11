package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;

/**
 * Database related services
 */
public interface DataHandler {

    /**
     * Finds and returns diagnose with the given id but if id is not
     * found, returns null.
     * @param id Diagnosis id
     * @return Diagnose 
     */
    public Diagnose getDiagnoseById(String id);

    /**
     * Returns all diagnoses basic information: id, name, etiology, checkboxes
     * and if it needs antibiotics
     * @return List<DiagnoseInfo> List of diagnose infos
     */
    public List<DiagnoseInfo> getAllDiagnoseInfos();

    /**
     * Finds and returns diagnosis info with the given id but if id is not
     * found, returns null.
     * @param id Diagnosis id
     * @return DiagnoseInfo Diagnosis info
     */
    public DiagnoseInfo getDiagnosisInfoById(String id);
    
    /**
     * Returns all info texts
     * @return List<InfoText> List of info texts
     */
    public List<InfoText> getAllInfoTexts();
}
