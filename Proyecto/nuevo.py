#!/usr/bin/python

import os
import re
import MySQLdb

os.system("touch archivo.txt")

#---------------------------------------- Conexion a la bd ----------------------------------------------
db = MySQLdb.connect("192.168.1.111", "xubuntu", "root", "proyecto") 
cursor = db.cursor()
#--------------------------------------------------------------------------------------------------------
#--------------------------------------------- Mi IP ----------------------------------------------------
os.system("ip -s route | grep src > archivo.txt")
archivo = open('archivo.txt', 'r')
cont = 0
variable = " "
bandera = 0
todo= 1
for linea in archivo:
	longitud = 1
	for letra in linea:
		if letra!=" " and longitud==len(linea):
			variable = variable + " "
			bandera = 1
		if letra!=" ":
			variable = variable + letra
			bandera = 1
		elif (letra==" " and bandera==1):
			variable = variable + " "
			bandera = 0
		longitud = longitud + 1
	todo = todo +1
archivo.close
print variable
print "todo ", todo


i=9
cuenta = 1
for parte in variable:
	if (cuenta<=todo-1):
		lineaentera = variable.split(" ")
		miIP = lineaentera[i]
		print "mi ip es: ", miIP
		cuenta = cuenta + 1
		i = i+12
#--------------------------------------------------------------------------------------------------------
#---------------------------------------------Creando NODO------------------------------------------------
cursor.execute("INSERT INTO nodo (ip) VALUES('%s')"%(miIP))
db.commit()
cursor.execute("SELECT id FROM nodo WHERE ip = '%s'" %(miIP))
resultados = cursor.fetchall()
for row in resultados: 
	#print "Codigo: " , row[0] 
	miID = row[0]

print "idddddddddddddddddd", miID
#---------------------------------------------------------------------------------------------------------
#---------------------------------------- Top 10 Procesos ------------------------------------------------

os.system("ps -eo pcpu,pid,comm --sort -pcpu | grep -v '0.0 ' > archivo.txt")
archivo = open('archivo.txt', 'r')
cont = 0
variable = " "
bandera = 0
todo= 1
for linea in archivo:
	if (cont !=0): 
		for letra in linea:
			if letra!=" ":
				variable = variable + letra
				bandera = 1
			elif (letra==" " and bandera==1):
				variable = variable + " "
				bandera = 0	
		cont= cont + 1
	elif (cont==0):
		cont= cont + 1
	todo = todo +1
archivo.close
print variable
print "todo ", todo

i=1
ii=2
iii=3
cuenta = 1
usoCPU = 0
for parte in variable:
	if (cuenta<=todo-2):
		if (cuenta<=10):
			lineaentera = variable.split(" ")
			porcentaje = lineaentera[i]
			usoCPU = usoCPU + float(porcentaje)
			print porcentaje
			pid = lineaentera[ii]
			print pid
			nombre = lineaentera[iii]
			print nombre
			cursor.execute("INSERT INTO proceso (pid,valor,porcentaje,fk_nodo) VALUES('%s','%s','%s','%s')"%(pid,nombre,porcentaje,miID))
			db.commit()
		else :
			lineaentera = variable.split(" ")
			porcentaje = lineaentera[i]
			usoCPU = usoCPU + float(porcentaje)
		#print variable
		i = i+3
		ii = ii+3
		iii = iii+3
		cuenta = cuenta + 1
		print cuenta
print "El uso del CPU es: ", usoCPU, "%"
cursor.execute("INSERT INTO cpu (cpu,fk_nodo) VALUES('%s','%s')"%(usoCPU,miID))
db.commit()
#------------------------------------------------------------------------------------------------------

#------------------------------------------ Uso de RAM ------------------------------------------------
os.system("free |grep ^M > archivo.txt")
archivo = open('archivo.txt', 'r')
variable = ""
bandera = 1
for linea in archivo:
	for letra in linea:
		if letra!=" ":
			variable = variable + letra
			bandera = 1
		elif (letra==" " and bandera==1):
			variable = variable + " "
			bandera = 0
archivo.close

for parte in variable:
	lineaentera = variable.split(" ")
	usoRAM = lineaentera[2]
print "Uso de la RAM: ", usoRAM
cursor.execute("INSERT INTO ram (ram,fk_nodo) VALUES('%s','%s')"%(usoRAM,miID))
db.commit()
#-------------------------------------------------------------------------------------------------------

#---------------------------------------Uso de Filesystems ---------------------------------------------
os.system("df -Th > archivo.txt")
archivo = open('archivo.txt', 'r')
cont = 0
variable = ""
bandera = 1
todo= 1
for linea in archivo:
	if (cont !=0): 
		longitud = 1
		for letra in linea:
			if letra!=" " and longitud==len(linea):
				variable = variable + " "
				bandera = 1
			elif letra!=" ":
				variable = variable + letra
				bandera = 1
			elif (letra==" " and bandera==1):
				variable = variable + " "
				bandera = 0
			longitud = longitud + 1	
		cont= cont + 1
	elif (cont==0):
		cont= cont + 1
	todo = todo +1
archivo.close

i=0
ii=3
iii=5
cuenta = 1
for parte in variable:
	if (cuenta<=todo-2):
		lineaentera = variable.split(" ")
		nombre = lineaentera[i]
		if (re.search(r'/dev/sda', nombre) or re.search(r'/dev/sdb', nombre) or re.search(r'/dev/hdd', nombre)):
			print nombre
			usado = lineaentera[ii]
			print usado
			porcentaje = lineaentera[iii]
			print porcentaje
			cursor.execute("INSERT INTO filesystem (nombre,valor,porcentaje,fk_nodo) VALUES('%s','%s','%s','%s')"%(nombre,usado,porcentaje,miID))
			db.commit()
		i = i+7
		ii = ii+7
		iii = iii+7
		cuenta = cuenta + 1
#-------------------------------------------------------------------------------------------------------

#--------------------------------------- Top 10 Directorios --------------------------------------------
# unidad: K
os.system("du -s /home/* | sort -nr > archivo.txt")
archivo = open('archivo.txt', 'r')
variable = ""
todo = 1
bandera = 1
for linea in archivo:
	longitud = 1
	for letra in linea:
		if (letra!='\t' or letra!=" ") and longitud==len(linea):
			variable = variable + "#"
			bandera= 1
		elif letra=='\t':
			variable = variable + "#"
		elif letra==" " and bandera==1:
			variable = variable + "#"
			bandera = 0
		else:
			variable = variable + letra
			bandera = 1
		longitud = longitud + 1
	todo = todo + 1 
archivo.close
print variable
print todo

i=0
ii=1
cuenta = 1
for parte in variable:
	if (cuenta<=todo-1):
		if (cuenta<=10):
			lineaentera = variable.split("#")
			uso = lineaentera[i]
			directorio = lineaentera[ii]
			print uso
			print directorio
			cursor.execute("INSERT INTO directorio (nombre,valor,fk_nodo) VALUES('%s','%s','%s')"%(directorio,uso,miID))
			db.commit()
			i= i+2
			ii= ii+2
			cuenta = cuenta + 1
#-------------------------------------------------------------------------------------------------------
cursor.close()
