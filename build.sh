cd Exam1;
mvn clean package;
cd ..;
cp Exam1/target/Exam1.jar docker/java/Exam1.jar;
docker-compose build;
sh start.sh;