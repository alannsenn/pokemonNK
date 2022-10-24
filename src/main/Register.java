package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame("PokemoNK");
	JPanel panel = new JPanel();
	
	JLabel title, username, firstName, lastName, email, age, password, confirmPassword, errorSuccessMessage;
	JTextField inputUsername, inputFirstName, inputLastName, inputEmail, inputAge;
	JPasswordField inputPassword, inputConfirmPassword;
	JRadioButton genderMale, genderFemale;
	JSpinner ageSpinner;
	JButton back, register;

	public Register() {
		// TODO Auto-generated constructor stub
		//title
				JLabel title = new JLabel("Register");
				title.setBounds(240, 1, 150, 30);
				title.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
				panel.add(title);
				
				//username
				username = new JLabel("Username");
				username.setBounds(90, 40, 80, 25);
				username.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(username);
				
				inputUsername = new JTextField();
				inputUsername.setBounds(90, 60, 330, 25);
				inputUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(inputUsername);
				
				//firstName
				firstName = new JLabel("First Name");
				firstName.setBounds(90, 80, 80, 25);
				firstName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(firstName);
				
				inputFirstName = new JTextField();
				inputFirstName.setBounds(90, 100, 330, 25);
				inputFirstName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(inputFirstName);
				
				//lastName
				lastName = new JLabel("Last Name");
				lastName.setBounds(90, 120, 80, 25);
				lastName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(lastName);
				
				inputLastName = new JTextField();
				inputLastName.setBounds(90, 140, 330, 25);
				inputLastName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(inputLastName);
				
				//email
				email = new JLabel("E-mail");
				email.setBounds(90, 160, 80, 25);
				email.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(email);
				
				inputEmail = new JTextField();
				inputEmail.setBounds(90, 180, 330, 25);
				inputEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(inputEmail);
				
				//gender
				genderMale = new JRadioButton("Male");
				genderMale.setBounds(180, 200, 75, 25);
				genderMale.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				genderMale.setBackground(Color.cyan);
				panel.add(genderMale);
				
				genderFemale = new JRadioButton("Female");
				genderFemale.setBounds(260, 200, 75, 25);
				genderFemale.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				genderFemale.setBackground(Color.cyan);
				panel.add(genderFemale);
				
				//age
				age = new JLabel("Age");
				age.setBounds(90, 220, 80, 25);
				age.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(age);
				
				ageSpinner = new JSpinner();
				ageSpinner.setBounds(90, 240, 330, 25);
				panel.add(ageSpinner);
				
				//password
				password = new JLabel("Password");
				password.setBounds(90, 260, 80, 25);
				password.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(password);
				
				inputPassword = new JPasswordField();
				inputPassword.setBounds(90, 280, 330, 25);
				password.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(inputPassword);
				
				//confirm password
				confirmPassword = new JLabel("Confiirm Password");
				confirmPassword.setBounds(90, 300, 150, 25);
				confirmPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(confirmPassword);
				
				inputConfirmPassword = new JPasswordField();
				inputConfirmPassword.setBounds(90, 320, 330, 25);
				inputConfirmPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				panel.add(inputConfirmPassword);
				
				//button
				register = new JButton("Register");
				register.setBounds(110, 360, 130, 25);
				register.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				register.addActionListener(this);
				panel.add(register);
				
				back = new JButton("Back to Login");
				back.setBounds(280, 360, 130, 25);
				back.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				back.addActionListener(this);
				panel.add(back);
					
				//error/success message
				errorSuccessMessage = new JLabel("");
				errorSuccessMessage.setBounds(200, 390, 330, 25);
				errorSuccessMessage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
				errorSuccessMessage.setForeground(Color.red);
				panel.add(errorSuccessMessage);		
		
		frame.setSize(500, 450);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.cyan);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Register();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			frame.dispose();
//			Login login = new Login();
		}
		
		if(e.getSource() == register) {
			String username = "", firstName = "", lastName = "", fullName = "", email = "", genderMale = "", genderFemale = "", password = "", confirmPassword = "", gender = "";
			int age = 0;
			
			username = inputUsername.getText();
			firstName = inputFirstName.getText();
			lastName = inputLastName.getText();
			email = inputEmail.getText();
//			age = inputAge.getSelectedText().toString(); 
			age = (int) ageSpinner.getValue();
			password = inputPassword.getText();
			confirmPassword = inputConfirmPassword.getText();
			genderMale = this.genderMale.getText();
			genderFemale = this.genderFemale.getText();
			boolean hasNumbers = true, hasLetters = true;
			
			if(username.equals("") || firstName.equals("") || lastName.equals("") || email.equals("") || age == 0 || password.equals("") || confirmPassword.equals("")) {
				errorSuccessMessage.setText("All field must be filled");
				return;
			}
			
			if((this.genderMale.isSelected() == false) && this.genderFemale.isSelected() == false){
				errorSuccessMessage.setText("Must Choose Gender!");
				return;
			}
			
			else {
				if(this.genderMale.isSelected()) {
					gender = "Male";
				}
				else {
					gender = "Female";
				}
			}
			
			if(!email.contains("@") && !email.contains(".com")) {
				errorSuccessMessage.setText("Email must use this format 'xxx@xxx.com'!");
				return;
			}
			
			if(!(age>=11 || age<=100)) {
				errorSuccessMessage.setText("Age must between 11-100!");
				return;
			}
			 if(!(username.length()>=8 && username.length() <= 15)) {
				 errorSuccessMessage.setText("Username length must be between 8-15 characters!");
				 return;
			 }
			
			if(!hasNumbers && !hasLetters && !(password.length()<=20)) {
				for (int i = 0; i < password.length(); i++) {
					if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
						hasNumbers = true;
					}else if (password.toLowerCase().charAt(i) >= 'a' && password.toLowerCase().charAt(i) <= 'z') {
						hasLetters = true;
					}else {
						hasNumbers = false;
						hasLetters = false;
						errorSuccessMessage.setText("Password must consist of letters and numbers!");
						return;
					}
				}
			
				if (confirmPassword != password) {
					errorSuccessMessage.setText("Password confirmation does not match");
					return;
				}
				
			}
			
			fullName = firstName + " " + lastName;
			if(User.register(new User(age, username, fullName, email, gender, password)) == false) {
				errorSuccessMessage.setText("Username has been taken!");
				return;
			}
			
			else {
				User.register(new User(age, username, fullName, email, gender, password));
				frame.dispose();
			}
		}
	}

}
