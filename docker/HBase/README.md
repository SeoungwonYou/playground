

```
// docker image build
docker build -t dev-hbase .

// image 확인
docker images


docker run -it -d -p 60010:60010 --name hbase dev-hbase bash

```