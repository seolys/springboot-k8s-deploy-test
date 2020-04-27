# 1. Google Kubernetes 환경설정(macOS)

## 1.1 GCP 프로젝트생성

- [Google Cloud Platform](https://console.cloud.google.com/) 접속후에 왼쪽상단 Platform 글자 오른쪽 클릭(프로젝트 선택화면)
- 새 프로젝트 선택 후 프로젝트 이름 입력.
- 고유값으로 프로젝트ID가 생성된다.

## 1.2 구글 클라우드 SDK(gcloud) 설치

```
# 1. 터미널 실행 후 구글 클라우드 설치
gcloud components update

# 2. SDK로 조작할 프로젝트 지정
gcloud config set project [프로젝트ID]

# 3. GCP 지역(region) 지정
gcloud config set compute/zone asia-northeast1-a

# 4. 쿠버네티스 클러스터 생성
gcloud container clusters create [클러스터명] --machine-type=n1-standard-1 --num-nodes=3

※ 클러스터 생성중 아래 오류가 발생했을때, 로그에 포함된 링크로 가서 사용설정하면 된다.
ERROR: (gcloud.container.clusters.create) ResponseError:
code=403, message=Kubernetes Engine API is not enabled for this project.
Please ensure it is enabled in Google Cloud Console and try again:
visit https://console.cloud.google.com/apis/api/container.googleapis.com/overview?project=[프로젝트] to do so.

# 5. 인증정보 설정
gcloud container clusters get-credentials [클러스터명]

# 6. Alias 설정(편의를 위해 kubectl을 k로 줄여 사용한다)
alias k=kubectl

# 7. Kubernetes Resources들이 잘 조회되는지 확인한다.
k get all
k get nodes
```
