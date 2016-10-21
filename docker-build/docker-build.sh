#!/bin/bash -e

IB_HOME=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
source ${IB_HOME}/env.sh

# build docker container - NB: need to version this
#
docker build -t ${CONTAINER_NAME} ${IB_HOME}
