package tp5.ejercicio5;

public class Persona {

	private String tipo; // jubilado o empleado
	private String nombre;
	private String domicilio;
	
	public Persona(String tipo, String nombre, String domicilio) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.domicilio = domicilio;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	
	
}
