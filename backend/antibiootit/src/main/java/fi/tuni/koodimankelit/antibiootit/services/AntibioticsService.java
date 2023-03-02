package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;


public interface AntibioticsService {


    public Diagnosis calculateTreatments(Parameters parameters);

    public List<DiagnoseInfo> getAllDiagnoseInfos();

    public DiagnoseInfo getDiagnoseInfoByID(String id);
}
