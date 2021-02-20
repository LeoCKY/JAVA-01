import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(SchoolProperties.class)
public class SchoolAutoConfiguration {

    @Autowired
    SchoolProperties schoolProperties;

    @Bean
    public SchoolService schoolService() {
        SchoolService schoolService = new SchoolService();
        schoolService.setSchoolProperties(schoolProperties);
        return schoolService;
    }

}
