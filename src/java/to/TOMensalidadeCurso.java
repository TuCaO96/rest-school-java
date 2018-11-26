/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

/**
 *
 * @author 71500286
 */
public class TOMensalidadeCurso {
    private int id;
    private TOCursos curso;
    private double valor;
    private double periodo;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the curso
     */
    public TOCursos getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(TOCursos curso) {
        this.curso = curso;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the periodo
     */
    public double getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(double periodo) {
        this.periodo = periodo;
    }
    
    
}
