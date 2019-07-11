package model.modelSQLite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author Eliandro
 */
public class SQLUsuariosDAO extends SQLBase {

    private static String sql = "CREATE TABLE IF NOT EXISTS Usuarios (\n"
            + "  id INTEGER PRIMARY KEY,\n"
            + "  nomeCompleto TEXT NOT NULL,\n"
            + "  email TEXT NOT NULL,\n"
            + "  dia VARCHAR(3) NOT NULL,\n"
            + "  mes VARCHAR(12) NOT NULL,\n"
            + "  ano VARCHAR(5) NOT NULL,\n"
            + "  pergunta TEXT NOT NULL,\n"
            + "  resposta TEXT NOT NULL,\n"
            + "  usuario VARCHAR(30) UNIQUE NOT NULL,\n"
            + "  senha VARCHAR(20) NOT NULL\n"
            + ");";

    public SQLUsuariosDAO() {

        connect = open();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }
    }

    public void addUsuario(Usuario usuario) {

        connect = open();
        try {
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Usuarios(nomeCompleto, "
                    + "email, dia, mes, ano, pergunta, resposta, usuario, senha) VALUES (?, ?, ?,"
                    + " ?, ?, ?, ?, ?, ?);");

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getDia());
            stmt.setString(4, usuario.getMes());
            stmt.setString(5, usuario.getAno());
            stmt.setString(6, usuario.getPerguntaSegur());
            stmt.setString(7, usuario.getRespostaSegur());
            stmt.setString(8, usuario.getUsuario());
            stmt.setString(9, usuario.getSenha());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }
    }

    public void update(Usuario usuario) {

        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("UPDATE Usuarios SET "
                    + "nomeCompleto = ?, "
                    + "email = ?, "
                    + "dia = ?, "
                    + "mes = ?, "
                    + "ano = ?, "
                    + "perguntaSegur = ?, "
                    + "respostaSegur = ?, "
                    + "usuario = ?, "
                    + "senha = ? "
                    + "WHERE id = ?;");

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getDia());
            stmt.setString(4, usuario.getMes());
            stmt.setString(5, usuario.getAno());
            stmt.setString(6, usuario.getPerguntaSegur());
            stmt.setString(7, usuario.getRespostaSegur());
            stmt.setString(8, usuario.getUsuario());
            stmt.setString(9, usuario.getSenha());
            stmt.setInt(10, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }
    }

    public void delete(Usuario usuario) {

        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Usuarios WHERE id = ?;");

            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }
    }

    public Usuario find(int id) {

        Usuario result = null;
        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Usuarios WHERE id = ?;");

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario(rs.getInt(1), // id 
                        rs.getString(2), // nomeCompleto
                        rs.getString(3), // email
                        rs.getString(4), // dia
                        rs.getString(5), // mes
                        rs.getString(6), // ano
                        rs.getString(7), // pergunta
                        rs.getString(8), // resposta
                        rs.getString(9), // usuario
                        rs.getString(10)); // senha

                result = usuario;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }

        return result;
    }
    
    public Usuario findUsuario(String usu) {

        Usuario result = null;
        connect = open();

        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Usuarios WHERE usuario = ?;");

            stmt.setString(1, usu);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario(rs.getString("email"), // email 
                        rs.getString("pergunta"), //Pergunta
                        rs.getString("resposta"), // resposta
                        rs.getString("usuario"), // usuario
                        rs.getString("senha")); // senha

                result = usuario;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }

        return result;
    }

    public List<Usuario> all() {

        ArrayList<Usuario> result = new ArrayList<>();

        connect = open();
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Usuarios ORDER BY id ASC;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario(rs.getInt(1), // id 
                        rs.getString(2), // nomeCompleto
                        rs.getString(3), // email
                        rs.getString(4), // dia
                        rs.getString(5), // mes
                        rs.getString(6), // ano
                        rs.getString(7), // pergunta
                        rs.getString(8), // resposta
                        rs.getString(9), // usuario
                        rs.getString(10)); // senha

                result.add(usuario);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no banco de dados", ex);
        } finally {
            close();
        }

        return result;
    }
}
