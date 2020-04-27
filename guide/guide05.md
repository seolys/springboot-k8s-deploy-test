# 5. 쿠버네티스 Nginx, SpringBoot 세팅

## 5.1 SpringBoot yml파일 생성

### Nginx(80포트)가 BackEnd를 포워딩해주도록 설정한다.

### backend.yml

```yml
apiVersion: v1
kind: Service
metadata:
  name: backend
  labels:
    app: backend
spec:
  selector:
    app: backend
  ports:
    - name: http
      port: 80
  type: NodePort # 필수

---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: backend
  labels:
    name: backend
spec:
  replicas: 2
  selector:
    matchLabels:
      app: backend
  template:
    metadata:
      labels:
        app: backend
    spec:
      containers:
        - name: nginx
          image: gihyodocker/nginx:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 80
          env:
            - name: WORKER_PROCESSES
              value: "2"
            - name: WORKER_CONNECTIONS
              value: "1024"
            - name: LOG_STD_OUT
              value: "true"
            - name: BACKEND_HOST
              value: "localhost:8080"
        - name: api
          image: outsiderys/springboot-deploy-test:0.0.2
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
          env:
            - name: MYSQL_HOST
              value: "mysql-master"
            - name: MYSQL_PORT
              value: "3306"
            - name: MYSQL_DATABASE
              value: "study"
            - name: MYSQL_USER
              value: "seolys"
            - name: MYSQL_PASSWORD
              value: "seolys!"
```

## 5.2 backend.yml 적용

```
k apply -f backend.yml
```
