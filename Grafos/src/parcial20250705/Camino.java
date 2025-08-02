package parcial20250705;

import java.util.List;

public class Camino {
	
	private List<String> camino;
	private int peligro;
	
	public Camino(List<String> camino, int peligro) {
		this.camino = camino;
		this.peligro = peligro;
	}

	public List<String> getCamino() {
		return camino;
	}

	public void setCamino(List<String> camino) {
		this.camino = camino;
	}

	public int getPeligro() {
		return peligro;
	}

	public void setPeligro(int peligro) {
		this.peligro = peligro;
	}
}
