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
		baraja = new Baraja();
		baraja.shuffle();
	}

	/**
	 * @throws MazoVacioException
	 */
	void ronda() throws MazoVacioException {
		Jugador ganador;
		int turno = 0;
		double[] puntuaciones = new double[participantes.size()];
		for (Jugador jugador : participantes) {
			System.out.println("Turno del jugador: " + jugador.getAlias());
			jugador.roundPlayed();
			puntuaciones[turno++] = play();
		}
		ganador = checkround(puntuaciones);
		ganador.winRound();
		System.out.println("ganador: " + ganador);

	}

	private Jugador checkround(double[] puntuaciones) {
		Jugador ganador = null;
		double puntosGanador = 0;
		for (int i = 0; i < puntuaciones.length; i++) {
			if (puntuaciones[i] > puntosGanador && puntuaciones[i] < SIETEYMEDIA) {
				puntosGanador = puntuaciones[i];
				ganador = participantes.get(i);
				
				if (puntosGanador == SIETEYMEDIA) {
					return ganador;
				}
			}
		}
		
		return ganador;

	}

	/**
	 * El jugador saca carta hasta que decida plantarse o gane/pierda
	 * 
	 * @throws MazoVacioException
	 */
	private double play() throws MazoVacioException {
		double puntuacion = 0;
		do {
			puntuacion += takeCard();
		} while (Teclado.deseaContinuar("desea otra carta? S - N") && puntuacion < SIETEYMEDIA);

		return puntuacion;
	}

	/**
	 * Saca una carta del mazo y posteriormente la remueve
	 * 
	 * @return valor valor de la figura
	 * @throws MazoVacioException
	 */
	private double takeCard() throws MazoVacioException {
		if (baraja.getMazo().isEmpty())
			throw new MazoVacioException("Se vacio el mazo, lo siento");
		Carta carta = baraja.extractCard();

		System.out.println("Ha salido " + carta.toString());
		double valor = carta.getValor();
		if (carta.getValor() > SIETEYMEDIA)
			System.out.println("perdiste");
		if (carta.getValor() == SIETEYMEDIA)
			System.out.println("ganaste!");
		return valor;

	}

	void deletePlayer() {
		try {
			for (Jugador j : participantes)
			System.out.println(j.toString());
			participantes.remove(Teclado.leerEntero("introduce indice de jugador a eliminar"));
		} catch (Exception e) {
			System.out.println("imposible eliminar");
		}

	}

	/**
	 * juega tres rondas con los jugadores que elija
	 * 
	 * @throws MazoVacioException
	 */
	void playRounds() throws MazoVacioException, SinJugadoresException {
		if (participantes.isEmpty())
			throw new SinJugadoresException("introduce jugadores para jugar");
		int rounds;
		do {
			rounds = Teclado.leerEntero("por favor, indica numero de rondas");
		} while (rounds < 1);
		for (int i = 0; i < rounds; i++) {
			System.out.println("ronda " + (i + 1));
			ronda();
		}
	}

	/**
	 * Inicializa lista con los jugadores que participan en la ronda
	 */
	void addPlayer() {
		// CORREGIR ESTE METODO, SE OCUPA DE INTRODUCIR UN JUGADOR A LA LISTA,
		// COMPROBANDO QUE EL NOMBRE
		// NO EXISTE YA
	}

	/**
	 * muestra el ranking de jugadores
	 */
	void ranking() {
		ArrayList<Jugador> ranking = (ArrayList<Jugador>) participantes.clone();
		Collections.sort(ranking);
		for (Jugador jugador : ranking) {
			System.out.println(jugador);
		}
	}

}
