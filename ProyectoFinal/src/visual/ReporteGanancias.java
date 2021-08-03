package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Empresa;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReporteGanancias extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JTable tblGanancias;
	
	private static DefaultTableModel model;
	private static Object[] rows;
	
	public ReporteGanancias(String fechaEntregaA, String fechaEntregaB) {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		
		String columns[] = {"Lenguaje", "Mes", "Total de ganancias"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					tblGanancias = new JTable();
					tblGanancias.setModel(model);
					tblGanancias.setBackground(new Color(255, 255, 255));
					tblGanancias.getTableHeader().setReorderingAllowed(false);
					
					
					
					scrollPane.setViewportView(tblGanancias);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		cargarGanancias(fechaEntregaA, fechaEntregaB);
	}
	
	private void cargarGanancias(String fechaEntregaA, String fechaEntregaB) {
		
		ResultSet resultSet = null;
		if(fechaEntregaA != null) {
			resultSet = Empresa.getConexion().getResultSet("select sum(C.montoTotal) as Ganancia, datename(m,P.fechaEntrega) as Mes, L.nombre as Lenguaje from Proyecto as P inner join Contrato as C 	on P.numeroContrato = C.numeroContrato inner join Lenguaje as L on P.idLenguaje = L.idLenguaje \r\n"
					+ "where P.fechaEntrega BETWEEN '"+ fechaEntregaA +"' and '" + fechaEntregaB + "' and P.estado = 0  \r\n"
					+ "group by datename(m,P.fechaEntrega), L.nombre order by Mes asc;");
		} else {
			resultSet = Empresa.getConexion().getResultSet("select sum(C.montoTotal) as Ganancia, datename(m,P.fechaEntrega) as Mes, L.nombre as Lenguaje from Proyecto as P inner join Contrato as C 	on P.numeroContrato = C.numeroContrato inner join Lenguaje as L on P.idLenguaje = L.idLenguaje where P.estado = 0 group by datename(m,P.fechaEntrega), L.nombre order by Mes asc");
		}
		
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		try {
			while(resultSet.next()) {
				rows[0] = resultSet.getString(1);
				rows[1] = resultSet.getString(2);
				rows[2] = resultSet.getString(3);
				model.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
