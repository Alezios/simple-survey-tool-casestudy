-- Insert surveys
INSERT INTO surveys (id, title, description) VALUES 
('4c0a9d99-2d3f-4cab-a4a3-8c69154530e6', 'Team Performance Survey', 'Help us understand how our team is performing'),
('e7c7af4f-e5fc-4233-b9e8-6ec4304b62da', 'Project Feedback', 'Share your thoughts on our recent project'),
('5993d8e8-be24-4aa2-9567-f3e31a24dd93', 'Remote Work Environment Assessment', 'Help us understand how to improve our remote work experience');

-- Insert survey questions
INSERT INTO survey_questions (id, question_type, question_text, is_required, min_rating, max_rating, min_rating_label, max_rating_label) 
VALUES ('13fbf7bc-0ad1-4304-80d8-86ff53dffc5d', 'RATING', 'How satisfied are you with our team''s performance?', FALSE, 1, 5, 'Not Satisfied', 'Very Satisfied');

INSERT INTO survey_questions (id, question_type, question_text, is_required, min_rating, max_rating, min_rating_label, max_rating_label) 
VALUES ('143eaa39-1d90-40f8-a680-ea17a156e776', 'RATING', 'How would you rate our team''s communication?', FALSE, 1, 5, 'Poor', 'Excellent');

INSERT INTO survey_questions (id, question_type, question_text, is_required, is_long_text) 
VALUES ('856917c8-800f-4682-9527-a9cc3dedb030', 'TEXT', 'What could we improve on?', FALSE, TRUE);

INSERT INTO survey_questions (id, question_type, question_text, is_required, min_rating, max_rating, min_rating_label, max_rating_label) 
VALUES ('7ffb0c63-390d-458b-adf6-43fa9f687dd3', 'RATING', 'How effective is our team collaboration?', FALSE, 1, 5, 'Not Effective', 'Very Effective');

INSERT INTO survey_questions (id, question_type, question_text, is_required) 
VALUES ('3d19a3ac-8d1c-4188-87c5-9ccc052f41de', 'BOOLEAN', 'Is the workload distributed fairly among team members?', FALSE);

-- Project Feedback Questions
INSERT INTO survey_questions (id, question_type, question_text, is_required, min_rating, max_rating, min_rating_label, max_rating_label) 
VALUES ('bbd45df0-64b2-4a9f-b929-fc454e56d363', 'RATING', 'How would you rate the overall quality of the project?', FALSE, 1, 5, 'Poor', 'Excellent');

INSERT INTO survey_questions (id, question_type, question_text, is_required) 
VALUES ('f40fb982-2207-4ecc-a67e-d69cf71e088f', 'BOOLEAN', 'Was the project completed within a reasonable timeline?', FALSE);

INSERT INTO survey_questions (id, question_type, question_text, is_required, is_long_text) 
VALUES ('5b10485e-7c77-4daf-b1a7-6e1017035f56', 'TEXT', 'Please provide any additional feedback', FALSE, TRUE);

INSERT INTO survey_questions (id, question_type, question_text, is_required, min_rating, max_rating, min_rating_label, max_rating_label) 
VALUES ('125a1d04-a21f-4080-b2dc-89a9e0ac0105', 'RATING', 'How satisfied do you think the client is with the deliverables?', FALSE, 1, 5, 'Not Satisfied', 'Very Satisfied');

INSERT INTO survey_questions (id, question_type, question_text, is_required, is_long_text) 
VALUES ('096879ae-3d17-4837-89ab-90799b164eee', 'TEXT', 'What would you suggest for future development of this project?', FALSE, TRUE);

-- Remote Work Environment Assessment Questions
INSERT INTO survey_questions (id, question_type, question_text, is_required, options, default_option) 
VALUES ('8c4f94c2-4665-4fb8-8710-387830ce7434', 'SINGLE_SELECT', 'Where do you primarily work from?', TRUE, 
        '["Home office", "Coworking space", "Company office", "Café or public space"]', 'Home office');

INSERT INTO survey_questions (id, question_type, question_text, is_required, options) 
VALUES ('4501c588-f665-4c8d-9f36-fd3d05df1d05', 'MULTI_SELECT', 'Which remote collaboration tools do you use regularly?', TRUE,
        '["Zoom", "Microsoft Teams", "Slack", "Discord", "Notion", "Trello", "Asana"]');

INSERT INTO survey_questions (id, question_type, question_text, is_required, options) 
VALUES ('a2dec307-fbd7-4ce9-8f35-4f65d3968f16', 'MULTI_SELECT', 'What are the biggest challenges you face when working remotely?', FALSE,
        '["Communication difficulties", "Social isolation", "Work-life boundaries", "Technical issues", "Home distractions", "Collaboration challenges"]');

INSERT INTO survey_questions (id, question_type, question_text, is_required, min_rating, max_rating, min_rating_label, max_rating_label) 
VALUES ('174b541e-1555-4e4f-b2d8-75fea3bf8119', 'RATING', 'How would you rate your productivity when working remotely compared to in-office?', FALSE, 1, 5, 'Much Less Productive', 'Much More Productive');

INSERT INTO survey_questions (id, question_type, question_text, is_required, is_long_text) 
VALUES ('b353f5d0-16e4-4a4a-9a86-0e76a664c12c', 'TEXT', 'What specific improvements would help you be more effective in a remote work environment?', FALSE, TRUE);

-- Map questions to surveys
INSERT INTO survey_question_mapping (survey_id, question_id) VALUES 
('4c0a9d99-2d3f-4cab-a4a3-8c69154530e6', '13fbf7bc-0ad1-4304-80d8-86ff53dffc5d'), 
('4c0a9d99-2d3f-4cab-a4a3-8c69154530e6', '143eaa39-1d90-40f8-a680-ea17a156e776'), 
('4c0a9d99-2d3f-4cab-a4a3-8c69154530e6', '856917c8-800f-4682-9527-a9cc3dedb030'), 
('4c0a9d99-2d3f-4cab-a4a3-8c69154530e6', '7ffb0c63-390d-458b-adf6-43fa9f687dd3'), 
('4c0a9d99-2d3f-4cab-a4a3-8c69154530e6', '3d19a3ac-8d1c-4188-87c5-9ccc052f41de');

-- Project Feedback mapping
INSERT INTO survey_question_mapping (survey_id, question_id) VALUES 
('e7c7af4f-e5fc-4233-b9e8-6ec4304b62da', 'bbd45df0-64b2-4a9f-b929-fc454e56d363'), 
('e7c7af4f-e5fc-4233-b9e8-6ec4304b62da', 'f40fb982-2207-4ecc-a67e-d69cf71e088f'), 
('e7c7af4f-e5fc-4233-b9e8-6ec4304b62da', '5b10485e-7c77-4daf-b1a7-6e1017035f56'), 
('e7c7af4f-e5fc-4233-b9e8-6ec4304b62da', '125a1d04-a21f-4080-b2dc-89a9e0ac0105'), 
('e7c7af4f-e5fc-4233-b9e8-6ec4304b62da', '096879ae-3d17-4837-89ab-90799b164eee');

-- Remote Work Environment Assessment mapping
INSERT INTO survey_question_mapping (survey_id, question_id) VALUES 
('5993d8e8-be24-4aa2-9567-f3e31a24dd93', '8c4f94c2-4665-4fb8-8710-387830ce7434'), 
('5993d8e8-be24-4aa2-9567-f3e31a24dd93', '4501c588-f665-4c8d-9f36-fd3d05df1d05'), 
('5993d8e8-be24-4aa2-9567-f3e31a24dd93', 'a2dec307-fbd7-4ce9-8f35-4f65d3968f16'), 
('5993d8e8-be24-4aa2-9567-f3e31a24dd93', '174b541e-1555-4e4f-b2d8-75fea3bf8119'), 
('5993d8e8-be24-4aa2-9567-f3e31a24dd93', 'b353f5d0-16e4-4a4a-9a86-0e76a664c12c');