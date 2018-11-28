package to;

import fw.Criptografia;
import java.sql.Timestamp;

/**
 *
 * @author dirceu
 */
public class TOUsuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String chave;
    private Timestamp expiracao;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) throws Exception {
        this.senha = senha;
    }

    /**
     * @return the chave
     */
    public String getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(String chave) {
        this.chave = chave;
    }

    /**
     * @return the expiracao
     */
    public Timestamp getExpiracao() {
        return expiracao;
    }

    /**
     * @param expiracao the expiracao to set
     */
    public void setExpiracao(Timestamp expiracao) {
        this.expiracao = expiracao;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
