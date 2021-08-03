package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reportes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbTodo;
	private JRadioButton rdbEntreFechas;
	private JSpinner spnInicio;
	private JSpinner spnFinal;
	private JButton btnGenerarReport;
	private JButton btnGRActivos;
	private JButton btnCancelar;

	public Reportes() {
		setTitle("Reportes");
		setBounds(100, 100, 362, 440);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setForeground(new Color(0, 51, 102));
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Proyectos Activos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 283, 325, 74);
			contentPanel.add(panel);
			
			btnGRActivos = new JButton("Generar Reporte");
			btnGRActivos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ReporteLenguaje reporteL = new ReporteLenguaje();
					reporteL.setModal(true);
					reporteL.setVisible(true);
				}
			});
			btnGRActivos.setForeground(new Color(0, 51, 102));
			panel.add(btnGRActivos);
		}
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 51, 102));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ganancias por Lenguaje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 18, 325, 254);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 22, 305, 83);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		rdbTodo = new JRadioButton("General");
		rdbTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbTodo.isSelected()) {
					rdbEntreFechas.setSelected(false);
					spnFinal.setEnabled(false);
					spnInicio.setEnabled(false);
					btnGenerarReport.setEnabled(true);
				}
			}
		});
		rdbTodo.setForeground(new Color(0, 51, 102));
		rdbTodo.setBackground(new Color(255, 255, 255));
		rdbTodo.setBounds(29, 29, 109, 23);
		panel_1.add(rdbTodo);
		
		rdbEntreFechas = new JRadioButton("Elegir fecha");
		rdbEntreFechas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbEntreFechas.isSelected()) {
					rdbTodo.setSelected(false);
					spnFinal.setEnabled(true);
					spnInicio.setEnabled(true);
					btnGenerarReport.setEnabled(true);
				}
			}
		});
		rdbEntreFechas.setForeground(new Color(0, 51, 102));
		rdbEntreFechas.setBackground(new Color(255, 255, 255));
		rdbEntreFechas.setBounds(167, 29, 109, 23);
		panel_1.add(rdbEntreFechas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 119, 305, 83);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		spnInicio = new JSpinner();
		Date today = new Date();
		spnInicio = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spnInicio, "dd/MM/yyyy");
		spnInicio.setEditor(editorInicio);
		spnInicio.setEnabled(false);
		spnInicio.setBounds(40, 33, 92, 20);
		panel_2.add(spnInicio);
		
		Date tomorrow = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(tomorrow);
		c.add(Calendar.DATE, 1);
		tomorrow = c.getTime();
		spnFinal = new JSpinner(new SpinnerDateModel(tomorrow, null, null, Calendar.MONTH));
		JSpinner.DateEditor editorEntrega = new JSpinner.DateEditor(spnFinal, "dd/MM/yyyy");
		spnFinal.setEditor(editorEntrega);
		spnFinal.setEnabled(false);
		spnFinal.setBounds(172, 33, 92, 20);
		panel_2.add(spnFinal);
		
		JLabel lblNewLabel = new JLabel("Fecha Inicio");
		lblNewLabel.setForeground(new Color(0, 51, 102));
		lblNewLabel.setBounds(40, 11, 92, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setForeground(new Color(0, 51, 102));
		lblFechaFin.setBounds(172, 8, 92, 14);
		panel_2.add(lblFechaFin);
		
		btnGenerarReport = new JButton("Generar Reporte");
		btnGenerarReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteGanancias reporteG = new ReporteGanancias();
				reporteG.setModal(true);
				reporteG.setVisible(true);
			}
		});
		btnGenerarReport.setForeground(new Color(0, 51, 102));
		btnGenerarReport.setEnabled(false);
		btnGenerarReport.setBounds(185, 213, 119, 23);
		panel.add(btnGenerarReport);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
