USE [agis]
GO

/****** Object:  Table [dbo].[matricula]    Script Date: 29/09/2025 01:55:54 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[matricula](
	[codigoMatricula] [int] NOT NULL,
	[ra] [varchar](9) NULL,
	[codigoCurso] [int] NULL,
	[codigoDisciplina1] [int] NULL,
	[codigoDisciplina2] [int] NULL,
	[codigoDisciplina3] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigoMatricula] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


