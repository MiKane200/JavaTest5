echo "wait mysql to be installed";
while ! nc -z ${IP} ${PORT}; do sleep 3; done;
echo "${MYSQL_IP}:${MYSQL_PORT}";
java -Dfile.encoding=UTF-8 -jar /Exam1.jar;