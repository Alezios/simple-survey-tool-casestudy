package nicolas.feith.simple_survey_tool_backend.core.exceptions;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException(String message) {
        super(message);
    }

    public SurveyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
