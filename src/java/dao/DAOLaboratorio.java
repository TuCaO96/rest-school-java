/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import to.TOLaboratorios;

/**
 *
 * @author 71500430
 */
public class DAOLaboratorio {
 
    public static boolean inserir(Connection c, TOLaboratorios t) throws Exception {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into laboratorios (nome, disciplinas, localidade_bloco, qtd_labo) values ( ?, ?, ?, ?) ");
        
        return Data.executeUpdate(c, sql.toString(), t.getNome(), t.getDisciplinas(), t.getLocalidade_bloco(), t.getQtd_labo()) > 0;
        
    }
    
    public static boolean alterar(Connection c, TOLaboratorios t) throws Exception {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" update laboratorios set nome = ?, disciplina = ?, localidade_bloco = ?, qtd_labo = ? where id = ? ");
        
        return Data.executeUpdate(c, sql.toString(), t.getNome(), t.getDisciplinas(), t.getLocalidade_bloco(), t.getQtd_labo()) > 0;
        
    }
    
    public static boolean excluir(Connection c, int id) throws Exception {
        
       StringBuilder sql = new StringBuilder();
       sql.append(" delete from laboratorios where id = ? ");
       
       return Data.executeUpdate(c, sql.toString(), id) > 0;
    }
    
    public static TOLaboratorios obter(Connection c, int id) throws Exception { /* colocar cache */
        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, disciplina, localidade_bloco, qtd_labo from laboratorios where id = ? ");        
        try (ResultSet rs = Data.executeQuery(c, sql.toString(), id)){            
            if(rs.next()){
                TOLaboratorios t = new TOLaboratorios();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setDisciplinas(rs.getString("disciplina"));
                t.setLocalidade_bloco(rs.getString("localidade_bloco"));
                t.setQtd_labo(rs.getInt("qtd_labo"));
                return t;
            }else{
                return null;
            }            
        }catch(Exception e){
            return null;
        }
    }
    
    public static List<TOLaboratorios> lista(Connection c) throws Exception { /* colocar cache */
        
        StringBuilder sql = new StringBuilder();
        sql.append("select * from laboratorios");
        
        List<TOLaboratorios> l = new ArrayList<>();
        
        try (ResultSet rs = Data.executeQuery(c, sql.toString())){
            if(rs.next()){
                TOLaboratorios t = new TOLaboratorios();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setDisciplinas(rs.getString("disciplina"));
                t.setLocalidade_bloco(rs.getString("localidade_bloco"));
                t.setQtd_labo(rs.getInt("qtd_labo"));
                l.add(t);
            }
        }catch(Exception e){
            return null;
        }
        return l;
    }
    
}
