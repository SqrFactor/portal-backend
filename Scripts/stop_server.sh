#!/bin/bash
TOMCAT_HOME="/usr/share/tomcat8"
#Time in second to wait before we kill tomcat process gracefully
SHUTDOWN_WAIT=10
stop_server() {
    pid=$(ps aux | grep org.apache.catalina.startup.Bootstrap | grep -v grep | awk '{ print $2 }')
    if [ -n "$pid" ]
    then
        echo "Stoping Tomcat"
        sudo $TOMCAT_HOME/bin/shutdown.sh
    let kwait=$SHUTDOWN_WAIT
    count=0
    count_by=5
    until [ `ps -p $pid | grep -c $pid` = '0' ] || [ $count -gt $kwait ]
    do
        echo "Waiting for processes to exit. Timeout before we kill the pid: ${count}/${kwait}"
        sleep $count_by
        let count=$count+$count_by;
    done
    if [ $count -gt $kwait ]; then
        echo "Killing processes which didn't stop after $SHUTDOWN_WAIT seconds"
        sudo kill -9 $pid
    fi
    else
        echo "Tomcat is not running"
    fi
    return 0
}
