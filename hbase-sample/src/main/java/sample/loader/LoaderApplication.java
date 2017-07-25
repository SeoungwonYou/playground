package sample.loader;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sample.config.AppConfig;

@SpringBootApplication(scanBasePackages={"sample.config"})
public class LoaderApplication implements CommandLineRunner {

	static private Logger log = LoggerFactory.getLogger(LoaderApplication.class);
	
	@Autowired
	private AppConfig appConfig;
	
	public static void main(String[] args) throws Exception {
		
		try {
			SpringApplication.run(LoaderApplication.class, args);	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void run(String... args) throws Exception {
		//createTable("testTbl2");
		//addRow();
		scan();
	}
	
	
	private void createTable(String tableName) throws MasterNotRunningException, ZooKeeperConnectionException, IOException{
		HBaseAdmin admin = new HBaseAdmin(appConfig.getHBaseConfig());
		HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
		
        desc.addFamily(new HColumnDescriptor("cfm1".getBytes()));
        desc.addFamily(new HColumnDescriptor("cfm2".getBytes()));
        
		admin.createTable(desc);
		admin.close();
		//HConnection connection = HConnectionManager.createConnection(conf);
		//connection.getAdmin(serverName);
	}
	
	private void addRow() throws IOException{
		Configuration config = appConfig.getHBaseConfig();
		HConnection conn =  HConnectionManager.createConnection(config);
		
		
		String rowKey = "rowKey";
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes("cfm1"), Bytes.toBytes("qual1"), Bytes.toBytes("v1"));
		put.add(Bytes.toBytes("cfm1"), Bytes.toBytes("qual2"), Bytes.toBytes("v2"));
		
		put.add(Bytes.toBytes("cfm2"), Bytes.toBytes("qual1"), Bytes.toBytes("v3"));
		put.add(Bytes.toBytes("cfm2"), Bytes.toBytes("qual2"), Bytes.toBytes("v4"));
		
		HTableInterface table = conn.getTable("testTbl".getBytes());
		table.put(put);
		
		table.close();
		
	}
	
	
	private void scan() throws Exception{
		
		Configuration config = appConfig.getHBaseConfig();
		HConnection conn =  HConnectionManager.createConnection(config);
		HTableInterface table = conn.getTable("testTbl".getBytes());
		
		Scan scan = new Scan();
		ResultScanner scanner = table.getScanner(scan);
		for(Result result: scanner){
			while(result.advance()){
				log.debug("cell: {}", result.current());
			}
		}
		
		String rowKey = "rowKey";
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes("cfm1"), Bytes.toBytes("qual1"));
		Result result = table.get(get);
	    log.debug("Get result: {}, value:{}" ,result.advance(), result);
	    
	    byte[] val = result.getValue(Bytes.toBytes("cfm1"),  Bytes.toBytes("qual1"));
	    System.out.println("Value only: " + Bytes.toString(val));
	}
}



