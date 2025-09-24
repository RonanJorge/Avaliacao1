package br.edu.fateczl.Avaliacao1.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.Avaliacao1.model.Aluno;
import br.edu.fateczl.Avaliacao1.persistence.AlunoDao;

@Controller
public class AlunoController {

	@Autowired
	private AlunoDao aDao;
	
	@RequestMapping(name = "aluno", value = "/aluno", method = RequestMethod.GET)
	public ModelAndView alunoGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String acao = params.get("acao");
		String cpf = params.get("cpf");
		
		Aluno a = new Aluno();
		String erro = "";
		List<Aluno> alunos = new ArrayList<>();
		
		try {
			if (acao != null) {
				a.setCpf(Long.parseLong(cpf));
				
				if (acao.equalsIgnoreCase("excluir")) {
					aDao.excluir(a);
					alunos = aDao.listar();
					a = null;
				} else {
					a = aDao.buscar(a);
					alunos = null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("aluno", a);
			model.addAttribute("alunos", alunos);
			
		}
		return new ModelAndView("aluno");
	}
	
	@RequestMapping(name = "aluno", value = "/aluno", method = RequestMethod.POST)
	public ModelAndView alunoPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno a = new Aluno();
		String cmd = "";
		
		try {
			String cpf = params.get("cpf");
			String nome = params.get("nome");
			String nomeSocial = params.get("nomeSocial");
			String nascimento = params.get("nascimento");
			String email =  params.get("email");
			String emailCorporativo =  params.get("emailCorporativo");
			String conclusaoEM = params.get("conclusaoEM");
			String anoIngresso = params.get("anoIngresso");
			String semestreIngresso = params.get("semestreIngresso");
			cmd = params.get("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				a.setCpf(Long.parseLong(cpf));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				a.setNome(nome);
				a.setNomeSocial(nomeSocial);
				a.setNascimento(LocalDate.parse(nascimento));
				a.setEmail(email);
				a.setEmailCorporativo(emailCorporativo);
				a.setConclusaoEM(LocalDate.parse(conclusaoEM));
				a.setAnoIngresso(Integer.parseInt(anoIngresso));
				a.setSemestreIngresso(Integer.parseInt(semestreIngresso));
			}
		
			if (cmd.equalsIgnoreCase("Inserir")) {
				saida = aDao.inserir(a);
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				saida = aDao.atualizar(a);
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				saida = aDao.excluir(a);
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				a = aDao.buscar(a);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				alunos = aDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				a = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				alunos = null;
			}
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("aluno", a);
			model.addAttribute("alunos", alunos);
		}

		return new ModelAndView("aluno");
	}
}
