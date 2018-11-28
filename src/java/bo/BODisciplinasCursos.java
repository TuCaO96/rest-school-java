/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAODisciplinasCursos;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TODisciplinasCurso;

/**
 *
 * @author 71500430
 */
public class BODisciplinasCursos {
    
    public static void inserir(TODisciplinasCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAODisciplinasCursos.inserir(c, t);
        }
    }

    public static void alterar(TODisciplinasCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAODisciplinasCursos.alterar(c, t);
        }
    }

    public static boolean excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAODisciplinasCursos.excluir(c, id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static TODisciplinasCurso obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAODisciplinasCursos.obter(c, id);
        }
    }

    public static List<TODisciplinasCurso> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAODisciplinasCursos.lista(c);
        }
    }
    
}
