package databases;

/**
 * Created by mrahman on 8/20/17.
 */
public class StudentProfile {

    String stName;
    String stID;
    String stDOB;

    public StudentProfile(){}

    public StudentProfile(String stName, String stID, String stDOB) {
        this.stName = stName;
        this.stID = stID;
        this.stDOB = stDOB;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStDOB() {
        return stDOB;
    }

    public void setStDOB(String stDOB) {
        this.stDOB = stDOB;
    }
}
