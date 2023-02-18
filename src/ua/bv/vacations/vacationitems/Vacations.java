package ua.bv.vacations.vacationitems;

import ua.bv.vacations.mysql.MysqlConnector;

import java.sql.*;

import java.util.ArrayList;
/** Makes possible working with an array of vacations
 * @author Trevertor
 */

public class Vacations {
    private MysqlConnector mysqlc = new MysqlConnector();
    private String dbName = "vacations_db";
    private String dbUser = "mysql";
    private String dbPassword = "mysql";

    /** Get vacation list from database with standart sorting
     * @return A string array of vacations
     */
    public String[][] getVacations() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ResultSet rs=stmt.executeQuery("select * from vacations;");
        ResultSet rs=stmt.executeQuery("select v.id,v.name,v.descr,v.type_id,t.name,v.transport,v.days,v.food,v.start_price " +
                                            "from vacations as v join types as t on (v.type_id = t.id);");
        int rowCount = 0;
        while(rs.next()){
            rowCount++;
        }

        String[][] vacs = new String[rowCount][9];
        rs.first();
        for(int i = 0;i<rowCount;i++) {
            for(int j = 0;j<9;j++){
                vacs[i][j] = rs.getString(j+1);
            }
            if(!rs.next()){
                break;
            }
        }

        con.close();

        return vacs;
    }

    public String[][] getVacations(String orderBy) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String query;
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        query = "select v.id,v.name,v.descr,v.type_id,t.name,v.transport,v.days,v.food,v.start_price " +
                "from vacations as v join types as t on (v.type_id = t.id)";
        if(orderBy=="price")
            query += " order by start_price asc";
        ResultSet rs=stmt.executeQuery(query);
        int rowCount = 0;
        while(rs.next()){
            rowCount++;
        }

        String[][] vacs = new String[rowCount][9];
        rs.first();
        for(int i = 0;i<rowCount;i++) {
            for(int j = 0;j<9;j++){
                vacs[i][j] = rs.getString(j+1);
            }
            if(!rs.next()){
                break;
            }
        }

        con.close();
        return vacs;
    }

    public String[][] getBookings() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ResultSet rs=stmt.executeQuery("select * from vacations;");
        ResultSet rs=stmt.executeQuery("select b.id,v.name,b.transport,b.days,b.food from bookings as b join vacations" +
                " as v on (b.vacation_id = v.id) where b.user_id = 122333;");
        int rowCount = 0;
        while(rs.next()){
            rowCount++;
        }

        String[][] vacs = new String[rowCount][5];
        rs.first();
        for(int i = 0;i<rowCount;i++) {
            for(int j = 0;j<5;j++){
                vacs[i][j] = rs.getString(j+1);
            }
            if(!rs.next()){
                break;
            }
        }

        con.close();
        return vacs;
    }

    public String[] getTransportArray(String transp){
        String[] trAr = transp.split(",");
        //String[] trAr = new String[]
        return trAr;
    }

    public String[] getDaysArray(String days){
        String[] dAr = days.split(",");
        //String[] trAr = new String[]
        return dAr;
    }

    public void makeBooking(String vacId, String transp, String days, byte food) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.execute("insert into bookings (user_id,vacation_id,transport,days,food)" +
                "values (122333," + vacId + ",'" + transp + "','" + days + "'," + food + ");");
        con.close();
    }

    public void deleteBooking(String bookId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.execute("delete from bookings where id = " + bookId +";");
        con.close();
    }
}
