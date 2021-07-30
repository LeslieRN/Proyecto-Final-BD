package logico;

import java.io.Serializable;

public class Planificador extends Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int frecuenciaTareas;

	public Planificador(String cedula, String nombre, String apellido, String direccion,String telefono, String sexo, float salario,String evaluacion, float precioHora, int frecuenciaTareas) {
		super(cedula, nombre, apellido, direccion,telefono, sexo, salario, evaluacion, precioHora);
		
		this.frecuenciaTareas = frecuenciaTareas;
	}

	
	public int getFrecuenciaTareas() {
		return frecuenciaTareas;
	}


	public void setFrecuenciaTareas(int frecuenciaTareas) {
		this.frecuenciaTareas = frecuenciaTareas;
	}


	public void modificarNombreProyecto() {
		

	}

}
