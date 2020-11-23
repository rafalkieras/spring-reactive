package it.mock;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class DbMock implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean started = false;

    private PostgreSQLContainer db;

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;

            db = new PostgreSQLContainer().withDatabaseName("test");
            db.start();

            System.setProperty("dataSource.url",
                    "jdbc:postgresql://localhost:" + db.getFirstMappedPort() + "/test");
            System.setProperty("dataSource.username", "test");
            System.setProperty("dataSource.password", "test");
            System.setProperty("dataSource.port", db.getFirstMappedPort().toString());

            context.getRoot().getStore(GLOBAL).put("it.mock.DbMock", this);
        }
    }

    @Override
    public void close() {
        db.stop();
    }
}
