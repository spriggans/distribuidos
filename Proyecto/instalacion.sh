#!/bin/sh

#sshpass -p 'xubuntu' scp -o StrictHostKeyChecking=no /home/angel/Escritorio/Proyecto/distribuidos/Proyecto/nuevo.py xubuntu@192.168.1.100:/home/xubuntu/Escritorio

#sshpass -p 'xubuntu' ssh xubuntu@192.168.1.100 'echo "*/5 * * * * /home/xubuntu/Escritorio/nuevo.py" | crontab -'

sshpass -p $2 scp -o StrictHostKeyChecking=no /home/xubuntu/NetBeansProjects/distribuidos/Proyecto/nuevo.py $1@$3:/home/xubuntu/Escritorio
sshpass -p $2 ssh $1@$3 'echo "*/5 * * * * /home/xubuntu/Escritorio/nuevo.py" | crontab -'

#$1 usuario, $2 password, $3 ip








