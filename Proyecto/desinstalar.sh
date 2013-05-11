sshpass -p 'xubuntu' ssh StrictHostKeyChecking=no xubuntu@192.168.1.100 'echo "crontab -l'
sshpass -p 'xubuntu' ssh StrictHostKeyChecking=no xubuntu@192.168.1.100 'echo "rm /home/xubuntu/Escritorio/nuevo.py'

#sshpass -p $2 ssh StrictHostKeyChecking=no $1@$3 'echo "crontab -l'
#sshpass -p $2 ssh StrictHostKeyChecking=no $1@$3 'echo "rm /home/$1/Escritorio/nuevo.py'

#$1 usuario, $2 password, $3 ip