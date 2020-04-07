#!/usr/bin/env bash

mvn clean package spring-boot:run -DskipTests -Dspring-boot.run.profiles=test -f skillful_network_server/pom.xml &
pid_backend=$!

sleep 30 &
pid_sleep=$!

while ps -p $pid_backend -o pid= && ps -p $pid_sleep -o pid=
do
	sleep 3
done

# the server should be still running
ps -p $pid_backend
status=$?
if [ $status -eq 0 ]
then
  kill -9 $pid_backend
fi
exit $status
