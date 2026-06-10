package pt1;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

//con abstractSet no hace falta implementar todos los metodos de Set
public class TelefonoSetProxy extends AbstractSet<Telefono> {

    private final int idPersona;
    private final PersonaDao personaDao;
    private Set<Telefono> sujeto;

    public TelefonoSetProxy(int idPersona, PersonaDao personaDao) {
        this.idPersona = idPersona;
        this.personaDao = personaDao;
    }

    //con este metodo activamos la busqueda de los telefonos, solo si son solicitados
    //ya que ahora estamos devolviendo una persona con un proxy de los telefonos (vacio)
    private void inicializarSiHaceFalta() {
        if (sujeto == null) {
            this.sujeto = personaDao.buscarTelefonosPorPersonaId(idPersona);
        }
    }


    @Override
    public Iterator<Telefono> iterator() {
        inicializarSiHaceFalta();
        return sujeto.iterator();
    }

    @Override
    public int size() {
        inicializarSiHaceFalta();
        return sujeto.size();
    }
}
