package arena;

/**
 * Clase base abstracta. Guarda el estado común de héroes y enemigos
 * y obliga a las subclases a definir su ataque especial.
 */
public abstract class Personaje implements ICombatiente {

    protected String nombre;
    protected int vida;
    protected int ataqueBase;
    protected int posicionX; // fila en el tablero
    protected int posicionY; // columna en el tablero

    private final int vidaMaxima;

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataqueBase = ataqueBase;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    // Cada subclase decide cómo es su ataque único
    public abstract void realizarAtaqueEspecial(Personaje objetivo);

    // Comportamiento por defecto: sin mitigación. Las subclases pueden sobreescribirlo.
    @Override
    public void defender(int danoRecibido) {
        recibirDano(danoRecibido);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void recibirDano(int cantidad) {
        if (cantidad < 0) {
            return;
        }
        vida = Math.max(0, vida - cantidad);
    }

    public void curar(int cantidad) {
        if (cantidad > 0 && estaVivo()) {
            vida = Math.min(vidaMaxima, vida + cantidad);
        }
    }

    public char getSimbolo() {
        return 'P';
    }

    public String getEstado() {
        return nombre + " | Vida: " + vida + "/" + vidaMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicion(int x, int y) {
        this.posicionX = x;
        this.posicionY = y;
    }
}