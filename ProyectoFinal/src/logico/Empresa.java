package logico;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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


	private static ArrayList <Empleado> temp = null;


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

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Empresa.loginUser = loginUser;
	}

	public void insertarEmpleado(Empleado emp) {
		this.empleados.add(emp);
	}

	public void insertarContrato(Contrato cont) {
		this.contratos.add(cont);
		numContratos++;
	}

	public void insertarProyecto(Proyecto pro, Contrato con) {
		int idL = 0, idP = 0, idProyecto = 0;
		String empProyectoSQL= "";
		String idLenguaje = "SELECT idLenguaje FROM Lenguaje where nombre='"+pro.getLenguaje()+"';";
		String idTipoProyecto = "SELECT id_TipoProyecto FROM TipoProyecto where nombre='"+pro.getTipo()+"';";
		ResultSet resultSet = Empresa.getConexion().getResultSet(idLenguaje);
		ResultSet resultSet2 = Empresa.getConexion().getResultSet(idTipoProyecto);
		try {
			while(resultSet.next() && resultSet2.next()) {
				idL=resultSet.getInt(1);
				idP=resultSet2.getInt(1);
			}
			/*Insertar en tabla de proyecto*/
			String selectSql = "insert into Proyecto (numeroContrato, nombre, fechaIncio, fechaFin, fechaEntrega, estado, extendido, idLenguaje, id_TipoProyecto)"
					+ "values('"+con.getNumeroContrato()+"','"+con.getNombreProyecto()+"', convert(date,'"+ pro.getFechaInicio() +"',111), convert(date,'"+pro.getFechaEntrega()+"',111), convert(date,'"+pro.getFechaTerminacionReal()+"', 111),"+pro.getEstado()+","+pro.getExtendido()+","+idL+","+idP+");";
			Empresa.getConexion().executeInsert(selectSql);
			String sqlSelectLast = "SELECT idProyecto FROM Proyecto where numeroContrato = "+con.getNumeroContrato()+";";
			ResultSet resultSet3 = Empresa.getConexion().getResultSet(sqlSelectLast);

			while(resultSet3.next()) {
				idProyecto=resultSet3.getInt(1);
			}
			for(int i = 0; i < pro.getEmpleados().size(); i++) {
				empProyectoSQL = "INSERT INTO ProyectoEmpleado values("+Integer.parseInt(pro.getEmpleados().get(i).getCedula())+", "+idProyecto+");";
				Empresa.getConexion().executeInsert(empProyectoSQL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.proyectos.add(pro);
		numProyectos++;
	}

	public void insertarCliente(Cliente cliente) {
		String selectSql = "insert into Cliente "
				+ "values('"+ cliente.getCedula() +"', '"+ cliente.getNombre()+"', '"+ cliente.getTelefono()+"', '"+cliente.getDireccion()+"');";
		Empresa.getConexion().executeInsert(selectSql);
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
		String selectSql = "select C.cedula,C.nombre,C.telefono, C.direccion, COUNT(Con.numeroContrato)  as cantidadContratos "
				+ "from Cliente as C left join Contrato as Con "
				+ "on C.cedula = Con.cedula "
				+ "where C.cedula="+cedula+" "
				+"group by  C.cedula,C.nombre,C.telefono, C.direccion;";
		ResultSet resultSet = Empresa.getConexion().getResultSet(selectSql);
		Cliente cli = null;
		try {
			while(resultSet.next()) {
				cli = new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
				cli.setCantiProyectos(resultSet.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Empleado buscarEmpleado(int cedula) {
		String selectSql = "select E.cedula, E.nombre, E.apellido, E.direccion, E.sexo, E.salario, E.evaluacion, E.precioHora, E.experiencia from Empleado as E where E.cedula = "+cedula+"";
		ResultSet resultSet = Empresa.getConexion().getResultSet(selectSql);
		Empleado emp = null;
		try {
			while(resultSet.next()) {
				emp = new Disenador(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), " ",resultSet.getString(5),resultSet.getFloat(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getInt(9));
				System.out.println("Encontrado!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	public ArrayList<Empleado> buscarEmpleadoPorLenguaje(String lenguaje) {
		String selectSql = "select E.cedula, E.nombre, P.nombre from Empleado as E inner join Puesto as P on E.idPuesto = P.idPuesto inner join EmpleadoLenguaje as EL on E.cedula = EL.cedula inner join Lenguaje as L on EL.idLenguaje = L.idLenguaje  where L.nombre = '"+lenguaje+"'";
		ResultSet resultSet = Empresa.getConexion().getResultSet(selectSql);
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

	public boolean checkSiExisteUser(String usuario) {
		for(User aux: this.usuarios) {
			if(aux.getNombreUsuario().equalsIgnoreCase(usuario)) {
				return false;
			}
		}
		return true;
	}
	public boolean checkUserData(String nombreUsuario, String passwordUsuario) {
		String selectSql = "SELECT U.codigo,U.nombre,U.contraseña,T.nombre  from Usuario as U inner join TipoUsuario as T on U.idTipoUsuario = T.idTipoUsuario where U.nombre='"+nombreUsuario+"' and U.contraseña='"+passwordUsuario+"';";
		ResultSet resultSet = Empresa.getConexion().getResultSet(selectSql);
		boolean result= false;
		User usuario = null;
		try {
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
	/*public float calcularGananciasPorMes(int mes) {
		float total = 0;
		for(Contrato aux: this.contratos) {
			int mesP = aux.getProyecto().getFechaTerminacionReal().getMonth();
			if(aux.getProyecto().getEstado() ==0 && mesP == mes) {
				total += aux.getMontoTotal();

			}
		}
		return total;
	}*/
}
