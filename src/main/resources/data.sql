-- Remove todas as tabelas
drop table if exists public.obra cascade;
drop table if exists public.obra_author cascade;
drop table if exists public.obra_subject cascade;

drop table if exists public.subject cascade;
drop table if exists public.author cascade;
drop table if exists public.publisher cascade;
drop table if exists public.editora cascade;
drop table if exists public.subject_related cascade;

drop table if exists public.work cascade;
drop table if exists public.work_subject cascade;
drop table if exists public.work_author cascade;
drop table if exists public.account cascade;
drop table if exists public.address cascade;

drop table if exists public.exemplar cascade;
drop table if exists public.reserva cascade;
drop table if exists public.departamento cascade;
drop table if exists public.devolucao cascade;
drop table if exists public.emprestimo cascade;
drop table if exists public.funcionario cascade;

drop sequence if exists public.subject_seq cascade;
drop sequence if exists public.author_seq cascade;
drop sequence if exists public.publisher_seq cascade;
drop sequence if exists public.work_seq cascade;
drop sequence if exists public.account_seq cascade;

drop sequence if exists public.exemplar_seq cascade;
drop sequence if exists public.reserva_seq cascade;
drop sequence if exists public.departamento_seq cascade;
drop sequence if exists public.devolucao_seq cascade;
drop sequence if exists public.emprestimo_seq cascade;
drop sequence if exists public.funcionario_seq cascade;

-- Cria todas as tabelas
create table public.subject (
	id integer not null,
	description varchar(255) not null,
	constraint subject_description_unique unique (description),
	constraint subject_pkey primary key (id)
);

create table public.author (
	id integer not null,
	name varchar(255) not null,
	uri varchar not null,
	constraint author_uri_unique unique (uri),
	constraint author_pkey primary key (id)
);

create table if not exists public.publisher (
	id integer not null,
	name varchar(255) not null,
	city varchar(255) not null,
	uri varchar not null,
	constraint publisher_pkey primary key (id)
);

create table public.subject_related (
	subject_id integer not null,
	subject_related_id integer not null,
	constraint subject_related_pkey primary key (subject_id, subject_related_id)
);

create table public.work (
	id integer not null,
	isbn varchar(255) not null,
	title varchar(255) not null,
	description varchar(255) not null,
	year_publication timestamp not null,
	work_type smallint not null,
	publisher_id integer not null,
	constraint work_pkey primary key (id)
);

create table public.work_subject (
	work_id integer not null,
	subject_id integer not null,
	constraint work_subject_pkey primary key (work_id, subject_id)
);

create table public.work_author (
	work_id integer not null,
	author_id integer not null,
	ordering smallint null,
	constraint work_author_pkey primary key (work_id, author_id)
);

create table public.account (
	id integer not null,
	login varchar(255) not null,
	password varchar(255) not null,
	name varchar(255) not null,
	phone varchar(20) not null,
	email varchar(255) not null,
	document varchar(11) not null,
	creation_date timestamp not null,
	account_indicated_id integer null,
	account_status smallint not null,
	constraint account_login_unique unique (login),
	constraint account_document_unique unique (document),
	constraint account_email_unique unique (email),
	constraint account_pkey primary key (id)
);

create table public.address (
	id integer not null,
	address_type integer not null,
	street varchar(255) not null,
	number varchar(10) not null,
	comments varchar(255) not null,
	district varchar(255) not null,
	city varchar(255) not null,
	state varchar(2) not null,
	country varchar(255) not null,
	zip_code varchar(8) not null,
	receiver_name varchar(255) not null,
	receiver_phone varchar(14) not null,
	constraint address_pkey primary key (id, address_type)
);

create table public.exemplar (
	work_id integer not null,
	num_exemplar integer not null,
	data_aquisicao timestamp not null,
	situacao_exemplar smallint not null,
	constraint exemplar_pkey primary key (work_id, num_exemplar)
);

create table public.reserva (
	cod_reserva integer not null,
	account_id integer not null,
	work_id integer not null,
	data_reserva timestamp not null,
	data_retirada timestamp not null,
	constraint reserva_pkey primary key (cod_reserva)
);

create table public.departamento (
	cod_departamento integer not null,
	nome_departamento varchar(255) not null,
	num_matricula_chefe integer null,
	constraint departamento_pkey primary key (cod_departamento)
);

create table public.devolucao (
	cod_emprestimo integer not null,
	data_devolucao timestamp not null,
	num_matricula_funcionario integer not null,
	constraint devolucao_pkey primary key (cod_emprestimo)
);

create table public.emprestimo (
	cod_emprestimo integer not null,
	work_id integer not null,
	num_exemplar integer not null,
	account_id integer not null,
	data_emprestimo timestamp not null,
	data_prevista_retorno timestamp not null,
	num_matricula_funcionario integer not null,
	constraint emprestimo_pkey primary key (cod_emprestimo)
);

create table public.funcionario (
	num_funcionario integer not null,
	nome_funcionario varchar(255) not null,
	cod_departamento integer not null,
	salario numeric not null,
	constraint funcionario_pkey primary key (num_funcionario)
);


create sequence if not exists public.subject_seq;
create sequence if not exists public.author_seq;
create sequence if not exists public.publisher_seq;
create sequence if not exists public.work_seq;
create sequence if not exists public.account_seq;
create sequence if not exists public.exemplar_seq;
create sequence if not exists public.reserva_seq;
create sequence if not exists public.departamento_seq;
create sequence if not exists public.devolucao_seq;
create sequence if not exists public.emprestimo_seq;
create sequence if not exists public.funcionario_seq;


alter table public.subject alter column id set default nextval('subject_seq'::regclass);
alter table public.author alter column id set default nextval('author_seq'::regclass);
alter table public.publisher alter column id set default nextval('publisher_seq'::regclass);
alter table public.work alter column id set default nextval('work_seq'::regclass);
alter table public.account alter column id set default nextval('account_seq'::regclass);
alter table public.exemplar alter column num_exemplar set default nextval('exemplar_seq'::regclass);
alter table public.reserva alter column cod_reserva set default nextval('reserva_seq'::regclass);
alter table public.departamento alter column cod_departamento set default nextval('departamento_seq'::regclass);
alter table public.devolucao alter column cod_emprestimo set default nextval('devolucao_seq'::regclass);
alter table public.emprestimo alter column cod_emprestimo set default nextval('emprestimo_seq'::regclass);
alter table public.funcionario alter column num_funcionario set default nextval('funcionario_seq'::regclass);


-- public.subject_relacionamento foreign keys
alter table public.subject_related add constraint fk_subject_related_subject foreign key (subject_id) references public.subject(id);
alter table public.subject_related add constraint fk_subject_related_subject_related foreign key (subject_related_id) references public.subject(id);

-- public.work foreign keys
alter table public.work add constraint fk_publisher foreign key (publisher_id) references public.publisher(id);

-- public.work_subject foreign keys
alter table public.work_subject add constraint fk_work_subject_subject foreign key (subject_id) references public.subject(id);
alter table public.work_subject add constraint fk_work_subject_work foreign key (work_id) references public.work(id);

-- public.work_author foreign keys
alter table public.work_author add constraint fk_work_author_author foreign key (author_id) references public.author(id);
alter table public.work_author add constraint fk_work_author_work foreign key (work_id) references public.work(id);

-- public.account foreign keys
alter table public.account add constraint fk_account_indicated foreign key (account_indicated_id) references public.account(id);

-- public.address foreign keys
alter table public.address add constraint fk_address_account foreign key (id) references public.account(id);

-- public.exemplar foreign keys
alter table public.exemplar add constraint fk_exemplar_work foreign key (work_id) references public.work(id);

-- public.reserva foreign keys
alter table public.reserva add constraint fk_reserva_work foreign key (work_id) references public.work(id);
alter table public.reserva add constraint fk_reserva_account foreign key (account_id) references public.account(id);

-- public.departamento foreign keys
alter table public.departamento add constraint fk_departamento_funcionario foreign key (num_matricula_chefe) references public.funcionario(num_funcionario);

-- public.devolucao foreign keys
alter table public.devolucao add constraint fk_devolucao_emprestimo foreign key (cod_emprestimo) references public.emprestimo(cod_emprestimo);
alter table public.devolucao add constraint fk_devolucao_funcionario foreign key (num_matricula_funcionario) references public.funcionario(num_funcionario);

-- public.emprestimo foreign keys
alter table public.emprestimo add constraint fk_emprestimo_exemplar foreign key (work_id,num_exemplar) references public.exemplar(work_id,num_exemplar);
alter table public.emprestimo add constraint fk_emprestimo_funcionario foreign key (num_matricula_funcionario) references public.funcionario(num_funcionario);
alter table public.emprestimo add constraint fk_emprestimo_account foreign key (account_id) references public.account(id);


-- public.funcionario foreign keys
alter table public.funcionario add constraint fk_funcionario_departamento foreign key (cod_departamento) references public.departamento(cod_departamento);


-- Editoras
insert into public.publisher (id, name, city, uri) values(nextval('publisher_seq'), 'ABRIL', 'São Paulo', 'www.abril.com');
insert into public.publisher (id, name, city, uri) values(nextval('publisher_seq'), 'ATLAS', 'Paris', 'www.atlas.com');
insert into public.publisher (id, name, city, uri) values(nextval('publisher_seq'), 'NOVA', 'Salvador', 'www.nova.com');

-- Autores
insert into public.author (id, name, uri) values (nextval('author_seq'), 'Machado de Assis', 'https://pt.wikipedia.org/wiki/Machado_de_Assis');
insert into public.author (id, name, uri) values (nextval('author_seq'), 'William Shakespeare', 'https://pt.wikipedia.org/wiki/William_Shakespeare');
insert into public.author (id, name, uri) values (nextval('author_seq'), 'Ariano Suassuna', 'https://pt.wikipedia.org/wiki/Ariano_Suassuna');

-- Obras
insert into public.work (id, isbn, title, description, year_publication, work_type, publisher_id)
	values(nextval('work_seq'), '00-000-0000-0', 'HTML Avançado', 'HTML Avançado: Recursos avançados dessa linguagem.','2023-01-12', 0, 1);

insert into public.work (id, isbn, title, description, year_publication, work_type, publisher_id)
	values(nextval('work_seq'), '11-111-1111-1', 'Javascript Básico', 'Javascript Básico: Recursos iniciais dessa linguagem.','2022-06-24', 0, 1);
	
insert into public.work (id, isbn, title, description, year_publication, work_type, publisher_id)
	values(nextval('work_seq'), '22-222-2222-2', 'Estilo com CSS', 'Estilo com CSS: Recursos iniciais dessa linguagem.','2024-01-23', 1, 3);

-- Obra x Autor
insert into public.work_author (work_id, author_id, ordering) values (1,1,1);
insert into public.work_author (work_id, author_id, ordering) values (2,2,1);
insert into public.work_author (work_id, author_id, ordering) values (3,1,1);
insert into public.work_author (work_id, author_id, ordering) values (3,2,2);

-- Usuarios

insert into public.account (
	id, login, password, name, phone, email, 
	document, creation_date, account_indicated_id, account_status)
values (
	nextval('account_seq'), 'eric', '123', 'Eric Azevedo', '5571994053945', 'eric@fikfit.com.br',
	'03438488566', '2024-01-01', null, 0
);

insert into public.account (
	id, login, password, name, phone, email, 
	document, creation_date, account_indicated_id, account_status)
values (
	nextval('account_seq'), 'junior', '123', 'Junior Cardoso', '5575988377087', 'junior@fikfit.com.br',
	'00011122233', '2024-01-01', 1, 0
);

-- Endereço (Tipos: 0 = Residencial, 1 = Trabalho, 2 = Cobrança)
insert into public.address (
	id, address_type, receiver_name, street, number, 
	comments, district, city, state, country, zip_code, receiver_phone
)
values (
	1, 0, 'Ana Vizinha', 'Rua Emídio Santos', '62',
	'1o Andar', 'Barbalho', 'Salvador', 'BA', 'Brasil', '40400400', '5571994053945'
);

insert into public.address (
	id, address_type, receiver_name, street, number, 
	comments, district, city, state, country, zip_code, receiver_phone
)
values (
	2, 0, 'Maria', 'Rua Vereador Joao Delfino', '125',
	'Fundo', 'Centro', 'Santo Antônio de Jesus', 'BA', 'Brasil', '44430192', '5575988880000'
);

insert into public.address (
	id, address_type, receiver_name, street, number, 
	comments, district, city, state, country, zip_code, receiver_phone
)
values (
	2, 1, 'Ana Carolina', 'Rua Emídio Santos', '62',
	'1o Andar', 'Barbalho', 'Salvador', 'BA', 'Brasil', '40400400', '5571988880000'
);

-- subjects
insert into public.subject(id, description) values (nextval('subject_seq'), 'Computação');
insert into public.subject(id, description) values (nextval('subject_seq'), 'Política');
insert into public.subject(id, description) values (nextval('subject_seq'), 'Tecnologia');
insert into public.subject(id, description) values (nextval('subject_seq'), 'HTML');
insert into public.subject(id, description) values (nextval('subject_seq'), 'JavaScript');
insert into public.subject(id, description) values (nextval('subject_seq'), 'Linguagem de estilo');

-- Obra x subject
insert into public.work_subject (work_id, subject_id ) values (1,1);
insert into public.work_subject (work_id, subject_id ) values (2,5);
insert into public.work_subject (work_id, subject_id ) values (2,1);
insert into public.work_subject (work_id, subject_id ) values (3,6);

-- Exemplares

insert into public.exemplar (work_id, num_exemplar, data_aquisicao, situacao_exemplar)
	values(1, nextval('exemplar_seq'), '2024-01-03', 0);

insert into public.exemplar (work_id, num_exemplar, data_aquisicao, situacao_exemplar)
	values(1, nextval('exemplar_seq'), '2024-01-03', 0);

insert into public.exemplar (work_id, num_exemplar, data_aquisicao, situacao_exemplar)
	values(2, nextval('exemplar_seq'), '2024-01-03', 0);

insert into public.exemplar (work_id, num_exemplar, data_aquisicao, situacao_exemplar)
	values(2, nextval('exemplar_seq'), '2024-01-03', 1);

insert into public.exemplar (work_id, num_exemplar, data_aquisicao, situacao_exemplar)
	values(2, nextval('exemplar_seq'), '2024-01-03', 0);

insert into public.exemplar (work_id, num_exemplar, data_aquisicao, situacao_exemplar)
	values(2, nextval('exemplar_seq'), '2024-01-03', 2);


-- Reservas

insert into public.reserva (cod_reserva, account_id, work_id, data_reserva, data_retirada)
	values(nextval('reserva_seq'), 1, 3, '2024-02-02 09:00:00', '2024-02-20 09:00:00');

insert into public.reserva (cod_reserva, account_id, work_id, data_reserva, data_retirada)
	values(nextval('reserva_seq'), 2, 2, '2024-02-01 10:00:00', '2024-02-25 09:00:00');



