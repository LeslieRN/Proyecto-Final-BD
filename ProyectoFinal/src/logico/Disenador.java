package logico;

public class Disenador extends Empleado{
	
	private int experiencia;
	public Disenador(String cedula, String nombre, String apellido, String direccion,String telefono, String sexo, float salario, String evaluacion, float precioHora, int experiencia) {
		super(cedula, nombre, apellido, direccion,telefono, sexo, salario, evaluacion, precioHora);
		this.experiencia = experiencia;
		
	}
	
	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	@Override
	public void modificarNombreProyecto() {
		

	}

}
