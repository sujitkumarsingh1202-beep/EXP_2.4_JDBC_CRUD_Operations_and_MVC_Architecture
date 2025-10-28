package controller;
import java.sql.*;
import java.util.*;

import model.Student;

public class StudentDAO {
	 
	
	 
	String url="jdbc:mysql://localhost:3306/student_management";
	String user="root";
	String pass="Sujit1202@";

	    public StudentDAO() {
	        try (Connection con = getConnection()) {
	            Statement stmt = con.createStatement();
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Student (" +
	                    "studentID INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "name VARCHAR(50), " +
	                    "department VARCHAR(50), " +
	                    "marks DOUBLE)");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url,user,pass);
	    }

	    public void addStudent(Student s) {
	        String sql = "INSERT INTO Student(name, department, marks) VALUES (?, ?, ?)";
	        try (Connection con = getConnection();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, s.getName());
	            pst.setString(2, s.getDepartment());
	            pst.setDouble(3, s.getMarks());
	            pst.executeUpdate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    public List<Student> getAllStudents() {
	        List<Student> list = new ArrayList<>();
	        String sql = "SELECT * FROM Student";
	        try (Connection con = getConnection();
	             PreparedStatement pst = con.prepareStatement(sql);
	             ResultSet rs = pst.executeQuery()) {
	            while (rs.next()) {
	                Student s = new Student(
	                        rs.getInt("studentID"),
	                        rs.getString("name"),
	                        rs.getString("department"),
	                        rs.getDouble("marks")
	                );
	                list.add(s);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return list;
	    }

	    public void updateStudent(Student s) {
	        String sql = "UPDATE Student SET name=?, department=?, marks=? WHERE studentID=?";
	        try (Connection con = getConnection();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, s.getName());
	            pst.setString(2, s.getDepartment());
	            pst.setDouble(3, s.getMarks());
	            pst.setInt(4, s.getStudentID());
	            pst.executeUpdate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    public void deleteStudent(int studentID) {
	        String sql = "DELETE FROM Student WHERE studentID=?";
	        try (Connection con = getConnection();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setInt(1, studentID);
	            pst.executeUpdate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	

}
