package logico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	// Connect to your database.
	// Replace server name, username, and password with your credentials
	private static String connectionUrl =
			"jdbc:sqlserver://192.168.77.24:1433;"
					+ "database=proyectoFinal_grupo1;"
					+ "user=lrodriguez;"
					+ "password=bD@2021L;"
					+ "encrypt=true;"
					+ "trustServerCertificate=true;"
					+ "loginTimeout=30;";
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Connection open!!!\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]] 

		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();

			// Create and execute a SELECT SQL statement.
			String selectSql = "SELECT count(*) as total from Jugador";
			resultSet = statement.executeQuery(selectSql);

			// Print results from select statement
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("total"));// + " " + resultSet.getString(3));
				//System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
		try {
			if (!connection.equals(null) && !connection.isClosed()) {
				connection.close();
				System.out.println("Connection closed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
		return connection;
	}
	public static void closeConnection(Connection con) {
		try {
			con.close();
			System.out.println("Connection closed!!!\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

