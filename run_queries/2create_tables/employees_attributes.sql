USE [Company]
GO

/****** Object:  Table [dbo].[EmployeeAttribute]    Script Date: 26/7/2020 9:47:42 μμ ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[EmployeeAttribute](
	[EMPATTR_EmployeeID] [uniqueidentifier] NOT NULL,
	[EMPATTR_AttributeID] [uniqueidentifier] NOT NULL,
 CONSTRAINT [PK_EmployeeAttribute] PRIMARY KEY CLUSTERED
(
	[EMPATTR_EmployeeID] ASC,
	[EMPATTR_AttributeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[EmployeeAttribute]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeAttribute_Attribute] FOREIGN KEY([EMPATTR_AttributeID])
REFERENCES [dbo].[Attribute] ([ATTR_ID])
GO

ALTER TABLE [dbo].[EmployeeAttribute] CHECK CONSTRAINT [FK_EmployeeAttribute_Attribute]
GO

ALTER TABLE [dbo].[EmployeeAttribute]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeAttribute_Employee] FOREIGN KEY([EMPATTR_EmployeeID])
REFERENCES [dbo].[Employee] ([EMP_ID])
GO

ALTER TABLE [dbo].[EmployeeAttribute] CHECK CONSTRAINT [FK_EmployeeAttribute_Employee]
GO


