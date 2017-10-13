#!/bin/bash

echo $1
# max wait retry * sleep = 300s (5min)
maxRetry=30
retry=1
#until [ $retry < $maxRetry ] && [ "`curl --silent --show-error --connect-timeout 1 -I ${1} | grep 'HTTP/1.1 200'`" != "" ];
until (( $retry > $maxRetry )) || [ "`curl --silent --show-error --connect-timeout 1 -I ${1} | grep 'HTTP/1.1 200'`" != "" ];
do
  echo --- sleeping for 10 seconds
  sleep 10
  retry=$(($retry + 1))
done

if (( $retry > $maxRetry )) ; then
  echo Failed to start server
  exit 1
else
  echo Server is ready!
fi