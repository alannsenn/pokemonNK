package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Pokemon {
	
	private String name, type;
	private int id, level;
	
	private int quantity;
	
	public Pokemon(int id, String name, int level, String type, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.level = level;
		this.quantity = quantity;
	}

	public Pokemon(int id, String name, int level, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.level = level;
	}
	
	public Pokemon(String name, int level, String type) {
		super();
		this.name = name;
		this.type = type;
		this.level = level;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
//	Get All Pokemon for each UserID
	public static Vector<Pokemon> getAllPokemonBought(int userId) {
		
		Vector<Pokemon> pokemons = new Vector<>();
		
		String queryGetData = "SELECT * from cart join pokemon on pokemon.pokemonid = cart.pokemonid WHERE UserId = "+userId;
		ResultSet res = Connect.getConnection().executeQuery(queryGetData);
		
		try {
			while(res.next()) {
				pokemons.add(new Pokemon(res.getInt("PokemonId"), res.getString("PokemonName"), res.getInt("PokemonLevel"), res.getString("PokemonType"), res.getInt("Quantity")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pokemons;
	}
	
//	Get All Pokemon from Database (Admin)
	public static Vector<Pokemon> getAllPokemon() {
		
		Vector<Pokemon> pokemons = new Vector<>();
		
		String queryGetData = "SELECT * FROM Pokemon";
		ResultSet res = Connect.getConnection().executeQuery(queryGetData);
		
		try {
			while(res.next()) {
				pokemons.add(new Pokemon(res.getInt("PokemonId"), res.getString("PokemonName"), res.getInt("PokemonLevel"), res.getString("PokemonType")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pokemons;
	}
	
//	Insert new Pokemon to Pokemon table (Admin)
	public static void insertNewPokemon(Pokemon pokemon) {
		
		String insertQuery = "INSERT INTO Pokemon (PokemonName, PokemonLevel, PokemonType) VALUES (?, ?, ?)";
		PreparedStatement prepareStatement = Connect.getConnection().prepareStatement(insertQuery);
		
		try {
			prepareStatement.setString(1, pokemon.getName());
			prepareStatement.setInt(2, pokemon.getLevel());
			prepareStatement.setString(3, pokemon.getType());
			prepareStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	Check Pokemon existance in Pokemon table
	public static boolean isPokemonExist (int PokemonId) {
		
		String countPokemonQuery = "SELECT COUNT(PokemonId) FROM pokemon WHERE PokemonId = '" + PokemonId + "'";
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
	
//	Delete Pokemon from Database (Admin)
	public static boolean deletePokemon (int PokemonId) {
		
		if (!isPokemonExist(PokemonId)) {
			return false;
		}
		
		String deleteQuery = "DELETE FROM Pokemon WHERE PokemonId = ?";
		PreparedStatement prepareStatement = Connect.getConnection().prepareStatement(deleteQuery);
		try {
			prepareStatement.setInt(1, PokemonId);
			prepareStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
//	Update Pokemon detail (Admin) 
	public static boolean updatePokemon (Pokemon pokemon) {
		
		if (!isPokemonExist(pokemon.getId())) {
			return false;
		}
		
		String updateQuery = "UPDATE Pokemon SET PokemonName = ?, PokemonLevel = ?, PokemonType = ? WHERE PokemonId = ?";
		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(updateQuery);
		
		try {
			preparedStatement.setString(1, pokemon.getName());
			preparedStatement.setInt(2, pokemon.getLevel());
			preparedStatement.setString(3, pokemon.getType());
			preparedStatement.setInt(4, pokemon.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
