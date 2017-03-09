package sieteYmedia;

public enum Figuras {
	AS(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6), SIETE(7), SOTA(0.5), CABALLO(0.5), REY(0.5);
	double valor;
	
	Figuras(double valor){
		setValor(valor);
	}

	 double getValor() {
		return valor;
	}

	private void setValor(double valor) {
		this.valor = valor;
	}
}
