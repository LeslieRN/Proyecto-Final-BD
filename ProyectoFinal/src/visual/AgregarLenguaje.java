package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Empresa;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class AgregarLenguaje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblLenguajes;
	private JTable tblAceptados;
	private JButton btnInsertar;
	private JButton btnRegresar;
	private JButton btnInsertarL;
	private JButton btnCancelar;
	private static DefaultTableModel model, modelAceptado;
	private static Object[] rows, rowsAceptados;
	private JScrollPane scrollPane_1;
	private static ArrayList<String> lenguaje;
	private int lenguajeS;
	private String lenguajeSN;


	public AgregarLenguaje() {
		setBounds(100, 100, 671, 443);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		String columnas[] = {"Lenguaje"};
		model = new DefaultTableModel();
		modelAceptado = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		modelAceptado.setColumnIdentifiers(columnas);
		this.lenguajeS = 0;
		model.setColumnIdentifiers(columnas);
		modelAceptado.setColumnIdentifiers(columnas);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)));
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);
			panel.setBounds(10, 48, 635, 313);
			contentPanel.add(panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(10, 11, 259, 277);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panel_1.add(scrollPane, BorderLayout.CENTER);
					tblLenguajes = new JTable();
					tblLenguajes.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int seleccion = -1;
							seleccion = tblLenguajes.getSelectedRow();
							if(seleccion > -1) {
								lenguajeSN = tblLenguajes.getValueAt(seleccion, 0).toString();
								btnInsertar.setEnabled(true);
							}
						}
					});
					tblLenguajes.setModel(model);
					TableColumnModel columM = tblLenguajes.getColumnModel();
					columM.getColumn(0).setPreferredWidth(258);
					tblLenguajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					tblLenguajes.getTableHeader().setReorderingAllowed(false);
					tblLenguajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(tblLenguajes);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(363, 11, 259, 277);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel_1.add(scrollPane_1, BorderLayout.CENTER);
				
				tblAceptados = new JTable();
				tblAceptados.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int seleccion = -1;
						seleccion = tblAceptados.getSelectedRow();
						if(seleccion > -1) {
							lenguajeS = seleccion;
							btnRegresar.setEnabled(true);
						}
					}
				});
				tblAceptados.setModel(modelAceptado);
				TableColumnModel columM = tblAceptados.getColumnModel();
				columM.getColumn(0).setPreferredWidth(258);
				tblAceptados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tblAceptados.getTableHeader().setReorderingAllowed(false);
				tblAceptados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblAceptados.setBackground(new Color(255, 255, 255));
				scrollPane_1.setViewportView(tblAceptados);
			}
			{
				btnInsertar = new JButton("");
				btnInsertar.setIcon(new ImageIcon(AgregarLenguaje.class.getResource("/icons/right-arrow (1).png")));
				btnInsertar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						rowsAceptados = new Object[modelAceptado.getColumnCount()];
						rowsAceptados[0]= lenguajeSN;
						if(checkElements(lenguajeSN)) {
							rowsAceptados[0]= lenguajeSN;
							modelAceptado.addRow(rowsAceptados);
						}
						btnInsertar.setEnabled(false);
					}
				});
				btnInsertar.setEnabled(false);
				btnInsertar.setBorderPainted(false);
				btnInsertar.setBackground(new Color(204, 204, 204));
				btnInsertar.setBounds(285, 110, 58, 39);
				panel.add(btnInsertar);
			}
			{
				btnRegresar = new JButton("");
				btnRegresar.setIcon(new ImageIcon(AgregarLenguaje.class.getResource("/icons/previous.png")));
				btnRegresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modelAceptado.removeRow(lenguajeS);
						btnRegresar.setEnabled(false);
						
					}
				});
				btnRegresar.setEnabled(false);
				btnRegresar.setBorderPainted(false);
				btnRegresar.setBackground(new Color(204, 204, 204));
				btnRegresar.setBounds(284, 160, 58, 39);
				panel.add(btnRegresar);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 102, 153));
		panel.setBounds(0, 361, 655, 45);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(AgregarLenguaje.class.getResource("/icons/home (2).png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(51, 102, 153));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBounds(457, 0, 89, 45);
		panel.add(btnCancelar);
		
		btnInsertarL = new JButton("");
		btnInsertarL.setIcon(new ImageIcon(AgregarLenguaje.class.getResource("/icons/diskette (1).png")));
		btnInsertarL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado.agregarLenguajeProgramador(modelAceptado);
				tblAceptados.removeAll();
				dispose();
			}
		});
		btnInsertarL.setBorderPainted(false);
		btnInsertarL.setForeground(new Color(255, 255, 255));
		btnInsertarL.setBackground(new Color(51, 102, 153));
		btnInsertarL.setBounds(556, 0, 89, 45);
		panel.add(btnInsertarL);
		
		JLabel lblNewLabel = new JLabel("Lenguajes Disponibles");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(73, 23, 164, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lenguajes Aceptados");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(431, 23, 164, 14);
		contentPanel.add(lblNewLabel_1);
		cargarLenguajes();
	}
	public static void cargarLenguajes() {
		
		ResultSet resultSet = Empresa.getConexion().getResultSet("select nombre from Lenguaje");
		rows = new Object[model.getColumnCount()];
		model.setRowCount(0);
		try {
			while(resultSet.next()) {
				rows[0] = resultSet.getString(1);
				model.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean checkElements(String elemento) {
		if(modelAceptado.getRowCount() < 0) {
			return true;
		}
		for(int i = 0; i < modelAceptado.getRowCount(); i++) {
			 if(modelAceptado.getValueAt(i, 0).toString().equalsIgnoreCase(elemento)) {
				 return false;
			 }
		}
		return true;
	}
}
