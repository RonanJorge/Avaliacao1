package br.edu.fateczl.Avaliacao1.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import br.edu.fateczl.Avaliacao1.model.Disciplina;
import br.edu.fateczl.Avaliacao1.model.Matricula;
import br.edu.fateczl.Avaliacao1.persistence.MatriculaDao;
import lombok.SneakyThrows;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaDao mDao;

    @RequestMapping(value = "/matricula", method = {RequestMethod.POST, RequestMethod.GET})
    @SneakyThrows
    public ModelAndView matricula(@RequestParam Map<String, String> params, ModelMap model) {
        String ra = params.get("ra");
        String acao = params.get("acao");

        if (ra != null && !ra.isEmpty()) {
            Aluno aluno = mDao.buscarAlunoPorRa(ra);
            if (aluno != null) {
                model.addAttribute("aluno", aluno);

                // Listar disciplinas
                List<Disciplina> disciplinas = mDao.listar(aluno.getCodigoCurso());
                model.addAttribute("disciplinas", disciplinas);

                // Ação de matricular
                if ("matricular".equals(acao)) {
                    String codigoDisc = params.get("codigoDisciplina"); // ou "codigoDisc"
                    if (codigoDisc != null && !codigoDisc.isEmpty()) {
                        Matricula m = new Matricula();
                        LocalDateTime agora = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
                        int codigoMatricula = Integer.parseInt(agora.format(formatter));
                        m.setCodigoMatricula(codigoMatricula);
                        m.setRa(aluno.getRa());
                        m.setCodigoCurso(aluno.getCodigoCurso());
                        m.setCodigoDisciplina1(Integer.parseInt(codigoDisc));

                        try {
                        	String saida = mDao.inserir(m);
                        	model.addAttribute("saida", saida);
                        } catch(SQLException e) {
                        	model.addAttribute("erro", e.getMessage());
                        }
                        
                        
                        // Atualiza a lista de disciplinas se quiser
                        disciplinas = mDao.listar(aluno.getCodigoCurso());
                        model.addAttribute("disciplinas", disciplinas);
                    }
                }

            } else {
                model.addAttribute("erro", "RA não encontrado!");
            }
        } else {
            model.addAttribute("erro", "Informe um RA válido.");
        }

        return new ModelAndView("matricula");
    }
    
    @RequestMapping("/disciplinasMatriculadas")
    public ModelAndView disciplinasMatriculadas(@RequestParam(required = false) String ra, ModelMap model) throws SQLException {
        if (ra != null && !ra.isEmpty()) {
            List<Disciplina> lista = mDao.listarDisciplinasMatriculadas(ra);
            model.addAttribute("disciplinas", lista);
        }
        return new ModelAndView("disciplinasMatriculadas");
    }
}
