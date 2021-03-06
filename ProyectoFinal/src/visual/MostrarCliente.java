package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Empresa;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MostrarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblMostrarClientes;
	private DefaultTableModel model;
	private Object[] rows;

	public MostrarCliente() {
		setTitle("Mostrar clientes");
		setBounds(100, 100, 590, 467);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		String columns[] = {"Cedula", "Nombre","Telefono", "Direccion", "Proyectos Activos"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Mostrar Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 38, 554, 337);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 21, 534, 305);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			tblMostrarClientes = new JTable();
			tblMostrarClientes.setModel(model);
			tblMostrarClientes.setBackground(new Color(255, 255, 255));
			tblMostrarClientes.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(tblMostrarClientes);
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton cancelButton = new JButton("");
				cancelButton.setIcon(new ImageIcon(MostrarCliente.class.getResource("/icons/home (2).png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarClientes();
	}
	
	private void cargarClientes() {
		ResultSet resultSet = Empresa.getConexion().getResultSet("select CL.cedula, CL.nombre, CL.telefono, CL.direccion, count(PR.idProyecto) as proyectosActivos from Cliente as CL inner join (Proyecto as PR inner join Contrato as CO on PR.numeroContrato = CO.numeroContrato) on CL.cedula = CO.cedula group by CL.cedula, CL.nombre, CL.telefono, CL.direccion");
		rows = new Object[model.getColumnCount()];
		model.setRowCount(0);
		try {
			while(resultSet.next()) {
				rows[0] = resultSet.getInt(1);
				rows[1] = resultSet.getString(2);
				rows[2] = resultSet.getString(3);
				rows[3] = resultSet.getString(4);
				rows[4] = resultSet.getInt(5);
				model.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
