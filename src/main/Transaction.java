package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Transaction {
	
	private String date, pokemonName, pokemonType;
	private int transactionID, userID, pokemonID, quantity, pokemonLevel;
	
	public void setPokemonLevel(int pokemonLevel) {
		this.pokemonLevel = pokemonLevel;
	}

	public Transaction(int transactionID, String date) {
		super();
		this.transactionID = transactionID;
		this.date = date;
		
	}
	
	public Transaction(String date, int userID) {
		super();
		this.userID = userID;
		this.date = date;
	}

	public Transaction(String date, String pokemonName, String pokemonType, int pokemonLevel, int transactionID,
			int userID, int pokemonID, int quantity) {
		super();
		this.date = date;
		this.pokemonName = pokemonName;
		this.pokemonType = pokemonType;
		this.pokemonLevel = pokemonLevel;
		this.transactionID = transactionID;
		this.userID = userID;
		this.pokemonID = pokemonID;
		this.quantity = quantity;
	}
	
	public Transaction(int transactionID,int pokemonID, String pokemonName, int pokemonLevel, String pokemonType,  
			 int quantity) {
		super();
		this.pokemonName = pokemonName;
		this.pokemonType = pokemonType;
		this.pokemonLevel = pokemonLevel;
		this.transactionID = transactionID;
		this.pokemonID = pokemonID;
		this.quantity = quantity;
	}

	public String getPokemonName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

	public String getPokemonType() {
		return pokemonType;
	}

	public void setPokemonType(String pokemonType) {
		this.pokemonType = pokemonType;
	}

	public int getPokemonID() {
		return pokemonID;
	}

	public void setPokemonID(int pokemonID) {
		this.pokemonID = pokemonID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public static Vector<Transaction> getTransactionHeader(int userID){
		Vector<Transaction> trans = new Vector<Transaction>();
		
		String query = "SELECT TransactionId, Time FROM headertransaction WHERE UserId = " + userID;
		ResultSet res = Connect.getConnection().executeQuery(query);
		
		try {
			while (res.next()) {
				trans.add(new Transaction(res.getInt("TransactionId"),res.getString("Time")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return trans;
	}

	public static Vector<Transaction> getTransactionDetail(int transactionID){
		Vector<Transaction> transDetail = new Vector<Transaction>();
		
		String query = "SELECT H.TransactionId, P.PokemonId, P.PokemonName, P.PokemonLevel, P.PokemonType, D.Quantity FROM headertransaction H JOIN detailtransaction D ON H.TransactionId = D.TransactionId   JOIN pokemon P ON D.PokemonId = P.PokemonId WHERE H.TransactionId =" + transactionID;
		ResultSet res = Connect.getConnection().executeQuery(query);
		
		try {
			while (res.next()) {
				transDetail.add(new Transaction(res.getInt("TransactionId"), res.getInt("PokemonId"), res.getString("PokemonName"), 
							res.getInt("PokemonLevel"), res.getString("PokemonType"), res.getInt("Quantity")));
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return transDetail;
	}
	
	public static int createTransactionHeader (Transaction transaction) {
		
		Transaction newTransaction = new Transaction(transaction.getDate(), transaction.getUserID());
		
		String insertQuery = "INSERT INTO headertransaction (UserId, Time) VALUES (?, ?)";
		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(insertQuery);
		
		try {
			preparedStatement.setInt(1, newTransaction.getUserID());
			preparedStatement.setString(2, newTransaction.getDate());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String getLatestTransactionIdQuery = "SELECT TransactionId FROM headertransaction WHERE UserId = " + transaction.getUserID() + " ORDER BY time DESC LIMIT 1";
		int latestTransactionId = 0;
		
		ResultSet res = Connect.getConnection().executeQuery(getLatestTransactionIdQuery);
		
		try {
			while(res.next()) {
				latestTransactionId = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return latestTransactionId;
	}
	
	public static void createNewTransaction (Transaction transaction, Vector<Pokemon> userPokemon) {
		
		int transactionId = createTransactionHeader(transaction);
		
		String insertDetailQuery = "INSERT INTO detailTransaction VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(insertDetailQuery);
		
		for (Pokemon p : userPokemon) {
			try {
				preparedStatement.setInt(1, transactionId);
				preparedStatement.setInt(2, p.getId());
				preparedStatement.setInt(3, p.getQuantity());
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public int getPokemonLevel() {
		return pokemonLevel;
	}

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

}
