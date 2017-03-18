package Entidades;

import java.util.List;

/**
 * Created by bravo3465 on 11/10/15.
 */
public class Usuario
{
    private int _id;
    private String nome;
    private String email;
    private String telefone;
    private List<Endereco> endereco;
    private String usuario;
    private String senha;
    private int logado;
    private int logarAuto;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getLogado() {
        return logado;
    }

    public void setLogado(int logado) {
        this.logado = logado;
    }

    public int getLogarAuto() {
        return logarAuto;
    }

    public void setLogarAuto(int logarAuto) {
        this.logarAuto = logarAuto;
    }
}
