/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sistema.Util;

public class PessoaController {
     public void inserirPessoa(Pessoa p) throws SQLException {
        try {
            
            Util util = new Util(); // inicializar a classe util
            Connection conexao = util.conecta();//utilizar o método conecta da classe util
                
            String sql = "INSERT INTO pessoa (nome, endereco, genero, nascimento ,cpf, cargo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);// note que agora criamos um Statement de forma diferente
            statement.setString(1, p.getNome());
            statement.setString(2, p.getEndereco());
            statement.setString(3, p.getGenero());
            statement.setString(4, p.getNascimento());
            statement.setString(5, p.getCpf());
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
            Util util = new Util(); // inicializar a classe util
             Connection conexao = util.conecta();//utilizar o método conecta da classe util
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()){
                                    System.out.println(result);

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
  
  public void atualiza() throws SQLException{
  String sql = "UPDATE pessoa SET endereco=?, cargo=? WHERE cpf=?";
  Connection conexao = null;
 
PreparedStatement statement = conexao.prepareStatement(sql);{
            statement.setString(1, "Ruas das Flores");
            statement.setString(2, "Gerente");
            statement.setString(3, "123456987-36");
        
int rowsUpdated = statement.executeUpdate();
if (rowsUpdated > 0) {
    System.out.println("Atualizado com sucesso");
}
}
}
}
