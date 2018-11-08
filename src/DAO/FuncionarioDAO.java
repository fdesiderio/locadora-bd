/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import Modelo.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends ExecuteSQL{

    public FuncionarioDAO(Connection con) {
        super(con);
    }
    public boolean Logar (String login, String senha) {
        boolean finalResult = false;
        try {
            String consulta = "select login, senha from funcionario " +
                    "where login = '" + login + "' and senha = '" + senha + "'";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {                    
                    Funcionario a = new Funcionario();
                    a.setLogin(rs.getString(1));
                    a.setSenha(rs.getString(2));
                    finalResult = true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return finalResult;
    }
    public String Inserir_Funcionario(Funcionario a){
        String sql = "Insert into Funcionario values(0,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
            
            ps.setString(2, a.getNome());
            ps.setString(3, a.getLogin());
            ps.setString(4, a.getSenha());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
        public List<Funcionario> ListarFuncionario() {
            String sql = "select idFuncionario,nome,preco from Funcionario";
            List<Funcionario> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Funcionario a = new Funcionario();
                        a.setCod(rs.getInt(1));
                        a.setNome(rs.getString(2));
                        a.setLogin(rs.getString(3));
                        a.setSenha(rs.getString(4));
                        
                        lista.add(a);
                    }
                    return lista;
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
            }
        public List<Funcionario> ListarComboFuncionario() {
            String sql = "select nome from Funcionario order by nome";
            List<Funcionario> lista = new ArrayList<>();
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {
                    Funcionario a = new Funcionario();
                    a.setNome(rs.getString(2));
                    a.setLogin(rs.getString(3));
                    a.setSenha(rs.getString(4));
                    lista.add(a);
                    }
                    return lista;
                } else {
                    return null;
                }
                
            } catch (Exception e) {
                return null;
        
            }
        }
        public List<Funcionario> ConsultaCodigoFuncionario(String nome){
            String sql = "select idFuncionario from Funcionario where nome = '" + nome + "'";
            List<Funcionario> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Funcionario a = new Funcionario();
                        a.setCod(rs.getInt(1));
                        lista.add(a);
                    }
                    return lista;
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        
        public String Excluir(Funcionario a) {
            String sql = "delete from Funcionario where idFuncionario = ? and nome = ? ";
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ps.setInt(1, a.getCod());
                ps.setString(2, a.getNome());
                ps.setString(3, a.getLogin());
                ps.setString(4, a.getSenha());
                if (ps.executeUpdate() > 0) {
                    return "Excluindo com sucesso.";
                } else {
                    return "Erro ao excluir.";
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        }
}
