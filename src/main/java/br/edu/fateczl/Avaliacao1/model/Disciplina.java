package br.edu.fateczl.Avaliacao1.model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

	private int codigoDisc;
	private int codigoCurso;
	private String nome;
	private LocalTime inicio;
	private int horasSemanais;
	private String conteudos;

}