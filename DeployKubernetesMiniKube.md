# Deploying Chirper to Kubernetes Minikube

This page describes the steps required to deploy Chriper to your local Minikube installation. For a more detailed 
guide and explanation of this process, please reference 
[Deploying Lagom Microservices on Kubernetes](https://developer.lightbend.com/guides/k8s-microservices/).


## Prerequisites

* JDK8+
* [Maven](https://maven.apache.org/) or [sbt](http://www.scala-sbt.org/)
* [Docker](https://www.docker.com/)
* [Minikube](https://kubernetes.io/docs/getting-started-guides/minikube/)

## Deployment

This repository ships with an install script that can be used to deploy the project to your local Minikube. Once
you've installed all of the prerequisites, launch the script below. Once completed, it will print a summary and
provide you with URLs that you can use to access Chirper in your browser.

For more information on how Chirper on Kubernetes works, please reference
[Deploying Lagom Microservices on Kubernetes](https://developer.lightbend.com/guides/k8s-microservices/).

##### Example Execution
```bash
deploy/kubernetes/scripts/install --minikube --new-minikube --all
```

```
****************************
***  Summary             ***
****************************
Registry:          N/A
Minikube:          New
Configure TLS:     Yes
Deploy Cassandra:  Yes
Build Chirper:     Yes
Upload Chirper:    No
Delete Chirper:    No
Deploy Chirper:    Yes
Deploy nginx:      Yes

Note: You must have kubectl setup to point to your Kubernetes cluster, and be logged into your Docker registry if applicable.

Press anything to continue, or CTRL-C to exit

...

NAME                                          READY     STATUS    RESTARTS   AGE
po/activityservice-0                          1/1       Running   0          42s
po/cassandra-0                                1/1       Running   0          3m
po/chirpservice-0                             1/1       Running   0          42s
po/friendservice-0                            1/1       Running   0          42s
po/nginx-default-backend-1866436208-wkhhd     1/1       Running   0          21s
po/nginx-ingress-controller-667491271-qkj31   1/1       Running   0          21s
po/web-0                                      1/1       Running   0          41s

NAME                                CLUSTER-IP   EXTERNAL-IP   PORT(S)                      AGE
svc/activityservice                 None         <none>        9000/TCP                     43s
svc/activityservice-akka-remoting   10.0.0.119   <none>        2551/TCP                     43s
svc/cassandra                       10.0.0.67    <none>        9042/TCP                     4m
svc/chirpservice                    None         <none>        9000/TCP                     42s
svc/chirpservice-akka-remoting      10.0.0.184   <none>        2551/TCP                     42s
svc/friendservice                   None         <none>        9000/TCP                     42s
svc/friendservice-akka-remoting     10.0.0.44    <none>        2551/TCP                     42s
svc/kubernetes                      10.0.0.1     <none>        443/TCP                      4m
svc/nginx-default-backend           10.0.0.185   <none>        80/TCP                       21s
svc/nginx-ingress                   10.0.0.161   <pending>     80:30458/TCP,443:30617/TCP   21s
svc/web                             10.0.0.165   <none>        9000/TCP                     42s

NAME                           DESIRED   CURRENT   AGE
statefulsets/activityservice   1         1         43s
statefulsets/cassandra         1         1         4m
statefulsets/chirpservice      1         1         42s
statefulsets/friendservice     1         1         42s
statefulsets/web               1         1         41s

NAME                              DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
deploy/nginx-default-backend      1         1         1            1           21s
deploy/nginx-ingress-controller   1         1         1            1           21s

NAME                                    DESIRED   CURRENT   READY     AGE
rs/nginx-default-backend-1866436208     1         1         1         21s
rs/nginx-ingress-controller-667491271   1         1         1         21s


Chirper UI (HTTP): http://192.168.99.100:30458
Chirper UI (HTTPS): https://192.168.99.100:30617
Kubernetes Dashboard: http://192.168.99.100:30000
```
