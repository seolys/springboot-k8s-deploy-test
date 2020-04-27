# SpringBoot Web Application을 Kubernetes를 활용하여 배포.

## 소개

웹서버, 백엔드, DB를 컨테이너화 하여 쿠버네티스에 배포하는것에 초점을 맞췄습니다.  
SpringBoot, Mysql, Nginx를 도커이미지로 만든 후 컨테이너로 실행하였고  
GCP에서 Kubernetes클러스터를 구성 후 배포 및 기능동작까지 흘러가는것을 기록하였습니다.

## 사용기술

- GCP(Google Cloud Platform)
- Docker
- Kubernetes
- SpringBoot(Maven)
- Mysql(Master-Slave구성)
- Nginx

## 순서
- [1. Google Kubernetes 환경설정(macOS)](./guide/guide01.md)
- [2. Mysql 이미지&컨테이너 생성](./guide/guide02.md)
- [3. SpringBoot 어플리케이션 생성](./guide/guide03.md)
- [4. 쿠버네티스 MySQL Master-Slave 세팅](./guide/guide04.md)
- [5. 쿠버네티스 Nginx, SpringBoot 세팅](./guide/guide05.md)
- [6. 쿠버네티스 Ingress 세팅](./guide/guide06.md)

※ 컨테이너 yaml파일 경로 https://github.com/seolys/springboot-k8s-deploy-test/tree/master/yaml


## 참고자료

- [도커/쿠버네티스를 활용한 컨테이너 개발 실전 입문](https://wikibook.co.kr/docker-kubernetes/)
- [백기선님 유튜브](https://www.youtube.com/watch?v=9tW0QSsrhwc&list=PLfI752FpVCS84hxOeCyI4SBPUwt4Itd0T)
- 김충섭님 강의
  - [T아카데미 유튜브 강의](https://www.youtube.com/watch?v=WxzWXqTNdlw&list=PL9mhQYIlKEhdTu31zyb_QelQMaqFGgASA)
  - [Github 강의자료](https://github.com/subicura/workshop-k8s-basic)
