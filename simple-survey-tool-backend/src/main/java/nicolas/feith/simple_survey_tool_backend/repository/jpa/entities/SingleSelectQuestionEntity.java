package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import nicolas.feith.simple_survey_tool_backend.core.constants.SurveyQuestionType;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveySingleSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.utils.StringListJSONConverter;

@Entity
@DiscriminatorValue("SINGLE_SELECT")
public class SingleSelectQuestionEntity extends SurveyQuestionEntity {

    @Column(name = "options")
    @Convert(converter = StringListJSONConverter.class)
    private List<String> options = new ArrayList<>();
    
    @Column(name = "default_option")
    private String defaultSelectedOption;

    public SingleSelectQuestionEntity() {
        // Required by JPA
    }
    
    public SingleSelectQuestionEntity(UUID id, String questionText, boolean isRequired, 
            List<String> options, String defaultSelectedOption) {
        super(id, questionText, isRequired);
        if (options != null) {
            this.options.addAll(options);
        }
        this.defaultSelectedOption = defaultSelectedOption;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getDefaultSelectedOption() {
        return defaultSelectedOption;
    }

    public void setDefaultSelectedOption(String defaultSelectedOption) {
        this.defaultSelectedOption = defaultSelectedOption;
    }

    @Override
    public SurveyQuestionType getQuestionType() {
        return SurveyQuestionType.SINGLE_CHOICE;
    }

    @Override
    public SurveyQuestion toSurveyQuestion() {
        return new SurveySingleSelectQuestion(
                this.getId(), this.getQuestionText(), this.isRequired(),
                this.getOptions(), this.getDefaultSelectedOption()
            );
    }
}