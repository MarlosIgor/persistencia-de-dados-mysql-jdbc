package br.com.agendat.repository;

import br.com.agendat.com.ConnectionFactory;
import br.com.agendat.dominio.Contato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public static void save(Contato contato) {
        String sql = "INSERT INTO agenda.contatos (nome, email) VALUES ('%s', '%s')".formatted(contato.getNome(), contato.getEmail());
        try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.printf("Produtor inserido na linha do banco de dados afetadas %s %s %n", contato.getNome(), rowsAffected);
        } catch (SQLException e) {
            System.out.printf("Erro ao tentar inserir contato %s %s %n", contato.getNome(), e);
            e.printStackTrace();
        }
    }

    public static void deleteById(Integer id) {
        String sql = "DELETE FROM agenda.contatos WHERE  id = '%d'".formatted(id);
        try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.printf("Deletando os contatos do banco de dados afetadas %d %s %n", id, rowsAffected);
        } catch (SQLException e) {
            System.out.printf("Erro ao tentar deletar contato  %d %s %n", id, e);
        }
    }

    public static List<Contato> findByName(String nome) {
        System.out.println("Encontrar contato pelo nome");
        List<Contato> producers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = preparedStatementFindByName(conn, nome); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Contato producer = Contato.builder().id(rs.getInt("id")).nome(rs.getString("nome")).email(rs.getString("email")).build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            System.out.printf("Erro ao tentar encontrar todos os contatos %s %n", e);
        }
        return producers;
    }

    private static PreparedStatement preparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM agenda.contatos WHERE nome LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static void update(Contato contato) {
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = preparedStatementUpdate(conn, contato)) {
            int rowsAffected = ps.executeUpdate();
            System.out.printf("Atualizando contato %d na linha do banco de dados afetadas %s", contato.getId(), rowsAffected);
        } catch (SQLException e) {
            System.out.printf("Erro ao tentar atualizar contato %d %s", contato.getId(), e);
        }
    }

    private static PreparedStatement preparedStatementUpdate(Connection conn, Contato contato) throws SQLException {
        String sql = "UPDATE agenda.contatos SET nome = ?, email = ? WHERE (id = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, contato.getNome());
        ps.setString(2, contato.getEmail());
        ps.setInt(3, contato.getId());
        return ps;
    }
}
