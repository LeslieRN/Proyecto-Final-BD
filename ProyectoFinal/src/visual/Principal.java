package visual;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JFrame;


import logico.Empresa;
import logico.Proyecto;


import java.awt.SystemColor;

import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dimension;
	private JMenuItem menuCEmpleado;
	private JMenuItem menuMEmpleado;
	private JMenuItem menuCProyecto;
	private JMenuItem menuMProyecto;
	private JMenuItem menuMContrato;
	private JMenuItem menuMCliente;
	private JMenuItem menuCUsuarios;
	private JMenuItem menuMUsuarios;
	private JMenu menuAdministrador;
	private JMenu mnNewMenu_3;
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Empresa.getConexion().cerrarConexion();
			}
		});
		dimension = super.getToolkit().getScreenSize();
		super.setSize(dimension.width, dimension.height);
		setLocationRelativeTo(null);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("Men\u00FA de Opciones");
		menuBar.setBackground(new Color(51, 102, 153));
		menuBar.setForeground(new Color(255, 255, 255));
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Empleado");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu);

		menuCEmpleado = new JMenuItem("Insertar Empleado");
		menuCEmpleado.setIcon(new ImageIcon(Principal.class.getResource("/icons/add.png")));
		menuCEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Empleado emp = new Empleado();
				emp.setModal(true);
				emp.setVisible(true);
			}
		});
		menuCEmpleado.setForeground(new Color(51, 102, 153));
		menuCEmpleado.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(menuCEmpleado);

		menuMEmpleado = new JMenuItem("Mostrar Empleado");
		menuMEmpleado.setIcon(new ImageIcon(Principal.class.getResource("/icons/list (1).png")));
		menuMEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarEmpleado emp = new MostrarEmpleado();
				emp.setModal(true);
				emp.setVisible(true);
			}
		});
		menuMEmpleado.setForeground(new Color(51, 102, 153));
		menuMEmpleado.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(menuMEmpleado);

		JMenu mnNewMenu_1 = new JMenu("Proyecto & Contrato");
		mnNewMenu_1.setForeground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu_1);

		menuCProyecto = new JMenuItem("Crear Proyecto");
		menuCProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoProyecto pro = new NuevoProyecto(null, null, null, null, null, null, null);
				pro.setModal(true);
				pro.setVisible(true);
			}
		});
		menuCProyecto.setIcon(new ImageIcon(Principal.class.getResource("/icons/add.png")));
		menuCProyecto.setForeground(new Color(51, 102, 153));
		menuCProyecto.setBackground(new Color(255, 255, 255));
		mnNewMenu_1.add(menuCProyecto);

		menuMProyecto = new JMenuItem("Mostrar Proyectos");
		menuMProyecto.setIcon(new ImageIcon(Principal.class.getResource("/icons/list (1).png")));
		menuMProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarProyecto mProyecto = new MostrarProyecto();
				mProyecto.setModal(true);
				mProyecto.setVisible(true);
			}
		});
		menuMProyecto.setForeground(new Color(51, 102, 153));
		menuMProyecto.setBackground(new Color(255, 255, 255));
		mnNewMenu_1.add(menuMProyecto);

		menuMContrato = new JMenuItem("Mostrar Contratos");
		menuMContrato.setIcon(new ImageIcon(Principal.class.getResource("/icons/list (1).png")));
		menuMContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarContrato mCont = new MostrarContrato();
				mCont.setModal(true);
				mCont.setVisible(true);
			}
		});
		menuMContrato.setForeground(new Color(51, 102, 153));
		menuMContrato.setBackground(new Color(255, 255, 255));
		mnNewMenu_1.add(menuMContrato);

		JMenu mnNewMenu_2 = new JMenu("Cliente");
		mnNewMenu_2.setForeground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu_2);

		menuMCliente = new JMenuItem("Mostrar Cliente");
		menuMCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarCliente m = new MostrarCliente();
				m.setVisible(true);
				;
			}
		});
		menuMCliente.setIcon(new ImageIcon(Principal.class.getResource("/icons/list (1).png")));
		menuMCliente.setForeground(new Color(51, 102, 153));
		menuMCliente.setBackground(new Color(255, 255, 255));
		mnNewMenu_2.add(menuMCliente);

		menuAdministrador = new JMenu("Administrador");
		menuAdministrador.setForeground(new Color(255, 255, 255));
		menuBar.add(menuAdministrador);

		menuCUsuarios = new JMenuItem("Crear Usuarios");
		menuCUsuarios.setForeground(new Color(51, 102, 153));
		menuCUsuarios.setBackground(Color.WHITE);
		menuCUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/icons/add.png")));
		menuCUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuevoUsuario user = new NuevoUsuario();
				user.setModal(true);
				user.setVisible(true);
			}
		});
		menuAdministrador.add(menuCUsuarios);

		menuMUsuarios = new JMenuItem("Mostrar Usuarios");
		menuMUsuarios.setForeground(new Color(51, 102, 153));
		menuMUsuarios.setBackground(Color.WHITE);
		menuMUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/icons/list (1).png")));
		menuMUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarUsuarios userMostrar = new MostrarUsuarios();
				userMostrar.setModal(true);
				userMostrar.setVisible(true);
			}
		});
		menuAdministrador.add(menuMUsuarios);
		
		mnNewMenu_3 = new JMenu("Reportes");
		mnNewMenu_3.setForeground(new Color(255, 255, 255));
		mnNewMenu_3.setBackground(new Color(245, 245, 245));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ganancias");
		mntmNewMenuItem.setIcon(new ImageIcon(Principal.class.getResource("/icons/bagofmoney_dollar_4399.png")));
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reportes report = new Reportes();
				report.setModal(true);
				report.setVisible(true);
			}
		});
		mntmNewMenuItem.setForeground(new Color(70, 130, 180));
		mnNewMenu_3.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		if(Empresa.getLoginUser().getTipo().equalsIgnoreCase("Secretaria/o")) {
			menuCEmpleado.setEnabled(false);
			menuMProyecto.setEnabled(false);
			menuCProyecto.setEnabled(false);
			menuCUsuarios.setEnabled(false);
			menuAdministrador.setEnabled(false);
		}
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}
}