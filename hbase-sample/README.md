#### Client configuration (windows)

1. `files/winutils.exe` file을 `%HADOOP_HOME%\bin` 폴더에 복사   
2. 환경변수 `HADOOP_HOME`을 설정하거나 실행시 `hadoop.home.dir` 자바 옵션을 지정해서 실행    
 ```
 -Dhadoop.home.dir=C:\tools\hadoop-2.7.1
 ```
 
#### Remote HBase Server configuration
외부에서 RPC 접속이 가능하도록 `/etc/host` 파일에 `127.0.0.1` 아닌 서버 ip를 지정합니다.   
`/etc/host`의 일반적인 설정    
```
127.0.0.1    vm-dev  localhost 
``` 
    
서버 IP가 `192.168.59.10`라고 가정했을 경우 아래와 같이 수정   
```
192.168.59.10   vm-dev  localhost
```

    
