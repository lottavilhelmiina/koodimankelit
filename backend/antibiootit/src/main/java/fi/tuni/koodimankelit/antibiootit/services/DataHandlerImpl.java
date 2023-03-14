package fi.tuni.koodimankelit.antibiootit.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import fi.tuni.koodimankelit.antibiootit.database.DiagnosisRepository;
import fi.tuni.koodimankelit.antibiootit.database.InfoTextRepository;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;
import fi.tuni.koodimankelit.antibiootit.exceptions.DiagnosisNotFoundException;

@Service
public class DataHandlerImpl implements DataHandler {
    
    private final DiagnosisRepository diagnosisRepository;
    private final InfoTextRepository infoTextRepository;

    public DataHandlerImpl(DiagnosisRepository diagnosisRepository, InfoTextRepository infoTextRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.infoTextRepository = infoTextRepository;
    }

    @Override
    public Diagnosis getDiagnosisById(String id) {
        Optional<Diagnosis> findById = diagnosisRepository.findById(id);
        // Check is diagnosis found with given id
        if (findById.isPresent()) {
            Diagnosis diagnosis = findById.get();
            return diagnosis;
        } else {
            throw new DiagnosisNotFoundException("Diagnosis not found with id: " + id);
        }
    }

    @Override
    public List<DiagnosisInfo> getAllDiagnosisInfos() {
        return diagnosisRepository.getAllDiagnosisInfos();
    }

    @Override
    public List<InfoText> getAllInfoTexts() {
        return infoTextRepository.findAll();
    }

    @Override
    public DiagnosisInfo getDiagnosisInfoById(String id) {
        Optional<DiagnosisInfo> findById = diagnosisRepository.getDiagnosisInfoById(id);
        // Check is diagnosis info found with given id
        if (findById.isPresent()) {
            DiagnosisInfo diagnosisInfo = findById.get();
            return diagnosisInfo;
        } else {
            throw new DiagnosisNotFoundException("Diagnosis not found with id: " + id);
        }
    }
}
