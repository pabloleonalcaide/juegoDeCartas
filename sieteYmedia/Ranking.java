package sieteYmedia;

import java.util.ArrayList;

public class Ranking {

	private Jugador[] rankingJuego;

	public Ranking(ArrayList<Jugador> jugadores) {
		rankingJuego = new Jugador[jugadores.size()];

		int i = 0;
		for (Jugador j : jugadores) {
			rankingJuego[i] = j;
			i++;
		}

		for (int j = 0; j < rankingJuego.length - 1; j++) {
			if (rankingJuego[j].getPartidasGanadas() < rankingJuego[j + 1].getPartidasGanadas()) {
				Jugador variableauxiliar = rankingJuego[j];
				rankingJuego[j] = rankingJuego[j + 1];
				rankingJuego[j + 1] = variableauxiliar;

			}
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (rankingJuego.length == 0)
			sb.append("\n Aun no tenemos jugadores");
		int i = 1;
		for (Jugador jugador : rankingJuego) {
			sb.append("\n" + i + ". " + jugador);
			i++;
		}

		return sb.toString();
	}

}