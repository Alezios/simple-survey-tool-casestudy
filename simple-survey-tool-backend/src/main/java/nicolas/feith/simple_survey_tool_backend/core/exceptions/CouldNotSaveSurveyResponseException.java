package nicolas.feith.simple_survey_tool_backend.core.exceptions;

public class CouldNotSaveSurveyResponseException extends RuntimeException {  

    public CouldNotSaveSurveyResponseException(String message) {
        super(message);
    }

    public CouldNotSaveSurveyResponseException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
