package sample.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class AppConfig {

	@Autowired
	private HbaseConfig hbaseConfig;
	
	@Bean
	public org.apache.hadoop.conf.Configuration getHBaseConfig(){
		org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
		System.out.println(hbaseConfig.getQuorum());
		conf.set("hbase.zookeeper.quorum", hbaseConfig.getQuorum());
		conf.set("hbase.zookeeper.property.clientPort", hbaseConfig.getPort());
		
		return conf;
	}
	
	@Configuration
	@EnableConfigurationProperties
	@ConfigurationProperties(prefix="hbase.zookeeper")
	public class HbaseConfig{
		private String quorum;
		private String port;
		
		public String getQuorum() {
			return quorum;
		}
		public void setQuorum(String quorum) {
			this.quorum = quorum;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		
		
	}
}
