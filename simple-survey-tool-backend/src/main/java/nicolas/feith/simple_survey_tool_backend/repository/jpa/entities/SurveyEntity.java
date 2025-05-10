package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;

@Entity
@Table(name = "surveys")
public class SurveyEntity {

    @Id
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "survey_question_mapping",
        joinColumns = @JoinColumn(name = "survey_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<SurveyQuestionEntity> questions = new ArrayList<>();

    public SurveyEntity() {
        // Required by JPA
    }
    
    public SurveyEntity(UUID id, String title, String description, List<SurveyQuestionEntity> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        if (questions == null) {
            this.questions = questions;
        }
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SurveyQuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestionEntity> questions) {
        this.questions = questions;
    }

    public Survey toSurvey() {
        return new Survey(
            id,
            title,
            description,
            questions.stream()
                .map(SurveyQuestionEntity::toSurveyQuestion)
                .toList()
        );
    }   
}