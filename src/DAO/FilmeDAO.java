/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Filme;
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
public class FilmeDAO extends ExecuteSQL{

    public FilmeDAO(Connection con) {
        super(con);
    }
    public String Inserir_Filme(Filme a){
        String sql = "Insert into filme values(0,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
            
            ps.setString(1, a.getTitulo());
            ps.setInt(2, a.getAno());
            ps.setString(3, a.getDuracao());
            ps.setInt(4, a.getCod_categoria());
            ps.setInt(5, a.getCod_classificacao());
            ps.setString(6, a.getCapa());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
        public List<Filme> ListarFilme() {
            String sql = "select idfilme,titulo,ano,duracao,idcategoria,idclassificacao,capa from filme";
            List<Filme> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Filme a = new Filme();
                        a.setCodigo(rs.getInt(1));
                        a.setTitulo(rs.getString(2));
                        a.setAno(rs.getInt(3));
                        a.setDuracao(rs.getString(4));
                        a.setCod_categoria(rs.getInt(5));
                        a.setCod_classificacao(rs.getInt(6));
                        a.setCapa(rs.getString(7));
                        
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
        
        public List<Filme> Pesquisar_Cod_Filme(int cod) {
            String sql = "select idfilme,titulo,ano,duracao,idcategoria,idclassificacao,capa from filme where idfilme = '"+cod+"'";
            List<Filme> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Filme a = new Filme();
                        a.setCodigo(rs.getInt(1));
                        a.setTitulo(rs.getString(2));
                        a.setAno(rs.getInt(3));
                        a.setDuracao(rs.getString(4));
                        a.setCod_categoria(rs.getInt(5));
                        a.setCod_classificacao(rs.getInt(6));
                        a.setCapa(rs.getString(7));
                        
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
        
        
        public List<Filme> Pesquisar_Nome_Filme(String nome) {
            String sql = "select idfilme,titulo,ano,duracao,idcategoria,idclassificacao,capa from filme where titulo like '%"+ nome +"%'";
            List<Filme> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Filme a = new Filme();
                        a.setCodigo(rs.getInt(1));
                        a.setTitulo(rs.getString(2));
                        a.setAno(rs.getInt(3));
                        a.setDuracao(rs.getString(4));
                        a.setCod_categoria(rs.getInt(5));
                        a.setCod_classificacao(rs.getInt(6));
                        a.setCapa(rs.getString(7));
                        
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
        
        public List<Filme> ListarComboFilme() {
            String sql = "select titulo from filme order by titulo";
            List<Filme> lista = new ArrayList<>();
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {
                    Filme a = new Filme();
                    a.setTitulo(rs.getString(1));
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
        public List<Filme> ConsultaCodigoFilme(String nome){
            String sql = "select idfilme from filme where titulo = '" + nome + "'";
            List<Filme> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Filme a = new Filme();
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
        
        public boolean Testar_Filme(int cod) {
            boolean Resultado = false;
            try {
                String sql = "select * from filme where idfilme = " + cod + "";
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs !=null) {
                   while (rs.next()) {
                       Resultado = true;
                   } 
                }
            } catch (Exception e) {
                e.getMessage();
            }
            return Resultado;
        }
        
        public List<Filme> CapturarFilme(int cod) {
            String sql = "select idfilme, titulo, ano, duracao, idcategoria, idclassificacao, capa from filme where idfilme =" + cod;
            List<Filme> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Filme a = new Filme();
                        a.setCodigo(rs.getInt(1));
                        a.setTitulo(rs.getString(2));
                        a.setAno(rs.getInt(3));
                        a.setDuracao(rs.getString(4));
                        a.setCod_categoria(rs.getInt(5));
                        a.setCod_classificacao(rs.getInt(6));
                        a.setCapa(rs.getString(7));
                      
                        
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
        
        public String Alterar_Filme(Filme a) {
            String sql = "update filme set titulo = ? ,ano = ? ,duracao = ? "
                    + ",idcategoria = ? ,idclassificacao = ? ,capa = ?  where idfilme = ? ";
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                
                ps.setString(1, a.getTitulo());
                ps.setInt(2, a.getAno());
                ps.setString(3, a.getDuracao());
                ps.setInt(4, a.getCod_categoria());
                ps.setInt(5, a.getCod_classificacao());
                ps.setString(6, a.getCapa());
                
                ps.setInt(7, a.getCodigo());
                if (ps.executeUpdate() > 0) {
                    return "Atualizado com sucesso";
                } else {
                    return "Erro ao atualizar";
                }           
            } catch (Exception e) {
                return e.getMessage();
            }
        }
                
        public String Excluir(Filme a) {
            String sql = "delete from filme where idfilme = ? and titulo = ? ";
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ps.setInt(1, a.getCodigo());
                ps.setString(2, a.getTitulo());
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








