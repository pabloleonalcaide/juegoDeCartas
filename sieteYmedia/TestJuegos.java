ppackage sieteYmedia;

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
	 * @throws MazoVacioException 
	 */
	private static void playRounds() throws MazoVacioException {
		generateActualPlayers();
		int rounds;
		do {
			rounds = Teclado
					.leerEntero("por favor, indica numero de rondas");
		} while (rounds<1);
		for (int i = 0; i <rounds ; i++) {
			System.out.println("ronda " + (i + 1));
			partida.ronda();
		}
		for (Jugador j : jugadores) {
			for (Jugador p : participantes) {
				if (j.equals(p)) {
					j.setPartidasJugadas(p.getPartidasJugadas());
					j.setPartidasGanadas(p.getPartidasGanadas());
				}
			}
		}
		participantes = null;
	}

	/**
	 * Inicializa lista con el total de jugadores
	 */
	private static void generateAllPlayers() {

		int totalJugadores = Teclado
				.leerEntero("indica total de jugadores, recuerda que no tienen por que jugar todos a la vez");
		for (int i = 0; i < totalJugadores; i++) {
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
				if(!participantes.contains(jugadores.get(opcion-1)))
				participantes.add(jugadores.get(opcion - 1));
			} while (opcion > 0 || opcion > jugadores.size());
		} catch (RuntimeException e) {
			System.out.println("saliendo");
		}
		System.out.println(participantes.toString());
		partida = new Partida(participantes);
	}

	
}

