package arena;

public class Guerrero extends Personaje {

    protected int fuerzaEscudo;

    public Guerrero(String nombre, int vida, int ataqueBase, int posX, int posY, int fuerzaEscudo) {
        super(nombre, vida, ataqueBase, posX, posY);
        this.fuerzaEscudo = fuerzaEscudo;
    }

    // El escudo absorbe parte del golpe, pero siempre entra al menos 1 de daño
    @Override
    public void defender(int danoRecibido) {
        int reducido = Math.max(1, danoRecibido - fuerzaEscudo / 2);
        System.out.println("  " + nombre + " se cubre con el escudo y el golpe baja a " + reducido);
        recibirDano(reducido);
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        int dano = ataqueBase * 2;
        System.out.println("  " + nombre + " lanza un Golpe de Escudo a " + objetivo.getNombre() + " (" + dano + " de daño)");
        objetivo.defender(dano);
    }

    // Sobrecarga: ataque normal
    public void atacar(Personaje p) {
        atacar(p, false);
    }

    // Sobrecarga: en modo furia pega el doble pero se lastima a sí mismo
    public void atacar(Personaje p, boolean modoFuria) {
        int dano = modoFuria ? ataqueBase * 2 : ataqueBase;
        System.out.println("  " + nombre + " ataca a " + p.getNombre() + (modoFuria ? " en modo furia" : "") + " (" + dano + ")");
        p.defender(dano);
        if (modoFuria) {
            recibirDano(5);
        }
    }

    @Override
    public char getSimbolo() {
        return 'G';
    }

    @Override
    public String getEstado() {
        return super.getEstado() + " | Escudo: " + fuerzaEscudo;
    }
}