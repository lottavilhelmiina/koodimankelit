package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.TreatmentResponse;
import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;

@Service
public class AntibioticsServiceImpl implements AntibioticsService {

    private final DataHandler dataHandler;

    public AntibioticsServiceImpl(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public DiagnoseResponse calculateTreatments(Parameters parameters) {
        Diagnose diagnose = dataHandler.getDiagnoseById(parameters.getDiagnosisID());
        TreatmentResponse treatment = getTreatment(parameters, diagnose);
        return null;
        
    }

    public List<DiagnoseInfo> getAllDiagnoseInfos() {
        return this.dataHandler.getAllDiagnoseInfos();
    }

    @Override
    public DiagnoseInfo getDiagnoseInfoByID(String id) {
        return dataHandler.getDiagnosisInfoById(id);
    }

    private TreatmentResponse getTreatment(Parameters parameters, Diagnose diagnose) {
        return null;
    }

}
