package br.edu.fateczl.Avaliacao1.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

	private long cpf;
	private String nome;
	private String nomeSocial;
	private LocalDate nascimento;
	private String dtNasc;
	private String email;
	private String emailCorporativo;
	private LocalDate conclusaoEM;
	private String conclEM;
	private int anoIngresso;
	private int semestreIngresso;
	private int codigoCurso;
	private int anoLimite;
	private int semestreLimite;
	private String ra;

}
