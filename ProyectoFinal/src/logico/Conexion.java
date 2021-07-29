package logico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	private String query;
	private String connectionUrl = "jdbc:sqlserver://192.168.77.24:1433;"
			+ "database=proyectoFinal_grupo1;"
			+ "user=dperalta;"
			+ "password=grupo1_12345;"
			+ "encrypt=true;"
			+ "trustServerCertificate=true;"
			+ "loginTimeout=30;";
	private ResultSet resultSet = null;
	private Connection connection = null;
	
	public Conexion(String query) {
		super();
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void iniciarConexion() {
		try {
			this.connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setQuery(String query) {
		this.query = query;
	}

	public ResultSet getResultSet() {
		Statement statement;
		try {
			statement = connection.createStatement();
			this.resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
