-- One admin user, named curro with passwor curro and authority admin
INSERT INTO users(username,password,enabled) VALUES ('curro','curro',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'curro','admin');

-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');








--A CONTINUACION EMPIEZA LO MODIFICADO POR EL GRUPO



--ejercicio 8 practica 1
INSERT INTO users(username,password,enabled) VALUES ('danricost','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'danricost','owner');

INSERT INTO users(username,password,enabled) VALUES ('ismlunati','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'ismlunati','owner');

INSERT INTO users(username,password,enabled) VALUES ('rodsangon','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'rodsangon','owner');

INSERT INTO users(username,password,enabled) VALUES ('frabreloz','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'frabreloz','owner');

INSERT INTO users(username,password,enabled) VALUES ('gonrodter','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'gonrodter','owner');

INSERT INTO users(username,password,enabled) VALUES ('robpazriv','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'robpazriv','owner');


INSERT INTO owners VALUES (11, 'Daniel', 'Rico Ostos', '110 W. Liberty St.', 'Madison', '6085551023', 'danricost');
INSERT INTO owners VALUES (12, 'Ismael', 'Luna Atienza', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'ismlunati');
INSERT INTO owners VALUES (13, 'Rodrigo', 'Sánchez González', '2693 Commerce St.', 'McFarland', '6085558763', 'rodsangon');
INSERT INTO owners VALUES (14, 'Francisco Jose', 'Brenes Lozano', '563 Friendly St.', 'Windsor', '6085553198', 'frabreloz');
INSERT INTO owners VALUES (15, 'Gonzalo', 'Rodríguez Terrón', '2387 S. Fair Way', 'Madison', '6085552765', 'gonrodter');
INSERT INTO owners VALUES (16, 'Roberto', 'Paz Rivera', '105 N. Lake St.', 'Monona', '6085552654', 'robpazriv');




INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'mascotaDani', '2010-09-07', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'mascotaIsmael', '2012-08-06', 6, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'mascotaRodrigo', '2011-04-17', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'mascotaFran', '2010-03-07', 2, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'mascotaGonzalo', '2010-11-30', 3, 15);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (19, 'mascotaRoberto', '2010-01-20', 4, 16);










--A CONTINUACION EMPIEZA LO MODIFICADO POR EL GRUPO PARA EL PROYECTO


-- insertamos empleados
--INSERT INTO empleados(nombre, dni, sueldo, fechanacimiento) VALUES ('Paco', '15151515R', 2000, '2000-01-01');

-- insertamos dependientes
INSERT INTO dependientes(nombre, dni, sueldo, fechanacimiento) VALUES ('Paco', '15151515R', 2000, '2000-01-01');

--insertamos vehiculo
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('4772HZC', 'Coche');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('7288ASM', 'Moto');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('9008XXC', 'Coche');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('7395LRG', 'Moto');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('3560GFH', 'Moto');

-- insertamos repartidores
--INSERT INTO repartidores(nombre, dni, sueldo, fechanacimiento, usuario, contrasena, vehiculo_id) VALUES ('Jorge', '77133335P', 10000, '2000-11-21', 'repartidor1', 'clave', 3);
--INSERT INTO repartidores(nombre, dni, sueldo, fechanacimiento, usuario, contrasena, vehiculo_id) VALUES ('Antonio', '37551947T', 10020, '1998-03-14', 'repartidor2', 'clave', 2);
INSERT INTO users(username,password,enabled) VALUES ('repartidor1','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'repartidor1','repartidor');
INSERT INTO repartidores(nombre, dni, sueldo, fechanacimiento, username, vehiculo_id) VALUES ('Jorge', '77133335P', 10000, '2000-11-21', 'repartidor1', 3);
INSERT INTO users(username,password,enabled) VALUES ('repartidor2','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'repartidor2','repartidor');
INSERT INTO repartidores(nombre, dni, sueldo, fechanacimiento, username, vehiculo_id) VALUES ('Antonio', '37551947T', 10020, '1998-03-14', 'repartidor2', 2);

-- insertamos cocineros
INSERT INTO cocineros(nombre, dni, sueldo, fechanacimiento) VALUES ('Cristian', '79051555L', 400, '1845-08-15');

-- insertamos clientes
--INSERT INTO clientes(nombre, apellidos, telefono, direccion, email, contrasena, fechanacimiento) VALUES ('Juan', 'Pérez Rodriguez', '123456789', 'avda el pantano', 'juanrod', 'juanrod', '1999-09-21');
INSERT INTO users(username,password,enabled) VALUES ('juan@gmail.com','juan',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'juan@gmail.com','cliente');
INSERT INTO clientes(nombre, apellidos, telefono, direccion, fechanacimiento, username) VALUES ('Juan', 'Pérez Rodriguez', '123456789', 'avda el pantano', '1999-09-21', 'juan@gmail.com');

-- insertamos pedidos
INSERT INTO pedidos(id,fecha, comentario,valoracion, metodopago, estadopedido, tipopedido) VALUES(1,'2014-08-06 15:59:48','excelente', 5, 'efectivo', 'pendiente', 'enLocal');

INSERT INTO pedidos(id,fecha, metodopago, estadopedido, tipopedido, cliente_id) VALUES(2,'2016-05-11 15:59:48', 'tarjeta', 'pendiente', 'aDomicilio', 1);
INSERT INTO pedidos(id,fecha, comentario,valoracion, metodopago, estadopedido, tipopedido, cliente_id) VALUES(3,'2010-07-05 15:59:48','excelente', 5, 'efectivo', 'enReparto', 'aDomicilio', 1);
INSERT INTO pedidos(id,fecha, comentario,valoracion, metodopago, estadopedido, tipopedido) VALUES(4,'2013-01-02 15:59:48','malo', 1, 'efectivo', 'pendiente', 'aDomicilio');
INSERT INTO pedidos(id,fecha, comentario,valoracion, metodopago, estadopedido, tipopedido) VALUES(5,'2015-01-02 15:50:48','malo', 1, 'efectivo', 'pendiente', 'aDomicilio');
INSERT INTO pedidos(id,fecha, comentario,valoracion, metodopago, estadopedido, tipopedido) VALUES(6,'2014-01-02 15:59:48','malo', 1, 'efectivo', 'pendiente', 'enLocal');


-- insertamos productos
INSERT INTO productos(name, precio, tamanopizza) VALUES ('Pizza Margarita', 10, 'pequena');
INSERT INTO productos(name, precio, tamanopizza) VALUES ('Pizza Peperoni', 12, 'pequena');
INSERT INTO productos(name, precio, tamanopizza) VALUES ('Pizza atun', 11, 'pequena');

-- insertamos reparto
INSERT INTO repartos(fecha, HORA_INICIO, HORA_FIN) VALUES ('1999-11-12', '12:11:11', '13:11:11');

-- -- relacion repartidores-reparto
-- --INSERT INTO REPARTIDORES_REPARTOS(REPARTIDOR_ID, REPARTOS_ID) VALUES ('1', '1');


