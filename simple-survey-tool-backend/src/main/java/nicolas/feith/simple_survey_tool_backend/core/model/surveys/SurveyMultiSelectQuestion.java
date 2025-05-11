package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;

public class SurveyMultiSelectQuestion extends SurveyQuestion {
    private List<String> options;
    private List<String> defaultSelectedOptions;

    public SurveyMultiSelectQuestion(UUID id, String questionText, boolean isRequired, List<String> options, List<String> defaultSelectedOptions) {
        super(id, questionText, isRequired);
        this.options = Objects.requireNonNull(options, "Options cannot be null");
        this.defaultSelectedOptions = validateSelectedOptions(defaultSelectedOptions);
    }

    public List<String> getOptions() {
        return options;
    }

    public List<String> getDefaultSelectedOptions() {
        return defaultSelectedOptions;
    }

    public List<String> validateAnswer(List<String> answer) {
        if (answer == null || answer.isEmpty()) {
            if (isRequired()) {
                throw new IllegalArgumentException("Answer is required.");
            }
            return null; // No answer provided, and it's not required
        }
        return validateSelectedOptions(answer);
    }

    @Override
    public <T> T accept(SurveyQuestionVisitor<T> visitor) {
        return visitor.visitMultipleSelectQuestion(this);
    }
    
    private List<String> validateSelectedOptions(List<String> selectedOptions) {
        if (selectedOptions != null) {
            if (selectedOptions.size() > options.size()) {
                throw new IllegalArgumentException("Selected options cannot exceed available options.");
            }  
            for (String selectedOption : selectedOptions) {
                if (!options.contains(selectedOption)) {
                    throw new IllegalArgumentException("Selected option " + selectedOption + " is not in the available options.");
                }
            }
        } 
        return selectedOptions;
    }
}
