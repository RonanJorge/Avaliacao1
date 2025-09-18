package br.edu.fateczl.Avaliacao1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	private int codigoCurso;
	private String nome;
	private int cargaHoraria;
	private String sigla;
	private int notaEnade;	

}