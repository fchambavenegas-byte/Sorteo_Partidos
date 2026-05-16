import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private GestorTorneo gestor = new GestorTorneo();

    public static void main(String[] args) {
        // Instanciamos la clase
        Main app = new Main();
        app.iniciarAplicacion();
    }

    public void iniciarAplicacion() {
        System.out.println("=============================================");
        System.out.println("   SORTEO DE LIGA PROFESIONAL DE FÚTBOL    ");
        System.out.println("=============================================");

        System.out.println("Seleccione la etapa inicial:");
        System.out.println("1. Octavos de Final (16 equipos)");
        System.out.println("2. Cuartos de Final (8 equipos)");
        System.out.println("3. Semifinales (4 equipos)");
        System.out.println("4. Final (2 equipos)");
        System.out.print("\nOpción: ");

        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        int cantidad = obtenerCantidadEquipos(opcion);
        if (cantidad == 0) return;

        List<String> equiposBase = ingresarNombres(cantidad);
        procesarTorneo(equiposBase, opcion);
    }

    private int obtenerCantidadEquipos(int op) {
        if (op == 1) return 16;
        if (op == 2) return 8;
        if (op == 3) return 4;
        if (op == 4) return 2;
        System.out.println("¡Error! Opción inválida.");
        return 0;
    }

    private List<String> ingresarNombres(int cant) {
        List<String> lista = new ArrayList<>();
        System.out.println("\n--- Registro de Equipos ---");
        for (int i = 1; i <= cant; i++) {
            System.out.print("Equipo " + i + ": ");
            lista.add(sc.nextLine());
        }
        return lista;
    }

    private void procesarTorneo(List<String> equipos, int etapaInicial) {
        String[] nombres = {"", "OCTAVOS", "CUARTOS", "SEMIFINAL", "FINAL"};
        List<String> clasificados = new ArrayList<>(equipos);

        for (int i = etapaInicial; i <= 4; i++) {
            System.out.println("\n---------------------------------------------");
            System.out.println(" >>> ETAPA " + nombres[i] + " DE FINAL <<< ");
            System.out.println("---------------------------------------------");

            List<Partido> partidosEtapa = gestor.generarSorteo(clasificados);
            List<String> proximosGanadores = new ArrayList<>();

            // Mostrar enfrentamientos
            System.out.println("PARTIDOS SORTEADOS:");
            for (int j = 0; j < partidosEtapa.size(); j++) {
                System.out.println("Partido " + (j + 1) + ":" + partidosEtapa.get(j));
                proximosGanadores.add(partidosEtapa.get(j).getGanador());
            }

            // Mostrar ganadores después de los partidos
            System.out.println("\nRESULTADOS DE LOS ENCUENTROS:");
            for (int j = 0; j < partidosEtapa.size(); j++) {
                System.out.println("  Ganador " + (j + 1) + ": [ " + partidosEtapa.get(j).getGanador() + " ]");
            }

            clasificados = proximosGanadores;

            if (i == 4) {
                System.out.println("\n*********************************************");
                System.out.println("  ¡EL GRAN CAMPEÓN ES: " + clasificados.get(0).toUpperCase() + "!");
                System.out.println("*********************************************");
            } else {
                System.out.println("\nPresione ENTER para avanzar a la siguiente etapa...");
                sc.nextLine();
            }
        }
    }
}