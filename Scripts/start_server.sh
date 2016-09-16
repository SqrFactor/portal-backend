#!/bin/bash
TOMCAT_HOME="/usr/share/tomcat8"
#Time in seconds to wait before we kill tomcat process gracefully
SHUTDOWN_WAIT=10
start_server() {
    pid=$(ps aux | grep org.apache.catalina.startup.Bootstrap | grep -v grep | awk '{ print $2 }')
    if [ -n "$pid" ]
    then
        echo "Tomcat is already running (pid: $pid)"
    else
        # Start tomcat
        echo "Starting tomcat"
        sudo $TOMCAT_HOME/bin/startup.sh
       
        until["`curl --silent --show-error --connect-timeout 1 -I http://localhost:8080 | grep 'Coyote'`" != ""]
        do
         echo "Waiting for deployment to complete"
         sleep 10
        done
              
    fi
    return 0
}