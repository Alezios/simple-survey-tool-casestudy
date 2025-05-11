package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyMultiSelectQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("options")
    private final List<String> options;
    @JsonProperty("selectedOptions")
    private final List<String> selectedOptions;

    public SurveyMultiSelectQuestionOutputDTO(UUID id, String questionText, boolean isRequired, String type, 
                                              List<String> options, List<String> selectedOptions) {
        super(id, questionText, type, isRequired);
        this.options = options != null ? List.copyOf(options) : options; // Immutable copy of the list
        this.selectedOptions = options != null ? List.copyOf(selectedOptions) : options; // Immutable copy of the list
    }
}