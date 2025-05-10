package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveyresponses;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyQuestionOutputDTO;

public class SurveyResponsesOutputDTO {
    @JsonProperty("surveyId")
    private final UUID surveyId;
    @JsonProperty("questions")
    private final List<SurveyQuestionOutputDTO> questions; // Caller will require the question's text and possible values (for display and aggregation) 
                                                           // so we send a recap before the answers to avoid duplicating 
    @JsonProperty("answers")
    private final List<SurveyResponseOutputDTO> answers;

    public SurveyResponsesOutputDTO(UUID surveyId, List<SurveyQuestionOutputDTO> questions, List<SurveyResponseOutputDTO> answers) {
        this.surveyId = surveyId;
        this.questions = questions != null ? List.copyOf(questions): questions; // No questions in this DTO
        this.answers = answers != null ? List.copyOf(answers) : answers; // Immutable copy of the list
    }

}
