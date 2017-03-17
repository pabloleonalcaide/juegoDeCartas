package sieteYmedia;

import java.util.*;

import utiles.Teclado;

/**
 * @author pablo
 * @version 1.0
 */
public class Partida {
	private Baraja baraja;
	private ArrayList<Jugador> participantes;
	private static final double SIETEYMEDIA = 7.5;

	Partida(ArrayList<Jugador> participantes) {
		this.participantes = participantes;
	}

	/**
	 * ejecuta cada ronda de la partida, comprobando quien gana en cada ronda,
	 * al inicio de cada ronda se toma una nueva baraja, se mezcla y se van
	 * eliminando las cartas jugadas
	 * @throws MazoVacioException 
	 */
	void ronda() throws MazoVacioException {
		//cada vez que comienza la partida cogemos una baraja nueva y la barajamos
		baraja = new Baraja();
		Collections.shuffle(baraja.mazo);
		int turno = 0;
		int ganador = 0;
		do {
			System.out.println("Turno del jugador: "
					+ participantes.get(turno).getAlias());
			participantes.get(turno).roundPlayed();
			// suma 1 a partidas jugadas
			play(participantes.get(turno));
			System.out.println(participantes.get(turno).getPuntos());
			ganador = checkWinner(turno, ganador);
			turno++;
		} while (turno < participantes.size()||makePerfect(turno-1));
		// hasta que recorra toda la lista de jugadores participantes
		participantes.get(ganador).winRound();
		System.out.println("ganador: " + participantes.get(ganador));
	}

	private boolean makePerfect(int turno) {
		return participantes.get(turno).getPuntuacion()==SIETEYMEDIA;
	}

	/**
	 * 
	 * @param turno
	 * @param ganador
	 * @return ganador jugador que vaya ganando la ronda en ese momento
	 */
	private int checkWinner(int turno, int ganador) {
		if (makePerfect(turno))
			ganador = turno;
		else if (participantes.get(turno).getPuntuacion() < 7.5
				&& participantes.get(turno).getPuntuacion() > participantes
						.get(ganador).getPuntuacion())
			ganador = turno;
		participantes.get(turno).resetPoints();
		return ganador;
	}

	/**
	 * El jugador saca carta hasta que decida plantarse o gane/pierda
	 * @throws MazoVacioException 
	 */
	private void play(Jugador jugador) throws MazoVacioException {
		do {
			jugador.sumarPuntuacion(takeCard());
			// saca una carta del montÃ³n, acumula puntos y quita la carta
		} while (Teclado.deseaContinuar("desea otra carta? S - N")
				&& checkPoints(jugador));

	}

	/**
	 * comprueba si el jugador se ha pasado, si ha ganado o ninguna de ambas
	 */
	private boolean checkPoints(Jugador jugador) {
		if (jugador.getPuntuacion() > SIETEYMEDIA) {
			return false;
		} else if (jugador.getPuntuacion() == SIETEYMEDIA) {
			return false;
		} else
			return true;
	}

	/**
	 * Saca una carta del mazo y posteriormente la remueve
	 * 
	 * @return valor valor de la figura
	 * @throws MazoVacioException 
	 */
	private double takeCard() throws MazoVacioException {
		if (baraja.mazo.isEmpty())throw new MazoVacioException("Se vacio el mazo, lo siento"); 
			Carta carta = baraja.extractCard();
			System.out.println("Ha salido " + carta.toString());
			double valor = carta.getFigura().getValor();
			//carta mostrada, carta que sacamos del mazo
			baraja.pullOut();
			return valor;
		
	}


}

