FROM centos:6

RUN yum install -y wget curl

RUN cd /usr/local/src; \
  wget --no-cookies --no-check-certificate --header \
    "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-secure backup-cookie" \
    http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz ; \
  wget http://archive.apache.org/dist/hbase/0.98.12.1/hbase-0.98.12.1-hadoop2-bin.tar.gz;\
  cd /usr/local; \
  tar zxf src/hbase*.gz; \
  tar zxf src/jdk*.gz; \
  rm -f src/*.gz; \
  ln -s jdk1.8.0_131 java; \
  ln -s hbase-0.98.12.1-hadoop2 hbase; \
  mkdir -p /usr/local/hbase/data/hbase; \
  mkdir -p /usr/local/hbase/data/zookeeper

ADD hbase-site.xml  /usr/local/hbase/conf/hbase-site.xml

ADD run.hbase.sh  /var/opt/run.hbase.sh

RUN echo "export JAVA_HOME=/usr/local/java/" >> /usr/local/hbase/conf/hbase-env.sh

CMD ["/var/opt/run.hbase.sh"]

