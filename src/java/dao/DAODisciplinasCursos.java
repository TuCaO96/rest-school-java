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
import to.TODisciplinasCurso;

/**
 *
 * @author 71500430
 */
public class DAODisciplinasCursos {
    
    public static void inserir(Connection c, TODisciplinasCurso t) throws Exception {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into disciplinas_curso (nome, descricao, periodo) values ( ?, ?, ?) ");
        
        Data.executeUpdate(c, sql.toString(), t.getNome(), t.getDescricao(), t.getPeriodo());
        
    }
    
    public static void alterar(Connection c, TODisciplinasCurso t) throws Exception {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" update disciplinas_curso set nome = ?, descricao = ?, periodo = ? where id = ? ");
        
        Data.executeUpdate(c, sql.toString(), t.getNome(), t.getDescricao(), t.getPeriodo(), t.getId());
        
    }
    
    public static void excluir(Connection c, int id) throws Exception {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from disciplinas_curso where id = ? ");
        
       Data.executeUpdate(c, sql.toString(), id);
    }
    
    public static TODisciplinasCurso obter(Connection c, int id) throws Exception { /* colocar cache */
        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, descricao, periodo from disciplinas_curso where id = ? ");        
        try (ResultSet rs = Data.executeQuery(c, sql.toString(), id)){            
            if(rs.next()){
                TODisciplinasCurso t = new TODisciplinasCurso();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setDescricao(rs.getString("descricao"));
                t.setPeriodo(rs.getInt("periodo"));
                return t;
            }else{
                return null;
            }            
        }        
    }
    
    public static List<TODisciplinasCurso> lista(Connection c) throws Exception { /* colocar cache */
        
        StringBuilder sql = new StringBuilder();
        sql.append("select id, nome, descricao, periodo from disciplinas_curso order by nome asc ");
        
        List<TODisciplinasCurso> l = new ArrayList<>();
        
        try (ResultSet rs = Data.executeQuery(c, sql.toString())){
            if(rs.next()){
                TODisciplinasCurso t = new TODisciplinasCurso();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setDescricao(rs.getString("descricao"));
                t.setPeriodo(rs.getInt("periodo"));
                l.add(t);
            }
        }
        
        return l;
        
    }
    
}
