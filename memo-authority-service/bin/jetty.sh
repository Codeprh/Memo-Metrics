#!/bin/bash
#docker_qc
BASE_HOME="/home/irms-external-service/sources"
#qc
#BASE_HOME="/home/dockerfile/irms-external-service/sources"


cd $BASE_HOME
work=$1
now=$(date +%Y%m%d)
log_file_url="console.log"
APP_START_DT=`date +"%Y%m%d"`
APP_JAR_LOCAL="irms-external-service-0.0.1.jar"
APP_JAR=`ls irms-external-service-0.0.1.jar`
JAVA_CMD="/usr/bin/java"

JAVA_OPTS=" -server \
-XX:+AggressiveOpts \
-XX:+UseBiasedLocking \
-XX:+UseParNewGC \
-XX:+UseConcMarkSweepGC "

JAVA_OPTIONS=$JAVA_OPTS

JETTY_PID="/data/logs/jetty.pid"


status() {
	 test -n   "`ps -ef |grep "$APP_JAR" |grep -v grep `"  && echo 1  || echo 0
}

health() {
	sleep 300
	while true
	 	do
	   		code=`curl -I -m 10 -o /dev/null -s -w %{http_code} localhost:6019/health`
	   		if [ "$code" -ne "200" ]; then
  		   		# 邮件告警,pod服务不可用
  		   		curl -X POST -H 'Content-Type:application/json' -H 'jobReqValidationKey:4c4d023b30a06979a4a85f3911e2385c' -d "{\"taskType\":\"sendEmailJob\",\"extParam\":{\"emails\":\"jeromeliu@can-dao.com,noahpan@can-dao.com,lionchen@can-dao.com\",\"title\":\"【$SERVER_ENV】-IRMS-POD-【$HOSTNAME】健康探针\",\"content\":\"容器实例 : $HOSTNAME, 宿主节点 : $MY_NODE_NAME 的实例不可用\"}}"  http://`eval echo $EMAIL_URI`/report/runTask?pushTime=1538983380293
	   		fi
	   		sleep 10
	 	done
}

start() {
	if [ "`status`" == "1"  ] ; then
		echo "app jetty is woring,do nothing!"
		exit 1
	fi
	
	# 服务健康探针和预警
	if [ "${LIVENESS_PROBE_STATUS}" == "1" ]; then
		health &
	fi
	
	$JAVA_CMD   $JAVA_OPTS $JAVA_JVM -Dloader.path="lib/*" -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF8 -jar   $BASE_HOME/$APP_JAR_LOCAL --spring.config.location=file:../config/ >> /data/irms-log/external-service/"$log_file_url"  2>&1
        echo "started $APP_JAR"
}

stop() {
	# 健康检测
    code=`curl -I -m 10 -o /dev/null -s -w %{http_code} localhost:6019/health`
	if [ "$code" -ne "200" ]; then
	    JAVA_PID=`ps -ef | grep java | grep irms-external-service | awk '{print $1}'`
  		sh /home/irms-external-service/bin/vjdump.sh $JAVA_PID
  		
  		# 邮件告警,pod重启
  		curl -i -X POST -H 'Content-Type:application/json' -H 'jobReqValidationKey:4c4d023b30a06979a4a85f3911e2385c' -d "{\"taskType\":\"sendEmailJob\",\"extParam\":{\"emails\":\"jeromeliu@can-dao.com,noahpan@can-dao.com,lionchen@can-dao.com\",\"title\":\"【$SERVER_ENV】-IRMS-POD-【$HOSTNAME】重启\",\"content\":\"容器实例 : $HOSTNAME,宿主节点 : $MY_NODE_NAME 的实例发生重启\"}}"  http://`eval echo $EMAIL_URI`/report/runTask?pushTime=1538983380293
  	else
  		# 邮件告警,pod重启
  		curl -i -X POST -H 'Content-Type:application/json' -H 'jobReqValidationKey:4c4d023b30a06979a4a85f3911e2385c' -d "{\"taskType\":\"sendEmailJob\",\"extParam\":{\"emails\":\"jeromeliu@can-dao.com,noahpan@can-dao.com,lionchen@can-dao.com\",\"title\":\"【$SERVER_ENV】-IRMS-POD-【$HOSTNAME】服务存活,发生重启\",\"content\":\"容器实例 : $HOSTNAME,宿主节点 : $MY_NODE_NAME 的实例发生重启\"}}"  http://`eval echo $EMAIL_URI`/report/runTask?pushTime=1538983380293
	fi
	curl -i -X PUT -H 'Content-type':'application/json' http://serviceregistrycenterha1.kube-irms.svc.cluster.local:6106/eureka/apps/EXTERNAL-SERVICE/$HOSTNAME:external-service:6019/status?value=OUT_OF_SERVICE
	sleep $SLEEP_TIME
	pid=` ps -ef |grep java | grep "$BASE_HOME/$APP_JAR_LOCAL" |awk '{print $1}'`
	if [ -n "$pid"  ] ; then
		kill $pid
	fi
	echo "stoped $APP_JAR"
}


case "$work" in
	"start" ) 
		start ;
		exit 1;
		;;
	"status")
		echo `status`;
		exit 1;
		;;
	"stop" )
		stop;
		exit 1;
		;;
	"restart" )
		stop 
		sleep 5
		start;
		exit 1
		;;
	* ) 
		echo "unknow command !"
		exit 1;
		;;
esac

