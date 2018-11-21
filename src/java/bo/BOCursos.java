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
public class BOCursos {
    
    public static void inserir(List<TOCurso> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOCurso t : l) {
                DAOCurso.inserir(c, t);
            }
        }
    }

    public static void inserir(TOCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOCurso.inserir(c, t);
        }
    }

    public static void alterar(TOCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOCurso.alterar(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOCurso.excluir(c, id);
        }
    }

    public static TOCurso obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOCurso.obter(c, id);
        }
    }

    public static List<TOCurso> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOCurso.lista(c);
        }
    }
    
}
