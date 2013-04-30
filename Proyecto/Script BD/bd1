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
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

create table directorio (
nombre VARCHAR (50) NOT NULL PRIMARY KEY, 
valor VARCHAR(10),
porcentaje float, 
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);

create table proceso (
pid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
valor VARCHAR(20), 
porcentaje float, 
fk_nodo int,
FOREIGN KEY (fk_nodo) REFERENCES nodo(id)
);
