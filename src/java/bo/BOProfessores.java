/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOProfessores;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TOProfessores;

/**
 *
 * @author 71500430
 */
public class BOProfessores {
    
    public static void inserir(List<TOProfessores> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOProfessores t : l) {
                DAOProfessores.save(c, t);
            }
        }
    }

    public static void inserir(TOProfessores t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOProfessores.save(c, t);
        }
    }

    public static void alterar(TOProfessores t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOProfessores.save(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOProfessores.delete(c, id);
        }
    }

    public static TOProfessores obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOProfessores.getOne(c, id);
        }
    }

    public static List<TOProfessores> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOProfessores.getAll(c);
        }
    }
    
}
