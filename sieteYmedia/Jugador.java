package sieteYmedia;

public class Jugador implements Comparable <Jugador> {
	private String alias;
	private int partidasJugadas;
	private int partidasGanadas;
	
	public Jugador(String alias) {
		setAlias(alias);
	}

	String getAlias() {
		return alias;
	}

	private void setAlias(String alias) {
		this.alias = alias;
	}


	int getPartidasJugadas() {
		return partidasJugadas;
	}

	void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	void roundPlayed() {
		setPartidasJugadas(getPartidasJugadas() + 1);
	}

	void winRound() {
		setPartidasGanadas(getPartidasGanadas() + 1);
	}

	int getPartidasGanadas() {
		return partidasGanadas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}

	void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	@Override
	public String toString() {
		return "\n Jugador alias=" + alias + ", partidasJugadas="
				+ partidasJugadas + ", partidasGanadas=" + partidasGanadas;
	}

	public int compareTo(Jugador jugador) {
		int comparepg = ((Jugador)jugador).getPartidasGanadas();
		return comparepg-this.partidasGanadas;
	}
}
