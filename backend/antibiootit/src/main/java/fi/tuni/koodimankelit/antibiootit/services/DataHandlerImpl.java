package fi.tuni.koodimankelit.antibiootit.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import fi.tuni.koodimankelit.antibiootit.database.DiagnoseRepository;
import fi.tuni.koodimankelit.antibiootit.database.InfoTextRepository;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;

@Service
public class DataHandlerImpl implements DataHandler {
    
    private final DiagnoseRepository diagnoseRepository;
    private final InfoTextRepository infoTextRepository;

    public DataHandlerImpl(DiagnoseRepository diagnoseRepository, InfoTextRepository infoTextRepository) {
        this.diagnoseRepository = diagnoseRepository;
        this.infoTextRepository = infoTextRepository;
    }

    @Override
    public Diagnose getDiagnoseById(String id) {
        Optional<Diagnose> findById = diagnoseRepository.findById(id);
        if (findById.isPresent()) {
            Diagnose diagnose = findById.get();
            return diagnose;
        }
        return null;
    }

    @Override
    public List<DiagnoseInfo> getAllDiagnoseInfos() {
        return diagnoseRepository.getAllDiagnoseInfos();
    }

    @Override
    public List<InfoText> getAllInfoTexts() {
        return infoTextRepository.findAll();
    }

    @Override
    public DiagnoseInfo getDiagnosisInfoById(String id) {
        Optional<DiagnoseInfo> findById = diagnoseRepository.getDiagnosisInfoById(id);
        if (findById.isPresent()) {
            DiagnoseInfo diagnoseInfo = findById.get();
            return diagnoseInfo;
        }
        return null;
    }
}
