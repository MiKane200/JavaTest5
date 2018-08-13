curl -X POST -H "Content-Type: application/json" -sd '{"firstName":"MARY","lastName":"SMITH"}' "$(docker-machine ip $(docker-machine active)):8888/customer/login";
echo -e "\n";
curl -X GET -s 192.168.99.100:8888/customer/?page=1\&pageSize=2;
echo -e "\n";
curl -X PUT -H "Content-Type: application/json" -sd '{"address":"47%20MySakila%20Drive","firstName":"MARY","lastName":"SMITH","email":"123@hand.com"}' 192.168.99.100:8888/customer/save;
echo -e "\n";
curl -X POST -H "Content-Type: application/json" -sd '{"firstName":"MARY","lastName":"UPDATESMITH"}' 192.168.99.100:8888/customer/update;
echo -e "\n";
curl -X DELETE -s --cookie "cId=600" "$(docker-machine ip $(docker-machine active)):8888/customer/delete";