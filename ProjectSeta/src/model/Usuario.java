package model;

import java.util.List;
import model.modelSQLite.SQLUsuariosDAO;

/**
 *
 * @author Eliandro
 */
public class Usuario {

    private Integer id;

    private String nomeCompleto;
    private String email;
    private String dia, mes, ano;
    private String perguntaSegur, respostaSegur;
    private String usuario;
    private String senha;

    public Usuario() {

    }

    public Usuario(String email, String perguntaSegur, String respostaSegur, String usuario, String senha) {
        this.email = email.toLowerCase();
        this.perguntaSegur = perguntaSegur;
        this.respostaSegur = respostaSegur;
        this.usuario = usuario;
        this.senha = senha;
    }    

    public Usuario(String nomeCompleto, String email,
            String dia, String mes, String ano, String perguntaSegur,
            String respostaSegur, String usuario, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email.toLowerCase();
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.perguntaSegur = perguntaSegur;
        this.respostaSegur = respostaSegur;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(Integer id, String nomeCompleto, String email,
            String dia, String mes, String ano, String perguntaSegur,
            String respostaSegur, String usuario, String senha) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email.toLowerCase();
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.perguntaSegur = perguntaSegur;
        this.respostaSegur = respostaSegur;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPerguntaSegur() {
        return perguntaSegur;
    }

    public void setPerguntaSegur(String perguntaSegur) {
        this.perguntaSegur = perguntaSegur;
    }

    public String getRespostaSegur() {
        return respostaSegur;
    }

    public void setRespostaSegur(String respostaSegur) {
        this.respostaSegur = respostaSegur;
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

    // ----------------- Active Record --------------------
    
    private static SQLUsuariosDAO dao = new SQLUsuariosDAO();

    public void save() {
        if (id != null && dao.find(id) != null) {
            dao.update(this);
        } else {
            dao.addUsuario(this);
        }
    }

    public void delete() {
        if (dao.find(id) != null) {
            dao.delete(this);
        }
    }

    public static List<Usuario> all() {
        return dao.all();
    }

    public static Usuario find(int id) {
        return dao.find(id);
    }
    
    public static Usuario findUsuario(String usuario) {
        return dao.findUsuario(usuario);
    }

    @Override
    public String toString() {
        return "Usuario{" + " id= " + id + ", nomeCompleto = "
                + nomeCompleto + ", \nemail = " + email
                + ", dia = " + dia + ", mes = " + mes + ", ano = " + ano
                + ", \nperguntaSegur = " + perguntaSegur
                + ", respostaSegur = " + respostaSegur + ", \nusuario = " + usuario
                + ", senha = " + senha + "}\n";
    }

}
