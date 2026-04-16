package co.edu.uis.traffic.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Set;

/**
 * <h1>Cargar variables de entorno</h1>
 * <p>
 *     Utilidad para cargar variables del archivo .env de forma controlada.
 * </p>
 */
public class EnvLoader {

    private final Dotenv dotenv;

    public EnvLoader() {
        dotenv = Dotenv.configure()
                .directory(".") // busca en la raíz del proyecto
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

    /**
     * <p>
     * Carga las variables definidas en el archivo .env,
     * solo las que están en la lista permitida.
     * </p>
     *
     * @param keys nombre de las variables a cargar
     */
    public void loadSelected(Set<String> keys) {
        keys.stream()
                .filter(key -> dotenv.get(key) != null && !dotenv.get(key).isEmpty())
                .forEach(key -> System.setProperty(key, dotenv.get(key)));
    }

    /**
     * Imprime todas las variables del archivo .env (sin tocar variables de entorno del sistema).
     */
    public void printEnvVars(Set<String> keys) {
        keys.forEach(key -> System.out.println(key + ": " + getValue(key)));
    }

    /**
     * Acceso directo a una variable específica.
     */
    public String getValue(String key) {
        return dotenv.get(key);
    }

}

