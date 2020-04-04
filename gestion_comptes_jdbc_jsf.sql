create database if not exists db_jdbc_jsf;

create table if not exists conseiller(id_conseiller int primary key auto_increment, nom varChar(20), mail varChar(20), mot_de_passe varChar(50));
create table if not exists clients(id_client int auto_increment, nom varChar(50), prenom varChar(50), adresse varChar(50), code_postal varChar(50), ville varChar(50), telephone varChar(50), conseiller_id int,
									constraint pk_clients primary key (id_client),
                                    constraint fk_clients_conseiller foreign key(conseiller_id) references conseiller(id_conseiller) on delete cascade on update cascade);
create table if not exists comptes(id_compte int auto_increment, type_compte varChar(50), num_compte int, solde decimal(20,2), taux double, decouvert double, client_id int,
									constraint pk_clients primary key (id_compte), 
									constraint fk_comptes_clients foreign key(client_id) references clients(id_client) on delete cascade on update cascade);



insert into conseiller(nom, mail, mot_de_passe) values ('J.Ducrocq', 'J.Ducrocq@gmail.com', '123'),
														('A.Duchesse', 'A.Duchesse@gmail.com', '456'),
                                                        ('A.Caron', 'A.Caron@gmail.com', '123');

insert into clients(nom, prenom, adresse, code_postal, ville, telephone, conseiller_id) values ('Laforge', 'Ancelina', '13, Chemin Du Lavarin Sud', '14000', 'CAEN', '0299237221', 1),
																								('Duchesse', 'Angelina', '26, Boulevard POISSONNIERE', '75010', 'PARIS', '0126002600', 1),
																								('Ducrocq', 'Jérôme', '10, Rue Choron', '75009', 'PARIS', '0606060606', 2);


insert into comptes(type_compte, num_compte, solde, taux, decouvert, client_id) values ('courant', 010101, 10000.0, 0.00, 100.00, 1),
																						('epargne', 010102, 27964.850, 0.03, 0.00, 1),
                                                                                        ('courant', 050505, 1200200.36, 0.00, 100.00, 2),
                                                                                        ('epargne', 666, 10000.00, 0.03, 0.00, 3);
             

                                                                                        

select * from conseiller;
select * from clients;
select * from comptes;