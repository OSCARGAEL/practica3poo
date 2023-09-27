import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al juego de Dominó");

        System.out.print("Ingrese la cantidad de jugadores: ");
        int numJugadores = scanner.nextInt();

        int mulaMaxima = (numJugadores <= 3) ? 6 : (numJugadores <= 6) ? 9 : 12;

        List<Ficha> fichas = crearFichas(mulaMaxima);
        Collections.shuffle(fichas);

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }

        Cementerio cementerio = new Cementerio();
        for (int i = 0; i < numJugadores; i++) {
            for (int j = 0; j < 3; j++) {
                jugadores.get(i).recibirFicha(fichas.remove(0));
            }
        }

        boolean juegoActivo = true;
        int jugadorActual = 0;

        while (juegoActivo) {
            Jugador jugadorActualObj = jugadores.get(jugadorActual);
            System.out.println("\nTurno de " + jugadorActualObj);

            // Mostrar fichas del jugador
            List<Ficha> manoJugadorActual = jugadorActualObj.getMano();
            System.out.println("Fichas en mano: " + manoJugadorActual);

            // Mostrar ficha del cementerio
            Ficha fichaCementerio = cementerio.obtenerFicha();
            System.out.println("Ficha del cementerio: " + (fichaCementerio != null ? fichaCementerio : "Cementerio vacío"));

            // Colocar ficha
            if (fichaCementerio != null) {
                System.out.println("¿Desea colocar una ficha en el extremo de la ficha del cementerio? (S/N)");
                String respuesta = scanner.next().toUpperCase();
                if (respuesta.equals("S")) {
                    System.out.print("Seleccione el lado de la ficha (1 o 2): ");
                    int lado = scanner.nextInt();
                    if (lado == 1) {
                        if (manoJugadorActual.contains(new Ficha(fichaCementerio.getLado1(), -1))) {
                            jugadorActualObj.jugarFicha(new Ficha(fichaCementerio.getLado1(), -1));
                            cementerio.agregarFicha(fichaCementerio);
                        } else {
                            System.out.println("No tienes la ficha necesaria para colocar en el lado 1.");
                        }
                    } else if (lado == 2) {
                        if (manoJugadorActual.contains(new Ficha(-1, fichaCementerio.getLado2()))) {
                            jugadorActualObj.jugarFicha(new Ficha(-1, fichaCementerio.getLado2()));
                            cementerio.agregarFicha(fichaCementerio);
                        } else {
                            System.out.println("No tienes la ficha necesaria para colocar en el lado 2.");
                        }
                    } else {
                        System.out.println("Selección de lado inválida.");
                    }
                }
            }

            // Mostrar ficha del jugador
            System.out.print("Seleccione una ficha de su mano (0 para pasar): ");
            int seleccion = scanner.nextInt();

            if (seleccion > 0 && seleccion <= manoJugadorActual.size()) {
                Ficha fichaSeleccionada = manoJugadorActual.get(seleccion - 1);
                if (fichaCementerio == null || fichaSeleccionada.getLado1() == fichaCementerio.getLado1()
                        || fichaSeleccionada.getLado2() == fichaCementerio.getLado1()
                        || fichaSeleccionada.getLado1() == fichaCementerio.getLado2()
                        || fichaSeleccionada.getLado2() == fichaCementerio.getLado2()) {
                    jugadorActualObj.jugarFicha(fichaSeleccionada);
                    cementerio.agregarFicha(fichaSeleccionada);
                } else {
                    System.out.println("Ficha seleccionada no es compatible con la ficha del cementerio.");
                }
            }

            // Verificar fin de ronda
            if (jugadorActualObj.getMano().isEmpty()) {
                juegoActivo = false;
                System.out.println("¡" + jugadorActualObj + " ha ganado la ronda!");
            }

            // Pasar al siguiente jugador
            jugadorActual = (jugadorActual + 1) % numJugadores;
        }

        // Calcular puntos y determinar al ganador
        int minPuntos = Integer.MAX_VALUE;
        Jugador ganador = null;

        for (Jugador jugador : jugadores) {
            int puntos = jugador.calcularPuntos();
            System.out.println(jugador + " tiene " + puntos + " puntos.");

            if (puntos < minPuntos) {
                minPuntos = puntos;
                ganador = jugador;
            }
        }

        System.out.println("El ganador de la ronda es " + ganador);
        scanner.close();
    }

    private static List<Ficha> crearFichas(int mulaMaxima) {
        List<Ficha> fichas = new ArrayList<>();
        for (int i = 0; i <= mulaMaxima; i++) {
            for (int j = i; j <= mulaMaxima; j++) {
                fichas.add(new Ficha(i, j));
            }
        }
        return fichas;
    }
}
