# Playground

### test Docker

```
// 독커머신 연결 스크립트 확인
docker-machine rm default

docker-machine create -d "virtualbox" --virtualbox-memory "4096" default

C:\tools\repo\playground>docker-machine env --shell cmd
SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://192.168.99.100:2376
SET DOCKER_CERT_PATH=C:\Users\Naver\.docker\machine\machines\default
SET DOCKER_MACHINE_NAME=default
SET COMPOSE_CONVERT_WINDOWS_PATHS=true
REM Run this command to configure your shell:
REM     @FOR /f "tokens=*" %i IN ('docker-machine env') DO @%i

// windows에서 독커머신 연결
C:\tools\repo\playground>@FOR /f "tokens=*" %i IN ('docker-machine env') DO @%i

// 독커 centos6 이미지 실행
C:\tools\repo\playground>docker run -it --name c6 -p 9200:9200 -p 9300:9300 centos:6

// 테스트용 독커에 연결
C:\tools\repo\playground>docker exec -it c6 bash

// 설치
[root@4ca42bcefe21 src]# yum install -y wget
[root@4ca42bcefe21 src]# wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-secure
backup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz

[root@4ca42bcefe21 src]# ls
jdk-8u131-linux-x64.tar.gz
[root@4ca42bcefe21 src]# cd ..
[root@4ca42bcefe21 local]# tar zxf src/jdk-8u131-linux-x64.tar.gz
[root@4ca42bcefe21 local]# ls
bin  etc  games  include  jdk1.8.0_131  lib  lib64  libexec  sbin  share  src
[root@4ca42bcefe21 local]# ln -s jdk1.8.0_131/ java
[root@4ca42bcefe21 local]# ls
bin  etc  games  include  java  jdk1.8.0_131  lib  lib64  libexec  sbin  share  src
[root@4ca42bcefe21 local]#
```