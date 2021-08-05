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

import logico.Contrato;
import logico.Disenador;
import logico.Empresa;
import logico.Jefe;
import logico.Planificador;
import logico.Programador;
import logico.Proyecto;


import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class MostrarProyecto extends JDialog {
	private JButton btnCancelar;
	private JTable tblMostrarProyecto;
	private static DefaultTableModel model;
	private static Object[] rows;
	private JComboBox cmbEstado;
	private String[] proyectos;
	private JButton btnProrroga;
	private JButton btnFinalizar;

	public MostrarProyecto() {
		setTitle("Mostrar proyectos");
		setBounds(100, 100, 670, 462);
		setLocationRelativeTo(null);
		this.proyectos = new String[5];
		String columns[] = {"Nombre", "Tipo", "Lenguaje", "Fecha Inicio", "Fecha Terminacion"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		getContentPane().setLayout(null);
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 380, 654, 43);
			getContentPane().add(panel_1);
			panel_1.setLayout(null);
			panel_1.setBackground(new Color(51, 102, 153));
			{
				btnCancelar = new JButton("");
				btnCancelar.setIcon(new ImageIcon(MostrarProyecto.class.getResource("/icons/home (2).png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.setBackground(new Color(51, 102, 153));
				btnCancelar.setBounds(565, 0, 89, 43);
				panel_1.add(btnCancelar);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(null);
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 0, 654, 380);
			getContentPane().add(panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Mostrar Proyectos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(10, 11, 634, 358);
				panel.add(panel_1);
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBackground(Color.WHITE);
					panel_2.setBounds(10, 63, 614, 284);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						panel_2.add(scrollPane, BorderLayout.NORTH);
						{
							tblMostrarProyecto = new JTable();
							tblMostrarProyecto.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									int seleccion = -1;
									seleccion = tblMostrarProyecto.getSelectedRow();
									if(seleccion > -1) {
										proyectos[0] = tblMostrarProyecto.getValueAt(seleccion, 0).toString();
										proyectos[1] = tblMostrarProyecto.getValueAt(seleccion, 1).toString();
										proyectos[2] = tblMostrarProyecto.getValueAt(seleccion, 2).toString();
										proyectos[3] = tblMostrarProyecto.getValueAt(seleccion, 3).toString();
										proyectos[4] = tblMostrarProyecto.getValueAt(seleccion, 4).toString();
										btnFinalizar.setEnabled(true);
										btnProrroga.setEnabled(true);
									}
								}
							});
							tblMostrarProyecto.setModel(model);
							tblMostrarProyecto.getTableHeader().setReorderingAllowed(false);
							scrollPane.setViewportView(tblMostrarProyecto);
						}
					}
				}

				JLabel lblNewLabel = new JLabel("Estado:");
				lblNewLabel.setBounds(10, 38, 74, 14);
				panel_1.add(lblNewLabel);

				cmbEstado = new JComboBox();
				cmbEstado.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarProyectos(cmbEstado.getSelectedItem().toString());
					}
				});
				cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"Todo", "En Proceso", "Finalizado"}));
				cmbEstado.setBounds(73, 32, 115, 20);
				panel_1.add(cmbEstado);

				btnFinalizar = new JButton("Finalizar");
				btnFinalizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Empresa.getConexion().executeInsert("update Proyecto set estado=0 where nombre = '" + proyectos[0] + "'");	
						cargarProyectos(cmbEstado.getSelectedItem().toString());
					}
				});
				btnFinalizar.setEnabled(false);
				btnFinalizar.setForeground(new Color(0, 0, 0));
				btnFinalizar.setBorderPainted(false);
				btnFinalizar.setBackground(new Color(204, 204, 204));
				btnFinalizar.setBounds(535, 22, 89, 30);
				panel_1.add(btnFinalizar);

				btnProrroga = new JButton("Prorroga");
				btnProrroga.setForeground(new Color(0, 0, 0));
				btnProrroga.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						CambiarFecha cambiarFecha = new CambiarFecha(proyectos[0],proyectos[4]);
						cambiarFecha.setModal(true);
						cambiarFecha.setVisible(true);
						cargarProyectos(cmbEstado.getSelectedItem().toString());
					}
				});
				btnProrroga.setEnabled(false);
				btnProrroga.setBorderPainted(false);
				btnProrroga.setBackground(new Color(204, 204, 204));
				btnProrroga.setBounds(436, 22, 89, 30);
				panel_1.add(btnProrroga);
			}
		}
		cargarProyectos(cmbEstado.getSelectedItem().toString());
	}
	
	public static void cargarProyectos(String selectedItem) {
		ResultSet resultSet = Empresa.getConexion().getResultSet("select PR.idProyecto, PR.estado from Proyecto as PR");
		model.setRowCount(0);
		try {
			while(resultSet.next()) {
				if(selectedItem.equalsIgnoreCase("En Proceso")) {
					if(resultSet.getInt(2)==1) {
						model.addRow(insertInRow(resultSet.getInt(1)));
					}
				} else if (selectedItem.equalsIgnoreCase("Finalizado")) {
					if(resultSet.getInt(2)==0) {
						model.addRow(insertInRow(resultSet.getInt(1)));
					}
				}else if (selectedItem.equalsIgnoreCase("Todo")) {
					model.addRow(insertInRow(resultSet.getInt(1)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Object[] insertInRow(int idProyecto) {
		ResultSet resultSet = Empresa.getConexion().getResultSet("select PR.nombre, TP.nombre, LE.nombre, PR.fechaIncio, PR.fechaFin from (Proyecto as PR inner join TipoProyecto as TP on PR.id_TipoProyecto = TP.id_TipoProyecto) inner join Lenguaje as LE on LE.idLenguaje = PR.idLenguaje where PR.idProyecto = " + idProyecto);
		rows = new Object[model.getColumnCount()];
		try {
			resultSet.next();
			rows[0] = resultSet.getString(1);
			rows[1] = resultSet.getString(2);
			rows[2] = resultSet.getString(3);
			rows[3] = resultSet.getDate(4);
			rows[4] = resultSet.getDate(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
}
