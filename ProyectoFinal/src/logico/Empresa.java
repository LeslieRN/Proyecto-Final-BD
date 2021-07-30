package logico;

import java.sql.Statement;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList <Cliente> clientes;
	private ArrayList <Contrato> contratos;
	private ArrayList <Empleado> empleados;
	private ArrayList <Proyecto> proyectos;
	private ArrayList<User> usuarios;

	private static User loginUser;
	private static Empresa empresa = null;

	private static int numClientes = 1;
	private static int numContratos = 1;
	private static int numEmpleados = 1;
	private static int numProyectos = 1;
	private static int codigo = 0;
	private static int tipo = 0;
	private static int lenguaje = 0;
	private static String nombre = null;
	private static Date inicio = null;
	private static Date fin = null;
	
	
	private static ArrayList <Empleado> temp = null;
	private static Proyecto ptemp = null;
	private static Boolean enable = false;
	
	// PELIGROSO NO TOCAR
	private static Conexion conexion = null;
	
	public Empresa() {
		super();
		this.clientes = new ArrayList <Cliente>();
		this.contratos = new ArrayList <Contrato>();
		this.empleados = new ArrayList <Empleado>();
		this.proyectos = new ArrayList <Proyecto>();
		this.usuarios = new ArrayList<User>();
	}
	
	public static Empresa getInstance() {
		if(empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
		
	}

	
	// CONEXION PELIGROSO NO TOCAR

	public static Conexion getConexion() {
		return conexion;
	}

	public static void setConexion() {
		Empresa.conexion =  new Conexion();
	}
	
	// CONEXION PELIGROSO NO TOCAR
	
	
	
	public ArrayList<Cliente> getClientes() {
		
		
		
		
		
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
	public ArrayList<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<User> usuarios) {
		this.usuarios = usuarios;
	}

	public static Empresa getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(Empresa empresa) {
		Empresa.empresa = empresa;
	}

	public static int getNumClientes() {
		return numClientes;
	}

	public static void setNumClientes(int numClientes) {
		Empresa.numClientes = numClientes;
	}

	public static int getNumContratos() {
		return numContratos;
	}

	public static void setNumContratos(int numContratos) {
		Empresa.numContratos = numContratos;
	}

	public static int getNumEmpleados() {
		return numEmpleados;
	}

	public static void setNumEmpleados(int numEmpleados) {
		Empresa.numEmpleados = numEmpleados;
	}

	public static int getNumProyectos() {
		return numProyectos;
	}

	public static void setNumProyectos(int numProyectos) {
		Empresa.numProyectos = numProyectos;
	}
	public static int getCodigo() {
		return codigo;
	}

	public static void setCodigo(int codigo) {
		Empresa.codigo = codigo;
	}

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Empresa.nombre = nombre;
	}
	
	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Empresa.loginUser = loginUser;
	}

	public static Date getInicio() {
		return inicio;
	}

	public static void setInicio(Date inicio) {
		Empresa.inicio = inicio;
	}

	public static Date getFin() {
		return fin;
	}

	public static void setFin(Date fin) {
		Empresa.fin = fin;
	}

	public static ArrayList<Empleado> getTemp() {
		return temp;
	}

	public static void setTemp(ArrayList<Empleado> temp) {
		Empresa.temp = temp;
	}

	public static Proyecto getPtemp() {
		return ptemp;
	}

	public static void setPtemp(Proyecto ptemp) {
		Empresa.ptemp = ptemp;
	}

	public static int getTipo() {
		return tipo;
	}
	public static void setTipo(int tipo) {
		Empresa.tipo = tipo;
	}
	public static int getLenguaje() {
		return lenguaje;
	}
	public static void setLenguaje(int lenguaje) {
		Empresa.lenguaje = lenguaje;
	}
	public static Boolean getEnable() {
		return enable;
	}
	public static void setEnable(Boolean enable) {
		Empresa.enable = enable;
	}
	public void insertarEmpleado(Empleado emp) {
		this.empleados.add(emp);
	}
	
	public void insertarContrato(Contrato cont) {
		this.contratos.add(cont);
		numContratos++;
	}
	
	public void insertarProyecto(Proyecto pro) {
		this.proyectos.add(pro);
		numProyectos++;
	}
	
	public void insertarCliente(Cliente cli) {
		this.clientes.add(cli);
		numClientes++;
	}
	
	public boolean insetarUsuario(User user) {
		if(checkSiExisteUser(user.getNombreUsuario())) {
			this.usuarios.add(user);
			return true;
		}
		return false;
	}
	public Cliente buscarCliente(String cedula) {
		Cliente cli = null;
		for(Cliente aux: this.clientes) {
			if(aux.getCedula().equalsIgnoreCase(cedula)) {
				cli = aux;
				return cli;
			}
		}
		return cli;
	}
	
	
	public Contrato buscarContrato(String codigo) {
		Contrato cont = null;
		for(Contrato aux: this.contratos) {
			if((""+aux.getNumeroContrato()).equalsIgnoreCase(codigo)) {
				cont = aux;
				return cont;
			}
		}
		return cont;
	}
	
	
	
	public Contrato buscarContratoProyecto(String codigo) {
		Contrato cont = null;
		for(Contrato aux: this.contratos) {
			if(aux.getProyecto().getNombre().equalsIgnoreCase(codigo)) {
				cont = aux;
				return cont;
			}
		}
		return cont;
	}
	public Proyecto buscarProyecto(String nombre) {
		Proyecto pro = null;
		for(Proyecto aux: this.proyectos) {
			if(aux.getNombre().equalsIgnoreCase(nombre)) {
				pro = aux;
				return pro;
			}
		}
		return pro;
	}
	
	public Empleado buscarEmpleado(String cedula) {
		Empleado emp = null;
		for(Empleado aux: this.empleados) {
			if(aux.getCedula().equalsIgnoreCase(cedula)) {
				emp = aux;
				return emp;
			}
		}
		return emp;
	}
	
	public ArrayList<Empleado> buscarEmpleadoPorLenguaje(String lenguaje) {
		Empleado emp = null;
		ArrayList<Empleado> empA = new ArrayList<Empleado>();
		Programador pro = null;
		for(Empleado aux: this.empleados) {
			if(aux instanceof Programador) {
				pro = (Programador) aux; 
				if(pro.getLenguaje().contains(lenguaje)) {
					empA.add(pro);
				}
			} else if(!(aux instanceof Programador)) {
				empA.add(aux);
			}
		}
		return empA;
	}
	
	public void modificarNombreProyecto(Proyecto pro) {
		String nombre = pro.getNombre();
		for(Empleado aux: pro.getEmpleados()) {
			aux.setNombreProyecto(nombre);
		}
	}
	public boolean checkSiExisteUser(String usuario) {
		for(User aux: this.usuarios) {
			if(aux.getNombreUsuario().equalsIgnoreCase(usuario)) {
				return false;
			}
		}
		return true;
	}
	public boolean checkUserData(String nombreUsuario, String passwordUsuario) {
		boolean result= false;
		String selectSql = "SELECT U.codigo,U.nombre,U.contraseña,T.nombre  from Usuario as U inner join TipoUsuario as T on U.idTipoUsuario = T.idTipoUsuario where U.nombre='"+nombreUsuario+"' and U.contraseña='"+passwordUsuario+"';";
		ResultSet resultSet = null;
		User usuario = null;
		try {
			Statement statement = SQLConnection.getConnection().createStatement();
			resultSet = statement.executeQuery(selectSql);
			if(!resultSet.isBeforeFirst()) {
				result = false;
			} else {
				while(resultSet.next()) {
					usuario = new User(Integer.toString(resultSet.getInt(1)),resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
				}
				result = true;
				Empresa.loginUser = usuario;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public float calcularMontoTotalContrato(long daysBetween, ArrayList<Empleado> emp) {
		float total = 0;
		if(daysBetween == 0 ) {
			daysBetween = 1;
		}
		for(int i = 0; i < emp.size(); i++) {			
			
			total += emp.get(i).getSalario();						
		}				
		total = total * emp.size() * 8 * daysBetween;
		total += (total*0.30);
		return total;
	}
	public float calcularGananciasPorMes(int mes) {
		float total = 0;
		for(Contrato aux: this.contratos) {
			int mesP = aux.getProyecto().getFechaTerminacionReal().getMonth();
			if(aux.getProyecto().getEstado().equals(false) && mesP == mes) {
				total += aux.getMontoTotal();
				
			}
		}
		return total;
	}
}
