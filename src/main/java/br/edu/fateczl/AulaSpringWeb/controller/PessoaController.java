package br.edu.fateczl.AulaSpringWeb.controller;

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

import br.edu.fateczl.AulaSpringWeb.model.Pessoa;
import br.edu.fateczl.AulaSpringWeb.persistence.PessoaDao;

@Controller
public class PessoaController {

	@Autowired
	private PessoaDao pDao;
	
	@RequestMapping(name = "pessoa", value = "/pessoa", method = RequestMethod.GET)
	public ModelAndView pessoaGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String acao = params.get("acao");
		String id = params.get("id");
		
		Pessoa p = new Pessoa();
		String erro = "";
		List<Pessoa> pessoas = new ArrayList<>();
		
		try {
			if (acao != null) {
				p.setId(Integer.parseInt(id));
				
				if (acao.equalsIgnoreCase("excluir")) {
					pDao.excluir(p);
					pessoas = pDao.listar();
					p = null;
				} else {
					p = pDao.buscar(p);
					pessoas = null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("pessoa", p);
			model.addAttribute("pessoas", pessoas);
			
		}
		return new ModelAndView("pessoa");
	}
	
	@RequestMapping(name = "pessoa", value = "/pessoa", method = RequestMethod.POST)
	public ModelAndView pessoaPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa p = new Pessoa();
		String cmd = "";
		
		try {
			String id = params.get("id");
			String nome = params.get("nome");
			String nascimento = params.get("nascimento");
			String email =  params.get("email");
			cmd = params.get("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				p.setId(Integer.parseInt(id));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				p.setNome(nome);
				p.setNascimento(LocalDate.parse(nascimento));
				p.setEmail(email);
			}
		
			if (cmd.equalsIgnoreCase("Inserir")) {
				saida = pDao.inserir(p);
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				saida = pDao.atualizar(p);
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				saida = pDao.excluir(p);
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				p = pDao.buscar(p);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				pessoas = pDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				p = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				pessoas = null;
			}
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("pessoa", p);
			model.addAttribute("pessoas", pessoas);
		}

		return new ModelAndView("pessoa");
	}
}
