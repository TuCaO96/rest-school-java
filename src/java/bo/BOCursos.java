/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOCursos;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TOCursos;

/**
 *
 * @author 71500430
 */
public class BOCursos {
    
    public static void inserir(List<TOCursos> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOCursos t : l) {
                DAOCursos.save(c, t);
            }
        }
    }

    public static void inserir(TOCursos t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOCursos.save(c, t);
        }
    }

    public static void alterar(TOCursos t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOCursos.save(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOCursos.delete(c, id);
        }
    }

    public static TOCursos obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOCursos.getOne(c, id);
        }
    }

    public static List<TOCursos> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOCursos.getAll(c);
        }
    }
    
}
