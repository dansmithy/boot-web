#!/bin/bash -e

IB_HOME=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
source ${IB_HOME}/env.sh

echo "launching container '${CONTAINER_NAME}' from image '${IMAGE_NAME}'..."

if [ `docker ps -a | awk 'FNR>1 {print $NF}' | grep ^${CONTAINER_NAME}$` ]
then
  echo "container '${CONTAINER_NAME}' already running!"
  echo "shutting down container '${CONTAINER_NAME}'..."
  echo "stopped container: " `docker rm -f ${CONTAINER_NAME}`
fi


USER_UID=`id -u`
USER_GPID=`id -g`
echo "current user uid:gid > ${USER_UID}:${USER_GPID}"
echo "starting container '${CONTAINER_NAME}'..."
ID=$(docker run \
      -d \
      -u ${USER_UID}:${USER_GPID} \
      --name ${CONTAINER_NAME} \
      -v ${IB_HOME}/..:/opt \
      -v /opt/node_modules \
      -p 9090:9090 \
      -p 9091:9091 \
      ${IMAGE_NAME}
   )


if [ `docker ps -a | awk 'FNR>1 {print $NF}' | grep ^${CONTAINER_NAME}$` ]
then
  echo "container '${CONTAINER_NAME}' started! [id='$ID']"
fi