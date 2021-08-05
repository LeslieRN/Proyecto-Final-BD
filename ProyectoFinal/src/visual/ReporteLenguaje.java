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
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReporteLenguaje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblLenguaje;
	private JButton btnCancelar;
	
	private static DefaultTableModel model;
	private static Object[] rows;

	public ReporteLenguaje() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Reporte de lenguajes");
		String columns[] = {"Lenguaje de programacion", "Cantidad"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
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
					tblLenguaje = new JTable();
					tblLenguaje.setModel(model);
					tblLenguaje.setBackground(new Color(255, 255, 255));
					tblLenguaje.getTableHeader().setReorderingAllowed(false);
					scrollPane.setViewportView(tblLenguaje);
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
		cargarLenguajes();
	}

	private void cargarLenguajes() {
		ResultSet resultSet = Empresa.getConexion().getResultSet("select count(*) as 'Cantidad Total de Proyectos Activos', L.nombre as Lenguaje from Proyecto as P inner join Lenguaje as L on P.idLenguaje = L.idLenguaje where P.estado = 1 group by L.nombre;");
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		try {
			while(resultSet.next()) {
				rows[0] = resultSet.getString(1);
				rows[1] = resultSet.getString(2);
				model.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
