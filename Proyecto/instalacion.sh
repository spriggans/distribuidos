#!/bin/sh
#para poner en el crontab una linea cada 5 minutos
#crontab -l ; echo "0 4 * * * myscript"| crontab - 


sshpass -p 'xubuntu' scp -o StrictHostKeyChecking=no /home/angel/Escritorio/Proyecto/distribuidos/Proyecto/nuevo.py xubuntu@192.168.1.100:/home/xubuntu/Escritorio

sshpass -p 'xubuntu' ssh xubuntu@192.168.1.100 'echo "*/5 * * * * /home/xubuntu/Escritorio/nuevo.py" | crontab -'





#sshpass -p 'xubuntu' ssh -o StrictHostKeyChecking=no xubuntu@10.42.0.37





