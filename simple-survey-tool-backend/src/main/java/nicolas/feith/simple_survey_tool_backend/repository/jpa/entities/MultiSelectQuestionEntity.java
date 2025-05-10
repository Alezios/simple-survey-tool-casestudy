package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import nicolas.feith.simple_survey_tool_backend.core.constants.SurveyQuestionType;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyMultiSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.utils.StringListJSONConverter;

@Entity
@DiscriminatorValue("MULTI_SELECT")
public class MultiSelectQuestionEntity extends SurveyQuestionEntity {

    @Column(name = "options")
    @Convert(converter = StringListJSONConverter.class)
    private List<String> options = new ArrayList<>();
    
    @Column(name = "default_option")
    @Convert(converter = StringListJSONConverter.class)
    private List<String> defaultSelectedOptions = new ArrayList<>();

    public MultiSelectQuestionEntity() {
        // Required by JPA
    }
    
    public MultiSelectQuestionEntity(UUID id, String questionText, boolean isRequired, 
            List<String> options, List<String> defaultSelectedOptions) {
        super(id, questionText, isRequired);
        if (options != null) {
            this.options.addAll(options);
        }
        if (defaultSelectedOptions != null) {
            this.defaultSelectedOptions.addAll(defaultSelectedOptions);
        }
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getDefaultSelectedOptions() {
        return defaultSelectedOptions;
    }

    public void setDefaultSelectedOptions(List<String> defaultSelectedOptions) {
        this.defaultSelectedOptions = defaultSelectedOptions;
    }

    @Override
    public SurveyQuestionType getQuestionType() {
        return SurveyQuestionType.MULTIPLE_CHOICE;
    }

    @Override
    public SurveyQuestion toSurveyQuestion() {
        return new SurveyMultiSelectQuestion(
                this.getId(), this.getQuestionText(), this.isRequired(),
                this.getOptions(),
                this.getDefaultSelectedOptions()
            );
    }
}