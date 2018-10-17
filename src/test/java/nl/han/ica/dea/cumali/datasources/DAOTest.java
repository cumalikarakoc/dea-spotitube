package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;
import nl.han.ica.dea.cumali.datasources.util.ScriptRunner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOTest {
    protected Connection connection;

    DAOTest (){
        DatabaseProperties databaseProperties = new DatabaseProperties();
        connection = databaseProperties.getConnection();
        ScriptRunner scriptRunner = new ScriptRunner(connection, true, true);
        try {
            scriptRunner.runScript(new InputStreamReader(ClassLoader.getSystemResourceAsStream("import.sql")));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
