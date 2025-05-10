package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.Objects;
import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.constants.SurveyQuestionType;
import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;

public class SurveyRatingQuestion extends SurveyQuestion {
    private int minRating;
    private int maxRating;
    private String minRatingLabel;
    private String maxRatingLabel;
    private Integer defaultSelectedRating; // Integer to allow null values

    public SurveyRatingQuestion(UUID id, String questionText, boolean isRequired, int minRating, int maxRating, String minRatingLabel, String maxRatingLabel, Integer defaultSelectedRating) {
        super(id, questionText, isRequired);
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.minRatingLabel = Objects.requireNonNull(minRatingLabel, "MinRatingLabel cannot be null");
        this.maxRatingLabel = Objects.requireNonNull(maxRatingLabel, "MaxRatingLabel cannot be null");
        this.defaultSelectedRating = validateSelectedRating(defaultSelectedRating);
    }

    public int getMinRating() {
        return minRating;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public String getMinRatingLabel() {
        return minRatingLabel;
    }
    
    public String getMaxRatingLabel() {
        return maxRatingLabel;
    }

    public Integer getDefaultSelectedRating() {
        return defaultSelectedRating;
    }

    public Integer validateAnswer(Integer answer) {
        if (this.isRequired() && answer == null) {
            throw new IllegalArgumentException("Answer is required.");
        }
        return validateSelectedRating(answer);
    }

    @Override
    public <T> T accept(SurveyQuestionVisitor<T> visitor) {
        return visitor.visitRatingQuestion(this);
    }

    @Override
    public SurveyQuestionType getType() {
        return SurveyQuestionType.RATING;
    }

    private Integer validateSelectedRating(Integer selectedRating) {
        if (selectedRating != null && (selectedRating < minRating || selectedRating > maxRating)) {
            throw new IllegalArgumentException("Selected rating must be between min and max rating");
        }
        return selectedRating;
    }
}
