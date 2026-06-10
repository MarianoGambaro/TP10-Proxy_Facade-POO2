package pt1;

public class Main {

    public static void main(String args[]) {
        PersonaDao dao = new PersonaDao();
        Persona p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            System.out.println(telefono);
        }
    }
}

/* Sujeto : interfaz Set, define el comportamiento comun

 * Sujeto Real : HashSet, la colección que guarda los telefonos
   traidos de la BD. Es el objeto que queremos encapsular

 * Proxy : TelefonoSetProxy, la clase que se "hace pasar por los telefonos",
   intercepta las llamadas a la interfaz Set para retrasar la consulta SQL

 * Cliente: la clase Persona, interactua con el sujeto (Set)
   sin notar la dif enrte el sujeto real y el proxy

 */