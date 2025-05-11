package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;

public class SurveySingleSelectQuestion extends SurveyQuestion {
    private List<String> options;
    private String defaultSelectedOption;

    public SurveySingleSelectQuestion(UUID id, String questionText, boolean isRequired, List<String> options, String defaultSelectedOption) {
        super(id, questionText, isRequired);
        this.options = Objects.requireNonNull(options, "Options cannot be null");
        this.defaultSelectedOption = validateSelectedOption(defaultSelectedOption);
    }

    public List<String> getOptions() {
        return options;
    }

    public String getDefaultSelectedOption() {
        return defaultSelectedOption;
    }

    public String validateAnswer(String answer) {
        if (this.isRequired() && answer == null) {
            throw new IllegalArgumentException("Answer is required.");
        }
        
        return validateSelectedOption(answer);
    }

    @Override
    public <T> T accept(SurveyQuestionVisitor<T> visitor) {
        return visitor.visitSingleSelectQuestion(this);
    }

    private String validateSelectedOption(String selectedOption) {
        if (selectedOption != null && !options.contains(selectedOption)) {
            throw new IllegalArgumentException("Selected option " + selectedOption + " is not in the available options.");
        }
        return selectedOption;
    }
    
}
