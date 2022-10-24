package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class buyPokemonForm implements ActionListener{
	
	private JFrame frameBuyPokemon;
	private JPanel pnlDefault, pnlTop, pnlBot, pnlFormTop, pnlFormBot, pnlFormMid;
	private JLabel lblPokemonID, lblQuantity, lblError;
	private JTextField txtPokemonID, txtQuantity;
	private JButton btnInsert, btnBack;
	
	private DefaultTableModel dtm;
	private JScrollPane scroll;
	private JTable table;

	public buyPokemonForm() {
		// TODO Auto-generated constructor stub
		frameBuyPokemon = new JFrame("1");
		pnlDefault = new JPanel(new BorderLayout());
		pnlTop = new JPanel();
		pnlBot = new JPanel(new BorderLayout());
		pnlFormMid = new JPanel(new GridLayout(1,2));
		pnlFormTop = new JPanel(new GridLayout(3,2));
		pnlFormBot = new JPanel();
		
		pnlDefault.setBackground(Color.cyan);
		pnlTop.setBackground(Color.cyan);
		pnlBot.setBackground(Color.cyan);
		pnlFormTop.setBackground(Color.cyan);
		pnlFormMid.setBackground(Color.cyan);
		pnlFormBot.setBackground(Color.cyan);
		
		lblPokemonID = new JLabel("Insert Pokemon Id");
		lblPokemonID.setFont(new Font("Comic sans ms", Font.PLAIN, 14));
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Comic sans ms", Font.PLAIN, 14));
		txtPokemonID = new JTextField();
		txtQuantity = new JTextField();
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		btnBack = new JButton("Back to Main");
		btnBack.addActionListener(this);
		
		lblError = new JLabel(" ");
		lblError.setFont(new Font("Comic sans ms", Font.PLAIN, 14));
		lblError.setForeground(Color.red);
		pnlFormBot.add(lblError);
		
		pnlFormTop.add(lblPokemonID);
		pnlFormTop.add(txtPokemonID);
		pnlFormTop.add(lblQuantity);
		pnlFormTop.add(txtQuantity);
		
		pnlFormTop.add(btnInsert);
		pnlFormTop.add(btnBack);
		
		Vector<Object> tableHeader, tableData;
		tableHeader = new Vector<Object>();
		tableHeader.add(new String("Pokemon ID"));
		tableHeader.add(new String("Pokemon Name"));
		tableHeader.add(new String("Pokemon Level"));
		tableHeader.add(new String("Pokemon Type"));
		
		dtm = new DefaultTableModel(tableHeader, 0);
		dtm.setRowCount(0);
		
		Vector<Pokemon> pokemons = Pokemon.getAllPokemon();
		
		for (Pokemon p : pokemons) {
			tableData = new Vector<>();
			tableData.add(p.getId());
			tableData.add(p.getName());
			tableData.add(p.getLevel());
			tableData.add(p.getType());
			dtm.addRow(tableData);
		}
		
		table = new JTable(dtm);
		table.setBackground(Color.cyan);
		table.getTableHeader().setBackground(Color.cyan);
		table.setModel(dtm);
		
		scroll = new JScrollPane(table);
		//scroll.setPreferredSize(new Dimension(400, 300));
		
		pnlTop.add(scroll);
		pnlBot.add(pnlFormTop, BorderLayout.NORTH);
		pnlBot.add(pnlFormMid, BorderLayout.CENTER);
		pnlBot.add(pnlFormBot, BorderLayout.SOUTH);
		
		pnlDefault.add(pnlTop, BorderLayout.NORTH);
		pnlDefault.add(pnlBot, BorderLayout.CENTER);
		
		frameBuyPokemon.add(pnlDefault);
		
		frameBuyPokemon.setSize(600, 650);
		frameBuyPokemon.setDefaultCloseOperation(frameBuyPokemon.EXIT_ON_CLOSE);
		frameBuyPokemon.setLocationRelativeTo(null);
		frameBuyPokemon.setVisible(true);
		
	}
	
	
	public static void main (String[] args) {
		new buyPokemonForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack) {
			frameBuyPokemon.dispose();
//			WelcomeUser wu = new WelcomeUser();
			
		}else if (e.getSource() == btnInsert) {
			String id = txtPokemonID.getText().trim();
			String qty = txtQuantity.getText().trim();
			if (id.equals("") || qty.equals("")) {
				lblError.setText("Data must not be empty");
				return;
			} 
			int checkQty = 0;
			try {
				checkQty = Integer.parseInt(qty);
			} catch (Exception e2) {
				// TODO: handle exception
				lblError.setText("Quantity must be numeric");
			}
			if (checkQty<0) {
				lblError.setText("Quantity can not less than 0");
			}
			int parsePokID = 0;
			try {
				parsePokID = Integer.parseInt(id);
			} catch (Exception e2) {
				// TODO: handle exception
				lblError.setText("Pokemon Id must be numeric");
			}
			if (Cart.insertToCart(new Cart(parsePokID, User.getLoginUser(), checkQty))) {
				lblError.setText("Successfully Inserted");
			} else {
				lblError.setText("Pokemon Must Exists");
			}

		}
	}

}
