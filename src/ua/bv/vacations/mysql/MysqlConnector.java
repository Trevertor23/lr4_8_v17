package ua.bv.vacations.mysql;
import java.sql.*;

/** A tool to make the usage of MySQL easier
 * @author Trevertor
 * @version 1.0
 */

public class MysqlConnector {
    private String dbName = "vacations_db";
    private String dbUser = "mysql";
    private String dbPassword = "mysql";

    public void changeDB(String newName){
        this.dbName = newName;
    }

    public void changeUser(String login, String password){
        this.dbUser = login;
        this.dbPassword = password;
    }

    public ResultSet execQuery(String query){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            //while(rs.next())
            //   System.out.println(rs.getString(2));
            con.close();
            return rs;
        }catch(Exception e){ System.out.println(e);return null;}
    }
}
