#!/usr/bin/env bash

mvn clean package spring-boot:run -DskipTests -Dspring-boot.run.profiles=test -f skillful_network_server/pom.xml