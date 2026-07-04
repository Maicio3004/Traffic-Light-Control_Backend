/*package co.edu.uis.traffic.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private static final String IMPORT_FLAG = "IMPORT_INITIAL_DATA_DONE";

    private final JdbcTemplate jdbcTemplate;   // Inyectado automáticamente

    @Bean
    CommandLineRunner runSql(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        return args -> {
            try {
                // Verificar si ya se ejecutó el import
                boolean alreadyImported = checkIfAlreadyImported();

                if (alreadyImported) {
                    logger.info("✅ Los datos iniciales ya fueron cargados anteriormente. Saltando import.sql");
                    return;
                }

                logger.info("🚀 Ejecutando import.sql por primera vez...");

                Thread.sleep(2000);

                try (Connection connection = dataSource.getConnection()) {
                    ScriptUtils.executeSqlScript(
                            connection,
                            new ClassPathResource("import.sql"));
                }

                // Marcar como completado
                markAsImported();
                logger.info("✅ Importación inicial completada exitosamente.");

            } catch (Exception ex) {
                logger.error("❌ Error al ejecutar el script de inicialización", ex);
                throw new RuntimeException(ex);
            }
        };
    }

    private boolean checkIfAlreadyImported() {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM schema_version WHERE type = ?",
                    Integer.class,
                    IMPORT_FLAG);
            return count != null && count > 0;
        } catch (Exception e) {
            // Si la tabla no existe aún, significa que es la primera vez
            return false;
        }
    }

    private void markAsImported() {
        try {
            jdbcTemplate.update(
                    "INSERT INTO schema_version (type, version, description) VALUES (?, '1.0', 'Initial data import')",
                    IMPORT_FLAG);
        } catch (Exception e) {
            // Ignorar si ya existe (por si acaso)
            logger.error("Error al actualizar el registro: {}", e.getMessage());
        }
    }
}*/