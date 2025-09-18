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

import br.edu.fateczl.Avaliacao1.model.Curso;

@Repository
public class CursoDao implements ICrud<Curso> {

	@Autowired
	private GenericDao gDao;

	@Override
	public Curso buscar(Curso curso) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigoCurso, nome, cargaHoraria, sigla, notaEnade FROM curso WHERE codigoCurso = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1,curso.getCodigoCurso());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			curso.setCodigoCurso(rs.getInt("codigoCurso"));
			curso.setNome(rs.getString("nome"));
			curso.setCargaHoraria(rs.getInt("cargaHoraria"));
			curso.setSigla(rs.getString("sigla"));
			curso.setNotaEnade(rs.getInt("notaEnade"));
		}
		rs.close();
		ps.close();
		return curso;
	}

	@Override
	public List<Curso> listar() throws SQLException, ClassNotFoundException {
		List<Curso> cursos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigoCurso, nome, cargaHoraria, sigla, notaEnade FROM curso";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Curso curso = new Curso();
			curso.setCodigoCurso(rs.getInt("codigoCurso"));
			curso.setNome(rs.getString("nome"));
			curso.setCargaHoraria(rs.getInt("cargaHoraria"));
			curso.setSigla(rs.getString("sigla"));
			curso.setNotaEnade(rs.getInt("notaEnade"));
			
			cursos.add(curso);
		}
		rs.close();
		ps.close();
		return cursos;
	}

//	Chamada de SP deve estar entre chaves
	@Override
	public String inserir(Curso curso) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_curso(?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "I");
		cs.setInt(2, curso.getCodigoCurso());
		cs.setString(3, curso.getNome());
		cs.setInt(4, curso.getCargaHoraria());
		cs.setString(5, curso.getSigla());
		cs.setInt(6, curso.getNotaEnade());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Curso curso) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_curso(?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "U");
		cs.setInt(2, curso.getCodigoCurso());
		cs.setString(3, curso.getNome());
		cs.setInt(4, curso.getCargaHoraria());
		cs.setString(5, curso.getSigla());
		cs.setInt(6, curso.getNotaEnade());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		cs.close();
		
		return saida;

	}

	@Override
	public String excluir(Curso curso) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_curso(?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setInt(2, curso.getCodigoCurso());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.setNull(6, Types.VARCHAR);
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		cs.close();
		
		return saida;
	}
}