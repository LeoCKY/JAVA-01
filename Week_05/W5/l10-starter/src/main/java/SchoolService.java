public class SchoolService {


    SchoolProperties schoolProperties;

    public SchoolProperties getSchoolProperties() {
        return schoolProperties;
    }

    public void setSchoolProperties(SchoolProperties schoolProperties) {
        this.schoolProperties = schoolProperties;
    }


    public String getSchoolName() {
        return schoolProperties.getSchoolName();
    }
}
