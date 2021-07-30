package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import logico.Contrato;
import logico.Empresa;
import logico.Proyecto;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class CambiarFecha extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnCambiar;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField txtFechaEntregaInicial;
	private JFormattedTextField txtFechaEntregaA;
	private String fechaEntregaInicial;
	private String codigoProyecto;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CambiarFecha dialog = new CambiarFecha();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 * @param proyectos 
	 */
	public CambiarFecha(String nombre,String proyectosFecha) {
		this.codigoProyecto = nombre;
		this.fechaEntregaInicial = proyectosFecha;
		setBounds(100, 100, 386, 231);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 102, 153), new Color(51, 102, 153)), "Prorrogar Fecha Entrega", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				lblNewLabel = new JLabel("Fecha Entrega Inicial:");
				lblNewLabel.setBounds(10, 51, 134, 14);
				panel.add(lblNewLabel);
			}
			
			txtFechaEntregaInicial = new JTextField();
			txtFechaEntregaInicial.setEnabled(false);
			txtFechaEntregaInicial.setText(fechaEntregaInicial);
			txtFechaEntregaInicial.setBounds(195, 48, 155, 20);
			panel.add(txtFechaEntregaInicial);
			txtFechaEntregaInicial.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Fecha Entrega Actualizada:");
			lblNewLabel_1.setBounds(10, 98, 175, 14);
			panel.add(lblNewLabel_1);
			
			txtFechaEntregaA = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
			txtFechaEntregaA.setValue(new java.util.Date());
			txtFechaEntregaA.setBounds(195, 95, 155, 20);
			panel.add(txtFechaEntregaA);
			txtFechaEntregaA.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 102, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCambiar = new JButton("");
				btnCambiar.setIcon(new ImageIcon(CambiarFecha.class.getResource("/icons/diskette (1).png")));
				btnCambiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Proyecto pro = Empresa.getInstance().buscarProyecto(codigoProyecto);
						if(pro != null) {
							Date fechaEntregaA;
							try {
								fechaEntregaA = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaEntregaA.getText());
								//pro.setFechaEntrega(fechaEntregaA);
								pro.setExtendido(1);
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Ocurrio un error", "Informacion", JOptionPane.ERROR_MESSAGE);
							}
							JOptionPane.showMessageDialog(null, "Proyecto Modificado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				{
					btnCancelar = new JButton("");
					btnCancelar.setIcon(new ImageIcon(CambiarFecha.class.getResource("/icons/home (2).png")));
					btnCancelar.setBorderPainted(false);
					btnCancelar.setForeground(new Color(255, 255, 255));
					btnCancelar.setBackground(new Color(51, 102, 153));
					btnCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					btnCancelar.setActionCommand("Cancel");
					buttonPane.add(btnCancelar);
				}
				btnCambiar.setBorderPainted(false);
				btnCambiar.setForeground(new Color(255, 255, 255));
				btnCambiar.setBackground(new Color(51, 102, 153));
				btnCambiar.setActionCommand("OK");
				buttonPane.add(btnCambiar);
				getRootPane().setDefaultButton(btnCambiar);
			}
		}
	}

}
