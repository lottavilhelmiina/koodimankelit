package fi.tuni.koodimankelit.antibiootit.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class DiagnosisNotFoundException extends EmptyResultDataAccessException {

    public DiagnosisNotFoundException(String message) {
        super(message, 1);
    }
    
}
