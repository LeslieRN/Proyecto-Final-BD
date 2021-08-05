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

import logico.Empresa;

import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MostrarContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JTable tblMostrarProyectos;
	private static DefaultTableModel model;
	private static Object[] rows;

	public MostrarContrato() {
		setTitle("Mostrar contratos");
		setBounds(100, 100, 723, 422);
		setLocationRelativeTo(null);
		String columns[] = {"Num. Contrato", "Cedula Cliente", "Nombre Cliente", "Fecha Inicio", "Fecha Entrega", "Monto Total"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Mostrar Contratos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					tblMostrarProyectos = new JTable();
					tblMostrarProyectos.setModel(model);
					tblMostrarProyectos.setBackground(new Color(255, 255, 255));
					tblMostrarProyectos.getTableHeader().setReorderingAllowed(false);
					scrollPane.setViewportView(tblMostrarProyectos);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelar = new JButton("");
				btnCancelar.setIcon(new ImageIcon(MostrarContrato.class.getResource("/icons/home (2).png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBorderPainted(false);
				btnCancelar.setForeground(new Color(255, 255, 255));
				btnCancelar.setBackground(new Color(51, 102, 153));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		cargarContratos();
	}
	private static void cargarContratos() {
		
		ResultSet resultSet = Empresa.getConexion().getResultSet("select CO.numeroContrato, CL.cedula, CL.nombre, PR.fechaIncio, PR.fechaEntrega, CO.montoTotal from (Contrato as CO inner join Cliente as CL on CO.cedula = CL.cedula) inner join Proyecto as PR on PR.numeroContrato = CO.numeroContrato");
		
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		try {
			while(resultSet.next()) {
				rows[0] = resultSet.getInt(1);
				rows[1] = resultSet.getInt(2);
				rows[2] = resultSet.getString(3);
				rows[3] = resultSet.getDate(4);
				rows[4] = resultSet.getDate(5);
				rows[5] = resultSet.getInt(6);
				model.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
