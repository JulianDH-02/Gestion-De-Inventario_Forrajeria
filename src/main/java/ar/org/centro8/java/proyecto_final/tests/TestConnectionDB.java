package ar.org.centro8.java.proyecto_final.tests;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestConnectionDB {
    public static void main(String[] args) {
        Properties props = new Properties();
        //Creadmos un objeto de Properties para cargar el fichero de configuracion

        try (InputStream in = TestConnectionDB.class
                                .getClassLoader() //Obtenemos el objeto Class de esta clase
                                .getResourceAsStream("application.properties")) { //Busca el archivo que le pasamos como parametro y lo devuelve como un flujo de bytes
            if (in == null) {
                System.err.println("No se encontro el application.properties en el classpath");
                return;
            }
            props.load(in);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
            return;
        }

        //Creamos la configuracion del pool
        HikariConfig config = new HikariConfig();

        //Leemos la URL de la DB
        config.setJdbcUrl(props.getProperty("spring.datasource.url"));
        //Leemos las creedenciales de la DB
        config.setUsername(props.getProperty("spring.datasource.username"));
        config.setPassword(props.getProperty("spring.datasource.password"));

        //Creamos el datasource con el pool de de conexiones y probamos la conexion

        try (HikariDataSource ds = new HikariDataSource(config);
            Connection conn = ds.getConnection()) { //obtenemos la conexion
            if (conn.isValid(2)){ // esto comprueba si la coexion es valida. El parametro indica la cantidad de segundos que el driver JDBC va a esperar para confirmar la conexion con el servidor.
                System.out.println("Conexion exitosa a: "
                                    + conn.getMetaData().getURL());// con el getMetaData() obenemos la informacion sobre la conexion activa
                                                                //getUrl retorna la URL de la conexion utilizada
                                                                //la imprimimos para verificar a que base de datos nos conectamos.
            } else {
                System.err.println("Conecion establecida pero no valida");
            }
        } catch (Exception e) {
            System.err.println("No se pudo conectar" + e.getMessage());
        }



    }
}
