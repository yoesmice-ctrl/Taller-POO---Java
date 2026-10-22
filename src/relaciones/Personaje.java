package relaciones;

public class Personaje {
    private String nombre;
    private int nivel;
    private Arma armaEquipada;        // Asociación unidireccional (0..1)
    private Gremio gremio;            // Asociación bidireccional (0..*  a 1)
    private final CorazonRPG corazon; // Composición (1..1)

    public Personaje(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.armaEquipada = null; // Inicialmente desarmado
        this.gremio = null;
        // Composición: el corazón se crea aquí dentro, nadie de afuera lo pasa
        this.corazon = new CorazonRPG(100 + nivel * 10, 50 + nivel * 5);
    }

    // ---------- Asociación unidireccional ----------
    public void equiparArma(Arma nuevaArma) {
        this.armaEquipada = nuevaArma;
        if (nuevaArma != null) {
            System.out.println(nombre + " equipó el arma: " + nuevaArma.getNombre());
        } else {
            System.out.println(nombre + " quedó sin arma.");
        }
    }

    public void desequiparArma() {
        if (this.armaEquipada != null) {
            System.out.println(nombre + " guardó el arma: " + armaEquipada.getNombre());
            this.armaEquipada = null;
        } else {
            System.out.println(nombre + " no tiene ningún arma para retirar.");
        }
    }

    public int calcularAtaqueTotal() {
        int ataqueBase = this.nivel * 5;
        if (this.armaEquipada != null) {
            ataqueBase += this.armaEquipada.getDanioBase();
        }
        return ataqueBase;
    }

    // ---------- Asociación bidireccional ----------
    public void unirseAGremio(Gremio nuevoGremio) {
        if (nuevoGremio == null || nuevoGremio == this.gremio) {
            return;
        }
        // Si ya estaba en otro gremio, primero se sale de ese
        if (this.gremio != null) {
            salirDeGremio();
        }
        this.gremio = nuevoGremio;
        nuevoGremio.agregarMiembro(this);
    }

    public void salirDeGremio() {
        if (this.gremio == null) {
            return;
        }
        Gremio anterior = this.gremio;
        this.gremio = null;
        anterior.removerMiembro(this);
    }

    // ---------- Getters ----------
    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public Gremio getGremio() {
        return gremio;
    }

    public CorazonRPG getCorazon() {
        return corazon;
    }

    // Composición: no existe setCorazon() a propósito

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Personaje: ").append(nombre).append(" (nivel ").append(nivel).append(")\n");
        sb.append("  ").append(corazon).append("\n");
        sb.append("  Arma: ").append(armaEquipada != null ? armaEquipada : "desarmado").append("\n");
        // Solo el nombre del gremio, no gremio.toString(), para no caer en recursión infinita
        sb.append("  Gremio: ").append(gremio != null ? gremio.getNombre() : "sin gremio").append("\n");
        sb.append("  Ataque total: ").append(calcularAtaqueTotal());
        return sb.toString();
    }
}