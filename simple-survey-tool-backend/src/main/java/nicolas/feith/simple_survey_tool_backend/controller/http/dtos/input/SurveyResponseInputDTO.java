package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.input;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyResponseInputDTO {
    private final UUID surveyId;
    private final Map<UUID, Object> answers;  // Question ID to answer value

    @JsonCreator // specifying @JsonCreator and @JsonProperty on parameters to help deserialization from JSON to this DTO java object
    public SurveyResponseInputDTO(@JsonProperty("surveyId") UUID surveyId, @JsonProperty("answers") Map<UUID, Object> answers) {
        this.surveyId = surveyId;
        this.answers = answers != null ? Map.copyOf(answers) : answers; // Immutable copy of the map
    }

    public UUID getSurveyId() {
        return surveyId;
    }

    public Map<UUID, Object> getAnswers() {
        return answers;
    }
}