package fi.tuni.koodimankelit.antibiootit.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;

/**
 * MongoDB database repository for diagnoses
 */
@Repository
public interface DiagnosisRepository extends MongoRepository<Diagnosis, String> {

    /**
     * Returns all diagnosis basic information from database: id, name, etiology, checkBoxes and if it needs antibiotics
     * @return List<DiagnosisInfo> 
     */
    @Query(value = "{}", fields = "{'_id': 1, 'name': 1, 'etiology': 1, 'checkBoxes': 1, 'needsAntibiotics': 1}")
    List<DiagnosisInfo> getAllDiagnosisInfos();

    /**
     * Finds and returns diagnosis info from database by given id
     * @param id diagnosis ICD-10 identifier
     * @return Optional<DiagnosisInfo>
     */
    @Query("{_id: '?0'}")
    Optional<DiagnosisInfo> getDiagnosisInfoById(String id);
}