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
		Jugador ganador = new Jugador("");
		int turno = 0;
		double[] puntuaciones = new double[participantes.size()];
		for (Jugador jugador : participantes) {
			System.out.println("Turno del jugador: " + jugador.getAlias());
			jugador.roundPlayed();
			puntuaciones[turno++] = play();
		}
		ganador = checkround(puntuaciones, participantes);
		System.out.println("ganador: " + ganador);

	}

	private Jugador checkround(double[] puntuaciones, ArrayList<Jugador> participantes) {
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
		for (Jugador j : TestJuegos.participantes)
			System.out.println(j.toString());
		try {
			TestJuegos.participantes.remove(Teclado.leerEntero("introduce indice de jugador a eliminar"));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("indice de jugador no valido");
		}

	}

	/**
	 * juega tres rondas con los jugadores que elija
	 * 
	 * @throws MazoVacioException
	 */
	void playRounds() throws MazoVacioException, SinJugadoresException {
		if (participantes.size() == 0)
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
	 * Inicializa lista con el total de jugadores
	 */
	void generateAllPlayers() {
		int totalJugadores = Teclado
				.leerEntero("indica total de jugadores, recuerda que no tienen por que jugar todos a la vez");
		for (int i = 0; i < totalJugadores; i++) {
			TestJuegos.jugadores.add(new Jugador("jugador" + (i + 1)));
		}
	}

	/**
	 * Inicializa lista con los jugadores que participan en la ronda
	 */
	void addPlayer() {
		int opcion;
		try {
			System.out.println(TestJuegos.jugadores.toString());
			do {
				opcion = Teclado.leerEntero("indica que jugadores van a participar en esta ronda (pulsa 0 o "
						+ (TestJuegos.jugadores.size() + 1) + " para salir)");
				// Si ya está el jugador, no dejamos que vuelva a introducirlo
				if (!TestJuegos.participantes.contains(TestJuegos.jugadores.get(opcion - 1)))
					TestJuegos.participantes.add(TestJuegos.jugadores.get(opcion - 1));
			} while (opcion < 0 || opcion < TestJuegos.jugadores.size());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("vamos a jugar");
		}
		System.out.println(participantes.toString());
	}

	/**
	 * muestra el ranking de jugadores
	 */
	void ranking() {
		ArrayList<Jugador> ranking = (ArrayList<Jugador>) TestJuegos.jugadores.clone();
		Collections.sort(ranking);
		for (Jugador jugador : ranking) {
			System.out.println(jugador);
		}
	}

}
