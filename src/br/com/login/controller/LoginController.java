package br.com.login.controller;

import br.com.login.dao.Conexao;
import br.com.login.dao.LoginDAO;
import br.com.login.view.CadastroView;
import br.com.login.view.LoginView;
import java.sql.Connection;  // Importar o pacote correto
import java.sql.SQLException;

/**
 *
 * @autor User
 */
public class LoginController {

    public void cadastroUsuario(CadastroView view) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = new Conexao().getConnection();
            LoginDAO cadastro = new LoginDAO();
            cadastro.cadastrarUsuario(view.getjTextFieldNome().getText(), view.getjTextFieldEmail().getText(), view.getjPasswordFieldSenha().getText());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar usuário", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void loginUsuario(LoginView view) throws SQLException, ClassNotFoundException {
    Connection connection = null;
    try {
        connection = new Conexao().getConnection();
        LoginDAO login = new LoginDAO();
        login.login(view.getjTextFieldUsuario().getText(), view.getjPasswordSenha().getText()); // Chamada corrigida
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao fazer login", e);
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    
}
