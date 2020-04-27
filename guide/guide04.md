# 4. 쿠버네티스 MySQL Master-Slave 세팅

## 4.1 MySQL 스토리지 생성

### 4.1.1 storage-class-ssd.yml 생성

```yml
kind: StorageClass
apiVersion: storage.k8s.io/v1
metadata:
  name: ssd
  annotations:
    storageclass.kubernetes.io/is-default-class: "false"
  labels:
    kubernetes.io/cluster-service: "true"
provisioner: kubernetes.io/gce-pd
parameters:
  type: pd-ssd
```

### 4.1.2 storage-class-ssd.yml 적용

```
k apply -f storage-class-ssd.yml
```

## 4.2 MySQL Master 설정

### mysql-master.yml 생성

```yml
apiVersion: v1
kind: Service
metadata:
  name: mysql-master
  labels:
    app: mysql-master
spec:
  ports:
    - port: 3306
      name: mysql
  clusterIP: None
  selector:
    app: mysql-master

---
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql-master
  labels:
    app: mysql-master
spec:
  serviceName: "mysql-master"
  selector:
    matchLabels:
      app: mysql-master
  replicas: 1
  template:
    metadata:
      labels:
        app: mysql-master
    spec:
      terminationGracePeriodSeconds: 60
      containers:
        - name: mysql
          image: [도커ID]/mysql:[태그]
          imagePullPolicy: Always
          args:
            - "--ignore-db-dir=lost+found"
          ports:
            - containerPort: 3306
          env:
            - name: MYSQL_ROOT_PASSWORD
              value: "root!"
            - name: MYSQL_DATABASE
              value: "study"
            - name: MYSQL_USER
              value: "user"
            - name: MYSQL_PASSWORD
              value: "password!"
            - name: MYSQL_MASTER
              value: "true"
          volumeMounts:
            - name: mysql-data
              mountPath: /var/lib/mysql
  volumeClaimTemplates:
    - metadata:
        name: mysql-data
      spec:
        accessModes: ["ReadWriteOnce"]
        storageClassName: ssd
        resources:
          requests:
            storage: 4Gi
```

### 4.2.2 mysql-master.yml 적용

```
k apply -f mysql-master.yml
```

## 4.3 MySQL Slave설정

### mysql-slave.yml

```yml
apiVersion: v1
kind: Service
metadata:
  name: mysql-slave
  labels:
    app: mysql-slave
spec:
  ports:
    - port: 3306
      name: mysql
  clusterIP: None
  selector:
    app: mysql-slave

---
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql-slave
  labels:
    app: mysql-slave
spec:
  serviceName: "mysql-slave"
  selector:
    matchLabels:
      app: mysql-slave
  replicas: 2
  updateStrategy:
    type: OnDelete
  template:
    metadata:
      labels:
        app: mysql-slave
    spec:
      terminationGracePeriodSeconds: 60
      containers:
        - name: mysql
          image: [도커ID]/mysql:[태그]
          imagePullPolicy: Always
          args:
            - "--ignore-db-dir=lost+found"
          ports:
            - containerPort: 3306
          env:
            - name: MYSQL_MASTER_HOST
              value: "mysql-master"
            - name: MYSQL_ROOT_PASSWORD
              value: "root!"
            - name: MYSQL_DATABASE
              value: "study"
            - name: MYSQL_USER
              value: "user"
            - name: MYSQL_PASSWORD
              value: "password!"
            - name: MYSQL_REPL_USER
              value: "repl"
            - name: MYSQL_REPL_USER
              value: "user"
          volumeMounts:
            - name: mysql-data
              mountPath: /var/lib/mysql
  volumeClaimTemplates:
    - metadata:
        name: mysql-data
      spec:
        accessModes: ["ReadWriteOnce"]
        storageClassName: ssd
        resources:
          requests:
            storage: 4Gi

```

### 4.3.2 mysql-slave.yml 적용

```
k apply -f mysql-slave.yml
```

### ※ 더 공부해야할 사항

- sercet 적용하여 host,user,password등 암호화 진행
