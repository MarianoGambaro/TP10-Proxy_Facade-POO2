package pt1;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public PersonaDao() {
        inicializarBaseDeDatos();
    }

    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void inicializarBaseDeDatos() {
        String scriptTablas =
                "CREATE TABLE IF NOT EXISTS personas (" +
                        "  id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "  nombre VARCHAR(100)" +
                        "); " +
                        "CREATE TABLE IF NOT EXISTS telefonos (" +
                        "  id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "  numero VARCHAR(20), " +
                        "  idPersona INT, " +
                        "  FOREIGN KEY (idPersona) REFERENCES personas(id)" +
                        ");";

        //Inserto datos de prueba
        String scriptDatos =
                "INSERT INTO personas (id, nombre) VALUES (1, 'Juan'); " +
                        //inserto telefonos asociados al idPersona = 1
                        "INSERT INTO telefonos (numero, idPersona) VALUES ('2920223344', 1); " +
                        "INSERT INTO telefonos (numero, idPersona) VALUES ('2920443322', 1);";

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(scriptTablas);
            stmt.execute(scriptDatos);
        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar la BD en memoria", e);
        }
    }

    public Persona personaPorId(int id) {
        String sql = "SELECT nombre FROM personas WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String nombrePersona = result.getString(1);

                Set<Telefono> proxyTelefonos = new TelefonoSetProxy(id, this);

                //retorno a la persona con un proxy(sin sus telefonos, hasta que se soliciten)
                return new Persona(id, nombrePersona, proxyTelefonos);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Telefono> buscarTelefonosPorPersonaId(int idPersona) {
        String sql = "SELECT numero FROM telefonos WHERE idPersona = ?";
        Set<Telefono> telefonos = new HashSet<>();

        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, idPersona);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                telefonos.add(new Telefono(result.getString(1)));
            }
            return telefonos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
