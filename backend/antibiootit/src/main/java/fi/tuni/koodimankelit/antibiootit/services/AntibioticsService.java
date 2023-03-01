package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;


public interface AntibioticsService {


    public Treatments calculateTreatments(Parameters parameters);

    public List<DiagnoseInfo> getAllDiagnoseInfos();
}
