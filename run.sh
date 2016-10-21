#!/bin/bash


#
echo "***** starting docker builder *****"

pushd docker-build

# load environment...
source env.sh

echo ">> building image $IMAGE_NAME ..."
./docker-build.sh

echo ">> starting container $CONTAINER_NAME..."
./docker-run.sh

if [[ ! $0 = './run.sh' ]]; then
  echo ">> enabling ${CONTAINER_NAME} macros..."
  source alias.sh
fi

echo ">> building and watching source..."
docker exec $CONTAINER_NAME boot build
docker logs -tf ${CONTAINER_NAME}

popd

