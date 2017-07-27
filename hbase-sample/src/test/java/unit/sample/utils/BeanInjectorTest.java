package sample.utils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanInjectorTest {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void test() throws Exception{
		//String metaDataString = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("meta/log-metadata.json").getPath()));

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ArrayNode metaDataJson = factory.objectNode().arrayNode();
		String[] fieldNames = {
				"stringField", 
				"integerField", 
				"pIntField", 
				"doubleField", 
				"pDoubleField",
				"longField", 
				"pLongField",
				"dateField"
		};
		
		for(int index = 0; index < fieldNames.length; index++){
			ObjectNode jsonNode = factory.objectNode();
			jsonNode.put("index", index);
			jsonNode.put("fieldName", fieldNames[index]);
			metaDataJson.add(jsonNode);
		}
		
		String jsonStr = metaDataJson.toString();
		log.debug(jsonStr);
		
		;
		String[] dataArray = {
				"test", 
				String.valueOf(Integer.MAX_VALUE),//"integerField", 
				String.valueOf(Integer.MAX_VALUE),//"pIntField", 
				String.valueOf(Double.MAX_VALUE),//"doubleField", 
				String.valueOf(Double.MAX_VALUE),//"pDoubleField",
				String.valueOf(Long.MAX_VALUE),//"longField", 
				String.valueOf(Long.MAX_VALUE),//"pLongField",
				"2017-01-02T03:04:05+09:00"
		};
		
		BeanInjector<DataBean> injector = new BeanInjector<DataBean>(DataBean.class, jsonStr);
		
		DataBean dataBean = injector.createDocument(StringUtils.join(dataArray, "\t"), "\t");
		log.debug("{}", dataBean);
		
		//simpledateformat 의 offset time type : +0900 
		dataArray[dataArray.length-1] = "2017-01-02T03:04:05+0900";
		dataBean = injector.createDocument(StringUtils.join(dataArray, "\t"), "\t");
		log.debug("{}", dataBean);
		
	}

	
	
	
	
	
}
