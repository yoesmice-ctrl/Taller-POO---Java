package colecciones.model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class PerfilJugador {

    private final String nombreJugador;
    private final Set<String> logros = new TreeSet<>();

    public PerfilJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public boolean registrarLogro(String logro) {
        return logros.add(logro);
    }

    public Set<String> listarLogrosOrdenados() {
        return Collections.unmodifiableSet(logros);
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
}