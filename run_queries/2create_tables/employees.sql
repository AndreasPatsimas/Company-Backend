USE [Company]
GO

/****** Object:  Table [dbo].[Employee]    Script Date: 26/7/2020 9:47:30 μμ ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Employee](
	[EMP_ID] [uniqueidentifier] NOT NULL,
	[EMP_Name] [nvarchar](100) NOT NULL,
	[EMP_Address] [nvarchar](400) NOT NULL,
	[EMP_Car] [bit] NOT NULL,
	[EMP_DateOfBirth] [date] NULL,
	[EMP_DateOfHire] [datetime] NOT NULL,
	[EMP_Supervisor] [uniqueidentifier] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED
(
	[EMP_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Employee] FOREIGN KEY([EMP_Supervisor])
REFERENCES [dbo].[Employee] ([EMP_ID])
GO

ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Employee]
GO


