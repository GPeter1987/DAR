package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {
	
	private final String jdbc 	  = "jdbc:mysql://localhost/dar";
	private final String userName = "root";
	private final String password = "";
	
	Connection conn;
	
	public DataBase() {
		this.conn = null;
	}
	
	public void connectDB() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbc, userName, password);
			System.out.println("Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public void disconnectDB() throws SQLException {
		if(conn != null) {
			conn.close();
		}
	}

	public String getJdbc() {
		return jdbc;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public void createEvent(Event event) {
		String sql = "INSERT INTO event(Name, Type, Date, DojoID) VALUES(?,?,?,?)";
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getName());
			pstmt.setString(2, String.valueOf(event.getType()));
			pstmt.setString(3, String.valueOf(event.getDate()));
			pstmt.setInt(4, event.getDojoId());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Event saved!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Event searchEventById(int id) {
		String sql = "SELECT * FROM event WHERE ID = ?";
		Event event = null;
		
		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				event = new Event(rs.getInt("ID"), 
								  rs.getString("Name"), 
								  EventType.valueOf(rs.getString("Type")), 
								  rs.getDate("Date").toLocalDate(), 
								  rs.getInt("DojoID"));
			}
			disconnectDB();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return event;
	}
	
	public ArrayList<Event> searcAllEvent() {
		ArrayList<Event> events = new ArrayList<Event>();
		String sql = "SELECT * FROM event";
		
		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Event event = new Event(rs.getInt("ID"), 
									rs.getString("Name"), 
									EventType.valueOf(rs.getString("Type")), 
									rs.getDate("Date").toLocalDate(), 
									rs.getInt("DojoID"));
				events.add(event);
			}
			disconnectDB();
			
			return events;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createCustomer(Customer customer) {
		String sql = "INSERT INTO customer(Name, Status, Rank, DojoID, BDate, AccountID, Email, Passive) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, String.valueOf(customer.getStatus()));
			pstmt.setString(3, String.valueOf(customer.getRank()));
			
			if(customer.getStatus() == CustomerStatus.DOJO) {
				// If the customer is a dojo we set the dojoId attr to 0 becuse a dojo doesn't have a dojo
				pstmt.setInt(4, 0);
			} else {
				pstmt.setInt(4, customer.getDojoId());
			}
			
			pstmt.setString(5, String.valueOf(customer.getBirthDate()));
			pstmt.setInt(6, customer.getAccountId());
			pstmt.setString(7, customer.getEmail());
			pstmt.setBoolean(8, customer.getPassive());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Customer saved!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Customer searchCustomerById(int id) {
		String sql = "SELECT * FROM customer WHERE ID = ?";
		Customer customer = null;
		
		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				customer = new Customer(rs.getInt("ID"), 
										rs.getString("Name"), 
										CustomerStatus.valueOf(rs.getString("Status")), 
										CustomerRank.valueOf(rs.getString("Rank")),
										rs.getInt("DojoID"),
										rs.getDate("BDate").toLocalDate(), 
										rs.getInt("AccountID"),
										rs.getString("Email"),
										rs.getBoolean("Passive"));
			}
			disconnectDB();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public ArrayList<Customer> searcAllCustomer() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql = "SELECT * FROM customer";
		
		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), 
									rs.getString("Name"), 
									CustomerStatus.valueOf(rs.getString("Status")), 
									CustomerRank.valueOf(rs.getString("Rank")),
									rs.getInt("DojoID"),
									rs.getDate("BDate").toLocalDate(), 
									rs.getInt("AccountID"),
									rs.getString("Email"),
									rs.getBoolean("Passive"));
				customers.add(customer);
			}
			disconnectDB();
			
			return customers;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createAccount(Account account) {
		String sql = "INSERT INTO account(CustomerID) VALUES(?)";
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account.getCustomerId());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Account saved!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTransaction(Transaction trans) {
		String sql = "INSERT INTO transaction(AccountID, Type, Devisa, Amount) VALUES(?,?,?,?)";
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, trans.getAccountId());
			pstmt.setString(2, String.valueOf(trans.getType()));
			pstmt.setString(3, String.valueOf(trans.getDevisa()));
			pstmt.setDouble(4, trans.getAmount());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Transaction saved!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createPrice(Price price) {
		String sql = "INSERT INTO pricetable(Name, Amount, Devisa) VALUES(?,?,?)";
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, price.getName());
			pstmt.setDouble(2, price.getAmount());
			pstmt.setString(3, String.valueOf(price.getDevisa()));
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Price saved!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
