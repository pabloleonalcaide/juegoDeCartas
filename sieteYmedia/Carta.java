package sieteYmedia;

public class Carta {
	private Figuras figura;
	private Palos palo;
	
	public Carta(Palos palo, Figuras figura){
		setPalo(palo);
		setFigura(figura);
	}
	 Figuras getFigura() {
		return figura;
	}
	private void setFigura(Figuras figura) {
		this.figura = figura;
	}
	 Palos getPalo() {
		return palo;
	}
	private void setPalo(Palos palo) {
		this.palo = palo;
	}

	@Override
	public String toString() {	
		return figura + " de " + palo;
}

}
