package model.modelSQLite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Clientes;
import static model.modelSQLite.SQLBase.connect;
import static model.modelSQLite.SQLBase.open;

/**
 *
 * @author Eliandro
 */
public class SQLClientesDAO extends SQLBase {

    private static String sql = "CREATE TABLE IF NOT EXISTS Clientes (\n"
            + "id INTEGER PRIMARY KEY,\n"
            + "nomeCompleto TEXT NOT NULL,\n"
            + "apelido TEXT NOT NULL,\n"
            + "telefone VARCHAR(12) NOT NULL,\n"
            + "rua TEXT NOT NULL,\n"
            + "bairro TEXT NOT NULL,\n"
            + "numCasa SMALLINT NOT NULL, \n"
            + "cidade TEXT NOT NULL, \n"
            + "uf VARCHAR(3) NOT NULL, \n"
            + "dia VARCHAR(3),\n"
            + "mes VARCHAR(12),\n"
            + "valorTotal FLOAT(7, 2) NOT NULL,\n"
            + "parcelas SMALLINT NOT NULL,\n"
            + "entrada FLOAT(7, 2) NOT NULL,\n"
            + "vendedor TEXT NOT NULL,\n"
            + "valorRestante FLOAT(7, 2) NOT NULL );";

    public SQLClientesDAO() {

        connect = open();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"create\"", ex);
        } finally {
            close();
        }
    }

    public void addClientes(Clientes clientes) {

        connect = open();
        try {
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Clientes(nomeCompleto, apelido, "
                    + "telefone, rua, bairro, numCasa, cidade, uf, dia, mes, valorTotal, "
                    + "parcelas, entrada, vendedor, valorRestante) VALUES (?, ?, ?, ?,"
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            stmt.setString(1, clientes.getNomeCompleto());
            stmt.setString(2, clientes.getApelido());
            stmt.setString(3, clientes.getTelefone());
            stmt.setString(4, clientes.getRua());
            stmt.setString(5, clientes.getBairro());
            stmt.setString(6, clientes.getNumCasa());
            stmt.setString(7, clientes.getCidade());
            stmt.setString(8, clientes.getUF());
            stmt.setString(9, clientes.getDia());
            stmt.setString(10, clientes.getMes());
            stmt.setFloat(11, clientes.getValorTotal());
            stmt.setInt(12, clientes.getParcelas());
            stmt.setFloat(13, clientes.getEntrada());
            stmt.setString(14, clientes.getVendedor());
            stmt.setFloat(15, clientes.getValorRestante());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"add\"", ex);
        } finally {
            close();
        }
    }

    public void update(Clientes clientes) {

        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("UPDATE Clientes SET "
                    + "nomeCompleto = ?, "
                    + "apelido = ?, "
                    + "telefone = ?, "
                    + "rua = ?, "
                    + "bairro = ?, "
                    + "numCasa = ?, "
                    + "cidade = ?, "
                    + "uf = ?, "
                    + "dia = ?, "
                    + "mes = ?, "
                    + "valorTotal = ?, "
                    + "parcelas = ?, "
                    + "entrada = ?, "
                    + "vendedor = ?, "
                    + "valorRestante = ?"
                    + " WHERE id = ?;");

            stmt.setString(1, clientes.getNomeCompleto());
            stmt.setString(2, clientes.getApelido());
            stmt.setString(3, clientes.getTelefone());
            stmt.setString(4, clientes.getRua());
            stmt.setString(5, clientes.getBairro());
            stmt.setString(6, clientes.getNumCasa());
            stmt.setString(7, clientes.getCidade());
            stmt.setString(8, clientes.getUF());
            stmt.setString(9, clientes.getDia());
            stmt.setString(10, clientes.getMes());
            stmt.setFloat(11, clientes.getValorTotal());
            stmt.setInt(12, clientes.getParcelas());
            stmt.setFloat(13, clientes.getEntrada());
            stmt.setString(14, clientes.getVendedor());
            stmt.setFloat(15, clientes.getValorRestante());
            stmt.setInt(16, clientes.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"update\"", ex);
        } finally {
            close();
        }
    }

    public void delete(Clientes clientes) {

        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Clientes WHERE id = ?;");

            stmt.setInt(1, clientes.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"delete\"", ex);
        } finally {
            close();
        }
    }

    public Clientes find(int id) {

        Clientes result = null;
        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Clientes WHERE id = ?;");

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Clientes clientes = new Clientes(rs.getInt(1), //id 
                        rs.getString(2), //nomeCompleto
                        rs.getString(3), //apelido
                        rs.getString(4), //telefone
                        rs.getString(5), //rua
                        rs.getString(6), //bairro
                        rs.getString(7), //numCasa
                        rs.getString(8), //cidade
                        rs.getString(9), //uf
                        rs.getString(10), //dia
                        rs.getString(11), //mes
                        rs.getFloat(12), //valorTotal
                        rs.getInt(13), //parcelas
                        rs.getFloat(14), //entrada
                        rs.getString(15), //vendendor
                        rs.getFloat(16)); //valorRestante

                result = clientes;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"find\"", ex);
        } finally {
            close();
        }

        return result;
    }

    public List<Clientes> table() {

        ArrayList<Clientes> result = new ArrayList<>();

        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Clientes ORDER BY id ASC;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes clientes = new Clientes(rs.getInt("id"), //id 
                        rs.getString("nomeCompleto"), //nomeCompleto
                        rs.getInt("parcelas"), //parcelas
                        rs.getFloat("valorTotal"), //valorTotal
                        rs.getFloat("valorRestante"), //valorRestante
                        rs.getString("vendedor")); //vendedor

                result.add(clientes);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"table\"", ex);
        } finally {
            close();
        }

        return result;
    }

    public List<Clientes> all() {

        ArrayList<Clientes> result = new ArrayList<>();

        connect = open();
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Clientes ORDER BY id ASC;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes clientes = new Clientes(rs.getInt(1), //id 
                        rs.getString(2), //nomeCompleto
                        rs.getString(3), //apelido
                        rs.getString(4), //telefone
                        rs.getString(5), //rua
                        rs.getString(6), //bairro
                        rs.getString(7), //numCasa
                        rs.getString(8), //cidade
                        rs.getString(9), //uf
                        rs.getString(10), //dia
                        rs.getString(11), //mes
                        rs.getFloat(12), //valorTotal
                        rs.getInt(13), //parcelas
                        rs.getFloat(14), //entrada
                        rs.getString(15), //vendedor
                        rs.getFloat(16)); //valorRestante

                result.add(clientes);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados \"all\"", ex);
        } finally {
            close();
        }

        return result;
    }
}
