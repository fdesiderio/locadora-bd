/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Cliente;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author aluno
 */
public class ClienteDAO extends ExecuteSQL{

    public ClienteDAO(Connection con) {
        super(con);
    }
    public String Inserir_Cliente(Cliente a){
        String sql = "Insert into cliente values(0,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
            
            ps.setString(1, a.getNome());
            ps.setString(2, a.getNascimento());
            ps.setString(3, a.getRG());
            ps.setString(4, a.getCPF());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getTelefone());
            ps.setString(7, a.getBairro());
            ps.setString(8, a.getRua());
            ps.setInt(9, a.getNumero());
            ps.setString(10, a.getCEP());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
        public List<Cliente> ListarCliente() {
            String sql = "select idcliente,nome,rg,cpf,telefone,email from cliente";
            List<Cliente> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Cliente a = new Cliente();
                        a.setCodigo(rs.getInt(1));
                        a.setNome(rs.getString(2));
                        a.setRG(rs.getString(3));
                        a.setCPF(rs.getString(4));
                        a.setTelefone(rs.getString(5));
                        a.setEmail(rs.getString(6));
                        
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
        public List<Cliente> ListarComboCliente() {
            String sql = "select nome from cliente order by nome";
            List<Cliente> lista = new ArrayList<>();
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {
                    Cliente a = new Cliente();
                    a.setNome(rs.getString(1));
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
        public List<Cliente> ConsultaCodigoCliente(String nome){
            String sql = "select idcliente from cliente where nome = '" + nome + "'";
            List<Cliente> lista = new ArrayList<>();
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if (rs != null) {
                    while (rs.next()) {                        
                        Cliente a = new Cliente();
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
        public String Excluir(Cliente a) {
            String sql = "delete from cliente where idcliente = ? and nome = ? ";
            
            try {
                PreparedStatement ps = (PreparedStatement) getCon().prepareStatement(sql);
                ps.setInt(1, a.getCodigo());
                ps.setString(2, a.getNome());
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


