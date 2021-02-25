import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "l10.school")
public class SchoolProperties {

    private String schoolName;


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
