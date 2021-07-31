package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import logico.Empresa;
import logico.User;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class NuevoUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreUsuario;
	private JTextField txtPassword;
	private JTextField txtPasswordRepetir;
	private JButton btnInsertarUsuario;
	private JButton btnCancelar;
	private JComboBox cmbTipoUsuario;

	public NuevoUsuario() {
		setBounds(100, 100, 454, 330);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Crear Nuevo Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 418, 231);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Usuario:");
		lblNewLabel.setBounds(20, 34, 114, 14);
		panel.add(lblNewLabel);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(144, 30, 206, 20);
		panel.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1.setBounds(20, 83, 99, 14);
		panel.add(lblNewLabel_1);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(144, 80, 206, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Repetir Contrase\u00F1a:");
		lblNewLabel_2.setBounds(20, 133, 123, 14);
		panel.add(lblNewLabel_2);
		
		txtPasswordRepetir = new JTextField();
		txtPasswordRepetir.setBounds(144, 130, 206, 20);
		panel.add(txtPasswordRepetir);
		txtPasswordRepetir.setColumns(10);
		
		cmbTipoUsuario = new JComboBox();
		cmbTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Secretario"}));
		cmbTipoUsuario.setBackground(new Color(255, 255, 255));
		cmbTipoUsuario.setBounds(144, 180, 128, 20);
		panel.add(cmbTipoUsuario);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Usuario:");
		lblNewLabel_3.setBounds(20, 183, 99, 14);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 153));
		panel_1.setBounds(0, 248, 438, 43);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(NuevoUsuario.class.getResource("/icons/home (2).png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(51, 102, 153));
		btnCancelar.setBounds(240, 0, 89, 43);
		panel_1.add(btnCancelar);
		
		btnInsertarUsuario = new JButton("");
		btnInsertarUsuario.setIcon(new ImageIcon(NuevoUsuario.class.getResource("/icons/diskette (1).png")));
		btnInsertarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User nuevoUsuario = null;
				
				nuevoUsuario = new User("User " +String.valueOf("USER-"+User.getCodigoUsuario() + 1), txtNombreUsuario.getText(), txtPassword.getText(), cmbTipoUsuario.getSelectedItem().toString());
				
				if(!(Empresa.getInstance().checkSiExisteUser(txtNombreUsuario.getText()))) {
					JOptionPane.showMessageDialog(null, "Usuario no insertado", "Informacion", JOptionPane.ERROR_MESSAGE);
				} else if (!(txtPasswordRepetir.getText().equalsIgnoreCase(txtPassword.getText()))) {
					JOptionPane.showMessageDialog(null, "Usuario no insertado", "Informacion", JOptionPane.ERROR_MESSAGE);
				} else {
					Empresa.getConexion().executeInsert("insert into Usuario(nombre, contraseña, idTipoUsuario) values ('"+ txtNombreUsuario.getText() + "', '"+ txtPassword.getText() +"', "+ (cmbTipoUsuario.getSelectedIndex()+1) +")");
					JOptionPane.showMessageDialog(null, "Usuario insertado con éxito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
				
				clean();
			}
		});
		btnInsertarUsuario.setBackground(new Color(51, 102, 153));
		btnInsertarUsuario.setBounds(339, 0, 89, 43);
		panel_1.add(btnInsertarUsuario);
	}
	
	private void clean() {
		txtNombreUsuario.setText("");
		txtPassword.setText("");
		txtPasswordRepetir.setText("");
		
	}
}
