package sieteYmedia;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
	ArrayList<Carta> mazo;

	Baraja() {
		mazo = new ArrayList<Carta>();
		generatePile();
	}
	/**
	 * recorre el mazo asignando todas las cartas de la baraja espannola
	 */
	private void generatePile() {
		for (Palos p : Palos.values()) {
			for (Figuras f : Figuras.values()) {
				mazo.add(new Carta(p, f));
			}
		}
	}

	/**
	 * extrae la primera carta
	 * 
	 * @return Carta
	 */
	Carta extractCard() {
		return mazo.get(0);
	}

	/**
	 * elimina la primera carta
	 */
	void pullOut() {
		mazo.remove(0);

	}
	public void shuffle() {
		Collections.shuffle(mazo);

	}

}
