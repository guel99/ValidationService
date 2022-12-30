#!/usr/bin/env bash

# first, we compile the policy and signature servers
cd ../
mvn clean install

cd policyService-rest
yes | cp -rf target/*.jar ../docker/policyService

cd ../signatureValidator-rest
yes | cp -rf target/*.jar ../docker/signatureValidator

while getopts 'r' flag
do
    case "${flag}" in
        r) rebuild_images=true;;
    esac
done

# if selected -r option, the actual images are deleted
if [ "${rebuild_images}" = true ] ; then
    echo ">> [LOCALFULLDEPLOY.SH] Deleting the older docker images"
    docker image rm policy-service
    docker image rm signature-validator
    docker image rm val-service-web-form
fi 

# then, we start creating the docker images of back end services. 
# We try to build the web-form one.
cd ../docker
docker build -t policy-service policyService/
echo ">> [LOCALFULLDEPLOY.SH] Policy service image done"
docker build -t signature-validator signatureValidator/
echo ">> [LOCALFULLDEPLOY.SH] Signature service image done"
docker build -t val-service-web-form ../../ValServiceWebForm/

# finally we call the docker-compose
docker-compose up -d
