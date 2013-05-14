create table nodo (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
ip VARCHAR(20)
);

create table cpu (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
cpu float, 
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

create table ram (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
ram float, 
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

create table filesystem (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
nombre VARCHAR (20), 
valor VARCHAR(50),
porcentaje float,
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

create table directorio (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
nombre VARCHAR (50), 
valor VARCHAR(10), 
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

create table proceso (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
pid INT, 
valor VARCHAR(20), 
porcentaje float, 
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

grant all privileges on proyecto.* to xubuntu@'%' identified by "root";
grant all privileges on proyecto.* to root@'%' identified by "root";