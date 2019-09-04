#! /usr/bin/env sh

set -euo pipefail

TAG=$(git rev-parse HEAD)

echo $DOCKER_PASSWORD | docker login --username $DOCKER_USER --password-stdin
docker build -t rafaelgoncalvs/event-source-cqrs:latest .
docker tag rafaelgoncalvs/event-source-cqrs:latest rafaelgoncalvs/event-source-cqrs:$TAG
docker push rafaelgoncalvs/event-source-cqrs:latest
docker push rafaelgoncalvs/event-source-cqrs:$TAG
