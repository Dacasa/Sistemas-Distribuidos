package cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class ClienteFlotaSockets {
	
	// Sustituye esta clase por tu versión de la clase Juego de la práctica 1
	
	// Modifícala para que instancie un objeto de la clase AuxiliarClienteFlota en el método 'ejecuta'
	
	// Modifica todas las llamadas al objeto de la clase Partida
	// por llamadas al objeto de la clase AuxiliarClienteFlota.
	// Los métodos a llamar tendrán la misma signatura.
	
	/**
	 * Implementa el juego 'Hundir la flota' mediante una interfaz gráfica (GUI)
	 */

	/** Parametros por defecto de una partida */
	public static final int NUMFILAS=8, NUMCOLUMNAS=8, NUMBARCOS=6;

	private GuiTablero guiTablero = null;			// El juego se encarga de crear y modificar la interfaz gráfica
	private AuxiliarClienteFlota partida = null;                 // Objeto con los datos de la partida en juego
	
	/** Atributos de la partida guardados en el juego para simplificar su implementación */
	private int quedan = NUMBARCOS, disparos = 0;

	/**
	 * Programa principal. Crea y lanza un nuevo juego
	 * @param args
	 */
	public static void main(String[] args) {
		ClienteFlotaSockets cliente = new ClienteFlotaSockets();
		cliente.ejecuta();
	} // end main

	/**
	 * Lanza una nueva hebra que crea la primera partida y dibuja la interfaz grafica: tablero
	 */
	private void ejecuta() {
		// Instancia la primera partida
		partida = new AuxiliarClienteFlota();
		partida.nuevaPartida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiTablero = new GuiTablero(NUMFILAS, NUMCOLUMNAS);
				guiTablero.dibujaTablero();
			}
		});
	} // end ejecuta

	/******************************************************************************************/
	/*********************  CLASE INTERNA GuiTablero   ****************************************/
	/******************************************************************************************/
	private class GuiTablero {

		private int numFilas, numColumnas;

		private JFrame frame = null;        // Tablero de juego
		private JLabel estado = null;       // Texto en el panel de estado
		private JButton buttons[][] = null; // Botones asociados a las casillas de la partida

		/**
         * Constructor de una tablero dadas sus dimensiones
         */
		GuiTablero(int numFilas, int numColumnas) {
			this.numFilas = numFilas;
			this.numColumnas = numColumnas;
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		}

		/**
		 * Dibuja el tablero de juego y crea la partida inicial
		 */
		public void dibujaTablero() {
			anyadeMenu();
			anyadeGrid(numFilas, numColumnas);		
			anyadePanelEstado("Intentos: " + disparos + "    Barcos restantes: " + quedan);		
			frame.setSize(300, 300);
			frame.setVisible(true);	
		} // end dibujaTablero

		/**
		 * Anyade el menu de opciones del juego y le asocia un escuchador
		 */
		private void anyadeMenu() {
            // POR IMPLEMENTAR
			// Creamos el menu, a�adimos los desplegables y les asignamos un listener
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Menu");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
			JMenuItem menuItem = new JMenuItem("Mostrar Solucion");
			menuItem.setActionCommand("Mostrar Solucion");
			menuItem.addActionListener(new MenuListener());
			menu.add(menuItem);
			menuItem = new JMenuItem("Nueva Partida");
			menuItem.setActionCommand("Nueva Partida");
			menuItem.addActionListener(new MenuListener());
			menu.add(menuItem);
			menuItem = new JMenuItem("Salir");
			menuItem.setActionCommand("Salir");
			menuItem.addActionListener(new MenuListener());
			menu.add(menuItem);
			frame.setJMenuBar(menuBar);
			
		} // end anyadeMenu

		/**
		 * Anyade el panel con las casillas del mar y sus etiquetas.
		 * Cada casilla sera un boton con su correspondiente escuchador
		 * @param nf	numero de filas
		 * @param nc	numero de columnas
		 */
		private void anyadeGrid(int nf, int nc) {
			//JPanes
			//Primera linea de numeros
			JPanel tablero = new JPanel();
			tablero.setLayout(new GridLayout(nf+1,nc+2));
			JLabel etiqueta;
			JButton boton;
			this.buttons = new JButton[nf][nc];
			etiqueta = new JLabel("");
			tablero.add(etiqueta);
			for(int i=0; i<nc;i++){
				etiqueta = new JLabel(String.format("%d",i+1),SwingConstants.CENTER);
				tablero.add(etiqueta);
			}
			etiqueta = new JLabel("");
			tablero.add(etiqueta);
			
			//Etiquetas laterales y botones
			for(int i=0; i<nf; i++){
				etiqueta = new JLabel(String.format("%c", i+65),SwingConstants.CENTER);
				tablero.add(etiqueta);
				for(int j=0; j<nc; j++){
					//Botones
					boton = new JButton();
					boton.setActionCommand(String.format("%d-%d",i,j));
					boton.addActionListener(new ButtonListener());
					buttons[i][j] = boton;
					tablero.add(boton);
				}
				etiqueta = new JLabel(String.format("%c", i+65),SwingConstants.CENTER);
				tablero.add(etiqueta);
			}
			frame.getContentPane().add(tablero, BorderLayout.CENTER);
		} // end anyadeGrid


		/**
		 * Anyade el panel de estado al tablero
		 * @param cadena	cadena inicial del panel de estado
		 */
		private void anyadePanelEstado(String cadena) {	
			JPanel panelEstado = new JPanel();
			estado = new JLabel(cadena);
			panelEstado.add(estado);
			// El panel de estado queda en la posición SOUTH del frame
			frame.getContentPane().add(panelEstado, BorderLayout.SOUTH);
		} // end anyadePanel Estado

		/**
		 * Cambia la cadena mostrada en el panel de estado
		 * @param cadenaEstado	nuevo estado
		 */
		public void cambiaEstado(String cadenaEstado) {
			estado.setText(cadenaEstado);
		} // end cambiaEstado

		/**
		 * Muestra la solucion de la partida y marca la partida como finalizada
		 * @throws IOException 
		 */
		public void muestraSolucion() throws IOException {
            // POR IMPLEMENTAR
			//Cambia el color de todos los botones a gris, y luego coge la solucion de partida y pinta los barcos de rosa
			for(JButton[] fila : buttons){
				for(JButton boton : fila){
					pintaBoton(boton, Color.cyan);
					quedan=0;
				}
			}
			String[] stringSolucion = partida.getSolucion();
			for(String cadenaBarco : stringSolucion){
				String[] separarCadena = cadenaBarco.split("#");
				int fila = Integer.parseInt(separarCadena[0]);
				int columna = Integer.parseInt(separarCadena[1]);
				String orientacion = separarCadena[2];
				int tamano = Integer.parseInt(separarCadena[3]);
				for(int i=0; i<tamano; i++){
					if(orientacion.equals("H")){
						pintaBoton(buttons[fila][columna+i], Color.MAGENTA);
					}
					if(orientacion.equals("V")){
						pintaBoton(buttons[fila+i][columna], Color.MAGENTA);
					}
				}
			}
			
		} // end muestraSolucion


		/**
		 * Pinta un barco como hundido en el tablero
		 * @param cadenaBarco	cadena con los datos del barco codifificados como
		 *                      "filaInicial#columnaInicial#orientacion#tamanyo"
		 */
		public void pintaBarcoHundido(String cadenaBarco) {
            // POR IMPLEMENTAR
			// Recibe la cadena del barco y lo pinta de rojo
			String[] separarCadena = cadenaBarco.split("#");
			int fila = Integer.parseInt(separarCadena[0]);
			int columna = Integer.parseInt(separarCadena[1]);
			String orientacion = separarCadena[2];
			int tamano = Integer.parseInt(separarCadena[3]);
			for(int i=0; i<tamano; i++){

				if(orientacion.equals("H")){

					pintaBoton(buttons[fila][columna+i], Color.RED);
				}
				if(orientacion.equals("V")){
					
					pintaBoton(buttons[fila+i][columna], Color.RED);
	
				}
				
			}
			
		} // end pintaBarcoHundido

		/**
		 * Pinta un botón de un color dado
		 * @param b			boton a pintar
		 * @param color		color a usar
		 */
		public void pintaBoton(JButton b, Color color) {
			b.setBackground(color);
			// El siguiente código solo es necesario en Mac OS X
			b.setOpaque(true);
			b.setBorderPainted(false);
		} // end pintaBoton

		/**
		 * Limpia las casillas del tablero pintándolas del gris por defecto
		 */
		public void limpiaTablero() {
			for (int i = 0; i < numFilas; i++) {
				for (int j = 0; j < numColumnas; j++) {
					buttons[i][j].setBackground(null);
					buttons[i][j].setOpaque(true);
					buttons[i][j].setBorderPainted(true);
				}
			}
		} // end limpiaTablero

		/**
		 * 	Destruye y libera la memoria de todos los componentes del frame
		 */
		public void liberaRecursos() {
			frame.dispose();
		} // end liberaRecursos


	} // end class GuiTablero

	/******************************************************************************************/
	/*********************  CLASE INTERNA MenuListener ****************************************/
	/******************************************************************************************/

	/**
	 * Clase interna que escucha el menu de Opciones del tablero
	 * 
	 */
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            // POR IMPLEMENTAR
			switch (e.getActionCommand()) {
			case "Mostrar Solucion":
				guiTablero.muestraSolucion();
				break;
			case "Nueva Partida":
				guiTablero.limpiaTablero();
				quedan = NUMBARCOS;
				disparos = 0;
				//partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
				partida = new AuxiliarClienteFlota();
				break;
			case "Salir":
				guiTablero.liberaRecursos();
				break;
			}
		} // end actionPerformed

	} // end class MenuListener



	/******************************************************************************************/
	/*********************  CLASE INTERNA ButtonListener **************************************/
	/******************************************************************************************/
	/**
	 * Clase interna que escucha cada uno de los botones del tablero
	 * Para poder identificar el boton que ha generado el evento se pueden usar las propiedades
	 * de los componentes, apoyandose en los metodos putClientProperty y getClientProperty
	 */
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
           
			// POR IMPLEMENTAR
			if(quedan==0) return;
			int i, j;
			String text = e.getActionCommand();
			i= Integer.parseInt(text.substring(0,1));
			j= Integer.parseInt(text.substring(2,3));
			JButton boton = guiTablero.buttons[i][j];
			//System.out.println(text);
			int res;
			try {
				res = partida.pruebaCasilla(i, j);			
				if(res == -1) {//agua
					guiTablero.pintaBoton(boton, Color.cyan);
				}else if(res==-2) {//tocado
					guiTablero.pintaBoton(boton, Color.YELLOW);
				}else if(res>=0) {//hundido
		
					guiTablero.pintaBoton(boton, Color.YELLOW);
					guiTablero.pintaBarcoHundido(partida.getBarco(res));
					quedan--;
				}
				disparos++;
				guiTablero.cambiaEstado("Intentos: " + disparos + "    Barcos restantes: " + quedan);
				if(quedan==0) {
					guiTablero.cambiaEstado("GAME OVER en " + disparos + " disparos");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(res);

        } // end actionPerformed

	} // end class ButtonListener


}
