package sieteYmedia;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
	private ArrayList<Carta> mazo;

	Baraja() {
		setMazo(new ArrayList<Carta>());
		generatePile();
	}
	/**
	 * recorre el mazo asignando todas las cartas de la baraja espannola
	 */
	private void generatePile() {
		for (Palos p : Palos.values()) {
			for (Figuras f : Figuras.values()) {
				getMazo().add(new Carta(p, f));
			}
		}
	}

	/**
	 * extrae la primera carta
	 * 
	 * @return Carta
	 */
	Carta extractCard() {
		return getMazo().get(0);
	}

	/**
	 * elimina la primera carta
	 */
	void pullOut() {
		getMazo().remove(0);

	}
	void shuffle() {
		Collections.shuffle(getMazo());

	}
	ArrayList<Carta> getMazo() {
		return mazo;
	}
	 void setMazo(ArrayList<Carta> mazo) {
		this.mazo = mazo;
	}

}
