package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Conexion;
import logico.Disenador;
import logico.Empresa;
import logico.Jefe;
import logico.Planificador;
import logico.Programador;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MostrarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblEmpleado;
	private static DefaultTableModel model;
	private static Object[] rows;
	private JButton btnCancelar;
	private JComboBox cmbEmpleados;

	public MostrarEmpleado() {
		setTitle("Mostrar empleados");
		setBounds(100, 100, 590, 467);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 49, 564, 341);
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 564, 385);
				panel.add(scrollPane);
				{
					tblEmpleado = new JTable();
					tblEmpleado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] encabezados = {"Cedula", "Nombre", "Apellido", "Pago por Hora", "Clasificacion"};
					model.setColumnIdentifiers(encabezados);
					tblEmpleado.setModel(model);
					tblEmpleado.getTableHeader().setReorderingAllowed(false);
					scrollPane.setViewportView(tblEmpleado);
				}
			}
		}
		
		cmbEmpleados = new JComboBox();
		cmbEmpleados.setModel(new DefaultComboBoxModel(new String[] {"Todo", "Jefe", "Disenador", "Programador", "Planificador", "Secretario"}));
		cmbEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarEmpleados(cmbEmpleados.getSelectedItem().toString());
			}
		});
		cmbEmpleados.setBounds(88, 21, 132, 20);
		contentPanel.add(cmbEmpleados);
		
		JLabel lblNewLabel = new JLabel("Filtrar por");
		lblNewLabel.setBounds(10, 24, 100, 14);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelar = new JButton("");
				btnCancelar.setIcon(new ImageIcon(MostrarEmpleado.class.getResource("/icons/home (2).png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBorderPainted(false);
				btnCancelar.setBackground(new Color(51, 102, 153));
				btnCancelar.setForeground(new Color(255, 255, 255));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		cargarEmpleados(cmbEmpleados.getSelectedItem().toString());
	}

	public static void cargarEmpleados(String selectedItem) {
		
		ResultSet resultSet = Empresa.getConexion().getResultSet("select * from Empleado");
		
		model.setRowCount(0);
		try {
			while(resultSet.next()) {
				if(selectedItem.equalsIgnoreCase("Jefe")) {
					if(resultSet.getInt(10) == 4) {
						model.addRow(insertInRow(resultSet.getInt(1)));
					}
				} else if (selectedItem.equalsIgnoreCase("Programador")) {
					if(resultSet.getInt(10) == 2) {
						model.addRow(insertInRow(resultSet.getInt(1)));
					}
				}else if (selectedItem.equalsIgnoreCase("Disenador")) {
					if(resultSet.getInt(10) == 3) {
						model.addRow(insertInRow(resultSet.getInt(1)));
					}
				} else if (selectedItem.equalsIgnoreCase("Planificador")) {
					if(resultSet.getInt(10) == 1) {
						model.addRow(insertInRow(resultSet.getInt(1)));
					}
				} else if (selectedItem.equalsIgnoreCase("Todo")) {
					model.addRow(insertInRow(resultSet.getInt(1)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Object[] insertInRow(int cedula) {
		ResultSet resultSet = Empresa.getConexion().getResultSet("select E.cedula, E.nombre, E.apellido, E.precioHora, P.nombre from Empleado as E inner join Puesto as P on E.idPuesto = P.idPuesto where E.cedula = " + cedula);
		rows = new Object[model.getColumnCount()];
		try {
			resultSet.next();
			rows[0] = resultSet.getInt(1);
			rows[1] = resultSet.getString(2);
			rows[2] = resultSet.getString(3);
			rows[3] = resultSet.getString(4);
			rows[4] = resultSet.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
}
