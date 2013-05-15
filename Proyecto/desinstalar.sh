#sshpass -p 'xubuntu' ssh StrictHostKeyChecking=no xubuntu@192.168.1.100 'echo "crontab -l'
#sshpass -p 'xubuntu' ssh StrictHostKeyChecking=no xubuntu@192.168.1.100 'echo "rm /home/xubuntu/Escritorio/nuevo.py'

sshpass -p $2 ssh $1@$3 'crontab -r'
sshpass -p $2 ssh $1@$3 'rm /home/xubuntu/Escritorio/nuevo.py'
sshpass -p $2 ssh $1@$3 'rm /home/xubuntu/Escritorio/archivo.txt'

#$1 usuario, $2 password, $3 ip