package fi.tuni.koodimankelit.antibiootit.database;

import org.springframework.stereotype.Repository;

import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB repositoty for info texts
 */
@Repository
public interface InfoTextRepository extends MongoRepository<InfoText, String> {
    
}
