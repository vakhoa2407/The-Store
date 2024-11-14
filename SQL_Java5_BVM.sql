USE master;
Go

Create Database Java5_BVM;
Go

Use Java5_BVM;
Go

Create Table [user](
Id nvarchar(20) primary key,
Password nvarchar(50),
Fullname nvarchar(50),
Email nvarchar(50),
Admin bit not null
);