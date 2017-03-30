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
	static ArrayList<Jugador> participantes = new ArrayList<Jugador>();
	private static Menu menu = new Menu("Siete y Media",
			new String[] { "aniadir jugador", "borrar jugador", "jugar"});

	public static void main(String[] args) {
		Partida partida = new Partida(participantes);
		do {
			switch (menu.gestionar()) {
			case 1:
				try {
					partida.addPlayer();
				} catch (JugadorExisteException e1) {
					System.out.println(e1.getMessage());
				}
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
				partida.ranking();
				break;
			case 5:
				System.out.println("hasta luego");
				return;
			}
		} while (menu.getOpcion() < menu.getSALIR());

	}

}
