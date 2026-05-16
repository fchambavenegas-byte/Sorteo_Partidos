import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorTorneo {

    public List<Partido> generarSorteo(List<String> equipos) {
        List<String> bolsaDeEquipos = new ArrayList<>(equipos);
        // mezclamos para que sea aleatorio
        Collections.shuffle(bolsaDeEquipos);

        List<Partido> listaPartidos = new ArrayList<>();
        //  método recursivo
        sorteoRecursivo(bolsaDeEquipos, listaPartidos);

        return listaPartidos;
    }

    private void sorteoRecursivo(List<String> equipos, List<Partido> partidos) {
        // CASO BASE: Si hay menos de 2 equipos, ya no se pueden formar más partidos
        if (equipos.size() < 2) {
            return;
        }

        // tomamos los 2 primeros y creamos el partido
        String e1 = equipos.get(0);
        String e2 = equipos.get(1);
        partidos.add(new Partido(e1, e2));

        //Llamamos de nuevo con una sublista que excluye a los 2 ya usados
        sorteoRecursivo(equipos.subList(2, equipos.size()), partidos);
    }
}
