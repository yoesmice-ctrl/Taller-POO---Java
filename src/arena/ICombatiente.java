package arena;

/**
 * Contrato mínimo de defensa para cualquier entidad que participe en el combate.
 */
public interface ICombatiente {

    // Procesa el daño que llega, aplicando la mitigación propia de cada entidad
    void defender(int danoRecibido);
}