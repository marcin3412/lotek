-- Tabela z użytkownikami serwisu
CREATE TABLE IF NOT EXISTS klient(
	id int not null auto_increment,
	imie varchar(32) not null,
	nazwisko varchar(64) not null,
	email varchar(64) not null,
	haslo varchar(64) not null,
	confirm_cod varchar(64),
	confirmed boolean not null,
	create_at date not null,
	primary key(id)
);

-- Tabela z saldem użytkowników 1 -> 1
CREATE TABLE IF NOT EXISTS saldo(
	id int not null auto_increment,
	klient_id int not null,
	saldo int not null,
	primary key(id),
	foreign key(klient_id) references klient(id)
);

-- Tabela z kumulacjiami
CREATE TABLE IF NOT EXISTS kumulacja(
	id int not null auto_increment,
	pula int not null,
	data_losowania date not null,
	zakonczona boolean not null,
	num1 int not null,
	num2 int not null,
	num3 int not null,
	num4 int not null,
	num5 int not null,
	num6 int not null,
	primary key(id)
);

--Tabela losów użytkownika W -> 1
CREATE TABLE IF NOT EXISTS los(
	id int not null auto_increment,
	num1 int not null,
	num2 int not null,
	num3 int not null,
	num4 int not null,
	num5 int not null,
	num6 int not null,
	klient_id int not null,
	create_at date not null,
	kumulacja_id int not null,
	wygrany boolean,
	ile_traf int,
	wygrana int,
	uzyty boolean,
	primary key(id),
	foreign key(kumulacja_id) references kumulacja(id)
);


-- Tabela wygranych w kumulacjach  this1->1kli 1->1kum 1->1los
CREATE TABLE IF NOT EXISTS wygrana(
	id int not null auto_increment,
	klient_id int not null,
	kumulacja_id int not null,
	pula int not null,
	los_id int not null,
	primary key(id),
	foreign key(klient_id) references klient(id),
	foreign key(kumulacja_id) references kumulacja(id)
	foreign key(los_id) references los(id)
);

-- Tabela aktualnej puli do wylosowania 
CREATE TABLE IF NOT EXISTS glowna_pula(
	id int not null auto_increment,
	create_at date not null,
	pula int not null,
	primary key(id)
);