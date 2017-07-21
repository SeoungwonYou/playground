#!/bin/sh

hname=$(hostname)

exec /usr/local/hbase/bin/start-hbase.sh & sleep 3 | tee /usr/local/hbase/logs/hbase--master-$hname.log