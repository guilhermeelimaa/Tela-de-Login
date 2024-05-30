package br.com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class LoginDAO {

    public void cadastrarUsuario(String nome, String email, String senha) throws ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = new Conexao().getConnection();
            String sql = "INSERT INTO db_telalogin.tb_login (nome, email, senha) VALUES (?, ?, ?)";
            statement = conexao.prepareStatement(sql);

            // Definir os parâmetros do PreparedStatement
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, senha);

            // Executar a inserção
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean login(String email, String senha) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = new Conexao().getConnection();
            String sql = "SELECT email, senha FROM db_telalogin.tb_login WHERE email = ?";
            statement = conexao.prepareStatement(sql);

            // Definir o parâmetro do PreparedStatement
            statement.setString(1, email);

            // Executar a consulta
            resultSet = statement.executeQuery();

            // Verificar se encontrou algum registro correspondente ao login
            if (resultSet.next()) {
                // Verificar se a senha corresponde
                if (senha.equals(resultSet.getString("senha"))) {
                    System.out.println("Login bem-sucedido para o email: " + email);
                    return true;
                } else {
                    System.out.println("Senha incorreta para o email: " + email);
                    return false;
                }
            } else {
                System.out.println("Nenhum registro encontrado para o email: " + email);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro SQL
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
