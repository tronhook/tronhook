#!/bin/bash

JAVA_OPTS=""

if [ ${DEBUG_APP} -eq 1 ]
then
	JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"
fi

if [ ! -z ${EXECUTE_BEFORE_RUN} ]
then

	echo "Executing ${EXECUTE_BEFORE_RUN} before run ..."	
	chmod u+x ${EXECUTE_BEFORE_RUN}	
	sh ${EXECUTE_BEFORE_RUN}
	
fi

echo "Conf file=> ${APP_CONF_FILE}"

if [ -z ${APP_CONF_FILE} ]
then

echo "Running: java ${JAVA_OPTS} -jar /app.jar ${APP_OPTS} ..."	
java ${JAVA_OPTS} -jar /app.jar ${APP_OPTS}
		
else	

echo "Running: java ${JAVA_OPTS} -jar -Dconfig.file=${APP_CONF_FILE} /app.jar ${APP_OPTS} ..."	
java ${JAVA_OPTS} -jar -Dconfig.file=${APP_CONF_FILE} /app.jar ${APP_OPTS}

fi



