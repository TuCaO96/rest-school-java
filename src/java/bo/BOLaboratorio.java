/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import fw.Data;
import java.sql.Connection;


/**
 *
 * @author 71500430
 */
public class BOLaboratorio {

    public static void inserir(TOLaboratorio t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOLaboratorio.inserir(c, t);
        }
    }

    public static void alterar(TOLaboratorio t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOLaboratorio.alterar(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOLaboratorio.excluir(c, id);
        }
    }

    public static TOLaboratorio obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOLaboratorio.obter(c, id);
        }
    }

    public static List<TOLaboratorio> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOLaboratorio.lista(c);
        }
    }
    
}

