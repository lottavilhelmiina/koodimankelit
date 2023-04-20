package fi.tuni.koodimankelit.antibiootit.exceptions;


/**
 * Exception when no treatment could be resolved for diagnosis.
 * There should always be suitable treatment. This exception means configuration error
 */
public class TreatmentNotFoundException extends RuntimeException {

}
