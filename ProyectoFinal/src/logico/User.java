package logico;


public class User {
	
	private String codigo;
	private String nombreUsuario;
	private String passwordUsuario;
	private String tipo;
	private static int codigoUsuario = 2;
	public User(String codigo, String nombreUsuario, String passwordUsuario, String tipo) {
		super();
		this.codigo = codigo;
		this.nombreUsuario = nombreUsuario;
		this.passwordUsuario = passwordUsuario;
		this.tipo = tipo;
		codigoUsuario++;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPasswordUsuario() {
		return passwordUsuario;
	}
	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}
	public static int getCodigoUsuario() {
		return codigoUsuario;
	}
	public static void setCodigoUsuario(int codigoUsuario) {
		User.codigoUsuario = codigoUsuario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
