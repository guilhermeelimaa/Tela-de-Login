package br.com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Conexao {

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        // Carregando o JDBC Driver
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);

        // Configurando a conexão
        String server = "localhost"; // Caminho do MySQL
        String database = "db_telalogin";
        String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);

        // Testando a conexão
        if (connection != null) {
            System.out.println("Status: Conectado!");
        } else {
            System.out.println("Status: Não CONECTADO!");
        }

        return connection;
    }
}
