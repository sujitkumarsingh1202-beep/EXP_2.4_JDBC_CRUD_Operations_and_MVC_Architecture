import java.sql.*;
public class fetch_data {
	public static void main(String[]args) {
		
		String url="jdbc:mysql://localhost:3306/employee_details";
		String user="root";
		String pass="Sujit1202@";
		
		try(Connection conn=DriverManager.getConnection(url, user, pass);){
			
			Statement smt= conn.createStatement();
			
			ResultSet rs= smt.executeQuery("SELECT * FROM Employee");
			while(rs.next()) {
				int id=rs.getInt("emp_id");
				String name= rs.getString("name");
				double salary=rs.getDouble("salary");
				
				System.out.println("ID: "+id+", name: "+name+"-> salary: "+salary);
		
			}
		}catch(SQLException e) {
			System.out.println(e);
			
		}
	}

}


