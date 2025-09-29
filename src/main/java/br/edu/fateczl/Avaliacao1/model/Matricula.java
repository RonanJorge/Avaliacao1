package br.edu.fateczl.Avaliacao1.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
	
	private int codigoMatricula;
	private String ra;
	private int codigoCurso;
	private int codigoDisciplina1;
	private int codigoDisciplina2;
	private int codigoDisciplina3;
}
