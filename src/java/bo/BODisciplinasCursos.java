/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import fw.Data;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author 71500430
 */
public class BODisciplinasCursos {
    
    public static void inserir(TODisciplinasCursos t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAODisciplinasCursos.inserir(c, t);
        }
    }

    public static void alterar(TODisciplinasCursos t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAODisciplinasCursos.alterar(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAODisciplinasCursos.excluir(c, id);
        }
    }

    public static TODisciplinasCursos obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAODisciplinasCursos.obter(c, id);
        }
    }

    public static List<TODisciplinasCursos> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAODisciplinasCursos.lista(c);
        }
    }
    
}
