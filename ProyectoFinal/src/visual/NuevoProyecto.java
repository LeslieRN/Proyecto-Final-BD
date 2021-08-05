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
import javax.swing.text.DateFormatter;

import logico.Empresa;
import logico.Empleado;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;

public class NuevoProyecto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtNombre;
	private static JTextField txtCodigo;
	private static DefaultTableModel model;
	private static DefaultComboBoxModel modelComboLanguage;
	private static DefaultComboBoxModel modelComboTipo;
	private static Object[] rows;
	private static JComboBox cmbLenguajes;
	private JButton btnAgregarEmpleado;
	private JTable tblEmpleadosProyecto;
	private static JSpinner spnFentrega;
	private static JSpinner spnFinicio;
	private static JComboBox cbxTipo;

	public NuevoProyecto(String codigo, String nombre, Date fechaInicio,Date fechaFin,String tipo,String lenguaje,ArrayList<Empleado> temp_emp) {
		setBounds(100, 100, 584, 451);
		setLocationRelativeTo(null);
		model = new DefaultTableModel();
		modelComboLanguage = new DefaultComboBoxModel(); 
		modelComboTipo = new DefaultComboBoxModel();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(51, 102, 153));
		panel_3.setBounds(0, 368, 568, 44);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(NuevoProyecto.class.getResource("/icons/home (2).png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(51, 102, 153));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(370, 0, 89, 44);
		panel_3.add(btnNewButton);

		JButton btnSiguiente = new JButton("");
		btnSiguiente.setIcon(new ImageIcon(NuevoProyecto.class.getResource("/icons/right-arrow (1).png")));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(informacionLlena()) {
						ArrayList<Empleado> temporal_Empleado = insertarEmpleadoProyecto(model);
						setVisible(false);
					    
						NuevoContrato cont = new NuevoContrato(String.valueOf(txtCodigo.getText()), txtNombre.getText(), (Date)spnFinicio.getValue(), (Date)spnFentrega.getValue(),String.valueOf(cbxTipo.getSelectedItem()),String.valueOf(cmbLenguajes.getSelectedItem()),temporal_Empleado);
						cont.setModal(true);
						cont.setVisible(true);
					}else {

						JOptionPane.showMessageDialog(null,  "Debe llenar todas las casillas", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Aviso", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnSiguiente.setBorderPainted(false);
		btnSiguiente.setBackground(new Color(51, 102, 153));
		btnSiguiente.setForeground(new Color(255, 255, 255));
		btnSiguiente.setBounds(469, 0, 89, 44);
		panel_3.add(btnSiguiente);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 558, 365);
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Informacion Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Nombre");
				label.setBounds(212, 28, 72, 14);
				panel.add(label);
			}
			{
				txtNombre = new JTextField();
				txtNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (prevenirDigitos(e)) {
							e.consume();
						}
					}
				});
				txtNombre.setColumns(10);
				txtNombre.setBounds(264, 25, 278, 20);
				panel.add(txtNombre);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setBounds(121, 166, 300, 192);
				panel.add(panel_1);
				{
					btnAgregarEmpleado = new JButton("");
					btnAgregarEmpleado.setIcon(new ImageIcon(NuevoProyecto.class.getResource("/icons/loupe (2).png")));
					btnAgregarEmpleado.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							AgregarEmpleado emp = new AgregarEmpleado(cmbLenguajes.getSelectedItem().toString());
							emp.setModal(true);
							emp.setVisible(true);
						}
					});
					btnAgregarEmpleado.setBorderPainted(false);
					btnAgregarEmpleado.setBackground(Color.WHITE);
					btnAgregarEmpleado.setBounds(201, 11, 89, 34);
					panel_1.add(btnAgregarEmpleado);
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(new Color(255, 255, 255));
					panel_2.setBounds(10, 56, 280, 125);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));

					JScrollPane scrollPane = new JScrollPane();
					panel_2.add(scrollPane, BorderLayout.CENTER);

					tblEmpleadosProyecto = new JTable();
					tblEmpleadosProyecto.setModel(model);
					scrollPane.setViewportView(tblEmpleadosProyecto);
				}
			}
			{
				JLabel label = new JLabel("Fecha Inicio");
				label.setBounds(66, 72, 86, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Fecha Entrega");
				label.setBounds(264, 72, 87, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Tipo");
				label.setBounds(121, 119, 60, 14);
				panel.add(label);
			}
			{
				cbxTipo = new JComboBox();
				cbxTipo.setModel(modelComboTipo);
				cbxTipo.setBackground(new Color(255, 255, 255));
				cbxTipo.setBounds(168, 116, 86, 20);
				panel.add(cbxTipo);
			}

			JLabel lblNewLabel = new JLabel("Lenguaje");
			lblNewLabel.setBounds(280, 119, 72, 14);
			panel.add(lblNewLabel);

			cmbLenguajes = new JComboBox();
			cmbLenguajes.setBackground(new Color(255, 255, 255));
			cmbLenguajes.setModel(modelComboLanguage);
			cmbLenguajes.setBounds(345, 116, 86, 20);
			panel.add(cmbLenguajes);

			JLabel lblNewLabel_1 = new JLabel("Codigo");
			lblNewLabel_1.setBounds(10, 28, 46, 14);
			panel.add(lblNewLabel_1);
			txtCodigo = new JTextField();
			txtCodigo.setText(String.valueOf(Empresa.getInstance().buscarCantidadProyectos()));
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(52, 25, 150, 20);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);

			Date today = new Date();
			spnFinicio = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
			JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spnFinicio, "dd/MM/yyyy");
			spnFinicio.setEditor(editorInicio);
			spnFinicio.setBounds(136, 69, 118, 20);
			DateFormatter formatterInicio = (DateFormatter)editorInicio.getTextField().getFormatter();
			formatterInicio.setAllowsInvalid(false); 
			formatterInicio.setOverwriteMode(true);
			panel.add(spnFinicio);

			Date tomorrow = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(tomorrow);
			c.add(Calendar.DATE, 1);
			tomorrow = c.getTime();
			spnFentrega = new JSpinner();
			spnFentrega.setModel(new SpinnerDateModel(tomorrow, null, null, Calendar.MONTH));
			JSpinner.DateEditor editorEntrega = new JSpinner.DateEditor(spnFentrega, "dd/MM/yyyy");
			spnFentrega.setEditor(editorEntrega);
			spnFentrega.setBounds(345, 69, 118, 20);
			DateFormatter formatterEntrega = (DateFormatter)editorEntrega.getTextField().getFormatter();
			formatterEntrega.setAllowsInvalid(false); 
			formatterEntrega.setOverwriteMode(true);
			panel.add(spnFentrega);
		}
		cargarDatosComboboxLenguaje();
		cargarDatosComboboxTipo();
		if(codigo != null) {
			txtCodigo.setText(codigo);
			txtNombre.setText(nombre);
			spnFinicio.setValue(fechaInicio);
			cbxTipo.setSelectedItem(tipo);
			cmbLenguajes.setSelectedItem(lenguaje);
			spnFentrega.setValue(fechaFin);
			cargarDatosTabla(temp_emp);
		}
	}
	public static void agregarLenguajeProgramador(DefaultTableModel modelProyecto) {
		String[] empleados = {"Cedula", "Nombre", "Puesto"};
		model.setColumnIdentifiers(empleados);
		rows = new Object[model.getColumnCount()];
		try {
			for(int i = 0; i < modelProyecto.getRowCount(); i++) {
				if(checkElements(modelProyecto.getValueAt(i, 1).toString())) {
					rows[0] = modelProyecto.getValueAt(i, 0).toString();
					rows[1] = modelProyecto.getValueAt(i, 1).toString();
					rows[2] = modelProyecto.getValueAt(i, 2).toString();
					model.addRow(rows);
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al llenar los datos en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
	private static boolean checkElements(String elemento) {
		if(model.getRowCount() < 0) {
			return true;
		}
		for(int i = 0; i < model.getRowCount(); i++) {
			if(model.getValueAt(i, 0).toString().equalsIgnoreCase(elemento)) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Empleado> insertarEmpleadoProyecto(DefaultTableModel model) {
		ArrayList<Empleado> arrayEmpleados= new ArrayList<Empleado>();
		Empleado empProv = null;
		if(model.getRowCount() < 0) {
			return arrayEmpleados;
		}
		for(int i = 0; i < model.getRowCount(); i++) {
			empProv = Empresa.getInstance().buscarEmpleado(Integer.parseInt(model.getValueAt(i, 0).toString()));
			arrayEmpleados.add(empProv);
			empProv = null;
		}
		return arrayEmpleados;  
	}

	private static void cargarDatosComboboxLenguaje() {
		String selectSql = "select nombre from Lenguaje;";
		ResultSet resultSet = Empresa.getConexion().getResultSet(selectSql);
		try {
			while(resultSet.next()) {
				modelComboLanguage.addElement(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void cargarDatosComboboxTipo() {
		String selectsql = "select nombre from TipoProyecto;";
		ResultSet resultset = Empresa.getConexion().getResultSet(selectsql);
		try {
			while(resultset.next()) {
				modelComboTipo.addElement(resultset.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	private static void cargarDatosTabla(ArrayList<Empleado> temp_emp) {
		String[] empleados = {"Cedula", "Nombre", "Puesto"};
		model.setColumnIdentifiers(empleados);
		rows = new Object[model.getColumnCount()];
		for(int i = 0; i < temp_emp.size(); i++) {
			rows[0] = temp_emp.get(i).getCedula();
			rows[1] = temp_emp.get(i).getNombre();
			rows[2] = temp_emp.get(i).getClass().getSimpleName().toString();
			model.addRow(rows);
		}
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

	private boolean informacionLlena() {
		if(txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() || tblEmpleadosProyecto.getRowCount() == 0  ||spnFinicio.getValue().toString().isEmpty() || spnFentrega.getValue().toString().isEmpty()) {
			return false;
		} 
		return true;
	}
}
