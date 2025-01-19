# DevOps Tools Course : Docker, Kubernetes & Helm
This documentation contains all the commands and revision crash course required for Docker, Kubernetes and Helm charts.

## Docker
Docker is containerization platform which facilitates packaging, shipping and deployments of application, in an isolated environment.
Docker containers are lighweight in nature and startup time is fast, which facilitates scalability and higher performance as compared to Virtual machines.

### Docker Main Components -
1. `DockerFile` -> Its a simple file which contains the instructions on how to build the docker image.
2. `DockerImage` -> Its Blueprint of the container containing all the information inlcuding dependencies and application code.
3. `DockerContainer` -> Running instance of docker image is called as docker container.
4. `DockerRegistry` -> It's a centralized platform, where images can be stored and pulled from.
4. `DockerRepository` -> Storage of one image type with all its versions, is called as repository

### Docker Commands - 

1. `docker version` -> To know which version of docker is installed.

2. `docker build -t <image_name>:<image_version> .` -> To build docker image from the docker file.

3. `docker run --name <container_name> -p <host_port>:<image_port> -d <image_name>:<image_version>` -> To run the docker image i.e., to start the docker container. `--rm` can be added to same command, after which if a container is stopped, it is automatically deleted from process.

4. `docker image ls` -> To list all images.

5. `docker volume ls` -> To list all volumes.

6. `docker stop <container_name/container_id>` -> To stop a running docker container.

7. `docker image rm <image_name>:<image_tag>` -> To stop a running docker container.

8. `docker push <username>/<image_name>:<tag_version>` -> To push a docker image from local to repository

9. `docker pull <username>/<image_name>:<tag_version>` -> To pull a docker image from repository to local.

10. `docker run -it -v <myvolume>:</myapp> <image_name>:<tag_version>` -> To start a docker container with a persistance volume attached by using `-v` and `volume_name:/path_to_working_dir>`

11. `docker volume inspect <myvolume>` -> To list metadata about the volume.

12. `docker exec -it <container_name> /bin/bash` -> To login to container shell.

13. `docker tag <old_name> <new_name>` -> To rename an image

### DockerFile Commands - 

1. `FROM baseImage` -> This command is used to give a most minimal basic environment required for the application to run.

2. `WORKDIR /location` -> This command is used to create a seperate new directory where all the application code can be copied, and the location is set as working directiry

3. `COPY /from_location /to_location` -> This command is used to copy the application code from current wokring directory to the WORKDIR in the image.

4. `RUN command` -> This command is used to run any command, required during the creation of the image such as resolving third party external dependency libraries.

5. `CMD ["first", "second"] -> This command is used to run the command which starts the application only when a container is created from image. This command will not execute when creating the image but while running the container.

6. `EXPOSE port_numer` -> This command tells the container that the application runs on which port.

### Docker Volume Concept - 
In Docker, we can mount persistance to a docker container called as volumes, so that if any read activtity is doen by conainers on any of the static text files or any file can be retained even on the container deletion as containers are disposable.

There are two concepts of docker volumes, where we can mount a whole directory or mount a single file bind to from local to remote container.

This concepts is also very useful in case of development where we are doing changes to a file and so by bind mounting it we dont have to generate docker image again and again.

We can use `-v` as well as `--mount`, both does same thing. mount option is more verbose.

1. `docker volume ls` -> List Docker Volumes

2. `docker volume inspect <volume_name>` -> Print metadata about the volumes

3. `docker run -v <volume_name>:/path_to_be_persisted <docker_image>` -> This is used to create a seperate volume and attach it to the containers so all behave same.

4. `docker run -v /abs/path/of/host:/container/workdir/path <docker_image>` -> This is called as bind mount, which is used to bind only one file from local to the container path so any changes in local will reflect in container.

5. `docker volume create --driver local \
  --opt type=nfs \
  --opt o=addr=<nfs-server-ip>,rw \
  --opt device=:/mnt/shared_volume \
  shared_volume` -> To Create NFS network mounted volumes

### Docker Networking Concept - 
In Docker, containers are empherical i.e., when one container dies and another container comes up, the ip address is not static. So, for two container communication, ip address hardcoding is not effective way as the ip address is not static.

With help of docker, one container can point to another container with just its CONTAINER_NAME, i.e., name given to the container while running the container. 

In Docker networking, container name is mapped as the hostname of the running docker container. So, ip address is not required just the container name works as host name.

If container wants to talk to other container, then the hostname should be used same as docker container name.
If contaienr wants to talk to host machine, then the hostname should be used as `host.docker.internal`.

There can be two types of docker networks created -
1. Host Network (no seperate ip address, binded to host ip address)
2. Bridge Network (all container part of same network subnet, can communicate with each other)
3. Custom Bridge Network (Create custom networks to isolate containers from each other)


1. `docker network ls` -> to list all docker networks

2. `docker network create <network_name>` -> to create a docker network of Bridge type.

3. `docker run --network <network_name> <image_name>` -> to assign a docker network to a running container.


## Kubernetes
It is container orchestration tool, used for automatic deployments, management, rollouts, load balancing, high avaialability, fault tolerance and scaling of containerized applications. It's major components are -

Pods are the smallest unit in kubernetes, which are wrapper over docker container. Deployments are the wrapper over pods, pods cannot be run in isolation and can be only ran via deployments. To expose deployments we need to create a service wrapper.

### Master/Control Plane
1. API Server -> interface to interact with the cluster and fire commands, eg kubectl, kubernetes dashboard.
2. Control Manager -> responsible for managing the state of the cluster
3. Scheduler -> assign nodes to pods
3. ETCD Database -> Maintains the configuration of current state of cluster

### Slave/Worker Nodes
1. kubelet -> kubernetes agents which takes care of containers running in pods
2. kube-proxy -> Maintains networking for pods
3. container-runtime -> Tool responsible for running containers.

### Kubernetes Commads -

1. `kubectl create deployment <deployment_name> --image=<image_name> --replicas=3 --port=5701` -> to run a sample deployment.

2. `kubectl get deployments` -> to list all the deployments

3. `kubectl get pods` -> to list all the running pods

4. `kubectl logs <pod_name>` -> To check logs of pods

5. `kubectl describe pods <pod_name>` -> To check logs of pods

6. `kubectl delete deployment <deployment_name>` -> to list all the deployments

7. `kubectl apply -f <deployment.yml>` -> to list all the running pods

8. `kubectl delete -f <deployment.yml>` -> to list all the running pods

### Kubernetes Service
Services needs to be created to wrap the deployments and expose the running ports to the external networks.

Various service types are -

1. LoadBalancer -> To load balance between multiple deployemnts
2. NodePort -> External service, exposes the service on a port on the node IP addresses
3. ClusterIP -> Internal service, only can be communicated via internal pods

Commands -

1. `kubectl expose deployment <deployment_name> --port=<port_number> --type=<service_type>` -> to create a service

2. `minikube service <service_name>` -> to expose the service on host network

3. `kubectl get services` -> to list all the running pods

### Kubernetes Rollout Deployments -
In kubernetes, if we want to upgrade or change the image version for any pods, we can do that without any downtime. So basically applications can be updated and upgraded without any production downtime. 

Internally kubernetes keep running the existing pod, until a new pod is created and its up and running. This is how the rollout deployments with zero downtime happens in kubernetes.

`kubectl set image deployment <deployment_name> <old_image_name>=<new_image_name:tag_version>` -> This is how we do rollout updates

`kubectl rollout status deployment <deployment_name>` -> To know status of rollouts.

`kubectl rollout undo deployment <deployment_name>` -> to undo the previous rollout

### Kubernetes Deployment -

`kubectl scale deployment <deployment_name> --replicas=4` -> to dynamically scale up or scale down the deployment pods. 

## Helm
It is package manager for kubernetes. It is just like pakckage managers in linux where if we have to install any application all the dependent applications/libs are also installed. Similarly in kubernetes when we have large application with 100s of deployments, service, configmaps files, we can pack it into a package and install all with a simple command.
This package is also called as `Helm Chart`.

Helm also solves a intresting problem where we required to change the deployments files by changing the replicas and all, but helm creates template where we dont have to change the files as per env, and the values which changes with env are read from another file values.yml.

### Helm Charts
Helm chart is a package containing all the resource definations necessary to run an application inside K8s cluster. 

### Helm Release/revision
A release is an instance of running chart. One chart can be installed many times into same cluster and each time it is installed a new release is created.

### Commands 

1. `helm repo list` -> to list all the helm repositories

2. `helm repo add <name> <url>` -> to add helm repositories

3. `helm repo remove <name>` -> to delete helm repositories

4. `helm create helloworld` -> to create a sample helloworld helm chart, which is basic nginx template.

5. `helm install <release_name_custom> <chart_name>` -> to install a helm chart.

6. `helm uninstall <release_name_custom>` -> to uninstall helm chart.

7. `helm upgrade <release_name_custom> <chart_name>` -> to uninstall helm chart.

8. `helm rollback <release_name_custom> <revision_number>` -> to uninstall helm chart.

9. `helm template <chart_name>` -> to display all the underlying template of chart with actual values.

10. `helm lint <chart_name>` -> to display any errors with the configurations.