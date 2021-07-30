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
import java.awt.EventQueue;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

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
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		//>setBounds(100, 100, 1016, 496);
		/*addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ileOutputStream empresa2;
				ObjectOutputStream empresaWrite;
				try {
					empresa2 = new  FileOutputStream("empresa.dat");
					empresaWrite = new ObjectOutputStream(empresa2);
					empresaWrite.writeObject(Empresa.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});*/
		dimension = super.getToolkit().getScreenSize();
		super.setSize(dimension.width, dimension.height);
		setLocationRelativeTo(null);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String columns[] = {"Num. Contrato","Nombre proyecto" ,"Cedula Cliente", "Nombre Cliente", "Fecha Entrega"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		JMenuBar menuBar = new JMenuBar();
		//menuBar.setSize(dimension.width, dimension.width-100);
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
				/*String codigo, String nombre, Date fechaInicio,Date fechaFin,int tipo,int lenguaje,int valor*/
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
		menuMUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/icons/list (1).png")));
		menuMUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarUsuarios userMostrar = new MostrarUsuarios();
				userMostrar.setModal(true);
				userMostrar.setVisible(true);
			}
		});
		menuAdministrador.add(menuMUsuarios);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		/*if(Empresa.getLoginUser().getTipo().equalsIgnoreCase("Secretario")) {
			menuCEmpleado.setEnabled(false);
			menuMProyecto.setEnabled(false);
			menuCProyecto.setEnabled(false);
			menuCUsuarios.setEnabled(false);
			menuAdministrador.setEnabled(false);
		}*/
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(142, 38, 413, 300);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		DefaultPieDataset data = new DefaultPieDataset();
		float python = 0;
		float	cnumero = 0;
		float javascript = 0;
		float ruby = 0;
		float cplusplus = 0;
		float c = 0;
		float java = 0;
		float angular = 0;

		for(Proyecto p : Empresa.getInstance().getProyectos()) {

			switch(p.getLenguaje()) {

			case "Python":
				python++;
				break;

			case "C#":
				cnumero++;
				break;

			case "JavaScript":
				javascript++;
				break;

			case "Ruby":
				ruby++;
				break;

			case "C++":
				cplusplus++;
				break;

			case "C":
				c++;
				break;

			case "Java":
				java++;
				break;

			case "Angular":
				angular++;
				break;

			}
		}

		int size = Empresa.getInstance().getProyectos().size();

        data.setValue("Python", (python));
        data.setValue("C#", (cnumero));
        data.setValue("JavaScript", (javascript));
        data.setValue("Ruby", (ruby));
        data.setValue("C++", (cplusplus));
        data.setValue("C", (c));
        data.setValue("Java", (java));
        data.setValue("Angular", (angular));

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Proyectos Activos", 
         data, 
         true, 
         true, 
         false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setSize(413, 300);
		chartPanel.setLocation(0, 0);
        panel_1.add(chartPanel);

		panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(697, 23, 500, 330);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(392, 387, 555, 300);
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(model);
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        String meses[] = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sep.", "Oct.", "Nov.", "Dic."};
        for(int i = 1; i <= 12; i++) {
        	line_chart_dataset.addValue(Empresa.getInstance().calcularGananciasPorMes(i), "ganacias", meses[i-1]);
        }


        JFreeChart chartLine=ChartFactory.createLineChart("Ganancias",
                "Mes","ganacias",line_chart_dataset,PlotOrientation.VERTICAL,
                true,true,false); 

        ChartPanel chartLinePanel = new ChartPanel(chartLine);
        CategoryPlot plot = chartLine.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        plot.setRenderer(renderer);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        panel_2.add(chartLinePanel);

		cargarProyectosEnFechaEntrega();
	}

	public static void cargarProyectosEnFechaEntrega() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date date = new Date(); 
	    String empresa;
		String finalFecha = "";
		for(int i = 0; i < Empresa.getInstance().getContratos().size(); i++) {
			empresa = dtf.format(Empresa.getInstance().getContratos().get(i).getProyecto().getFechaEntrega());
			if(empresa.equalsIgnoreCase(dtf.format(date)) && Empresa.getInstance().getContratos().get(i).getProyecto().getEstado() == true) {
				rows[0] = Empresa.getInstance().getContratos().get(i).getNumeroContrato();
				rows[1] = Empresa.getInstance().getContratos().get(i).getNombreProyecto();
				rows[2] = Empresa.getInstance().getContratos().get(i).getCedulaCliente();
				rows[3] = Empresa.getInstance().getContratos().get(i).getCliente().getNombre();
				finalFecha = dtf.format(Empresa.getInstance().getContratos().get(i).getProyecto().getFechaEntrega());
				rows[4] = finalFecha;
				model.addRow(rows);
			}
		}

	}
}