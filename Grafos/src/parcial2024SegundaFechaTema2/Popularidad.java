package parcial2024SegundaFechaTema2;

public class Popularidad {
	private int cantUsuarios;
	private boolean popular;
	
	public Popularidad(int cantUsuarios, boolean popular) {
		this.cantUsuarios = cantUsuarios;
		this.popular = popular;
	}
	
	public Popularidad() {
		// TODO Auto-generated constructor stub
	}

	public int getCantUsuarios() {
		return cantUsuarios;
	}
	
	public void setCantUsuarios(int cantUsuarios) {
		this.cantUsuarios = cantUsuarios;
	}
	
	public boolean isPopular() {
		return popular;
	}
	
	public void setPopular(boolean popular) {
		this.popular = popular;
	}
	
	
}
