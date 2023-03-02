package fi.tuni.koodimankelit.antibiootit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import fi.tuni.koodimankelit.antibiootit.database.DiagnoseRepository;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;

@Service
public class DataHandlerImpl implements DataHandler {
    
    @Autowired
    private DiagnoseRepository repository;

    @Override
    public Diagnose getDiagnoseById(String id) {
        Optional<Diagnose> findById = repository.findById(id);
        if (findById.isPresent()) {
            Diagnose diagnose = findById.get();
            return diagnose;
        }
        return null;
    }

    @Override
    public List<DiagnoseInfo> getAllDiagnoseInfos() {
        return repository.getAllDiagnoseInfos();
    }

    @Override
    public DiagnoseInfo getDiagnosisInfoById(String id) {
        return repository.getDiagnosisInfoById(id);
    }
    
}
