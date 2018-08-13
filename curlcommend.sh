# curl -X POST -H "Content-Type: application/json" -sd '{"languageId":"1"}' localhost:8888/film/languageId;
# curl localhost:8888/film/save/1;
curl -s "$(docker-machine ip $(docker-machine active)):8888/film/save/1";
curl -X POST -H "Content-Type: application/json" -sd '{"languageId":"1"}' "$(docker-machine ip $(docker-machine active)):8888/film/languageId";