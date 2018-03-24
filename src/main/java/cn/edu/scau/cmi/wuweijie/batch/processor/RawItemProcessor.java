package cn.edu.scau.cmi.wuweijie.batch.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import cn.edu.scau.cmi.wuweijie.entity.client.Syncdata;

/**
 * 批处理数据处理器
 * 
 * @author TESLA_CN
 *
 */
public class RawItemProcessor implements ItemProcessor<Syncdata, Syncdata> {

	@Override
	public Syncdata process(Syncdata item) throws Exception {
		Log log = LogFactory.getLog(RawItemProcessor.class);
		return item;
	}

}
