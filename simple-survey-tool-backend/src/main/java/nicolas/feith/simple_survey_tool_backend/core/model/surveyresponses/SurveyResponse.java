package nicolas.feith.simple_survey_tool_backend.core.model.surveyresponses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyBooleanQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyMultiSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyRatingQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveySingleSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyTextQuestion;

public class SurveyResponse {
    // Only class with blatant violation of clean code in App. Used for simplicity
    // since SurveyResponse will only ever be a read-only object
    private Survey fromSurvey;
    private LocalDateTime answeredAt;
    private Map<UUID, Object> questionAnswers;

    public SurveyResponse(Survey fromSurvey, LocalDateTime answeredAt, Map<UUID, Object> questionAnswers) {
        this.fromSurvey = Objects.requireNonNull(fromSurvey, "FromSurvey cannot be null");
        if (answeredAt == null) {
            this.answeredAt = LocalDateTime.now(); // Set to current time if null
        } else {
            this.answeredAt = answeredAt;
        }
        Objects.requireNonNull(questionAnswers, "QuestionAnswers cannot be null");
        this.questionAnswers = new HashMap<UUID, Object>(); // Create a new map to avoid external modifications

        // Validate questions
        for (SurveyQuestion question : fromSurvey.getQuestions()) {
            if (question instanceof SurveyTextQuestion) {
                Object questionAnswer = questionAnswers.get(question.getId());
                String wellTypedQuestionAnswer = (questionAnswer instanceof String) ? (String) questionAnswer : null;
                Object validatedAnswer = ((SurveyTextQuestion) question).validateAnswer(wellTypedQuestionAnswer);
                this.questionAnswers.put(question.getId(), validatedAnswer);
            } 
            else if (question instanceof SurveyBooleanQuestion) {
                Object questionAnswer = questionAnswers.get(question.getId());
                Boolean wellTypedQuestionAnswer = (questionAnswer instanceof Boolean) ? (Boolean) questionAnswer : null; 
                Object validatedAnswer = ((SurveyBooleanQuestion) question).validateAnswer(wellTypedQuestionAnswer);
                this.questionAnswers.put(question.getId(), validatedAnswer);
            } 
            else if (question instanceof SurveySingleSelectQuestion) {
                Object questionAnswer = questionAnswers.get(question.getId());
                String wellTypedQuestionAnswer = (questionAnswer instanceof String) ? (String) questionAnswer : null; 
                Object validatedAnswer = ((SurveySingleSelectQuestion) question)
                        .validateAnswer(wellTypedQuestionAnswer);
                this.questionAnswers.put(question.getId(), validatedAnswer);
            } 
            else if (question instanceof SurveyMultiSelectQuestion) {
                Object questionAnswer = questionAnswers.get(question.getId());
                List<String> wellTypedQuestionAnswer = null;

                if (questionAnswer instanceof List<?>) {
                    List<?> rawList = (List<?>) questionAnswer;
                    // Check if we can safely cast all elements to String
                    boolean allStrings = true;
                    for (Object item : rawList) {
                        if (!(item instanceof String)) {
                            allStrings = false;
                            break;
                        }
                    }

                    if (allStrings && !rawList.isEmpty()) {
                        wellTypedQuestionAnswer = new ArrayList<>();
                        for (Object item : rawList) {
                            wellTypedQuestionAnswer.add((String) item);
                        }
                    }
                }
                
                Object validatedAnswer = ((SurveyMultiSelectQuestion) question).validateAnswer(wellTypedQuestionAnswer);
                this.questionAnswers.put(question.getId(), validatedAnswer);
            } 
            else if (question instanceof SurveyRatingQuestion) {
                Object questionAnswer = questionAnswers.get(question.getId());
                Integer wellTypedQuestionAnswer = (questionAnswer instanceof Integer) ? (Integer) questionAnswer : null; 
                Object validatedAnswer = ((SurveyRatingQuestion) question)
                        .validateAnswer(wellTypedQuestionAnswer);
                this.questionAnswers.put(question.getId(), validatedAnswer);
            }
        }

    }

    public Survey getFromSurvey() {
        return fromSurvey;
    }

    public UUID getFromSurveyId() { // Avoids giving the whole survey object anf helps with law of demeter
        return fromSurvey.getId();
    }

    public List<SurveyQuestion> getQuestions() {
        return fromSurvey.getQuestions();
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public Map<UUID, Object> getQuestionAnswers() {
        return questionAnswers;
    }

}
