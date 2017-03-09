package sieteYmedia;

public class Jugador {
	private String alias;
	private double puntuacion;
	private int partidasJugadas;
	private int partidasGanadas;
	private int partidasPerdidas;

	int getPartidasPerdidas() {
		return partidasPerdidas;
	}

	void setPartidasPerdidas() {
		this.partidasPerdidas = getPartidasJugadas() - getPartidasGanadas();
		;
	}

	public Jugador(String alias) {
		setAlias(alias);
	}

	String getAlias() {
		return alias;
	}

	private void setAlias(String alias) {
		this.alias = alias;
	}

	double getPuntuacion() {
		return puntuacion;
	}

	StringBuilder getPuntos() {
		StringBuilder puntos = new StringBuilder("puntuacion: " + puntuacion);
		if (getPuntuacion() == 7.5)
			puntos.append(" ganaste!");
		else if (getPuntuacion() > 7.5)
			puntos.append(" has perdido");
		return puntos;

	}

	private void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	void sumarPuntuacion(double puntuacion) {
		this.puntuacion += puntuacion;
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
		return "\nJugador [alias=" + alias + ", partidasJugadas="
				+ partidasJugadas + ", partidasGanadas=" + partidasGanadas
				+ "partidasPerdidas " + getPartidasPerdidas() + "]";
	}

	void resetPoints() {
		setPuntuacion(0);

	}

}
