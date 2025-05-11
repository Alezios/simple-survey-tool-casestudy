package nicolas.feith.simple_survey_tool_backend.repository.jpa.utils;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UUIDStringConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(final UUID entityValue) {
        return Optional.ofNullable(entityValue)
                .map(entityUuid -> entityUuid.toString())
                .orElse(null);
    }

    @Override
    public UUID convertToEntityAttribute(final String databaseValue) {
        return Optional.ofNullable(databaseValue)
                .map(databaseUuid -> UUID.fromString(databaseUuid))
                .orElse(null);
    }
}