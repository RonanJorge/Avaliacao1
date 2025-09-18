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

import br.edu.fateczl.Avaliacao1.model.Curso;
import br.edu.fateczl.Avaliacao1.persistence.CursoDao;

@Controller
public class CursoController {

	@Autowired
	private CursoDao cDao;
	
	@RequestMapping(name = "curso", value = "/curso", method = RequestMethod.GET)
	public ModelAndView cursoGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String acao = params.get("acao");
		String codigoCurso = params.get("codigoCurso");
		
		Curso c = new Curso();
		String erro = "";
		List<Curso> cursos = new ArrayList<>();
		
		try {
			if (acao != null) {
				c.setCodigoCurso(Integer.parseInt(codigoCurso));
				
				if (acao.equalsIgnoreCase("excluir")) {
					cDao.excluir(c);
					cursos = cDao.listar();
					c = null;
				} else {
					c = cDao.buscar(c);
					cursos = null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("curso", c);
			model.addAttribute("cursos", cursos);
			
		}
		return new ModelAndView("curso");
	}
	
	@RequestMapping(name = "curso", value = "/curso", method = RequestMethod.POST)
	public ModelAndView cursoPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Curso> cursos = new ArrayList<Curso>();
		Curso c = new Curso();
		String cmd = "";
		
		try {
			String codigoCurso = params.get("codigoCurso");
			String nome = params.get("nome");
			String cargaHoraria = params.get("cargaHoraria");
			String sigla = params.get("sigla");
			String notaEnade =  params.get("notaEnade");
			cmd = params.get("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				c.setCodigoCurso(Integer.parseInt(codigoCurso));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				c.setNome(nome);
				c.setCargaHoraria(Integer.parseInt(cargaHoraria));
				c.setSigla(sigla);
				c.setNotaEnade(Integer.parseInt(notaEnade));
			}
		
			if (cmd.equalsIgnoreCase("Inserir")) {
				saida = cDao.inserir(c);
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				saida = cDao.atualizar(c);
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				saida = cDao.excluir(c);
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				c = cDao.buscar(c);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				cursos = cDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				c = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				cursos = null;
			}
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("curso", c);
			model.addAttribute("cursos", cursos);
		}

		return new ModelAndView("curso");
	}
}