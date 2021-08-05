package logico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Empresa {
	private static User loginUser;
	private static Empresa empresa = null;
	private static Conexion conexion = null;

	public Empresa() {
		super();
	}

	public static Empresa getInstance() {
		if(empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}

	public static Conexion getConexion() {
		return conexion;
	}

	public static void setConexion() {
		Empresa.conexion =  new Conexion();
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Empresa.loginUser = loginUser;
	}

	public void insertarEmpleado(Empleado emp) {
		int idPuesto = 0, experiencia = 0;
		String puesto = emp.getClass().getSimpleName();
		String idPuestoSQL = "SELECT idPuesto FROM Puesto where nombre='"+puesto+"';";
		ResultSet resultSet = Empresa.getConexion().getResultSet(idPuestoSQL);
		try {
			while(resultSet.next()) {
				idPuesto=resultSet.getInt(1);
			}
			if(emp instanceof Disenador) {
				experiencia = ((Disenador) emp).getExperiencia();
			}
			String insertSql = "INSERT INTO Empleado VALUES("+emp.getCedula()+",'"+emp.getNombre()+"','"+emp.getApellido()+"','"+emp.getSexo()+"', '"+emp.getDireccion()+"', 'Excelente', "+emp.getSalario()+", "+emp.getPrecioHora()+", "+experiencia+","+idPuesto+");";
			Empresa.getConexion().executeInsert(insertSql);
			if(emp instanceof Programador) {
				for(int i = 0; i < ((Programador)emp).getLenguaje().size(); i++) {
					String languageSQL = "INSERT INTO EmpleadoLenguaje VALUES((SELECT idLenguaje from LenguajeDeProgramacion where nombre='"+((Programador)emp).getLenguaje().get(i)+"'),"+emp.getCedula()+");";
					Empresa.getConexion().executeInsert(languageSQL);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertarContrato(Contrato cont) {
		String insertSql = "insert into Contrato values("+cont.getMontoTotal()+","+0+","+cont.getCliente().getCedula()+","+Empresa.getLoginUser().getCodigo()+");";
		Empresa.getConexion().executeInsert(insertSql);
	}

	public void insertarProyecto(Proyecto pro, Contrato con) {
		int idL = 0, idP = 0, idProyecto = 0;
		String empProyectoSQL= "";
		String idLenguaje = "SELECT idLenguaje FROM LenguajeDeProgramacion where nombre='"+pro.getLenguaje()+"';";
		String idTipoProyecto = "SELECT id_TipoProyecto FROM TipoProyecto where nombre='"+pro.getTipo()+"';";
		ResultSet resultSet = Empresa.getConexion().getResultSet(idLenguaje);
		ResultSet resultSet2 = Empresa.getConexion().getResultSet(idTipoProyecto);
		try {
			while(resultSet.next() && resultSet2.next()) {
				idL=resultSet.getInt(1);
				idP=resultSet2.getInt(1);
			}
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
	}

	public void insertarCliente(Cliente cliente) {
		String selectSql = "insert into Cliente "
				+ "values('"+ cliente.getCedula() +"', '"+ cliente.getNombre()+"', '"+ cliente.getTelefono()+"', '"+cliente.getDireccion()+"');";
		Empresa.getConexion().executeInsert(selectSql);
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
			e.printStackTrace();
		}
		return cli;
	}
	
	public int buscarCantidadProyectos() {
		int cantidad = 0;
		ResultSet resultSet = Empresa.getConexion().getResultSet("select count(*) from Proyecto");
		try {
			while(resultSet.next()) {
				cantidad = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (cantidad+=1); 
	}
	public Empleado buscarEmpleado(int cedula) {
		String selectSql = "select E.cedula, E.nombre, E.apellido, E.direccion, E.sexo, E.salario, E.evaluacion, E.precioHora, E.experiencia from Empleado as E where E.cedula = "+cedula+"";
		ResultSet resultSet = Empresa.getConexion().getResultSet(selectSql);
		Empleado emp = null;
		try {
			while(resultSet.next()) {
				emp = new Disenador(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), " ",resultSet.getString(5),resultSet.getFloat(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	public boolean checkSiExisteUser(String usuario) {
		ResultSet resultSet = Empresa.getConexion().getResultSet("select count(US.nombre) from Usuario as Us where US.nombre = '" + usuario + "'");
		try {
			resultSet.next();
			if(resultSet.getInt(1)==0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
				Empresa.setLoginUser(usuario);
			}
		} catch (SQLException e) {
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
		total *=1.30;
		return total;
	}
}
