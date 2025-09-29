USE [agis]
GO

/****** Object:  StoredProcedure [dbo].[sp_validaMatricula]    Script Date: 29/09/2025 01:58:50 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_validaMatricula](@codigoDisc1 INT, @codigoDisc2 INT,
										@validoDisciplina BIT OUTPUT)
AS
	DECLARE @inicioD1 TIME,
			@inicioD2 TIME,
			@horasD1 INT,
			@horasD2 INT 

	SET @inicioD1 = (SELECT inicio FROM disciplina WHERE codigoDisc = @codigoDisc1)
	SET @inicioD2 = (SELECT inicio FROM disciplina WHERE codigoDisc = @codigoDisc2)
	SET @horasD1 = (SELECT horasSemanais FROM disciplina WHERE codigoDisc = @codigoDisc1)
	SET @horasD2 = (SELECT horasSemanais FROM disciplina WHERE codigoDisc = @codigoDisc2)
	IF(@codigoDisc1 = @codigoDisc2)
	BEGIN
		RAISERROR('As disciplinas têm o mesmo código', 16, 1)
	END
	ELSE
	BEGIN
		IF(@inicioD1 = @inicioD2)
		BEGIN
			RAISERROR('As disciplinas não podem começar no mesmo horário', 16, 1)
		END
		ELSE
		BEGIN
			IF(@horasD1 = 4 AND (@horasD2 = 4 OR @inicioD2 = '14:50' OR 
					(@inicioD1 = '14:50' AND @inicioD2 = '16:40')))
			BEGIN
				RAISERROR('Há choque de horário entre as disciplinas', 16, 1)
			END
			ELSE
			BEGIN
				SET @validoDisciplina = 1
			END
		END
	END
GO


