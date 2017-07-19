FROM centos:6

RUN yum install -y wget 

RUN cd /usr/local/src; \
  wget --no-cookies --no-check-certificate --header \
    "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-secure backup-cookie" \
    http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz ; \
  wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.4.3.tar.gz ; \
  cd /usr/local; \
  tar zxf src/elasticsearch*.gz; \
  tar zxf src/jdk*.gz; \
  rm -f src/*.gz;

CMD ["/bin/bash","/usr/sbin/init"]