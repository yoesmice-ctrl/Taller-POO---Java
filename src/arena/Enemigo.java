package arena;

public class Enemigo extends Personaje {

    public Enemigo(String nombre, int vida, int ataqueBase, int posX, int posY) {
        super(nombre, vida, ataqueBase, posX, posY);
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        int dano = ataqueBase + 5;
        System.out.println("  " + nombre + " se lanza con un Golpe Salvaje sobre " + objetivo.getNombre() + " (" + dano + ")");
        objetivo.defender(dano);
    }

    @Override
    public char getSimbolo() {
        return 'E';
    }
}