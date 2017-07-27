package sample.utils;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DataBean {
	private String stringField;
	private Integer integerField;
	private int pIntField;
	private Double doubleField;
	private double pDoubleField;
	private Long longField;
	private long pLongField;
	private Date dateField;
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public Integer getIntegerField() {
		return integerField;
	}

	public void setIntegerField(Integer integerField) {
		this.integerField = integerField;
	}

	public int getpIntField() {
		return pIntField;
	}

	public void setpIntField(int pIntField) {
		this.pIntField = pIntField;
	}

	public Double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(Double doubleField) {
		this.doubleField = doubleField;
	}

	public double getpDoubleField() {
		return pDoubleField;
	}

	public void setpDoubleField(double pDoubleField) {
		this.pDoubleField = pDoubleField;
	}

	public Long getLongField() {
		return longField;
	}

	public void setLongField(Long longField) {
		this.longField = longField;
	}

	public long getpLongField() {
		return pLongField;
	}

	public void setpLongField(long pLongField) {
		this.pLongField = pLongField;
	}

	public Date getDateField() {
		return dateField;
	}

	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}
	
	
}
