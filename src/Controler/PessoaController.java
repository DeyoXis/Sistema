/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sistema.Util;

/**
 *
 * @author info206
 */
public class PessoaController {
            public void inserirPessoa(Pessoa p) throws SQLException {
        try {
            Util util = new Util();
             Connection conexao = util.conecta();
            String sql = "INSERT INTO pessoa (nome, endereco, genero, nascimento ,cpf, cargo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);// note que agora criamos um Statement de forma diferente
            statement.setString(1, p.getNome());
            statement.setString(2, p.getEndereço());
            statement.setString(3, p.getGenero());
            statement.setString(4, p.getNascimento());
            statement.setString(5, p.getCPF());
            statement.setString(6, p.getCargo());
             
            int rowsInserted = statement.executeUpdate(); // Executa a inserção e retorna valor != 0 se inseriu (ID de inserção do banco)
            if (rowsInserted > 0) {
                System.out.println("Novo usuário inserido com sucesso");
            }
        statement.close();
        conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

public void selectPessoa()throws SQLException {
        try {
            String sql = "SELECT * FROM pessoa";
            Util util = new Util();
             Connection conexao = util.conecta();
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()){
                String nome = result.getString("nome");
                String endereco = result.getString("endereco");
                String genero = result.getString("genero");
                String nascimento = result.getString("nascimento");
                String cpf = result.getString("cpf");
                String cargo = result.getString("cargo");
                
                String output = "Pessoa #%d: %s - %s - %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, nome, endereco, genero, nascimento, cpf, cargo));
                                
                                statement.close();
                                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
}