package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import logico.Empresa;
import logico.SQLConnection;
import logico.User;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreUsuario;
	private JTextField txtPassword;
	private JButton btnIngresar;

	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Empresa.setConexion();
				/*FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					empresa = new FileInputStream ("empresa.dat");
					empresaRead = new ObjectInputStream(empresa);
					Empresa temp = (Empresa)empresaRead.readObject();
					
					
					// CONEXION
					Empresa.setConexion();
					// CONEXION
					
					
					Empresa.setEmpresa(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new  FileOutputStream("empresa.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						User aux = new User("USER-1","Administrador", "Admin", "Admin");
						Empresa.getInstance().insetarUsuario(aux);
						empresaWrite.writeObject(Empresa.getInstance());
						empresa2.close();
						empresaWrite.close();
					} catch (FileNotFoundException e1) {
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		Statement stmt;
		ResultSet resultSet = null;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)));
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Usuario");
				lblNewLabel.setBounds(93, 75, 46, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
				lblNewLabel_1.setBounds(93, 126, 104, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtNombreUsuario = new JTextField();
				txtNombreUsuario.setBounds(170, 72, 169, 20);
				panel.add(txtNombreUsuario);
				txtNombreUsuario.setColumns(10);
			}
			{
				txtPassword = new JTextField();
				txtPassword.setBounds(170, 123, 169, 20);
				panel.add(txtPassword);
				txtPassword.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnIngresar = new JButton("");
				btnIngresar.setIcon(new ImageIcon(Login.class.getResource("/icons/enter.png")));
				btnIngresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/*try {
							Statement stm = (Statement) SQLConnection.getConnection().createStatement();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						/*String selectSql = "SELECT count(*) as total from Jugador";
						stmt = (Statement) SQLConnection.getConnection().prepareStatement(selectSql);
						resultSet =  stmt.ece;
						Empresa.getInstance().setUsuarios(usuarios);*/
						if(!(Empresa.getInstance().checkUserData(txtNombreUsuario.getText(), txtPassword.getText()))) {
							JOptionPane.showMessageDialog(null, "Usuario no encontrado o datos incorrectos", "Informacion", JOptionPane.ERROR_MESSAGE);
						} else {
							Principal ventanaPrincipal = new Principal();
							ventanaPrincipal.setVisible(true);
							setVisible(false);

						}

					}
				});
				btnIngresar.setBorderPainted(false);
				btnIngresar.setBackground(new Color(0, 51, 102));
				btnIngresar.setForeground(new Color(255, 255, 255));
				btnIngresar.setActionCommand("Cancel");
				buttonPane.add(btnIngresar);
			}
		}
	}
}
