package logico;

public class Contrato {

	private int numeroContrato;
	private String cedulaCliente;
	private String nombreProyecto; //se puede cambiar a tipo proyecto, a espera de la siguiente clase
	private float montoTotal;
	private float penalidad;
	private Cliente cliente;
	private Proyecto proyecto;

	public Contrato(int numeroContrato, String cedulaCliente, String nombreProyecto, Cliente cliente, Proyecto proyecto, float montoTotal) {
		super();
		this.numeroContrato = numeroContrato;
		this.cedulaCliente = cedulaCliente;
		this.nombreProyecto = nombreProyecto;
		this.cliente = cliente; 
		this.proyecto = proyecto;
		this.montoTotal = montoTotal;

	}

	public int getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(int numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public float getPenalidad() {
		return penalidad;
	}
	public void setPenalidad(float penalidad) {
		this.penalidad = penalidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
