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
public class BOMensalidadeCurso {
    
    public static void inserir(TOMensalidadeCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOMensalidadeCurso.inserir(c, t);
        }
    }

    public static void alterar(TOMensalidadeCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOMensalidadeCurso.alterar(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOMensalidadeCurso.excluir(c, id);
        }
    }

    public static TOMensalidadeCurso obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOMensalidadeCurso.obter(c, id);
        }
    }

    public static List<TOMensalidadeCurso> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOMensalidadeCurso.lista(c);
        }
    }
    
}
