package logico;

import java.util.ArrayList;

public class Programador extends Empleado {
	
	private ArrayList<String> lenguaje;

	public Programador(String cedula, String nombre, String apellido, String direccion, String telefono, String sexo, float salario, String evaluacion, float precioHora,ArrayList<String> lenguajes) {
		super(cedula, nombre, apellido, direccion, telefono,sexo, salario, evaluacion, precioHora);
		
		this.lenguaje = lenguajes;
	}
	
	public ArrayList<String> getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(ArrayList<String> lenguaje) {
		this.lenguaje = lenguaje;
	}

	@Override
	public void modificarNombreProyecto() {
		

	}

}
