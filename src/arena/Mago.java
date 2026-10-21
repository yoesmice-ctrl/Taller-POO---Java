package arena;

public class Mago extends Personaje {

    private static final int COSTO_ESPECIAL = 15;

    protected int puntosMana;

    public Mago(String nombre, int vida, int ataqueBase, int posX, int posY, int puntosMana) {
        super(nombre, vida, ataqueBase, posX, posY);
        this.puntosMana = puntosMana;
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        if (puntosMana >= COSTO_ESPECIAL) {
            puntosMana -= COSTO_ESPECIAL;
            int dano = ataqueBase * 2;
            System.out.println("  " + nombre + " lanza una Bola de Fuego a " + objetivo.getNombre() + " (" + dano + " de daño)");
            objetivo.defender(dano);
        } else {
            // Sin maná solo le queda una chispa
            int dano = ataqueBase / 2;
            System.out.println("  " + nombre + " no tiene maná, solo lanza una chispa (" + dano + ")");
            objetivo.defender(dano);
        }
    }

    // Sobrecarga: ataque arcano simple
    public void atacar(Personaje p) {
        atacar(p, false);
    }

    // Sobrecarga: concentrado gasta 5 de maná y pega más fuerte
    public void atacar(Personaje p, boolean concentrado) {
        int dano = ataqueBase / 2;
        if (concentrado && puntosMana >= 5) {
            puntosMana -= 5;
            dano = ataqueBase;
        }
        System.out.println("  " + nombre + " lanza un rayo a " + p.getNombre() + " (" + dano + ")");
        p.defender(dano);
    }

    public void recuperarMana(int cantidad) {
        puntosMana += cantidad;
    }

    @Override
    public char getSimbolo() {
        return 'M';
    }

    @Override
    public String getEstado() {
        return super.getEstado() + " | Maná: " + puntosMana;
    }
}