package colecciones.model;

import colecciones.exception.CapacidadExcedidaException;
import colecciones.exception.SinManaException;
import colecciones.exception.VidaInsuficienteException;
import colecciones.model.enums.EstadoPersonaje;
import colecciones.model.enums.TipoPersonaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Personaje {

    private static final int MAX_HABILIDADES = 5;
    private static final int MAX_ITEMS = 3;
    private static final int DANO_VENENO = 8;

    private final String nombre;
    private final TipoPersonaje tipoPersonaje;
    private EstadoPersonaje estado;
    private int vida;
    private int mana;
    private final int ataqueBase = 20;
    private final int defensaBase = 10;
    private final Set<String> habilidades = new HashSet<>();
    private final List<Item> items = new ArrayList<>();

    public Personaje(String nombre, TipoPersonaje tipoPersonaje) {
        this.nombre = nombre;
        this.tipoPersonaje = tipoPersonaje;
        this.estado = EstadoPersonaje.VIVO;
        this.vida = 100;
        this.mana = 50;
    }

    // ---------- Set de habilidades ----------

    public boolean aprenderHabilidad(String habilidad) throws CapacidadExcedidaException {
        if (habilidades.contains(habilidad)) {
            return false;
        }
        if (habilidades.size() >= MAX_HABILIDADES) {
            throw new CapacidadExcedidaException("libro de habilidades", MAX_HABILIDADES);
        }
        return habilidades.add(habilidad);
    }

    public Set<String> obtenerHabilidades() {
        return Collections.unmodifiableSet(habilidades);
    }

    // ---------- Inventario ----------

    public void agregarItem(Item item) throws CapacidadExcedidaException {
        if (items.size() >= MAX_ITEMS) {
            throw new CapacidadExcedidaException("inventario", MAX_ITEMS);
        }
        items.add(item);
    }

    // ---------- Combate ----------

    public void lanzarHechizo(int costoMana) throws SinManaException {
        if (costoMana > mana) {
            throw new SinManaException(mana, costoMana);
        }
        mana -= costoMana;
    }

    public void consumirVida(int costoVida) {
        if (costoVida >= vida) {
            throw new VidaInsuficienteException(vida, costoVida);
        }
        vida -= costoVida;
    }

    public boolean puedeActuar() {
        return estado != EstadoPersonaje.ATURDIDO && estado != EstadoPersonaje.DERROTADO;
    }

    // se llama al final de cada turno
    public void aplicarEfectoPorTurno() {
        if (estado == EstadoPersonaje.ENVENENADO) {
            try {
                consumirVida(DANO_VENENO);
            } catch (VidaInsuficienteException e) {
                vida = 0;
                estado = EstadoPersonaje.DERROTADO;
            }
        }
    }

    public double getAtaque() {
        return ataqueBase * (1 + tipoPersonaje.getBonificadorAtaqueBase());
    }

    public double getDefensa() {
        return defensaBase * (1 + tipoPersonaje.getBonificadorDefensaBase());
    }

    // ---------- getters / setters ----------

    public String getNombre() {
        return nombre;
    }

    public TipoPersonaje getTipoPersonaje() {
        return tipoPersonaje;
    }

    public EstadoPersonaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoPersonaje estado) {
        this.estado = estado;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipoPersonaje + ") vida=" + vida + " mana=" + mana + " estado=" + estado;
    }
}