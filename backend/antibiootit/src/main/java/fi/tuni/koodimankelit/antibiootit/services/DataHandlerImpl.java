package fi.tuni.koodimankelit.antibiootit.services;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @Autowired
    private InfoTextRepository infoTextRepository;

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
    
}
