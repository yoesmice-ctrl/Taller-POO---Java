package arena;

public class Pocion {

    private final String nombre;
    private final int curacion;

    public Pocion(String nombre, int curacion) {
        this.nombre = nombre;
        this.curacion = curacion;
    }

    public void usar(Personaje p) {
        int antes = p.getVida();
        p.curar(curacion);
        System.out.println("  " + p.getNombre() + " usa " + nombre + " y recupera " + (p.getVida() - antes) + " de vida");
    }

    public String getNombre() {
        return nombre;
    }

    public int getCuracion() {
        return curacion;
    }
}