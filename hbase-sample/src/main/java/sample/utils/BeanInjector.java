package sample.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanInjector<T> {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	private JsonNode metaDataJsonNode;
	private Class<T> clazz;
	
	public BeanInjector(Class<T> clazz, String metaDataJsonString){
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			metaDataJsonNode = mapper.readTree(metaDataJsonString);
			this.clazz = clazz;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public T createDocument( String dataString, String splite) throws Exception{
		
		T doc = clazz.newInstance();
		String [] values = dataString.split(splite); 
		
		metaDataJsonNode.forEach(jsonNode ->{
			log.debug(jsonNode.toString());
			int index = jsonNode.findValue("index").getIntValue();
			String fieldName = jsonNode.findValue("fieldName").getTextValue();
			log.debug("json -> index: {}, fieldName: {}", index, fieldName);
			injection(doc, values[index], fieldName);
		});
		
		return doc;
	}
	
	private void injection(T doc, String value,  String fieldName){
		
		try {
			
			Field field = doc.getClass().getDeclaredField(fieldName);
			Class<?> cls = field.getType();
			
			Object valueObj = null;
			if(cls.isAssignableFrom(Double.TYPE) || cls == Double.class){
				valueObj = Double.valueOf(value);
			}else if(cls.isAssignableFrom(Long.TYPE) || cls == Long.class){
				valueObj = Long.valueOf(value);
			}else if(cls.isAssignableFrom(Integer.TYPE) || cls == Integer.class){
				valueObj = Integer.valueOf(value);
			}else if(cls.isAssignableFrom(String.class)){
				valueObj = value;
			}else if(cls.isAssignableFrom(Date.class)){
				int isoOffsetSeparatorPosition = value.length()- 3;
				if(":".equals(value.substring(isoOffsetSeparatorPosition, isoOffsetSeparatorPosition + 1))){
					valueObj = Date.from(OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant());
				}else{
					valueObj = (Date)simpleformat.parse(value);
				}
			}else{
				log.warn("data type missmatch .param -> value: {}, fieldName: {}", value, fieldName);
			}
			
			field.setAccessible(true);
			field.set(doc, valueObj);
			field.setAccessible(false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("injection error. param -> value: {}, fieldName: {}", value, fieldName);
			log.error(e.getMessage(), e);
		}
		
		
	}
	
}
