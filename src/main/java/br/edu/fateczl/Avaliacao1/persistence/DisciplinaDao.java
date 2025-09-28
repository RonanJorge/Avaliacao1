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

import br.edu.fateczl.Avaliacao1.model.Disciplina;

@Repository
public class DisciplinaDao implements ICrud<Disciplina> {

	@Autowired
	private GenericDao gDao;

	@Override
	public Disciplina buscar(Disciplina disciplina) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigoDisc, codigoCurso, nome, inicio, horasSemanais, conteudos FROM disciplina WHERE codigoDisc = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1,disciplina.getCodigoDisc());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			disciplina.setCodigoDisc(rs.getInt("codigoDisc"));
			disciplina.setCodigoCurso(rs.getInt("codigoCurso"));
			disciplina.setNome(rs.getString("nome"));
			disciplina.setInicio(rs.getTime("inicio").toLocalTime());
			disciplina.setHorasSemanais(rs.getInt("horasSemanais"));
			disciplina.setConteudos(rs.getString("conteudos"));
		}
		rs.close();
		ps.close();
		return disciplina;
	}

	@Override
	public List<Disciplina> listar() throws SQLException, ClassNotFoundException {
		List<Disciplina> disciplinas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigoDisc, codigoCurso, nome, inicio, horasSemanais, conteudos FROM disciplina";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Disciplina disciplina = new Disciplina();
			disciplina.setCodigoDisc(rs.getInt("codigoDisc"));
			disciplina.setCodigoCurso(rs.getInt("codigoCurso"));
			disciplina.setNome(rs.getString("nome"));
			disciplina.setInicio(rs.getTime("inicio").toLocalTime());
			disciplina.setHorasSemanais(rs.getInt("horasSemanais"));
			disciplina.setConteudos(rs.getString("conteudos"));
			
			disciplinas.add(disciplina);
		}
		rs.close();
		ps.close();
		return disciplinas;
	}

//	Chamada de SP deve estar entre chaves
	@Override
	public String inserir(Disciplina disciplina) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_disciplina(?,?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "I");
		cs.setInt(2, disciplina.getCodigoDisc());
		cs.setInt(3, disciplina.getCodigoCurso());
		cs.setString(4, disciplina.getNome());
		cs.setTime(5, java.sql.Time.valueOf(disciplina.getInicio()));
		cs.setInt(6, disciplina.getHorasSemanais());
		cs.setString(7, disciplina.getConteudos());
		cs.registerOutParameter(8, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(8);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Disciplina disciplina) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_disciplina(?,?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "U");
		cs.setInt(2, disciplina.getCodigoDisc());
		cs.setInt(3, disciplina.getCodigoCurso());
		cs.setString(4, disciplina.getNome());
		cs.setTime(5,java.sql.Time.valueOf(disciplina.getInicio()));
		cs.setInt(6, disciplina.getHorasSemanais());
		cs.setString(7, disciplina.getConteudos());
		cs.registerOutParameter(8, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(8);
		cs.close();
		
		return saida;

	}

	@Override
	public String excluir(Disciplina disciplina) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_disciplina(?,?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setInt(2, disciplina.getCodigoDisc());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.setNull(6, Types.VARCHAR);
		cs.setNull(7, Types.VARCHAR);
		cs.registerOutParameter(8, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(8);
		cs.close();
		
		return saida;
	}
}