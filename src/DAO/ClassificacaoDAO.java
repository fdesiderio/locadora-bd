/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Classificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ClassificacaoDAO extends ExecuteSQL{

    public ClassificacaoDAO(Connection con) {
        super(con);
    }
    public String Inserir_Classificacao(Classificacao a){
        String sql = "Insert into classificacao values(0,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
            
            ps.setString(1, a.getNome());
            ps.setDouble(2, a.getPreco());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
        public List<Classificacao> ListarClassificacao() {
            String sql = "select idClassificacao,nome,preco from Classificacao";
            List<Classificacao> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Classificacao a = new Classificacao();
                        a.setCodigo(rs.getInt(1));
                        a.setNome(rs.getString(2));
                        a.setPreco(rs.getDouble(3));
                        
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
        public List<Classificacao> ListarComboClassificacao() {
            String sql = "select nome from Classificacao order by nome";
            List<Classificacao> lista = new ArrayList<>();
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {
                    Classificacao a = new Classificacao();
                    a.setNome(rs.getString(2));
                    a.setPreco(rs.getDouble(3));
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
        public List<Classificacao> ConsultaCodigoClassificacao(String nome){
            String sql = "select idClassificacao from Classificacao where nome = '" + nome + "'";
            List<Classificacao> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Classificacao a = new Classificacao();
                        a.setCodigo(rs.getInt(1));
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
        public String Excluir(Classificacao a) {
            String sql = "delete from Classificacao where idClassificacao = ? and nome = ? ";
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ps.setInt(1, a.getCodigo());
                ps.setString(2, a.getNome());
                ps.setDouble(3, a.getPreco());
                
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