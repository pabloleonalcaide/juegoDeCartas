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
	static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	static ArrayList<Jugador> participantes = new ArrayList<Jugador>();
	private static Menu menu = new Menu("Siete y Media",
			new String[] { "aniadir jugador", "borrar jugador", "jugar", "mostrar ranking" });

	public static void main(String[] args) {
		Partida partida=new Partida(participantes);
		partida.generateAllPlayers();
		do {
			switch (menu.gestionar()) {
			case 1:
				partida.addPlayer();
				break;
			case 2:
				partida.deletePlayer();
				break;
			case 3:
				try {
					partida.playRounds();
				} catch (MazoVacioException e) {
					System.out.println(e.getMessage());
				} catch (SinJugadoresException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				ranking();
				break;
			case 5:
				System.out.println("hasta luego");
				return;
			}
		} while (menu.getOpcion() < menu.getSALIR());

	}


	/**
	 * muestra el ranking de jugadores
	 */
	private static void ranking() {
		ArrayList<Jugador> ranking = (ArrayList<Jugador>) jugadores.clone();
		Collections.sort(ranking);
		for (Jugador jugador : ranking) {
			System.out.println(jugador);
		}
	}

}
