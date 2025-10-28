package PRODUCT;
import java.sql.*;
import java.util.Scanner;
public class Product_rec {
	
public static void main(String[] args) {
	
	String url="jdbc:mysql://localhost:3306/PRODUCT_REC";
	String user="root";
	String pass="Sujit1202@";
	
    try (
        Connection con = DriverManager.getConnection(url,user,pass);
        Scanner sc = new Scanner(System.in)
    ) {
        // Table creation (if not exists)
        Statement stmt = con.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Product ("
                + "ProductID INT PRIMARY KEY AUTO_INCREMENT, "
                + "ProductName VARCHAR(50), "
                + "Price DOUBLE, "
                + "Quantity INT)");

        while (true) {
            System.out.println("\n1. Create Product");
            System.out.println("2. Read All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createProduct(con, sc);
                    break;
                case 2:
                    readProducts(con);
                    break;
                case 3:
                    updateProduct(con, sc);
                    break;
                case 4:
                    deleteProduct(con, sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

static void createProduct(Connection con, Scanner sc) {
    System.out.print("Enter Product Name: ");
    String name = sc.next();
    System.out.print("Enter Price: ");
    double price = sc.nextDouble();
    System.out.print("Enter Quantity: ");
    int qty = sc.nextInt();

    String sql = "INSERT INTO Product(ProductName, Price, Quantity) VALUES (?, ?, ?)";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, name);
        pst.setDouble(2, price);
        pst.setInt(3, qty);
        con.setAutoCommit(false); // Begin transaction
        pst.executeUpdate();
        con.commit();
        System.out.println("Product added successfully.");
    } catch (SQLException ex) {
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
    } finally {
        try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
    }
}

static void readProducts(Connection con) {
    String sql = "SELECT * FROM Product";
    try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        System.out.println("ProductID | ProductName | Price | Quantity");
        while (rs.next()) {
            System.out.printf("%-9d | %-11s | %-5.2f | %-8d\n",
                    rs.getInt("ProductID"), rs.getString("ProductName"),
                    rs.getDouble("Price"), rs.getInt("Quantity"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

static void updateProduct(Connection con, Scanner sc) {
    System.out.print("Enter Product ID to update: ");
    int id = sc.nextInt();
    System.out.print("Enter new Name: ");
    String name = sc.next();
    System.out.print("Enter new Price: ");
    double price = sc.nextDouble();
    System.out.print("Enter new Quantity: ");
    int qty = sc.nextInt();

    String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        con.setAutoCommit(false); // Begin transaction
        pst.setString(1, name);
        pst.setDouble(2, price);
        pst.setInt(3, qty);
        pst.setInt(4, id);
        int rows = pst.executeUpdate();
        if (rows > 0) {
            con.commit();
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product ID not found.");
            con.rollback();
        }
    } catch (SQLException ex) {
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
    } finally {
        try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
    }
}

static void deleteProduct(Connection con, Scanner sc) {
    System.out.print("Enter Product ID to delete: ");
    int id = sc.nextInt();
    String sql = "DELETE FROM Product WHERE ProductID=?";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        con.setAutoCommit(false); // Begin transaction
        pst.setInt(1, id);
        int rows = pst.executeUpdate();
        if (rows > 0) {
            con.commit();
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product ID not found.");
	                con.rollback();
	            }
	        } catch (SQLException ex) {
	            try {
	                con.rollback();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            ex.printStackTrace();
	        } finally {
	            try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
	        }
	    
	}

}
