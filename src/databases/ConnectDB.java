package databases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by mrahman on 8/20/17.
 */
public class ConnectDB {

    public static Connection connect = null;
    public CallableStatement callableStatement = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    List<String> list = new ArrayList<String>();
    String dbName = "PNT";

    public static Properties loadPropertiesFile()throws IOException, SQLException, ClassNotFoundException{
        Properties prop = new Properties();
        InputStream ism = new FileInputStream("/Users/mrahman/develop/vp/automation/ProjectDBSeleniumJUly2017/src/secret.properties");
        prop.load(ism);
        ism.close();
        return prop;
    }

    public static Connection dbConnect() throws IOException, SQLException, ClassNotFoundException{
        Properties prop = loadPropertiesFile();
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String userName = prop.getProperty("MYSQLJDBC.userName");
        String passWord = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url, userName, passWord);
        System.out.println("Database connected");
        return connect;

    }


    public static List<StudentProfile> findStudentProfile(){
        List<StudentProfile> listOfProfile = new ArrayList<StudentProfile>();
        StudentProfile  studentProfile = null;
        try {
            connect = dbConnect();
            statement = connect.createStatement();
            String query = "SELECT * FROM Students";
            resultSet = statement.executeQuery(query);
            listOfProfile = getResultSetDataForStudentProfile(resultSet);
            if(connect != null){
                connect = null;
            }
        }catch(Exception ex){
            //close();
        }
        return listOfProfile;
    }
    private static List<StudentProfile> getResultSetDataForStudentProfile(ResultSet resultSet) throws SQLException {
        List<StudentProfile> listOfProfile = new ArrayList<StudentProfile>();
        StudentProfile studentProfile = null;
        while (resultSet.next()) {
            String stName = resultSet.getString("stName");
            String stID = resultSet.getString("stID");
            String stDOB = resultSet.getString("stDOB");
            studentProfile = new StudentProfile(stName,stID,stDOB);
            listOfProfile.add(studentProfile);
        }
        return listOfProfile;
    }

    public static void main(String[] args)throws IOException, SQLException, ClassNotFoundException {

        List<StudentProfile> profile = new ArrayList<StudentProfile>();
        profile = findStudentProfile();
        for(StudentProfile sp : profile){
            System.out.println(sp.getStName()+ " "+ sp.getStID()+" "+ sp.getStDOB());
        }

    }

}
