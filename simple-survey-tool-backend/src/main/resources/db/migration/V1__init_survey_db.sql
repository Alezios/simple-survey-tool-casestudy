CREATE TABLE survey_questions (
    id VARCHAR(36) NOT NULL,
    question_type VARCHAR(31) NOT NULL,
    question_text TEXT NOT NULL,
    is_required BOOLEAN DEFAULT FALSE,
    question_type ENUM('TEXT', 'BOOLEAN', 'RATING', 'SINGLE_SELECT', 'MULTI_SELECT') NOT NULL,
    
    -- Fields for TextQuestionEntity
    default_text TEXT,
    is_long_text BOOLEAN,
    
    -- Fields for BooleanQuestionEntity
    default_value BOOLEAN,
    
    -- Fields for RatingQuestionEntity
    min_rating INT,
    max_rating INT,
    min_rating_label TEXT,
    max_rating_label TEXT,
    default_rating INT,
    
    -- Fields for SingleSelectQuestionEntity and MultiSelectQuestionEntity
    options TEXT,
    default_option TEXT,
    
    PRIMARY KEY (id)
);

CREATE TABLE surveys (
    id VARCHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE survey_question_mapping (
    survey_id VARCHAR(36) NOT NULL,
    question_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (survey_id, question_id),
    FOREIGN KEY (survey_id) REFERENCES surveys(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES survey_questions(id) ON DELETE CASCADE
);

CREATE TABLE survey_responses (
    id BIGINT AUTO_INCREMENT,
    survey_id VARCHAR(36) NOT NULL,
    answered_at DATETIME NOT NULL,
    answer_value TEXT,
    CONSTRAINT fk_survey_responses_survey FOREIGN KEY (survey_id) REFERENCES surveys(id)
    PRIMARY KEY (id)
);