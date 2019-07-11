package model;

import java.util.List;
import model.modelSQLite.SQLClientesDAO;

/**
 *
 * @author Eliandro
 */
public class Clientes {

    private Integer id;

    private String nomeCompleto, apelido;
    private String telefone;
    private String rua, bairro, numCasa, cidade, UF;
    private String dia, mes;
    private float valorTotal, entrada, valorRestante;
    private int parcelas;
    private String vendedor;

    public Clientes() {

    }

    public Clientes(Integer id, String nomeCompleto, int parcelas,
            float valorTotal, float valorRestante, String vendedor) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.parcelas = parcelas;
        this.valorTotal = valorTotal;
        this.valorRestante = valorRestante;
        this.vendedor = vendedor;
    }

    public Clientes(String nomeCompleto, String apelido,
            String telefone, String rua, String bairro, String numCasa,
            String cidade, String UF, String dia, String mes,
            float valorTotal, int parcelas, float entrada,
            String vendedor) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.telefone = telefone.trim();
        this.rua = rua;
        this.bairro = bairro;
        this.numCasa = numCasa;
        this.cidade = cidade;
        this.UF = UF.toUpperCase();
        this.dia = dia;
        this.mes = mes;
        this.valorTotal = valorTotal;
        this.parcelas = parcelas;
        this.entrada = entrada;
        this.vendedor = vendedor;
        this.valorRestante = valorTotal - entrada;
    }

    public Clientes(Integer id, String nomeCompleto,
            String apelido, String telefone, String rua,
            String bairro, String numCasa, String cidade,
            String UF, String dia, String mes, float valorTotal,
            int parcelas, float entrada, String vendedor, float valorRestante) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.telefone = telefone.trim();
        this.rua = rua;
        this.bairro = bairro;
        this.numCasa = numCasa;
        this.cidade = cidade;
        this.UF = UF.toUpperCase();
        this.dia = dia;
        this.mes = mes;
        this.valorTotal = valorTotal;
        this.parcelas = parcelas;
        this.entrada = entrada;
        this.vendedor = vendedor;
        this.valorRestante = valorRestante;
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
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

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public float getEntrada() {
        return entrada;
    }

    public void setEntrada(float entrada) {
        this.entrada = entrada;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public float getValorRestante() {
        return valorRestante;
    }

    public void setValorRestante(float valorRestante) {
        this.valorRestante = valorRestante;
    }

    // ----------------- Active Record --------------------
    private static SQLClientesDAO dao = new SQLClientesDAO();

    public void save() {

        if (id != null && dao.find(id) != null) {
            dao.update(this);
        } else {
            dao.addClientes(this);
        }
    }

    public void delete() {
        if (dao.find(id) != null) {
            dao.delete(this);
        }
    }

    public static List<Clientes> all() {
        return dao.all();
    }

    public static Clientes find(int id) {
        return dao.find(id);
    }

    public static List<Clientes> table() {
        return dao.table();
    }

    @Override
    public String toString() {
        return "Clientes{" + " id = " + id + ", nomeCompleto = " + nomeCompleto
                + ", apelido = " + apelido + ", \ntelefone = " + telefone
                + ", rua = " + rua + ", bairro = " + bairro + ", \nnumCasa = " + numCasa
                + ", cidade = " + cidade + ", UF = " + UF + ", dia = " + dia
                + ", mes = " + mes + ", \nvalorTotal = " + valorTotal
                + ", entrada = " + entrada + ", parcelas = " + parcelas
                + ", \nvendedor = " + vendedor + "\nValor Restante: " + valorRestante + "}\n";
    }

    public String toStringCliente() {
        return "Cliente: " + nomeCompleto;
    }
}
