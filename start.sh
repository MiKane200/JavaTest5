docker-compose up -d;
echo -e "please wait spring boot 50s to be started······· \n";
sleep 50;
sh curl.sh;
echo -e "\n\n wait data 30s to be loaded completely·········";
sleep 30;
sh stop.sh;
echo "attention !!! This program was over,see the results on the top";