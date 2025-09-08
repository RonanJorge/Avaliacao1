package br.edu.fateczl.AulaSpringWeb.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

	private int id;
	private String nome;
	private LocalDate nascimento;
	private String dtNasc;
	private String email;

}
