public class CandyCrush {
	// Manuel Astorgano y Javier Martin
	public static void main(String[] args) {
		// Main. Ejecuta el menu.
		int[][] matriz;
		matriz = new int[11][11];
		int opc;
		// Menu donde el usuario elige el nivel
		System.out.println("Elija un nivel de dificultad:");
		System.out.println("1. Facil.");
		System.out.println("2. Intermedio.");
		System.out.println("3. Dificil.");
		System.out.println("4. Tablero fijo.");
		System.out.println("0. Salir.");
		int mov = 10;
		java.util.Scanner tec = new java.util.Scanner(System.in);
		opc = tec.nextInt();
		while (opc < 0 || opc > 4) {
			System.out.println("Seleccione un nivel valido.");
			opc = tec.nextInt();
		}
		switch (opc) {
		case 1:
			System.out.println("Nivel facil.");
			System.out.println("Dispone de " + mov + " movimientos, suerte!");
			juego(matriz, opc, mov);
			break;
		case 2:
			System.out.println("Nivel intermedio");
			System.out.println("Dispone de " + mov + " movimientos, suerte!");
			juego(matriz, opc, mov);
			break;
		case 3:
			System.out.println("Nivel dificil.");
			System.out.println("Dispone de " + mov + " movimientos, suerte!");
			juego(matriz, opc, mov);
			break;
		case 4:
			System.out.println("Tablero fijo.");
			System.out.println("Dispone de " + mov + " movimientos, suerte!");
			juego(matriz, opc, mov);
			break;
		case 0:
			// Salir del juego.
			System.out.println("¡Gracias por jugar!");
			break;
		}
	}

	public static void juego(int[][] matriz, int opc, int mov) {
		// Iniciar puntos (pts) a 0. Crear matrices y comprobar (ver "comprobaciones")
		int pts = 0;
		if (opc > 0 && opc < 4) {
			crear_matriz(matriz, opc);
		} else {
			crear_matriz_fija(matriz);
		}
		poner_simbolos(matriz);
		int[] entradas = new int[4];
		comprobaciones(matriz, entradas, mov, pts, opc);
	}

	public static int[][] crear_matriz(int[][] matriz, int opc) {
		// PRE: matriz declarada
		// DEVUELVE matriz con las entradas asignadas
		if (opc==4) {
			opc=1;
		}
		int filas = 0, columnas = 0;
		for (filas = 1; filas <= 9; filas++) {
			for (columnas = 1; columnas <= 9; columnas++) {
				matriz[filas][columnas] = (int) (Math.random() * (opc + 2) + 1);
			}
		}
		for (columnas = 0; columnas <= 10; columnas++) {
			matriz[0][columnas] = 0;
		}
		for (filas = 0; filas <= 10; filas += 10) {
			matriz[filas][0] = 0;
		}
		comprobar_cambiar_bloques(matriz, opc); // Comprobar que no hay bloques hechos
		if (comprobar_crear_jugadas(matriz, opc)) { // Si no hay jugadas, se repite hasta que las haya.
			crear_matriz(matriz, opc);
		}
		return matriz;
	}

	public static int[][] crear_matriz_fija(int[][] matriz) {
		// PRE: matriz declarada, opcion elegida
		// DEV: tablero fijo hecho.
		int filas, columnas;
		for (filas = 1; filas <= 9; filas++) {
			for (columnas = 1; columnas <= 9; columnas++) {
				if (filas == 1 || filas == 4 || filas == 7) {
					matriz[filas][columnas] = 1;
				}
				if (filas == 2 || filas == 5 || filas == 8) {
					matriz[filas][columnas] = 2;
				}
				if (filas == 3 || filas == 6 || filas == 9) {
					matriz[filas][columnas] = 3;
				}
			}
		}
		matriz[1][1] = 2;
		matriz[2][1] = 1;
		matriz[2][5] = 1;
		matriz[2][9] = 1;
		matriz[3][2] = 2;
		matriz[3][6] = 1;
		matriz[5][5] = 1;
		matriz[5][8] = 1;
		matriz[5][9] = 3;
		matriz[6][9] = 1;
		matriz[7][3] = 2;
		matriz[7][5] = 3;
		matriz[7][3] = 1;
		matriz[8][8] = 1;
		matriz[9][2] = 2;
		matriz[9][3] = 1;
		matriz[9][9] = 1;
		return matriz;
	}

	// METODO PARA COMPROBAR BLOQUES ANTES DE JUGAR
	public static void comprobar_cambiar_bloques(int[][] matriz, int opc) {
		// PRE: Matriz generada y opcion escogida
		// Comprueba que el tablero no tiene bloques ya hechos
		int filas, columnas;
		for (columnas = 1; columnas <= 9; columnas++) {
			for (filas = 2; filas <= 8; filas++) {
				while (matriz[filas][columnas] == matriz[filas + 1][columnas]
						&& matriz[filas][columnas] == matriz[filas - 1][columnas]) {
					matriz[filas][columnas] = (int) (Math.random() * (opc + 2) + 1);
				}
			}
		}
	}

	public static boolean comprobar_crear_jugadas(int[][] matriz, int opc) {
		// PRE: Matriz sin bloques, opcion escogida, posible matriz sin jugadas
		// DEVUELVE: Indica si es posible hacer una jugada
		int filas, columnas;
		boolean x = false;
		for (columnas = 1; columnas <= 9; columnas++) {
			for (filas = 2; filas <= 7; filas++) {
				while (matriz[filas][columnas] == matriz[filas + 1][columnas]) {
					if (matriz[filas + 3][columnas] == matriz[filas][columnas]
							|| matriz[filas + 2][columnas + 1] == matriz[filas][columnas]
							|| matriz[filas + 2][columnas - 1] == matriz[filas][columnas]
							|| matriz[filas - 2][columnas] == matriz[filas][columnas]
							|| matriz[filas - 1][columnas + 1] == matriz[filas][columnas]
							|| matriz[filas - 1][columnas - 1] == matriz[filas][columnas]) {
						x = false;
						return x;
					} else {
						x = true;
						return x;
					}
				}
			}
		}
		// Nunca se llega aqui
		return x;
	}

	public static void poner_simbolos(int[][] matriz) {
		// PRE: Matriz ya hecha
		// Imprime, como simbolos, los numeros de la matriz del tablero
		int columnas;
		int filas;
		System.out.println("   −−−−−−−−−−−−−−−−−−−");
		for (filas = 1; filas <= 9; filas++) {
			System.out.print(10 - filas + " | ");
			for (columnas = 1; columnas <= 9; columnas++) {
				switch (matriz[filas][columnas]) {
				case 1:
					System.out.print("+" + " ");
					break;
				case 2:
					System.out.print("x" + " ");
					break;
				case 3:
					System.out.print("#" + " ");
					break;
				case 4:
					System.out.print("o" + " ");
					break;
				case 5:
					System.out.print("=" + " ");
					break;
				}
			}
			System.out.print("|");
			System.out.println("");
		}
		System.out.println("   −−−−−−−−−−−−−−−−−−−");
		System.out.println("    1 2 3 4 5 6 7 8 9");
		System.out.println();
		System.out.println("***********************");
	}

	public static int[] entradas(int[][] matriz, int mov, int pts) {
		// PRE: el tablero se ha mostrado
		// Pide 4 entradas: las dos primeras pertenecientes a la primera entrada y las 2
		// ultimas, a la segunda
		int[] entradas;
		entradas = new int[4];
		java.util.Scanner ent = new java.util.Scanner(System.in);
		System.out.println("Introduzca dos entradas de la primera casilla que quiera cambiar");
		if (mov == 10) {
			System.out.println("La primera entrada se referira a la fila, de 1 a 9, cuando 1 es abajo y 9, arriba");
			System.out.println(
					"La segunda entrada se referira a la columna, de 1 a 9, cuando 1 es izquierda, y 9 es derecha");
			System.out.println("Introduzca 00 00  para salir al menu en cualquier momento.");
		}
		int fil1 = ent.nextInt();
		int col1 = ent.nextInt();
		if (fil1 == 0 && col1 == 0) {
			int fil2 = ent.nextInt();
			int col2 = ent.nextInt();
			while (fil2 != 0 || col2 != 0) {
				System.out.println("Introduzca 00 para salir.");
				fil2 = ent.nextInt();
				col2 = ent.nextInt();
			}
			return entradas;
		} else {
			fil1 = 10 - fil1;
			while (fil1 < 1 || fil1 > 9 || col1 < 1 || col1 > 9) {
				System.out.println("Introduzca entradas validas");
				fil1 = ent.nextInt();
				col1 = ent.nextInt();
				fil1 = 10 - fil1;
			}
			System.out.println("Introduzca ahora las entradas de la segunda casilla que quiera cambiar");
			int fil2 = ent.nextInt();
			int col2 = ent.nextInt();
			fil2 = 10 - fil2;
			// Comprobar que son casillas adyacentes en vertical u horizontal.
			while (fil2 < 1 || fil2 > 9 || col2 < 1 || col2 > 9 || (fil1 == fil2 && col1 == col2)
					|| ((fil1 != fil2) && (col1 != col2))
					|| ((Math.abs(fil1 - fil2) > 1) || (Math.abs(col1 - col2) > 1))) {
				System.out.println("Introduzca entradas validas para la segunda casilla");
				fil2 = ent.nextInt();
				col2 = ent.nextInt();
				fil2 = 10 - fil2;
			}
			entradas[0] = fil1;
			entradas[1] = col1;
			entradas[2] = fil2;
			entradas[3] = col2;
			return entradas;
		}
	}

	// METODO PARA VERIFICAR MOVIMIENTOS
	public static boolean comprobar_movimiento(int[][] matriz, int[] entradas) {
		boolean x = false;
		int[][] mattemp;
		if (entradas[0] == 0 && entradas[1] == 0 && entradas[2] == 0 && entradas[3] == 0) {
			return x;
		} else {
			// Intercambiar valores para verificar mas facilmente
			mattemp = new int[10][10];
			mattemp[entradas[2]][entradas[3]] = matriz[entradas[0]][entradas[1]];
			matriz[entradas[0]][entradas[1]] = matriz[entradas[2]][entradas[3]];
			matriz[entradas[2]][entradas[3]] = mattemp[entradas[2]][entradas[3]];
			// Comprobar que forma un grupo de 3 (minimo) mirando las dos casillas de arriba
			// y abajo o la de arriba y abajo
			if (((matriz[entradas[2]][entradas[3]] == matriz[entradas[2] + 1][entradas[3]])
					&& (matriz[entradas[2]][entradas[3]] == matriz[entradas[2] - 1][entradas[3]]))
					|| ((matriz[entradas[2]][entradas[3]] == matriz[entradas[2] - 1][entradas[3]])
							&& (matriz[entradas[2]][entradas[3]] == matriz[entradas[2] - 2][entradas[3]]))
					|| ((matriz[entradas[2]][entradas[3]] == matriz[entradas[2] + 1][entradas[3]])
							&& (matriz[entradas[2]][entradas[3]] == matriz[entradas[2] + 2][entradas[3]]))) {
				x = true;
				return x;
			} else if (((matriz[entradas[0]][entradas[1]] == matriz[entradas[0] + 1][entradas[1]])
					&& (matriz[entradas[0]][entradas[1]] == matriz[entradas[0] - 1][entradas[1]]))
					|| ((matriz[entradas[0]][entradas[1]] == matriz[entradas[0] - 1][entradas[1]])
							&& (matriz[entradas[0]][entradas[1]] == matriz[entradas[0] - 2][entradas[1]]))
					|| ((matriz[entradas[0]][entradas[1]] == matriz[entradas[0] + 1][entradas[1]])
							&& (matriz[entradas[0]][entradas[1]] == matriz[entradas[0] + 2][entradas[1]]))) {
				x = true;
				return x;
			} else {
				// Cambiar de nuevo las casillas si el movimiento no es valido
				mattemp[entradas[2]][entradas[3]] = matriz[entradas[0]][entradas[1]];
				matriz[entradas[0]][entradas[1]] = matriz[entradas[2]][entradas[3]];
				matriz[entradas[2]][entradas[3]] = mattemp[entradas[2]][entradas[3]];
				return x;
			}
		}
	}

	public static int puntos(int[][] matriz) {
		// PRE: el movimiento ha sido permitido
		// Columna a columna, se observa cuantos bloques hay y de cuantas casillas y
		// dara los puntos necesarios
		int fil, col;
		int pts = 0;
		for (col = 1; col < 10; col++) {
			for (fil = 9; fil >= 3; fil--) {
				if (matriz[fil][col] == matriz[fil - 1][col] & matriz[fil][col] == matriz[fil - 2][col]) {
					pts += 10;
				}
			}
		}
		return pts;
	}

	public static boolean hay_nuevos_bloques(int[][] matriz) {
		// PRE:matriz con las nuevas casillas
		// Devuelve true si encuentra una, false si no se ha formado ninguna mas
		int col, fil;
		boolean x = false;
		for (col = 1; col < 10; col++) {
			for (fil = 9; fil > 1; fil--) {
				if (matriz[fil][col] == matriz[fil - 1][col] & matriz[fil - 2][col] == matriz[fil][col]) {
					return x = true;
				}
			}
		}
		return x;
	}

	public static void comprobaciones(int[][] matriz, int[] entradas, int mov, int pts, int opc) {
		// PRE: las casillas se han marcado y son correctas
		// Comprueba que el movimiento es posible, y en tal caso hace las operaciones se
		// correspondan
		while (mov > 0) {// Parara cuando el jugador se quede sin movimientos
			entradas = entradas(matriz, mov, pts);
			if (entradas[0] == 0 & entradas[1] == 0 & entradas[2] == 0 & entradas[3] == 0) {
				mov = -1;
				System.out.println("Ha salido del juego actual con " + pts + " puntos.");
				main(null);
			}

			else if (comprobar_movimiento(matriz, entradas) == false) { // Si el movimiento es valido, se procede al
				// movimiento
				System.out.println("Movimiento no posible, por favor, introduzca de nuevo las casillas.");
				// Vuelve a pedir las casillas y lo comprueba de nuevo
			} else {

				mov--;
				pts = pts + puntos(matriz);// puntos por el movimiento
				turno(matriz, opc);
				while (hay_nuevos_bloques(matriz) == true) {
					pts = pts + puntos(matriz);
					turno(matriz, opc);
				}
				System.out.println("Tiene " + pts + " puntos.");
				System.out.println("Le quedan " + mov + " movimientos.");
				if (comprobar_crear_jugadas(matriz, opc)) {
					crear_matriz(matriz, opc);
				}
			}
		}
		if (mov == 0) {
			System.out.println("No le quedan mas movimientos, ha acabado el juego.");
			main(null);
		}
	}

	public static void turno(int[][] matriz, int opc) {
		// PRE: Matriz con comprobaciones hechas y movimiento realizado.
		// DEVUELVE matriz sin el bloque formado y los nuevos generados.
		int fil, col;
		int bloque;
		int primer = 0;
		int cambio;
		for (col = 1; col < 10; col++) {
			bloque = 1;
			for (fil = 9; fil > 0; fil--) {
				if (matriz[fil][col] == matriz[fil - 1][col]) {
					bloque++;
					if (bloque >= 3) {
						if (fil > primer) {
							primer = fil + 1;
						}
					}
				} else if (bloque >= 3) {
					for (fil = primer; fil >= 1; fil--) {
						if (fil - bloque > 0) {
							cambio = matriz[fil - bloque][col];
							matriz[fil][col] = cambio;
						} else {
							if (opc > 0 && opc < 4) {
								matriz[fil][col] = (int) (Math.random() * (opc + 2) + 1);
							} else {
								matriz[fil][col] = (int) (Math.random() * (3) + 1);
							}

						}

					}
					fil = 1;
				} else
					bloque = 1;

			}
		}
		poner_simbolos(matriz);
	}
}