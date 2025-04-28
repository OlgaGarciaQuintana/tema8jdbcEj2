package tema8ejercicio2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.*;

public class Tema8ejercicio2 {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Tema8ejercicio2 window = new Tema8ejercicio2();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Tema8ejercicio2() {
		initialize();
	}

	private void initialize() {
		String url = "jdbc:mysql://127.0.0.1:3306/comunidades";
		String user = "root";
		String password = "1234-Abcd";

		frame = new JFrame();
		frame.setBounds(100, 100, 645, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		JLabel lblComunidadAutonoma = new JLabel("Comunidad Autónoma:");
		lblComunidadAutonoma.setBounds(64, 51, 160, 15);
		frame.getContentPane().add(lblComunidadAutonoma);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(362, 51, 70, 15);
		frame.getContentPane().add(lblProvincia);
		
		JComboBox comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(362, 89, 201, 24);
		frame.getContentPane().add(comboBoxProvincia);
		
		JComboBox comboBoxComunidad = new JComboBox();
		comboBoxComunidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					comboBoxProvincia.removeAllItems();
					Connection con = DriverManager.getConnection(url, user, password);
					PreparedStatement sel_pstmt = con.prepareStatement("SELECT * FROM provincia WHERE codc=?");
					int cod = comboBoxComunidad.getSelectedIndex()+1;
         			sel_pstmt.setInt(1, cod);
         			ResultSet rs_sel = sel_pstmt.executeQuery();
					 while (rs_sel.next()) {
					 String nombre=rs_sel.getString("nomp");
					 comboBoxProvincia.addItem(nombre);
					 }
					 rs_sel.close();
					 sel_pstmt.close();
					 con.close();
					} catch (SQLException ex) {
					ex.printStackTrace();
					}
			}
		});
		comboBoxComunidad.setBounds(64, 89, 201, 24);
		frame.getContentPane().add(comboBoxComunidad);
		try {
			 Connection con = DriverManager.getConnection(url, user, password);
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM comunidad");
			 while (rs.next()) {
			 String nombre=rs.getString("nomc");
			 comboBoxComunidad.addItem(nombre);
			 }
			 rs.close();
			 stmt.close();
			 con.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		
		
		
		
	}
}
