package sieteYmedia;

import java.util.*;

import utiles.*;

/**
 * Juego de Cartas (Test Para juego 7 y Media)
 * 
 * @author pablo
 * @version 1.0
 */
public class TestJuegos {
	private static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private static ArrayList<Jugador> participantes = new ArrayList<Jugador>();
	private static Partida partida;
	private static Menu menu = new Menu("Siete y Media", new String[] {
			"jugar", "mostrar ranking" });

	public static void main(String[] args) {
		// los jugadores se definen al inicio del programa y se mantienen hasta
		// el final
		// en este caso no se ha dado opcion a introducir o eliminar jugadores.
		generateAllPlayers();
		do {
			switch (menu.gestionar()) {
			case 1:
				try {
					playRounds();
				} catch (MazoVacioException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				ranking();
				break;
			case 3:
				System.out.println("hasta luego");
				return;
			}
		} while (menu.getOpcion() < menu.getSALIR());

	}

	/**
	 * muestra el ranking de jugadores
	 */
	private static void ranking() {
		Collections.sort(jugadores);

		for (Jugador jugador : jugadores) {
			System.out.println(jugador);
		}
	}

	/**
	 * juega tres rondas con los jugadores que elija
	 * 
	 * @throws MazoVacioException
	 */
	private static void playRounds() throws MazoVacioException {
		// al inicio de cada partida introducimos los participantes
		generateActualPlayers();
		int rounds;
		do {
			rounds = Teclado.leerEntero("por favor, indica numero de rondas");
		} while (rounds < 1);
		for (int i = 0; i < rounds; i++) {
			System.out.println("ronda " + (i + 1));
			partida.ronda();
		}// reasignamos el numero de partidas jugadas y ganadas
		givePoints();// vaciamos la lista de participantes cuando termina la
						// partida
		participantes = null;
	}

	/**
	 * asigna la puntuación de los participantes de cada ronda a la lista
	 * general de jugadores
	 */
	private static void givePoints() {
		for (Jugador j : jugadores) {
			for (Jugador p : participantes) {
				if (j.equals(p)) {
					j.setPartidasJugadas(p.getPartidasJugadas());
					j.setPartidasGanadas(p.getPartidasGanadas());
				}
			}
		}
	}

	/**
	 * Inicializa lista con el total de jugadores
	 */
	private static void generateAllPlayers() {

		int totalJugadores = Teclado
				.leerEntero("indica total de jugadores, recuerda que no tienen por que jugar todos a la vez");
		for (int i = 0; i < totalJugadores; i++) {
			// podríamos solicitar nombre del jugador, pero se ha automatizado
			// para agilizar las pruebas
			jugadores.add(new Jugador("jugador" + (i + 1)));
		}
	}

	/**
	 * Inicializa lista con los jugadores que participan en la ronda
	 */
	private static void generateActualPlayers() {
		int opcion;
		try {
			System.out.println(jugadores.toString());
			do {
				opcion = Teclado
						.leerEntero("indica que jugadores van a participar en esta ronda (pulsa 0 o "
								+ (jugadores.size() + 1) + " para salir)");
				// Si ya está el jugador, no dejamos que vuelva a introducirlo
				if (!participantes.contains(jugadores.get(opcion - 1)))
					participantes.add(jugadores.get(opcion - 1));
			} while (opcion < 0 || opcion < jugadores.size());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("vamos a jugar");
		}
		System.out.println(participantes.toString());
		partida = new Partida(participantes);
	}

}
