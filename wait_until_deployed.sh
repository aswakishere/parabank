#!/bin/bash

echo $1
until [ "`curl --silent --show-error --connect-timeout 1 -I ${1} | grep 'HTTP/1.1 200'`" != "" ];
do
  echo --- sleeping for 5 seconds
  sleep 5
done

echo Tomcat is ready!