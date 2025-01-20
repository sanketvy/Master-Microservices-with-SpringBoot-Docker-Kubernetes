# Master-Microservices-with-SpringBoot-Docker-Kubernetes
This repository contains the coursework done for the Microservices course. 

## OpenAPI Documentation 

Add dependency for springdoc. The documentation will be exposed on below path - `http://localhost:8081/swagger-ui/index.html`

## Reactive Gateway

`http://172.27.240.1:8051/actuator/gateway/routes` -> to view all the routes

## Prometheus Setup for Monitoring - 

Run docker command - 
docker run -p 9000:9090 -v .\devops\prometheus\prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

http://localhost:3000/ -> Grafana
http://localhost:9000/ -> Prometheus