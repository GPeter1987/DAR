package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {

	private final String jdbc = "jdbc:mysql://localhost/dar";
	private final String userName = "root";
	private final String password = "";

	Connection conn;

	public DataBase() {
		this.conn = null;
	}

	public void connectDB() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbc, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void disconnectDB() throws SQLException {
		if (conn != null) {
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
		} catch (SQLException e) {
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

			while (rs.next()) {
				event = new Event(rs.getInt("ID"), rs.getString("Name"), EventType.valueOf(rs.getString("Type")),
						rs.getDate("Date").toLocalDate(), rs.getInt("DojoID"));
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return event;
	}

	public Event searchEventByName(String name) {
		String sql = "SELECT * FROM event WHERE Name = ?";
		Event event = null;

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new Event(rs.getInt("ID"), rs.getString("Name"), EventType.valueOf(rs.getString("Type")),
						rs.getDate("Date").toLocalDate(), rs.getInt("DojoID"));
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return event;
	}

	public void modifyEvent(Event event) {
		String sql = "UPDATE event SET Name = ?, Type = ?, Date = ?, DojoID = ? WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getName());
			pstmt.setString(2, String.valueOf(event.getType()));
			pstmt.setString(3, String.valueOf(event.getDate()));
			pstmt.setInt(4, event.getDojoId());
			pstmt.setInt(5, event.getId());
			pstmt.executeUpdate();

			disconnectDB();

			System.out.println("Event record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Event> searcAllEvent() {
		ArrayList<Event> events = new ArrayList<Event>();
		String sql = "SELECT * FROM event";

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Event event = new Event(rs.getInt("ID"), rs.getString("Name"), EventType.valueOf(rs.getString("Type")),
						rs.getDate("Date").toLocalDate(), rs.getInt("DojoID"));
				events.add(event);
			}
			disconnectDB();

			return events;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteEvent(Event event) {
		String sql = "DELETE FROM event WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, event.getId());
			pstmt.executeUpdate();

			disconnectDB();
			System.out.println("Event with the ID:" + event.getId() + " is deleted from the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
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

			if (customer.getStatus() == CustomerStatus.DOJO) {
				// If the customer is a dojo we set the dojoId attr to 0 becuse a dojo doesn't
				// have a dojo
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
		} catch (SQLException e) {
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

			while (rs.next()) {
				customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	public int searchCustIdByName(String name, String email) {
		String sql = "SELECT * FROM customer WHERE Name = '" + name + "' AND Email = '" + email + "'";

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Customer customer = null;
			while (rs.next()) {
				customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));

			}
			disconnectDB();

			return customer.getId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int searchDojoIdByName(String name) {
		String sql = "SELECT * FROM customer WHERE Name = '" + name + "' ";

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Customer customer = null;
			while (rs.next()) {
				customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));

			}
			disconnectDB();

			return customer.getId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Customer> searchAllDojo() {
		String sql = "SELECT * FROM customer WHERE Status = 'DOJO'";
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));
				customers.add(customer);
			}
			disconnectDB();

			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Integer> searchAllDojoLike(String namePart) {
		String sql = "SELECT * FROM customer WHERE Status = 'DOJO' AND Name LIKE '%" + namePart + "%'";
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));
				customers.add(customer);
			}
			disconnectDB();

			ArrayList<Integer> dojoIds = new ArrayList<Integer>();
			for (Customer cus : customers) {
				dojoIds.add(cus.getId());
			}

			return dojoIds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Customer> searchAllCustomerLike(Customer cus) {
		ArrayList<Customer> customers = new ArrayList<Customer>();

		String cusId = cus.getId() != 0 ? "ID LIKE '%" + cus.getId() + "%' " : "";
		String cusName = cus.getName().equals("") ? "" : "AND Name LIKE '%" + cus.getName() + "%' ";
		String cusStatus = String.valueOf(cus.getStatus()).equals("") ? ""
				: "AND Status LIKE '%" + String.valueOf(cus.getStatus()) + "%' ";
		String cusRank = String.valueOf(cus.getRank()).equals("") ? ""
				: "AND Rank LIKE '%" + String.valueOf(cus.getRank()) + "%' ";
		String dojoId = cus.getDojoId() == 0 ? "" : "AND DojoID LIKE '%" + cus.getDojoId() + "%' ";
		String bDate = cus.getBirthDate() == null ? ""
				: "AND BDate LIKE '%" + String.valueOf(cus.getBirthDate()) + "%' ";
		String accId = cus.getAccountId() == 0 ? "" : "AND AccountID LIKE '%" + cus.getAccountId() + "%' ";
		String cusEmail = cus.getEmail().equals("") ? "" : "AND Email LIKE '%" + cus.getEmail() + "%' ";
		String cusPassive = cus.getPassive() ? "" : "AND Passve LIKE '%" + cus.getDojoId() + "%' ";

		String sql = "SELECT * FROM customer WHERE " + cusId + cusName + cusStatus + cusRank + dojoId + bDate + accId
				+ cusEmail + cusPassive;

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));
				customers.add(customer);
			}
			disconnectDB();

			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Customer> searcAllCustomer() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql = "SELECT * FROM customer";

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), rs.getString("Name"),
						CustomerStatus.valueOf(rs.getString("Status")), CustomerRank.valueOf(rs.getString("Rank")),
						rs.getInt("DojoID"), rs.getDate("BDate").toLocalDate(), rs.getInt("AccountID"),
						rs.getString("Email"), rs.getBoolean("Passive"));
				customers.add(customer);
			}
			disconnectDB();

			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void modifyCustomer(Customer customer) {
		String sql = "UPDATE customer SET Name = ?, Status = ?, Rank = ?, DojoID = ?, BDate = ?, AccountID = ?, Email = ?, Passive = ? WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, String.valueOf(customer.getStatus()));
			pstmt.setString(3, String.valueOf(customer.getRank()));
			pstmt.setInt(4, customer.getDojoId());
			pstmt.setString(5, String.valueOf(customer.getBirthDate()));
			pstmt.setInt(6, customer.getAccountId());
			pstmt.setString(7, customer.getEmail());
			pstmt.setBoolean(8, customer.getPassive());
			pstmt.setInt(9, customer.getId());
			pstmt.executeUpdate();

			disconnectDB();

			System.out.println("Customer record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int searchAccIdByCustId(int custId) {
		String sql = "SELECT * FROM account WHERE CustomerID = " + String.valueOf(custId);

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Account acc = null;
			while (rs.next()) {
				acc = new Account(rs.getInt("ID"), rs.getInt("CustomerID"));

			}
			disconnectDB();

			return acc.getId();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void modifyAccount(Account acc) {
		String sql = "UPDATE account SET CustomerID = ? WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acc.getCustomerId());
			pstmt.setInt(2, acc.getId());
			pstmt.executeUpdate();

			disconnectDB();

			System.out.println("Account record is updated in the DB!");
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Transaction searchTransactionById(int id) {
		String sql = "SELECT * FROM transaction WHERE ID = ?";
		Transaction transaction = null;

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				transaction = new Transaction(rs.getInt("ID"), rs.getInt("AccountID"),
						TransactionType.valueOf(rs.getString("Type")), Devisa.valueOf(rs.getString("Devisa")),
						rs.getDouble("Amount"), rs.getString("Comment"));
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transaction;
	}

	public ArrayList<Transaction> searchTransactionsByAccId(int AccountId) {
		String sql = "SELECT * FROM transaction WHERE AccountID = ?";
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AccountId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getInt("ID"), rs.getInt("AccountID"),
						TransactionType.valueOf(rs.getString("Type")), Devisa.valueOf(rs.getString("Devisa")),
						rs.getDouble("Amount"), rs.getString("Comment"));

				transactions.add(transaction);
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactions;
	}

	public ArrayList<Transaction> searchTransactionsByType(TransactionType type) {
		String sql = "SELECT * FROM transaction WHERE Type = ?";
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(type));
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getInt("ID"), rs.getInt("AccountID"),
						TransactionType.valueOf(rs.getString("Type")), Devisa.valueOf(rs.getString("Devisa")),
						rs.getDouble("Amount"), rs.getString("Comment"));

				transactions.add(transaction);
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactions;
	}

	public void modifyTransaction(Transaction transaction) {
		String sql = "UPDATE transaction SET AccountID = ?, Type = ?, Devisa = ?, Amount = ?, Comment = ? WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, transaction.getAccountId());
			pstmt.setString(2, String.valueOf(transaction.getType()));
			pstmt.setString(3, String.valueOf(transaction.getDevisa()));
			pstmt.setDouble(4, transaction.getAmount());
			pstmt.setString(5, transaction.getComment());
			pstmt.setInt(6, transaction.getId());
			pstmt.executeUpdate();

			disconnectDB();

			System.out.println("Transaction record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Transaction> searcAllTransaction() {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String sql = "SELECT * FROM transaction";

		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getInt("ID"), rs.getInt("AccountID"),
						TransactionType.valueOf(rs.getString("Type")), Devisa.valueOf(rs.getString("Devisa")),
						rs.getDouble("Amount"), rs.getString("Comment"));
				transactions.add(transaction);
			}
			disconnectDB();

			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modifyPrice(Price price) {
		String sql = "UPDATE pricetable SET Name = ?, Amount = ?, Devisa = ? WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, price.getName());
			pstmt.setDouble(2, price.getAmount());
			pstmt.setString(3, String.valueOf(price.getDevisa()));
			pstmt.setInt(4, price.getId());
			pstmt.executeUpdate();

			disconnectDB();

			System.out.println("Price record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePrice(Price price) {
		String sql = "DELETE FROM pricetable WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price.getId());
			pstmt.executeUpdate();

			disconnectDB();
			System.out.println("Price with the ID:" + price.getId() + " is deleted from the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createParticipant(Event event, Customer customer) {
		String sql = "INSERT INTO participant(EventID, CustomerID) VALUES(?,?)";

		try {
			connectDB();

			Participant participant = new Participant(event.getId(), customer.getId());

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, participant.getEventId());
			pstmt.setInt(2, participant.getCustomerId());
			pstmt.executeUpdate();

			disconnectDB();
			System.out.println("Participant saved!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Participant searchParticipantById(int id) {
		String sql = "SELECT * FROM participant WHERE ID = ?";
		Participant participant = null;

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				participant = new Participant(rs.getInt("ID"), rs.getInt("EventID"), rs.getInt("CustomerID"));
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return participant;
	}

	public ArrayList<Participant> searchParticipantByEventId(int id) {
		String sql = "SELECT * FROM participant WHERE EventID = ?";
		ArrayList<Participant> participants = new ArrayList<>();

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Participant participant = new Participant(rs.getInt("ID"), rs.getInt("EventID"),
						rs.getInt("CustomerID"));
				participants.add(participant);
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return participants;
	}

	public ArrayList<Participant> searchParticipantByCustId(int id) {
		String sql = "SELECT * FROM participant WHERE CustomerID = ?";
		ArrayList<Participant> participants = new ArrayList<>();

		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Participant participant = new Participant(rs.getInt("ID"), rs.getInt("EventID"),
						rs.getInt("CustomerID"));
				participants.add(participant);
			}
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return participants;
	}

	public void deleteParticipant(Participant participant) {
		String sql = "DELETE FROM pricetable WHERE ID = ?";

		try {
			connectDB();

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, participant.getId());
			pstmt.executeUpdate();

			disconnectDB();
			System.out.println("Participant with the ID:" + participant.getId() + " is deleted from the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
