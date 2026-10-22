package relaciones;

import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private String nombre;
    private List<Personaje> miembros; // Agregación (0..*)

    public Escuadron(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>(); // Inicialización de la colección
    }

    public String getNombre() {
        return nombre;
    }

    public List<Personaje> getMiembros() {
        return new ArrayList<>(miembros);
    }

    public void reclutarPersonaje(Personaje p) {
        if (p == null) {
            System.out.println("No se puede reclutar un personaje nulo.");
            return;
        }
        if (miembros.contains(p)) {
            System.out.println(p.getNombre() + " ya está en el escuadrón " + nombre + ".");
            return;
        }
        // El personaje ya existía, solo se guarda la referencia
        miembros.add(p);
        System.out.println(p.getNombre() + " fue reclutado en " + nombre + ".");
    }

    public void expulsarPersonaje(Personaje p) {
        if (p != null && miembros.remove(p)) {
            // p sigue existiendo con su estado, arma y corazón intactos
            System.out.println(p.getNombre() + " fue expulsado de " + nombre + ".");
        } else {
            System.out.println("Ese personaje no pertenece al escuadrón " + nombre + ".");
        }
    }

    public int getAtaqueTotalEscuadron() {
        int total = 0;
        for (Personaje p : miembros) {
            total += p.calcularAtaqueTotal();
        }
        return total;
    }

    public void imprimirReporte() {
        System.out.println("Escuadrón: " + nombre);
        System.out.printf("%-15s %-6s %-8s %-8s %-8s%n", "Nombre", "Nivel", "Salud", "Maná", "Ataque");
        for (Personaje p : miembros) {
            System.out.printf("%-15s %-6d %-8d %-8d %-8d%n",
                    p.getNombre(), p.getNivel(),
                    p.getCorazon().getSaludBase(), p.getCorazon().getManaBase(),
                    p.calcularAtaqueTotal());
        }
        System.out.printf("Ataque total del escuadrón: %d%n", getAtaqueTotalEscuadron());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escuadrón ").append(nombre).append(" | Integrantes: ");
        if (miembros.isEmpty()) {
            sb.append("ninguno");
        } else {
            for (int i = 0; i < miembros.size(); i++) {
                sb.append(miembros.get(i).getNombre());
                if (i < miembros.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }
}