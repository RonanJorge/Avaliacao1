package br.edu.fateczl.Avaliacao1.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.Avaliacao1.model.Aluno;

@Repository
public class AlunoDao implements ICrud<Aluno> {

	@Autowired
	private GenericDao gDao;

	@Override
	public Aluno buscar(Aluno aluno) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT cpf, nome, nomeSocial, nascimento, email, emailCorporativo, conclusaoEM, "
				+ "anoIngresso, semestreIngresso, anoLimite, semestreLimite "
				+ "FROM aluno WHERE cpf = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1,aluno.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			aluno.setCpf(rs.getLong("cpf"));
			aluno.setNome(rs.getString("nome"));
			aluno.setNomeSocial(rs.getString("nomeSocial"));
			aluno.setNascimento(LocalDate.parse(rs.getString("nascimento")));
			aluno.setEmail(rs.getString("email"));
			aluno.setEmailCorporativo(rs.getString("emailCorporativo"));
			aluno.setConclusaoEM(LocalDate.parse(rs.getString("conclusaoEM")));
			aluno.setAnoIngresso(Integer.parseInt(rs.getString("anoIngresso")));
			aluno.setSemestreIngresso(Integer.parseInt(rs.getString("semestreIngresso")));
			aluno.setAnoLimite(Integer.parseInt(rs.getString("anoLimite")));
			aluno.setSemestreLimite(Integer.parseInt(rs.getString("semestreLimite")));
		}
		rs.close();
		ps.close();
		return aluno;
	}

	@Override
	public List<Aluno> listar() throws SQLException, ClassNotFoundException {
		List<Aluno> alunos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT cpf, nome, nomeSocial, nascimento,"
				+ " CONVERT(CHAR(10), nascimento, 103) AS dtNasc, email, emailCorporativo,"
				+ " conclusaoEM, CONVERT(CHAR(10), conclusaoEM, 103) AS conclEM , anoIngresso, semestreIngresso, "
				+ " anoLimite, semestreLimite "
				+ " FROM aluno";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setCpf(rs.getLong("cpf"));
			aluno.setNome(rs.getString("nome"));
			aluno.setNomeSocial(rs.getString("nomeSocial"));
			aluno.setNascimento(LocalDate.parse(rs.getString("nascimento")));
			aluno.setDtNasc(rs.getString("dtNasc"));
			aluno.setEmail(rs.getString("email"));
			aluno.setEmailCorporativo(rs.getString("emailCorporativo"));
			aluno.setConclusaoEM(LocalDate.parse(rs.getString("conclusaoEM")));
			aluno.setConclEM(rs.getString("conclEM"));
			aluno.setAnoIngresso(Integer.parseInt(rs.getString("anoIngresso")));
			aluno.setSemestreIngresso(Integer.parseInt(rs.getString("semestreIngresso")));
			aluno.setAnoLimite(Integer.parseInt(rs.getString("anoLimite")));
			aluno.setSemestreLimite(Integer.parseInt(rs.getString("semestreLimite")));
			
			alunos.add(aluno);
		}
		rs.close();
		ps.close();
		return alunos;
	}

//	Chamada de SP deve estar entre chaves
	@Override
	public String inserir(Aluno aluno) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "I");
		cs.setLong(2, aluno.getCpf());
		cs.setString(3, aluno.getNome());
		cs.setString(4, aluno.getNomeSocial());
		cs.setString(5, aluno.getNascimento().toString());
		cs.setString(6, aluno.getEmail());
		cs.setString(7, aluno.getEmailCorporativo());
		cs.setString(8, aluno.getConclusaoEM().toString());
		cs.setInt(9, aluno.getAnoIngresso());
		cs.setInt(10, aluno.getSemestreIngresso());
		cs.setInt(11, 0);
		cs.setInt(12, 0);
		cs.registerOutParameter(13, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(13);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Aluno aluno) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "U");
		cs.setLong(2, aluno.getCpf());
		cs.setString(3, aluno.getNome());
		cs.setString(4, aluno.getNomeSocial());
		cs.setString(5, aluno.getNascimento().toString());
		cs.setString(6, aluno.getEmail());
		cs.setString(7, aluno.getEmailCorporativo());
		cs.setString(8, aluno.getConclusaoEM().toString());
		cs.setInt(9, aluno.getAnoIngresso());
		cs.setInt(10, aluno.getSemestreIngresso());
		cs.setInt(11, 0);
		cs.setInt(12, 0);
		cs.registerOutParameter(13, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(13);
		cs.close();
		
		return saida;

	}

	@Override
	public String excluir(Aluno aluno) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setLong(2, aluno.getCpf());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.setNull(6, Types.VARCHAR);
		cs.setNull(7, Types.VARCHAR);
		cs.setNull(8, Types.VARCHAR);
		cs.setNull(9, Types.VARCHAR);
		cs.setNull(10, Types.VARCHAR);
		cs.setNull(11, Types.VARCHAR);
		cs.setNull(12, Types.VARCHAR);
		cs.registerOutParameter(13, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(13);
		cs.close();
		
		return saida;
	}
}
