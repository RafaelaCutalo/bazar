CREATE TABLE categoria(
	id_categoria int(5) primary key auto_increment,
	nome varchar(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE produto(
	codigo int(5) primary key auto_increment,
	nome varchar(50),
	preco double,
    id_categoria int(5), 
    foreign key (id_categoria) references categoria(id_categoria)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
