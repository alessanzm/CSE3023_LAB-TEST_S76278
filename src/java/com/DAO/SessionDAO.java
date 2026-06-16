/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Model.SessionBean;

/**
 *
 * @author MP2-4
 */
public class SessionDAO {
    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/drivesmart_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
        private static final String INSERT_SESSION_SQL = "INSERT INTO Training_session (student_name, branch_location, lesson_type, status) VALUES (?, ?, ?, ?);";
        private static final String GET_SESSION_BY_ID = "select seesion_id, student_name, branch_location, lesson_type, status from Training_Session where id =?";
        private static final String GET_ALL_SESSION = "select * from Training_Session";

    
    public SessionDAO(){}
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,
            jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
        }

    
    public boolean bookSession(SessionBean sessionbean) throws SQLException{
        System.out.println(INSERT_SESSION_SQL);
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SESSION_SQL)){
                    preparedStatement.setString(1, sessionbean.getStudent_name());
                    preparedStatement.setString(2, sessionbean.getBranch_location());
                    preparedStatement.setString(3, sessionbean.getLesson_type());
                    preparedStatement.setString(4, sessionbean.getStatus());
                    System.out.println(preparedStatement);
                    return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            printSQLException(e);
        }
        return false;
    }
    
    public SessionBean getSession(int session_id) {
        SessionBean sessionbean = null;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.
                prepareStatement(GET_SESSION_BY_ID);) {
                preparedStatement.setInt(1, session_id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String student_name = rs.getString("student_name");
                    String branch_location = rs.getString("branch_location");
                    String lesson_type = rs.getString("lesson_type");
                    String status = rs.getString("status");
                    sessionbean = new SessionBean(session_id, student_name, branch_location, lesson_type, status);
                }
        }catch (SQLException e){
            printSQLException(e);
        }
        return sessionbean;
    }
    
    public List < SessionBean > getAllSessions() {
        List < SessionBean > Training_Session = new ArrayList < > ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.
            prepareStatement(GET_ALL_SESSION);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int session_id = rs.getInt("session_id");
                String student_name = rs.getString("student_name");
                String branch_location = rs.getString("branch_location");
                String lesson_type = rs.getString("lesson_type");
                String status = rs.getString("status");
                Training_Session.add(new SessionBean(session_id, student_name, branch_location, lesson_type, status));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
            return Training_Session;
        }


    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException)
                e).getSQLState());
                System.err.println("Error Code: " + ((SQLException)
                e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
                }
            }
        }
    }

    public boolean bookSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

    
                        
                
    
