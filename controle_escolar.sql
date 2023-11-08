CREATE DATABASE controle_escolar;
USE controle_escolar;
CREATE TABLE professor( 
id_professor int not null auto_increment,
cpf_professor BIGINT (11) not null,
nome varchar(70) not null,
primary key(id_professor)
);

CREATE TABLE aluno( 
nr_matricula int not null auto_increment,
cpf_aluno BIGINT (11) not null,
nome_aluno varchar(70) not null,
endereco_aluno varchar(70) not null,
primary key(nr_matricula)
);

CREATE TABLE coordenador( 
id_coordenador int not null auto_increment,
telefone int(15) not null,
nome_coordenador varchar(70) not null,
email_coordenador varchar (70) not null,
primary key(id_coordenador)
);


create table disciplina(
id_disciplina int not null auto_increment ,
nome_disciplina varchar(70) not null,
id_coordenador int not null,
primary key (id_disciplina ) 
);

create table curso(
id_curso int not null auto_increment,
nome_curso varchar (30),
id_coordenador int not null,
primary key (id_curso)
);

create table pagamentos(
id_pagamento int not null auto_increment,
nr_matricula int not null ,
id_curso int not null ,
data_pagamento date not null,
valor float not null,
primary key (id_pagamento)
);

create table turma(
id_turma int not null auto_increment,
nr_matricula int not null ,
id_curso int not null,
turno varchar(30)not null,
data_turma date not null,
primary key (id_turma)
);

create table Disponibilidade_Professor(
id_professor int not null  ,
Data_hora date not null,
id_disponibilidade int not null auto_increment,
primary key (id_disponibilidade)
);

create table cronograma(
id_conograma int not null auto_increment,
id_professor int not null  ,
id_disciplina int not null,
Data_hora date not null,
id_turma int not null,
primary key (id_conograma)
);

create table nota(
id_nota int not null auto_increment,
id_turma int not null,  
id_professor int not null  ,
id_curso int not null,
id_disciplina int not null,
nota_disciplina float not null,
nr_matricula int not null,
data_nota date not null,
primary key (id_nota)
);