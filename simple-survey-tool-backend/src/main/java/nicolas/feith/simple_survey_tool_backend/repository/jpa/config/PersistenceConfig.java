package nicolas.feith.simple_survey_tool_backend.repository.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "nicolas.feith.simple_survey_tool_backend.repository.jpa.entities")
@EnableJpaRepositories(basePackages = "nicolas.feith.simple_survey_tool_backend.repository.jpa")
public class PersistenceConfig {
    
}