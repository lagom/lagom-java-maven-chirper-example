# Publishing

## Overview

This file contains notes on publishing resources that are referenced to in published guides. The intended
audience is a maintainer of Chirper with write access to Lightbend's Bintray Docker Registry.

## Targets

### Marathon

The provided configuration for DC/OS and Marathon uses publicly available Chirper images. Use the commands
below to publish new versions. You'll need to ensure that you have authenticated to the registry using
`docker login`.

Be sure to update the `VERSION` and `REGISTRY` if necessary, plus make any adjustments to `deploy/resources/chirper.json`.

```bash
export VERSION=1.2.1
export REGISTRY=lightbend-docker-registry.bintray.io/chirper-marathon

sbt "-DbuildVersion=$VERSION" -DbuildTarget=marathon clean docker:publishLocal

docker tag "chirper-marathon/friend-impl:$VERSION" "$REGISTRY/friend-impl:$VERSION"
docker tag "chirper-marathon/activity-stream-impl:$VERSION" "$REGISTRY/activity-stream-impl:$VERSION"
docker tag "chirper-marathon/front-end:$VERSION" "$REGISTRY/front-end:$VERSION"
docker tag "chirper-marathon/chirp-impl:$VERSION" "$REGISTRY/chirp-impl:$VERSION"

docker push "$REGISTRY/friend-impl:$VERSION"
docker push "$REGISTRY/activity-stream-impl:$VERSION"
docker push "$REGISTRY/front-end:$VERSION"
docker push "$REGISTRY/chirp-impl:$VERSION"
```
