package utiles;

public class Menu {
	private String titulo;
	private String opciones[];
	private int opcion;
	private int opcionSalida;
	
	public Menu(String titulo, String[]opciones){
		setTitulo(titulo);
		setOpciones(opciones);
		setSALIR(opciones.length+1);
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
	private void setSALIR(int opcionSalida){
			this.opcionSalida = opcionSalida;
		}
	
	public int getSALIR(){
			return opcionSalida;
		}
	/**
	 * Permite mostrar el men�
	 */

	 public void mostrar(){
		System.out.println(getTitulo());
		for(int i=0; i<opciones.length; i++){
			System.out.println((i+1)+"."+getOpciones()[i]);
		}
	}
	/**
	 * Permite recoger una opci�n del men�
	 * @return entero introducido por teclado, que se corresponder� con una opci�n del men�
	 */
	public int recogerOpcion(){
		int opcion;
		do{
			opcion=Teclado.leerEntero("Introduce una opcion entre 1 y "+getSALIR()+" (salir)");
		}while(esValida(opcion));
		return opcion;
	}


	private boolean esValida(int opcion) {
		return opcion>opciones.length+1||opcion<0;
	}
	
	public int getOpcion() {
		return opcion;
	}


	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
/**
 * Gestiona el men�
 * @return n�mero de opci�n recogida por teclado
 */

	public int gestionar(){
		mostrar();
		return recogerOpcion();
	}
	
}
