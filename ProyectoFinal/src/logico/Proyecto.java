package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Proyecto implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList <Empleado> empleados;
	private String nombre;
	private String tipo;
	private int estado;
	private String lenguaje;
	private int extendido;
	private String fechaInicio;
	private String fechaEntrega;
	private String fechaTerminacionReal;
	
	public Proyecto(String nombre, String tipo, int estado, String lenguaje, int extendido,
			String fechaInicio2, String fechaFin, String fechaFin2, ArrayList <Empleado> empleados) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
		this.lenguaje = lenguaje;
		this.extendido = extendido;
		this.fechaInicio = fechaInicio2;
		this.fechaEntrega = fechaFin;
		this.fechaTerminacionReal = fechaFin2;
		this.empleados = empleados;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public int getExtendido() {
		return extendido;
	}

	public void setExtendido(int extendido) {
		this.extendido = extendido;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getFechaTerminacionReal() {
		return fechaTerminacionReal;
	}

	public void setFechaTerminacionReal(String fechaTerminacionReal) {
		this.fechaTerminacionReal = fechaTerminacionReal;
	}
	
	
	


}
