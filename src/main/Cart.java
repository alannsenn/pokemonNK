package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Cart {
	
	private int pokemonId, userId, quantity;

	public Cart(int pokemonId, int userId) {
		super();
		this.pokemonId = pokemonId;
		this.userId = userId;
		
	}

	public Cart(int pokemonId, int userId, int quantity) {
		super();
		this.pokemonId = pokemonId;
		this.userId = userId;
		this.quantity = quantity;
	}

	public int getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//Insert Pokemon to cart (User)
	public static boolean insertToCart(Cart cart) {
		String query = "";
		if (Pokemon.isPokemonExist(cart.getPokemonId())) {
			if (isPokemonExistInCart(cart.getPokemonId(), cart.userId)) {
				query = "UPDATE cart SET Quantity = Quantity + ? WHERE PokemonId = ? AND UserId = ?";
				PreparedStatement ps = Connect.getConnection().prepareStatement(query);
				try {
					ps.setInt(1, cart.getQuantity());
					ps.setInt(2, cart.getPokemonId());
					ps.setInt(3, cart.getUserId());
					ps.execute();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
				
			} else {
				query = "INSERT INTO cart (PokemonID, UserID, Quantity) VALUES (?,?,?)";
				PreparedStatement ps = Connect.getConnection().prepareStatement(query);
				try {
					ps.setInt(1, cart.getPokemonId());
					ps.setInt(2, cart.getUserId());
					ps.setInt(3, cart.getQuantity());
					ps.execute();
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println(e);
				}
				
			} return true;
		} else {
			return false;
		}
	}
	
public static boolean isPokemonExistInCart (int PokemonId, int UserId) {
		
		String countPokemonQuery = "SELECT COUNT(PokemonId) FROM cart WHERE PokemonId = '" + PokemonId + "' AND UserId = " +UserId;
		ResultSet res = Connect.getConnection().executeQuery(countPokemonQuery);
		int count = 0;
		
		try {
			while(res.next()) {
				count = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (count == 0) {
			return false;
		}
		
		return true;
	}

public static boolean deletePokemonFromCart (Cart cart) {
	if (isPokemonExistInCart(cart.getPokemonId(), cart.userId)) {
		System.out.println("helo");
		String query = "DELETE FROM cart WHERE PokemonId=? AND UserId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, cart.getPokemonId());
			ps.setInt(2, cart.getUserId());
			ps.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		} 
		return true;
	}else {
		return false;
	}
	
}

	public static boolean checkoutCart (int userID) {
		Vector<Pokemon> userPokemon = Pokemon.getAllPokemonBought(userID);
		
		if (userPokemon.isEmpty()) return false;
		
		String deleteCartQuery = "DELETE FROM cart WHERE UserId = ?";
		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(deleteCartQuery);
		
		try {
			preparedStatement.setInt(1, userID);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date).toString();
		
		Transaction newTransaction = new Transaction(time, userID);
		
		Transaction.createNewTransaction(newTransaction, userPokemon);
		
		return true;
	}
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

}
