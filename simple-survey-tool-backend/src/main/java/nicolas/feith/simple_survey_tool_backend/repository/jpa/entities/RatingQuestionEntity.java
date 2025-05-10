package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import nicolas.feith.simple_survey_tool_backend.core.constants.SurveyQuestionType;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyRatingQuestion;

@Entity
@DiscriminatorValue("RATING")
public class RatingQuestionEntity extends SurveyQuestionEntity {

    @Column(name = "min_rating")
    private int minRating;
    
    @Column(name = "max_rating")
    private int maxRating;
    
    @Column(name = "min_rating_label", nullable = false)
    private String minRatingLabel;
    
    @Column(name = "max_rating_label", nullable = false)
    private String maxRatingLabel;
    
    @Column(name = "default_rating")
    private Integer defaultSelectedRating;

    public RatingQuestionEntity() {
        // Required by JPA
    }
    
    public RatingQuestionEntity(UUID id, String questionText, boolean isRequired, 
            int minRating, int maxRating, String minRatingLabel, String maxRatingLabel, 
            Integer defaultSelectedRating) {
        super(id, questionText, isRequired);
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.minRatingLabel = minRatingLabel;
        this.maxRatingLabel = maxRatingLabel;
        this.defaultSelectedRating = defaultSelectedRating;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }

    public String getMinRatingLabel() {
        return minRatingLabel;
    }

    public void setMinRatingLabel(String minRatingLabel) {
        this.minRatingLabel = minRatingLabel;
    }

    public String getMaxRatingLabel() {
        return maxRatingLabel;
    }

    public void setMaxRatingLabel(String maxRatingLabel) {
        this.maxRatingLabel = maxRatingLabel;
    }

    public Integer getDefaultSelectedRating() {
        return defaultSelectedRating;
    }

    public void setDefaultSelectedRating(Integer defaultSelectedRating) {
        this.defaultSelectedRating = defaultSelectedRating;
    }

    @Override
    public SurveyQuestionType getQuestionType() {
        return SurveyQuestionType.RATING;
    }

    @Override
    public SurveyQuestion toSurveyQuestion() {
        return new SurveyRatingQuestion(
                this.getId(), this.getQuestionText(), this.isRequired(),
                this.getMinRating(),
                this.getMaxRating(),
                this.getMinRatingLabel(),
                this.getMaxRatingLabel(),
                this.getDefaultSelectedRating()
            );
    }
}