package cn.edu.scau.cmi.wuweijie.utils.led;

import java.sql.Timestamp;
import java.util.Date;

import cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter;
import cn.edu.scau.cmi.wuweijie.entity.server.Ledmeterdata;

/**
 * LED 数据工厂
 * @author TESLA_CN
 *
 */
public abstract class LedDataFactory {

	/**
	 * 数据浮动率，范围 0~1
	 */
	private double fluctuation;
	
	/**
	 * 
	 */
	private int intervalSecond;
	
	/**
	 * Ledmeterdata 数据生成器
	 * 在 applicationContext.xml 中配置实现类注入
	 */
	private LedValueGenerator ledValueGenerator;
	
	public Ledmeterdata generateData(Ledmeter ledmeter) {
		Ledmeterdata data = new Ledmeterdata();
		Double value = ledValueGenerator.generateValue(ledmeter, getFluctuation(), getIntervalSecond());
		data.setValue(value);
		data.setLedmeter(ledmeter);
		
		Timestamp timestamp = Timestamp.from(getDate().toInstant());
		data.setTime(timestamp);
		return data;
	}
	
	/**
	 * 由 Spring 实现
	 * @return
	 */
	public abstract Date getDate();

	public double getFluctuation() {
		return fluctuation;
	}

	public void setFluctuation(double fluctuation) {
		this.fluctuation = fluctuation;
	}

	public int getIntervalSecond() {
		return intervalSecond;
	}

	public void setIntervalSecond(int intervalSecond) {
		this.intervalSecond = intervalSecond;
	}

	public LedValueGenerator getLedValueGenerator() {
		return ledValueGenerator;
	}

	public void setLedValueGenerator(LedValueGenerator ledValueGenerator) {
		this.ledValueGenerator = ledValueGenerator;
	}
	
}
