package cn.edu.scau.cmi.wuweijie.utils.led;

import cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter;

public interface LedValueGenerator {

	public Double generateValue(Ledmeter ledmeter, double fluctuation, int intervalSecond);
}
