package desk.mitienda.utils;

import org.flywaydb.core.Flyway;

public class FlyWay {
    private static Flyway flyway = Flyway.configure()
            .dataSource("jdbc:mysql://localhost:3306/mitienda", "root", "").load();

    public static void migrate() {
        flyway.migrate();
    }
}
