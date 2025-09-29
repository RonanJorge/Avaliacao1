USE [agis]
GO

/****** Object:  StoredProcedure [dbo].[sp_matricula]    Script Date: 29/09/2025 01:58:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_matricula] (@codigoMatricula INT, @ra VARCHAR(9),
								@codigoCurso INT, @codigoDisc INT,
								@saida VARCHAR(100) OUTPUT)
AS
	DECLARE @matriculaValida1 BIT,
			@matriculaValida2 BIT,
			@codigoDisciplina1 INT,
			@codigoDisciplina2 INT
	IF(@ra IS NULL)
	BEGIN
		RAISERROR('Operação requer Ra válido', 16, 1)
	END
	ELSE
	BEGIN
	    SET @codigoDisciplina1 = (SELECT codigoDisciplina1 FROM matricula WHERE ra = @ra)
		IF(@codigoDisciplina1 IS NULL)
		BEGIN
			INSERT INTO matricula VALUES
			(@codigoMatricula, @ra, @codigoCurso, @codigoDisc, NULL, NULL)
			SET @saida = 'Primeira Disciplina matriculada com sucesso! '
		END
		ELSE
		BEGIN
			SET @codigoDisciplina2 = (SELECT codigoDisciplina2 FROM matricula WHERE ra = @ra)
			IF(@codigoDisciplina2 is NULL)
			BEGIN
				EXEC sp_validaMatricula @codigoDisciplina1, @codigoDisc, @matriculaValida1 OUTPUT
				IF(@matriculaValida1 = 1)
				BEGIN
					UPDATE matricula
					SET codigoDisciplina2 = @codigoDisc
					WHERE ra = @ra
					SET @saida = 'Segunda Disciplina matriculada com sucesso! '
				END
			END
			ELSE
			BEGIN
				EXEC sp_validaMatricula @codigoDisciplina1, @codigoDisc, @matriculaValida1 OUTPUT
				EXEC sp_validaMatricula @codigoDisciplina2, @codigoDisc, @matriculaValida2 OUTPUT
				IF(@matriculaValida1 = 1 AND @matriculaValida2 = 1)
				BEGIN
					UPDATE matricula
					SET codigoDisciplina3 = @codigoDisc
					WHERE ra = @ra
					SET @saida = 'Terceira Disciplina matriculada com sucesso! Máximo de disciplinas matriculadas atingido.'
				END
				ELSE
				BEGIN
					SET @saida = 'Disciplina não matriculada por choque de horário.'
				END
			END
		END
	END
GO


