package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.constants.SurveyQuestionDTOType;

public class SurveyRatingQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("rateMin")
    private final int minRating;
    @JsonProperty("rateMax")
    private final int maxRating;
    @JsonProperty("minRateDescription")
    private final String minRatingLabel;
    @JsonProperty("maxRateDescription")
    private final String maxRatingLabel;
    @JsonProperty("selectedRating")
    private final Integer selectedRating;

    public SurveyRatingQuestionOutputDTO(UUID id, String questionText, boolean isRequired, SurveyQuestionDTOType type, 
                                        int minRating, int maxRating, String minRatingLabel, 
                                        String maxRatingLabel, Integer selectedRating) {
        super(id, questionText, type, isRequired);
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.minRatingLabel = minRatingLabel;
        this.maxRatingLabel = maxRatingLabel;
        this.selectedRating = selectedRating;
    }
    
}
