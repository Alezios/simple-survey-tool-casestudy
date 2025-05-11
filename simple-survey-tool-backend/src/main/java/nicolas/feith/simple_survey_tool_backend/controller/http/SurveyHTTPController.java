package nicolas.feith.simple_survey_tool_backend.controller.http;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nicolas.feith.simple_survey_tool_backend.controller.http.utils.SurveyDTOMapper;
import nicolas.feith.simple_survey_tool_backend.core.services.survey.SurveyService;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.input.SurveyResponseInputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveyresponses.SurveyResponsesOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyOutputDTO;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "*")
public class SurveyHTTPController {

    private static final Logger LOGGER = getLogger(SurveyHTTPController.class);
    
    private final SurveyService surveyService;
    
    public SurveyHTTPController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    
    @GetMapping
    public ResponseEntity<List<SurveyOutputDTO>> getAllSurveys() {
        LOGGER.info("Getting all surveys");
        
        final var surveys = surveyService.getAllSurveys();
        final var surveysDTO = SurveyDTOMapper.toSurveysOutput(surveys);
        return ResponseEntity.ok(surveysDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SurveyOutputDTO> getSurveyById(@PathVariable("id") UUID id) { // change return type to a surveyResponseDTO
        LOGGER.info("Getting survey with id: {}", id);

        final var survey = surveyService.getSurveyById(id);
        final var surveyDTO = SurveyDTOMapper.toSurveyOutput(survey);
        return ResponseEntity.ok(surveyDTO);
    }
    
    
    @GetMapping("/{id}/responses")
    public ResponseEntity<SurveyResponsesOutputDTO> getSurveyResponses(@PathVariable("id") UUID id) {
        LOGGER.info("Getting survey responses of survey with id: {}", id);
        
        final var survey = surveyService.getSurveyById(id);
        final var surveyResponses = surveyService.getSurveyResponses(id);
        final var surveyReponsesDTO = SurveyDTOMapper.toSurveyResponsesOutput(survey, surveyResponses);
        return ResponseEntity.ok(surveyReponsesDTO);
    }
    
    @PostMapping("/{id}/response")
    public ResponseEntity<Void> submitSurveyResponse(@PathVariable("id") UUID id, 
    @RequestBody SurveyResponseInputDTO responseInputDTO) {
        LOGGER.info("Submitting response for survey with id: {}", id);
        
        surveyService.addSurveyResponse(responseInputDTO.getSurveyId(), responseInputDTO.getAnswers());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}