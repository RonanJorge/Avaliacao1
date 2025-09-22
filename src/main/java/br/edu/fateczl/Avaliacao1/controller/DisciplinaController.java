package br.edu.fateczl.Avaliacao1.controller;

import java.sql.SQLException;
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

import br.edu.fateczl.Avaliacao1.model.Disciplina;
import br.edu.fateczl.Avaliacao1.persistence.DisciplinaDao;

@Controller
public class DisciplinaController {

	@Autowired
	private DisciplinaDao dDao;
	
	@RequestMapping(name = "disciplina", value = "/disciplina", method = RequestMethod.GET)
	public ModelAndView disciplinaGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String acao = params.get("acao");
		String codigoDisc = params.get("codigoDisc");
		
		Disciplina d = new Disciplina();
		String erro = "";
		List<Disciplina> disciplinas = new ArrayList<>();
		
		try {
			if (acao != null) {
				d.setCodigoDisc(Integer.parseInt(codigoDisc));
				
				if (acao.equalsIgnoreCase("excluir")) {
					dDao.excluir(d);
					disciplinas = dDao.listar();
					d = null;
				} else {
					d = dDao.buscar(d);
					disciplinas = null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("disciplina", d);
			model.addAttribute("disciplinas", disciplinas);
			
		}
		return new ModelAndView("disciplina");
	}
	
	@RequestMapping(name = "disciplina", value = "/disciplina", method = RequestMethod.POST)
	public ModelAndView disciplinaPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Disciplina d = new Disciplina();
		String cmd = "";
		
		try {
			String codigoDisc = params.get("codigoDisc");
			String codigoCurso = params.get("codigoCurso");
			String nome = params.get("nome");
			String horasSemanais = params.get("horasSemanais");
			String conteudos = params.get("conteudos");
			cmd = params.get("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				d.setCodigoDisc(Integer.parseInt(codigoDisc));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				d.setCodigoCurso(Integer.parseInt(codigoCurso));
				d.setNome(nome);
				d.setHorasSemanais(Integer.parseInt(horasSemanais));
				d.setConteudos(conteudos);
			}
		
			if (cmd.equalsIgnoreCase("Inserir")) {
				saida = dDao.inserir(d);
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				saida = dDao.atualizar(d);
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				saida = dDao.excluir(d);
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				d = dDao.buscar(d);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				disciplinas = dDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				d = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				disciplinas = null;
			}
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("disciplina", d);
			model.addAttribute("disciplinas", disciplinas);
		}

		return new ModelAndView("disciplina");
	}
}