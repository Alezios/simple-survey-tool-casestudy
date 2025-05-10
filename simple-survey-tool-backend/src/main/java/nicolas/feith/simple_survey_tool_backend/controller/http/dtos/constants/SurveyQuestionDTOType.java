package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.constants;

public enum SurveyQuestionDTOType {
    TEXT("text"),
    COMMENT("comment"),
    SINGLE_CHOICE("radiogroup"), //single choice in the frontend is a radio group
    MULTIPLE_CHOICE("checkbox"), //multiple choice in the frontend is a checkbox group
    RATING("rating"),
    BOOLEAN("boolean");

    private final String type;

    SurveyQuestionDTOType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
