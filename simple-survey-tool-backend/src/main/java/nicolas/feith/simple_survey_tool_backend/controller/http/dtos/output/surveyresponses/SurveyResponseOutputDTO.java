package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveyresponses;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyResponseOutputDTO {
    @JsonProperty("answeredAt")
    private final LocalDateTime answeredAt;
    @JsonProperty("questionResponses")
    private final Map<UUID, Object> questionResponses;

    public SurveyResponseOutputDTO(LocalDateTime answeredAt, Map<UUID, Object> questionResponses) {
        this.answeredAt = answeredAt;
        this.questionResponses = questionResponses;
    }
}
