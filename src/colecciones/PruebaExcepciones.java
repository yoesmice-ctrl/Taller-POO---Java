package colecciones;

import colecciones.exception.CapacidadExcedidaException;
import colecciones.exception.SinManaException;
import colecciones.exception.VidaInsuficienteException;
import colecciones.model.Item;
import colecciones.model.PerfilJugador;
import colecciones.model.Personaje;
import colecciones.model.enums.EstadoPersonaje;
import colecciones.model.enums.RaridadItem;
import colecciones.model.enums.TipoPersonaje;

/**
 * Clase de prueba para comprobar cada regla y sacar las capturas.
 */
public class PruebaExcepciones {

    public static void main(String[] args) {
        Personaje mago = new Personaje("Merlín", TipoPersonaje.MAGO);

        // Set: duplicados
        try {
            System.out.println("Bola de Fuego: " + mago.aprenderHabilidad("Bola de Fuego"));
            System.out.println("Bola de Fuego otra vez: " + mago.aprenderHabilidad("Bola de Fuego"));
            mago.aprenderHabilidad("Rayo");
            mago.aprenderHabilidad("Escudo");
            mago.aprenderHabilidad("Teletransporte");
            mago.aprenderHabilidad("Curación");
            mago.aprenderHabilidad("Invisibilidad"); // la sexta debe fallar
        } catch (CapacidadExcedidaException e) {
            System.out.println("Libro lleno -> " + e.getMessage());
        }

        // TreeSet: orden alfabético
        PerfilJugador perfil = new PerfilJugador("Yojhan");
        perfil.registrarLogro("Maestro del Mana");
        perfil.registrarLogro("Cazador de Dragones");
        perfil.registrarLogro("Primera Victoria");
        System.out.println("Repetido: " + perfil.registrarLogro("Primera Victoria"));
        System.out.println("Logros: " + perfil.listarLogrosOrdenados());

        // checked: SinManaException
        try {
            mago.lanzarHechizo(80);
        } catch (SinManaException e) {
            System.out.println("Sin maná -> " + e.getMessage());
        }

        // unchecked: VidaInsuficienteException
        try {
            mago.consumirVida(150);
        } catch (VidaInsuficienteException e) {
            System.out.println("Sin vida -> " + e.getMessage());
        }

        // checked: inventario lleno
        try {
            for (int i = 1; i <= 4; i++) {
                mago.agregarItem(new Item("Anillo " + i, RaridadItem.RARO, 10));
            }
        } catch (CapacidadExcedidaException e) {
            System.out.println("Inventario -> " + e.getMessage());
        }

        // enum con estado: veneno turno a turno
        mago.setEstado(EstadoPersonaje.ENVENENADO);
        for (int turno = 1; turno <= 14; turno++) {
            mago.aplicarEfectoPorTurno();
        }
        System.out.println("Tras el veneno: " + mago);
    }
}