package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import logico.Disenador;
import logico.Empresa;
import logico.Jefe;
import logico.Planificador;
import logico.Programador;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Empleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedulaEmp;
	private JTextField txtNombreEmp;
	private JTextField txtTelefonoEmp;
	private JTextField txtDireccionEmp;
	private JPanel panel_5;
	private JTextField txtTrabajadoresEmp;
	private JTextField txtReunionEmp;
	private JTable tblLenguajesEmp;
	private static DefaultTableModel modelProg;
	private static Object[] rowsProg;
	private JButton btnBuscar;
	private JRadioButton rdbMasculino;
	private JRadioButton rdbFemenino;
	private JSpinner spnSalarioHora;
	private JButton btnInsertar;
	private JButton btnCancelar;
	private JTextField txtApellido;
	private JSpinner spnAnExperiencia;
	private JScrollPane scrollPane;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_7;
	private JFormattedTextField formattedTextField;

	public Empleado() {
		setBounds(100, 100, 684, 491);
		setLocationRelativeTo(null);
		modelProg = new DefaultTableModel();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Informacion Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 648, 219);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cedula");
		lblNewLabel.setBounds(22, 24, 62, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(249, 24, 62, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 74, 67, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Direccion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(249, 74, 62, 14);
		panel.add(lblNewLabel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Informacion Personal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(66, 115, 208, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);

		rdbMasculino = new JRadioButton("Masculino");
		rdbMasculino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbMasculino.isSelected()) {
					rdbFemenino.setSelected(false);
				}
			}
		});
		rdbMasculino.setBackground(new Color(255, 255, 255));
		rdbMasculino.setForeground(new Color(0, 0, 0));
		rdbMasculino.setBounds(50, 14, 109, 23);
		panel_1.add(rdbMasculino);

		rdbFemenino = new JRadioButton("Femenino");
		rdbFemenino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbFemenino.isSelected()) {
					rdbMasculino.setSelected(false);
				}
			}
		});
		rdbFemenino.setBackground(new Color(255, 255, 255));
		rdbFemenino.setForeground(new Color(0, 0, 0));
		rdbFemenino.setBounds(50, 51, 109, 23);
		panel_1.add(rdbFemenino);

		txtCedulaEmp = new JTextField();
		txtCedulaEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirLetras(e)) {
					e.consume();
				}
			}
		});
		txtCedulaEmp.setBounds(66, 21, 135, 20);
		panel.add(txtCedulaEmp);
		txtCedulaEmp.setColumns(10);

		txtNombreEmp = new JTextField();
		txtNombreEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (prevenirDigitos(arg0)) {
					arg0.consume();
				}
			}
		});
		txtNombreEmp.setBounds(302, 21, 123, 20);
		panel.add(txtNombreEmp);
		txtNombreEmp.setColumns(10);

		txtTelefonoEmp = new JTextField();
		txtTelefonoEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirLetras(e)) {
					e.consume();
				}
			}
		});
		txtTelefonoEmp.setBounds(66, 71, 173, 20);
		panel.add(txtTelefonoEmp);
		txtTelefonoEmp.setColumns(10);

		txtDireccionEmp = new JTextField();
		txtDireccionEmp.setBounds(305, 71, 333, 20);
		panel.add(txtDireccionEmp);
		txtDireccionEmp.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setForeground(new Color(51, 102, 153));
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Informacion Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(340, 115, 242, 72);
		panel.add(panel_3);
		panel_3.setLayout(null);

		spnSalarioHora = new JSpinner();
		spnSalarioHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirLetras(e)) {
					e.consume();
				}
			}
		});
		spnSalarioHora.setBackground(new Color(255, 255, 255));
		spnSalarioHora.setForeground(new Color(0, 0, 0));
		spnSalarioHora.setModel(new SpinnerNumberModel(new Integer(100), new Integer(100), null, new Integer(1)));
		spnSalarioHora.setBounds(70, 28, 153, 20);
		panel_3.add(spnSalarioHora);

		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setBounds(10, 31, 67, 14);
		panel_3.add(lblSalario);

		JLabel lblNewLabel_7 = new JLabel("Apellido");
		lblNewLabel_7.setBounds(449, 24, 62, 14);
		panel.add(lblNewLabel_7);

		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirDigitos(e)) {
					e.consume();
				}
			}
		});
		txtApellido.setBounds(501, 21, 123, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Tipo Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 241, 648, 162);
		contentPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane, BorderLayout.CENTER);

		panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)));
		panel_5.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Jefe", null, panel_5, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		panel_5.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Cantidad de Trabajadores");
		lblNewLabel_4.setBounds(170, 41, 148, 14);
		panel_5.add(lblNewLabel_4);

		txtTrabajadoresEmp = new JTextField();
		txtTrabajadoresEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirLetras(e)) {
					e.consume();
				}
			}
		});
		txtTrabajadoresEmp.setBounds(337, 38, 86, 20);
		panel_5.add(txtTrabajadoresEmp);
		txtTrabajadoresEmp.setColumns(10);

		panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)));
		tabbedPane.addTab("Programador", null, panel_7, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));
		panel_7.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Lenguaje:");
		lblNewLabel_5.setBounds(138, 11, 71, 14);
		panel_7.add(lblNewLabel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(219, 11, 179, 90);
		panel_7.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_6.add(scrollPane, BorderLayout.CENTER);

		tblLenguajesEmp = new JTable();
		tblLenguajesEmp.setModel(modelProg);
		tblLenguajesEmp.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(tblLenguajesEmp);

		btnBuscar = new JButton("B");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarLenguaje leng = new AgregarLenguaje();
				leng.setModal(true);
				leng.setVisible(true);
			}
		});
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBackground(new Color(204, 204, 204));
		btnBuscar.setBounds(408, 11, 78, 41);
		panel_7.add(btnBuscar);
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)));
		tabbedPane.addTab("Planificador", null, panel_8, null);
		tabbedPane.setBackgroundAt(2, new Color(255, 255, 255));
		panel_8.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Frecuencia de Reuniones por semana:");
		lblNewLabel_6.setBounds(133, 51, 214, 14);
		panel_8.add(lblNewLabel_6);

		txtReunionEmp = new JTextField();
		txtReunionEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirLetras(e)) {
					e.consume();
				}
			}
		});
		txtReunionEmp.setBounds(346, 48, 86, 20);
		panel_8.add(txtReunionEmp);
		txtReunionEmp.setColumns(10);

		panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Dise\u00F1ador", null, panel_9, null);
		tabbedPane.setBackgroundAt(3, new Color(255, 255, 255));
		panel_9.setLayout(new BorderLayout(0, 0));

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)));
		panel_11.setBackground(new Color(255, 255, 255));
		panel_9.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("A\u00F1os de Experiencia");
		lblNewLabel_8.setBounds(183, 48, 155, 14);
		panel_11.add(lblNewLabel_8);

		spnAnExperiencia = new JSpinner();
		spnAnExperiencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (prevenirLetras(e)) {
					e.consume();
				}
			}
		});
		spnAnExperiencia.setBackground(new Color(255, 255, 255));
		spnAnExperiencia.setModel(new SpinnerNumberModel(0, 0, 40, 1));
		spnAnExperiencia.setForeground(new Color(0, 0, 0));
		spnAnExperiencia.setBounds(303, 45, 70, 20);
		panel_11.add(spnAnExperiencia);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(51, 102, 153));
		panel_4.setBounds(0, 407, 668, 44);
		contentPanel.add(panel_4);
		panel_4.setLayout(null);

		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Empleado.class.getResource("/icons/home (2).png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				clean();
			}
		});
		btnCancelar.setBorderPainted(false);
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(51, 102, 153));
		btnCancelar.setBounds(470, 0, 89, 44);
		panel_4.add(btnCancelar);

		btnInsertar = new JButton("");
		btnInsertar.setIcon(new ImageIcon(Empleado.class.getResource("/icons/diskette (1).png")));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if(informacionLlena(tabbedPane.getSelectedIndex())) {
						Jefe emp;
						Programador emp2;
						Disenador emp3;
						Planificador emp4;
						int aux = 0, auxText = 0;
						String auxRadio = "";
						ArrayList<String> lenguajeProg = new ArrayList<String>();
						if(rdbMasculino.isSelected()) {
							auxRadio = "Masculino";
						} else {
							auxRadio = "Femenino";
						}

						if(tabbedPane.getSelectedIndex() == 0) {

							aux = Integer.valueOf(txtTrabajadoresEmp.getText());
							emp = new Jefe(txtCedulaEmp.getText(), txtNombreEmp.getText(), txtApellido.getText(), txtDireccionEmp.getText(), txtTelefonoEmp.getText(), auxRadio, Float.valueOf(spnSalarioHora.getValue().toString()),"",Float.valueOf(0), aux);
							Empresa.getInstance().insertarEmpleado(emp);

						} else if (tabbedPane.getSelectedIndex() == 1) {

							lenguajeProg = collectLanguages();
							emp2 = new Programador(txtCedulaEmp.getText(), txtNombreEmp.getText(), txtApellido.getText(), txtDireccionEmp.getText(), txtTelefonoEmp.getText(), auxRadio, Float.valueOf(spnSalarioHora.getValue().toString()),"",Float.valueOf(0), lenguajeProg);
							Empresa.getInstance().insertarEmpleado(emp2);

						} else if (tabbedPane.getSelectedIndex() == 2) {
							auxText = Integer.valueOf(txtReunionEmp.getText().toString());
							emp4 = new Planificador(txtCedulaEmp.getText(), txtNombreEmp.getText(), txtApellido.getText(), txtDireccionEmp.getText(), txtTelefonoEmp.getText(), auxRadio, Float.valueOf(spnSalarioHora.getValue().toString()),"",Float.valueOf(0), auxText);
							Empresa.getInstance().insertarEmpleado(emp4);

						} else if(tabbedPane.getSelectedIndex() == 3){

							emp3 = new Disenador(txtCedulaEmp.getText(), txtNombreEmp.getText(), txtApellido.getText(), txtDireccionEmp.getText(), txtTelefonoEmp.getText(), auxRadio, Float.valueOf(spnSalarioHora.getValue().toString()),"",Float.valueOf(0), Integer.valueOf(spnAnExperiencia.getValue().toString()));
							Empresa.getInstance().insertarEmpleado(emp3);
						}
						JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						clean();
					} else {
						JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1){
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Aviso", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnInsertar.setBorderPainted(false);
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(51, 102, 153));
		btnInsertar.setBounds(569, 0, 89, 44);
		panel_4.add(btnInsertar);



	}
	public static void agregarLenguajeProgramador(DefaultTableModel model) {
		String[] lenguaje = {"Lenguaje"};
		modelProg.setColumnIdentifiers(lenguaje);
		rowsProg = new Object[model.getColumnCount()];
		try {
			for(int i = 0; i < model.getRowCount(); i++) {
				if(checkElements(model.getValueAt(i, 0).toString())) {
					rowsProg[0] = model.getValueAt(i, 0).toString();
					modelProg.addRow(rowsProg);
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al llenar los datos en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
	private static boolean checkElements(String elemento) {
		if(modelProg.getRowCount() < 0) {
			return true;
		}
		for(int i = 0; i < modelProg.getRowCount(); i++) {
			if(modelProg.getValueAt(i, 0).toString().equalsIgnoreCase(elemento)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Funcion para agregar los lenguajes al arrayList de programador
	 * */

	private ArrayList<String> collectLanguages() {
		ArrayList<String> lenguajesProg = new ArrayList<String>();
		if(modelProg.getRowCount() < 0) {
			return null;
		}
		for(int i = 0; i < modelProg.getRowCount(); i++) {
			lenguajesProg.add(modelProg.getValueAt(i, 0).toString());
		}
		return lenguajesProg;
	}
	private void clean() {
		txtCedulaEmp.setText("");
		txtNombreEmp.setText("");
		txtTelefonoEmp.setText("");
		txtDireccionEmp.setText("");
		txtTrabajadoresEmp.setText("");
		txtReunionEmp.setText("");

		modelProg = new DefaultTableModel();
		tblLenguajesEmp.setModel(modelProg);

		if(rdbFemenino.isSelected()) {
			rdbFemenino.setSelected(false);
			rdbMasculino.setSelected(true);
		}
		spnSalarioHora.setValue(100);
		txtApellido.setText("");
		spnAnExperiencia.setValue(0);

	}
	private boolean prevenirDigitos(KeyEvent arg0) {
		if (!Character.isLetter(arg0.getKeyChar()) && !(arg0.getKeyChar() == KeyEvent.VK_SPACE) && !(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
			JOptionPane.showMessageDialog(null, "Solo debe insertar Letras", "Informacion", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	private boolean prevenirLetras(KeyEvent arg0) {
		if (!Character.isDigit(arg0.getKeyChar()) && !(arg0.getKeyChar() == KeyEvent.VK_SPACE) && !(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
			JOptionPane.showMessageDialog(null, "Solo debe insertar numeros", "Informacion", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	private boolean informacionLlena(int selectedIndex) {
		if(txtCedulaEmp.getText().isEmpty() || txtNombreEmp.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefonoEmp.getText().isEmpty() || txtDireccionEmp.getText().isEmpty() || (!rdbMasculino.isSelected() && !rdbFemenino.isSelected())) {
			return false;
		} else if(selectedIndex == 0 && txtTrabajadoresEmp.getText().isEmpty()) {
			return false;
		} else if (selectedIndex == 1 && tblLenguajesEmp.getRowCount() == 0) {
			return false;
		} else if (selectedIndex == 2 && txtReunionEmp.getText().isEmpty()) {
			return false;
		} else if(selectedIndex == 3 && spnAnExperiencia.getValue().toString().equalsIgnoreCase("0")){
			return false;
		}
		return true;
	}
}