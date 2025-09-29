package br.edu.fateczl.Avaliacao1.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.Avaliacao1.model.Aluno;
import br.edu.fateczl.Avaliacao1.model.Disciplina;
import br.edu.fateczl.Avaliacao1.model.Matricula;
import lombok.SneakyThrows;

@Repository
public class MatriculaDao {

    @Autowired
    private GenericDao gDao; // Conexão JDBC centralizada

    // Buscar aluno pelo RA (traz RA, nome e código do curso)
    @SneakyThrows
    public Aluno buscarAlunoPorRa(String ra) {
        String sql = "SELECT ra, nome, codigoCurso " +
                     "FROM aluno " +
                     "WHERE ra = ?";

        try (Connection c = gDao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, ra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setRa(rs.getString("ra"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCodigoCurso(rs.getInt("codigoCurso"));
                    return aluno;
                }
            }
        }
        return null;
    }

    // Listar disciplinas de um curso
    
    public List<Disciplina> listar(int codigoCurso) throws SQLException, ClassNotFoundException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT codigoDisc, codigoCurso, nome, inicio, horasSemanais, conteudos FROM disciplina WHERE codigoCurso = ?";
        Connection c = gDao.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codigoCurso);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	Disciplina d = new Disciplina();
            d.setCodigoDisc(rs.getInt("codigoDisc"));
            d.setCodigoCurso(rs.getInt("codigoCurso"));
            d.setNome(rs.getString("nome"));
            d.setInicio(rs.getTime("inicio").toLocalTime());
            d.setHorasSemanais(rs.getInt("horasSemanais"));
        	d.setConteudos(rs.getString("conteudos"));
            disciplinas.add(d);
        }
        
        return disciplinas;
    }

    // Método para chamar a stored procedure de matrícula (opcional)
    public String inserir(Matricula matricula) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_matricula(?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, matricula.getCodigoMatricula());
		cs.setString(2, matricula.getRa());
		cs.setInt(3, matricula.getCodigoCurso());
		cs.setInt(4, matricula.getCodigoDisciplina1());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		cs.close();
		
		return saida;
	}
    
    public List<Disciplina> listarDisciplinasMatriculadas(String ra) throws SQLException {
        List<Disciplina> lista = new ArrayList<>();
        String sql = "SELECT d.codigoDisc, d.nome, d.inicio, d.horasSemanais " +
                     "FROM matricula m " +
                     "JOIN disciplina d ON m.codigoDisciplina1 = d.codigoDisc " +
                     "WHERE m.ra = ?";

        Connection c = null;
		try {
			c = gDao.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, ra);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Disciplina d = new Disciplina();
            d.setCodigoDisc(rs.getInt("codigoDisc"));
            d.setNome(rs.getString("nome"));
            d.setInicio(rs.getTime("inicio").toLocalTime());
            d.setHorasSemanais(rs.getInt("horasSemanais"));
            lista.add(d);
        }

        return lista;
    }
}