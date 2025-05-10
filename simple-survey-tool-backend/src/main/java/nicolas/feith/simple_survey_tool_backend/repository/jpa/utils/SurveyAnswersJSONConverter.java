package nicolas.feith.simple_survey_tool_backend.repository.jpa.utils;

import java.util.Map;
import java.util.UUID;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SurveyAnswersJSONConverter implements AttributeConverter<Map<UUID,Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<UUID, Object> attribute) {
        if (attribute == null) {
            return null;
        }
        
        return JsonHelper.toJson(attribute);
    }

    @Override
    public Map<UUID, Object> convertToEntityAttribute(String dbData) {
        return JsonHelper.jsonToAnswersMap(dbData);
    }
    
}
