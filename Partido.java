public class Partido {
    private String local;
    private String visitante;
    private String ganador;

    public Partido(String local, String visitante) {
        this.local = local;
        this.visitante = visitante;
        // Se decide el ganador de forma aleatoria para simular el avance del torneo
        this.ganador = (Math.random() > 0.5) ? local : visitante;
    }

    public String getGanador() {
        return ganador;
    }

    @Override
    public String toString() {
        // Formato con espaciado para que se vea ordenado en columnas
        return String.format("   %-15s vs %15s", local, visitante);
    }
}