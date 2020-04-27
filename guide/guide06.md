# 6. 쿠버네티스 Ingress 세팅

## 6.1 Ingress yml파일 생성

### Ingress를 세팅하면 외부에서 웹서비스에 접근할 수 있다.

### ingress.yml

```yml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: ingress
spec:
  rules:
    - http:
        paths:
          - path: /*
            backend:
              serviceName: backend # backend 서비스와 연결.
              servicePort: 80
```

## 6.2 ingress.yml 적용

```
k apply -f ingress.yml
```

## 6.3 글로벌 IP 할당 확인

```
# 생성 후 1~2분 지나면 글로벌IP주소가 할당된다.
k get ingress

※ 몇분이 지나도 IP주소가 보이지않는다면, GCP에 접속하여 알림을 먼저 확인해봐야 한다. 에러가 났을가능성이 높다.
```

## 6.4 Ingress세팅 확인

IP가 할당됐어도 'GCP -> Kubernetes Engine -> 서비스 및 수신'을 확인하면 아직 작업이 진행일것이다.  
기다렸다가 완료가 되면 글로벌IP로 웹서비스가 잘 동작되는지 확인하면 된다.
