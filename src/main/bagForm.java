package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class bagForm implements ActionListener{
	
	private JFrame frameBagForm;

	private JPanel pnlDefault, pnlTop, pnlBot, pnlFormTitle, pnlFormTop, pnlFormBot, pnlFormMid, pnlFormMessage;
	private JLabel lblPokemonID, lblTitle,lblMessage;
	private JTextField txtPokemonID;
	private JButton btnCheckOut, btnBack, btnDelete;
	
	private DefaultTableModel dtm;
	private JScrollPane scroll;
	private JTable table;
	
	private int userId = User.getLoginUser();
	
	public bagForm() {
		// TODO Auto-generated constructor stub
		frameBagForm = new JFrame("Manage Bag");
		
		initiatePage();
		frameBagForm.getContentPane().setBackground(Color.cyan);
		frameBagForm.setSize(600, 650);
		frameBagForm.setDefaultCloseOperation(frameBagForm.EXIT_ON_CLOSE);
		frameBagForm.setLocationRelativeTo(null);
		frameBagForm.setVisible(true);

	}
	
	
	private void initiatePage() {
		pnlDefault = new JPanel(new BorderLayout());
		pnlTop = new JPanel();
		pnlBot = new JPanel(new GridLayout(5,1));
		pnlFormMid = new JPanel();
		pnlFormTop = new JPanel(new GridLayout(1,2));
		pnlFormBot = new JPanel(new GridLayout(1,2));
		pnlFormMessage = new JPanel();
		pnlFormTitle = new JPanel();

		pnlTop.setBackground(Color.cyan);
		pnlBot.setBackground(Color.cyan);
		pnlFormBot.setBackground(Color.cyan);
		pnlFormMid.setBackground(Color.cyan);
		pnlFormTop.setBackground(Color.cyan);
		pnlFormMessage.setBackground(Color.cyan);
		pnlFormTitle.setBackground(Color.cyan);
		
		lblTitle = new JLabel("Manage Cart");
		lblTitle.setFont(new Font("Comic sans ms", Font.PLAIN, 30));
		lblPokemonID = new JLabel("Insert Pokemon Id");
		lblPokemonID.setFont(new Font("Comic sans ms", Font.PLAIN, 14));
		txtPokemonID = new JTextField();
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(this);
		btnBack = new JButton("Back to Main");
		btnBack.addActionListener(this);
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		
		lblMessage = new JLabel(" ");
		lblMessage.setFont(new Font("Comic sans ms", Font.PLAIN, 14));
		lblMessage.setForeground(Color.red);
		pnlFormMessage.add(lblMessage);
		
		pnlFormTitle.add(lblTitle);
		
		pnlFormTop.add(lblPokemonID);
		pnlFormTop.add(txtPokemonID);
		
		pnlFormMid.add(btnDelete);
		
		pnlFormBot.add(btnCheckOut);
		pnlFormBot.add(btnBack);
		
		Vector<Object> tableHeader, tableData;
		tableHeader = new Vector<Object>();
		tableHeader.add(new String("Pokemon ID"));
		tableHeader.add(new String("Pokemon Name"));
		tableHeader.add(new String("Pokemon Level"));
		tableHeader.add(new String("Pokemon Type"));
		tableHeader.add(new String("Quantity"));
		
		dtm = new DefaultTableModel(tableHeader, 0);
		dtm.setRowCount(0);
		
//		userId = User.getLoginUser();
		Vector<Pokemon> pokemons = Pokemon.getAllPokemonBought(userId);
		
		for (Pokemon p : pokemons) {
			tableData = new Vector<>();
			tableData.add(p.getId());
			tableData.add(p.getName());
			tableData.add(p.getLevel());
			tableData.add(p.getType());
			tableData.add(p.getQuantity());
			dtm.addRow(tableData);
		}
		
		table = new JTable(dtm);
		table.setBackground(Color.cyan);
		table.getTableHeader().setBackground(Color.cyan);
		table.setModel(dtm);
		
		scroll = new JScrollPane(table);
		scroll.setBackground(Color.cyan);
		scroll.setPreferredSize(new Dimension(550, 300));
		pnlTop.add(scroll);
		pnlDefault.add(pnlTop, BorderLayout.NORTH);
		
		pnlBot.add(pnlFormTitle);
		pnlBot.add(pnlFormTop);
		pnlBot.add(pnlFormMid);
		pnlBot.add(pnlFormBot);
		pnlBot.add(pnlFormMessage);
		
		
		pnlDefault.add(pnlBot, BorderLayout.CENTER);
		
		frameBagForm.add(pnlDefault);
		
		
	}
	
	private void refeshTableDetail() {
		pnlDefault.removeAll();
		initiatePage();
		pnlDefault.revalidate();
		pnlDefault.repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new bagForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnBack) {
			frameBagForm.dispose();
//			WelcomeUser wu = new WelcomeUser();
			
		}else if (e.getSource()==btnCheckOut) {
			boolean isSuccess = Cart.checkoutCart(2);
			
			if (!isSuccess) {
				lblMessage.setText("Cart is empty");
			}else {
				refeshTableDetail();
				lblMessage.setText("Checkout successfully");
			}
			
		}else if (e.getSource()==btnDelete) {
			String id = txtPokemonID.getText().trim();
			int parseID =0;
			boolean checkDelete = true;
			try {
				parseID = Integer.parseInt(id);
			} catch (Exception e2) {
				// TODO: handle exception
				lblMessage.setText("Input must be a number");
			}
			if (id.equals("")) {
				lblMessage.setText("Data must not be empty");
			} else {
				checkDelete=Cart.deletePokemonFromCart(new Cart(parseID, userId));
				if (checkDelete) {
					lblMessage.setText("Deleted successfuly");
					refeshTableDetail();
				} else {
					lblMessage.setText("Pokemon Doesn't Exist");
				}
				
			}
		} 
	}

}




