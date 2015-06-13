/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import Model.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author info206
 */
public class Conecta {
    
       public Connection conecta() throws SQLException{
    Connection conexao = null;

        String url = "jdbc:mysql://localhost/farmacia";  //Nome da base de dados
        String user = "root"; //nome do usuário do MySQL
        String password = "123456"; //senha do MySQL
        try{
            conexao = DriverManager.getConnection(url, user, password);
}catch(SQLException sqlex){
System.out.println("Erro na conexão "+ sqlex);
}
        return conexao;
 }
       
       public void desconecta(Connection conexao){
try{
conexao.close();
}catch(SQLException sqlex){
System.out.println("Erro na conexão "+ sqlex);
}
}
       public void CriaTabela(String nomeTabela,String atributos) throws SQLException {
            Statement statement = null;
            Connection conexao = null;
        try {
            conexao = conecta();
            statement = conexao.createStatement();
            String createTableSQL = "CREATE TABLE "+nomeTabela+"("+atributos+");";
            System.out.println(createTableSQL);
               statement.execute(createTableSQL); // executa o comando de criação
            System.out.println("Tabela \"nomeTabela\" foi criada com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {   // sempre feche o statement a conexão com banco
            if (statement != null) {
                statement.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
    }
   public void inserirPessoa(Pessoa p) throws SQLException {
        try {
             Connection conexao = conecta();
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
            Connection conexao = conecta();
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



       
       


    


