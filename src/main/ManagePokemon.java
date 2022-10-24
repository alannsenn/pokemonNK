package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManagePokemon implements ActionListener{
	
	private JPanel mainPanel, topPanel, botPanel, midPanel, formPanel, leftFormPanel, midFormPanel, rightFormPanel, errorMsgPanel, btnBackPanel, additionalPanel;
	private JFrame frame = new JFrame("Welcome Admin");
	
	private JLabel lblNameIns, lblLevelIns, lblTypeIns, lblIdDel, lblNameUp, lblLevelUp, lblTypeUp, lblIdUp, lblTempIns, lblTempDel, lblTempUp, lblError;
	private JTextField txtNameIns, txtLevelIns, txtTypeIns, txtIdDel, txtNameUp, txtLevelUp, txtTypeUp, txtIdUp;
	
	private JButton btnInsert, btnDelete, btnUpdate, btnBack;
	
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	
	private void initTopPanel () {
		topPanel = new JPanel(new BorderLayout());
		
		Vector<Object> tableHeader, tableData;
		tableHeader = new Vector<>();
		tableHeader.add(new String ("Pokemon ID"));
		tableHeader.add(new String ("Pokemon Name"));
		tableHeader.add(new String ("Pokemon Level"));
		tableHeader.add(new String ("Pokemon Type"));
		
		tableModel = new DefaultTableModel(tableHeader, 0);
		tableModel.setRowCount(0);
		
		Vector<Pokemon> pokemons = Pokemon.getAllPokemon();
		
		for (Pokemon p : pokemons) {
			tableData = new Vector<>();
			tableData.add(p.getId());
			tableData.add(p.getName());
			tableData.add(p.getLevel());
			tableData.add(p.getType());
			tableModel.addRow(tableData);
		}
		
		table = new JTable(tableModel);
		table.setModel(tableModel);
		table.setBackground(Color.CYAN);
		table.getTableHeader().setBackground(Color.CYAN);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 500));
		
		topPanel.add(scrollPane);
		mainPanel.add(topPanel, BorderLayout.NORTH);
	}
 	
	private void initInsertForm () {
		leftFormPanel = new JPanel(new GridLayout(4, 2, 5, 7));
		leftFormPanel.setBackground(Color.CYAN);
		
		lblNameIns = new JLabel("Pokemon Name : ");
		lblLevelIns = new JLabel("Pokemon Level : ");
		lblTypeIns = new JLabel("Pokemon Type : ");
		lblTempIns = new JLabel("");
		
		txtNameIns = new JTextField();
		txtLevelIns = new JTextField();
		txtTypeIns = new JTextField();
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		
		leftFormPanel.add(lblNameIns);
		leftFormPanel.add(txtNameIns);
		leftFormPanel.add(lblLevelIns);
		leftFormPanel.add(txtLevelIns);
		leftFormPanel.add(lblTypeIns);
		leftFormPanel.add(txtTypeIns);
		leftFormPanel.add(lblTempIns);
		leftFormPanel.add(btnInsert);
	}
	
	private void initDeleteForm () {
		midFormPanel = new JPanel(new FlowLayout());
		midFormPanel.setBackground(Color.CYAN);
		
		lblIdDel = new JLabel("Pokemon ID : ");
		lblTempDel = new JLabel("");

		txtIdDel = new JTextField();
		txtIdDel.setPreferredSize(new Dimension(150, 25));
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		
		midFormPanel.add(lblIdDel);
		midFormPanel.add(txtIdDel);
		midFormPanel.add(lblTempDel);
		midFormPanel.add(btnDelete);
	}
	
	private void initUpdateForm () {
		rightFormPanel = new JPanel(new GridLayout(5, 2, 5, 7));
		rightFormPanel.setBackground(Color.CYAN);
		
		lblIdUp = new JLabel("Pokemon ID : ");
		lblNameUp = new JLabel("Pokemon Name : ");
		lblLevelUp = new JLabel("Pokemon Level : ");
		lblTypeUp = new JLabel("Pokemon Type : ");
		lblTempUp = new JLabel("");
		
		txtIdUp = new JTextField();
		txtNameUp = new JTextField();
		txtLevelUp = new JTextField();
		txtTypeUp = new JTextField();
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		
		rightFormPanel.add(lblIdUp);
		rightFormPanel.add(txtIdUp);
		rightFormPanel.add(lblNameUp);
		rightFormPanel.add(txtNameUp);
		rightFormPanel.add(lblLevelUp);
		rightFormPanel.add(txtLevelUp);
		rightFormPanel.add(lblTypeUp);
		rightFormPanel.add(txtTypeUp);
		rightFormPanel.add(lblTempUp);
		rightFormPanel.add(btnUpdate);
	}
	 
	private void initFormPanel () {
		formPanel = new JPanel(new GridLayout(1, 3, 10, 5));
		formPanel.setBackground(Color.CYAN);
		
		initInsertForm();
		initDeleteForm();
		initUpdateForm();
		
		formPanel.add(leftFormPanel);
		formPanel.add(midFormPanel);
		formPanel.add(rightFormPanel);
	}
	
	private void initMidPanel () {
		midPanel = new JPanel(new BorderLayout());
		midPanel.setBackground(Color.CYAN);
		
		initFormPanel();
		
		midPanel.add(formPanel);
		mainPanel.add(midPanel, BorderLayout.CENTER);
	}
	
	private void initBotPanel () {
		botPanel = new JPanel(new BorderLayout());
		additionalPanel = new JPanel(new BorderLayout());
		errorMsgPanel = new JPanel(new FlowLayout());
		btnBackPanel = new JPanel(new FlowLayout());
		
		additionalPanel.setBackground(Color.CYAN);
		errorMsgPanel.setBackground(Color.CYAN);
		errorMsgPanel.setBackground(Color.CYAN);
		btnBackPanel.setBackground(Color.CYAN);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.red);
		
		btnBack = new JButton("Back to Main");
		btnBack.addActionListener(this);
		
		errorMsgPanel.add(lblError);
		btnBackPanel.add(btnBack);
		
		additionalPanel.add(btnBackPanel, BorderLayout.NORTH);
		additionalPanel.add(errorMsgPanel, BorderLayout.SOUTH);
		
		botPanel.add(additionalPanel);
		mainPanel.add(botPanel, BorderLayout.SOUTH);
	}
	
	private void initUI () {
		mainPanel = new JPanel(new BorderLayout());
		frame.setSize(900, 800);
		
		initTopPanel();
		initMidPanel();
		initBotPanel();
		
		frame.add(mainPanel);
	}

	private void refresh () {
		mainPanel.removeAll();
		initUI();
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public ManagePokemon(){
		initUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new ManagePokemon();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int level = 0, id = 0;
		String name, tempLvl, tempId, type;
		boolean isSuccess;
		
		if (e.getSource() == btnInsert) {
			name = txtNameIns.getText().trim();
			tempLvl = txtLevelIns.getText().trim();
			type = txtTypeIns.getText().trim();
			
			if (name.equals("")) {
				lblError.setText("Pokemon Name can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			if (tempLvl.equals("")) {
				lblError.setText("Pokemon Level can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			try {
				level = Integer.parseInt(tempLvl);
			} catch (NumberFormatException e1) {
				lblError.setText("Level must be a number!");
				return;
			}
			
			if (level <= 0) { 
				lblError.setText("Pokemon Level must be more than 0");
				return;
			}else {
				lblError.setText("");
			}
			
			if (type.equals("")) {
				lblError.setText("Pokemon Name can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			Pokemon.insertNewPokemon(new Pokemon(name, level, type));
			refresh();
			lblError.setText("Pokemon inserted successfully");
		}else if (e.getSource() == btnDelete) {
			
			tempId = txtIdDel.getText().trim();
			
			if (tempId.equals("")) {
				lblError.setText("Pokemon ID can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			try {
				id = Integer.parseInt(tempId);
			} catch (NumberFormatException e1) {
				lblError.setText("Pokemon ID must be a number");
				return;
			}
			
			if (id <= 0) { 
				lblError.setText("Pokemon ID must be more than 0");
				return;
			}else {
				lblError.setText("");
			}
			
			isSuccess = Pokemon.deletePokemon(id);
			
			if (isSuccess) {
				refresh();
				lblError.setText("Deleted Successfully");
			}else {
				lblError.setText("Pokemon Doesn't Exist");
				return;
			}
		}else if (e.getSource() == btnUpdate) {
			
			tempId = txtIdUp.getText().trim();
			name = txtNameUp.getText().trim();
			tempLvl = txtLevelUp.getText().trim();
			type = txtTypeUp.getText().trim();
			
			if (tempId.equals("")) {
				lblError.setText("Pokemon ID can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			try {
				id = Integer.parseInt(tempId);
			} catch (NumberFormatException e1) {
				lblError.setText("Pokemon ID must be a number");
				return;
			}
			
			if (id <= 0) { 
				lblError.setText("Pokemon ID must be more than 0");
				return;
			}else {
				lblError.setText("");
			}
			
			if (name.equals("")) {
				lblError.setText("Pokemon Name can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			if (tempLvl.equals("")) {
				lblError.setText("Pokemon Level can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			try {
				level = Integer.parseInt(tempLvl);
			} catch (NumberFormatException e1) {
				lblError.setText("Level must be a number!");
				return;
			}
			
			if (level <= 0) { 
				lblError.setText("Pokemon Level must be more than 0");
				return;
			}else {
				lblError.setText("");
			}
			
			if (type.equals("")) {
				lblError.setText("Pokemon Name can't be empty");
				return;
			}else {
				lblError.setText("");
			}
			
			isSuccess = Pokemon.updatePokemon(new Pokemon(id, name, level, type));
			
			if (isSuccess) {
				refresh();
				lblError.setText("Updated Successfully");
			}else {
				lblError.setText("Pokemon Doesn't Exist");
				return;
			}
			
		}else if (e.getSource() == btnBack) {
			
		}
	}

}
