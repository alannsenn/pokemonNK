package main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

	private int userID, age;
	private String username, name, email, gender, password;
	static int iduser =0;
	
	//get user
	public static int getLoginUser() {
		return iduser;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int userID, int age, String username, String name, String email, String gender, String password) {
		super();
		this.userID = userID;
		this.age = age;
		this.username = username;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.password = password;
	}
	
	public User(int age, String username, String name, String email, String gender, String password) {
		super();
		this.age = age;
		this.username = username;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.password = password;
	}
	
	//login
	public static User login(String username, String password){
		String query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		int userID = 0, age = 0;
		String name = "", email = "", gender = "";
		
		try {
			while (rs.next()) {
				userID = rs.getInt(1);
				username = rs.getString(2);
				name = rs.getString(3);
				age = rs.getInt(4);
				email = rs.getString(5);
				gender = rs.getString(6);
				password = rs.getString(7);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		if(userID == 0) {
			return null;
		}
		User user = new User(userID, age, username, name, email, gender, password);
		iduser=user.getUserID();
		return user;

	}
	
	//register
	public static boolean register(User user) {
		String selectQuery = "SELECT username FROM user WHERE username = '" + user.getUsername() + "'";
		String selectUsername = "";
		ResultSet rs = Connect.getConnection().executeQuery(selectQuery);
		
		try {
			while(rs.next()) {
				selectUsername = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		if(!selectUsername.equals("")) {
			return false;
		}
		
		String query = "INSERT INTO user (Username, Name, Email, Gender, Age, Password) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getGender());
			ps.setInt(5, user.getAge());
			ps.setString(6, user.getPassword());
			
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("gagal");
		}
		return true;
	}
	
	
	public User() {
		// TODO Auto-generated constructor stub
		
	}

}
