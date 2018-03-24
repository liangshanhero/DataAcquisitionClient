package cn.edu.scau.cmi.wuweijie.batch.writer;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.edu.scau.cmi.wuweijie.entity.client.Syncdata;

/**
 * 此处明确设定了Excel表格数据格式，写入效率相对于使用反射可能更高
 * 
 * @author TESLA_CN
 *
 */
public abstract class FixedItemWriter extends SyncdataItemWriter {

	@Override
	public XSSFWorkbook writeWorkbook(XSSFWorkbook workbook, List<? extends Syncdata> items) {

		Log log = LogFactory.getLog(FixedItemWriter.class);

		XSSFSheet sheet = workbook.createSheet();

		XSSFRow headers = sheet.createRow(0);
		headers.createCell(0).setCellValue("iDate");
		headers.createCell(1).setCellValue("BuildName");
		headers.createCell(2).setCellValue("DeviceID");
		headers.createCell(3).setCellValue("ParaName");
		headers.createCell(4).setCellValue("iValue");
		headers.createCell(5).setCellValue("isIO");
		headers.createCell(6).setCellValue("Upload");
		headers.createCell(7).setCellValue("ID");

		for (int i = 0; i < items.size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);
			Syncdata item = items.get(i);
			if (item == null) continue;
			row.createCell(0).setCellValue(getIDateFormat().format(item.getIdate()));
			row.createCell(1).setCellValue(item.getBuildName());
			row.createCell(2).setCellValue(item.getDeviceId());
			row.createCell(3).setCellValue(item.getParaName());
			row.createCell(4).setCellValue(item.getIvalue());
			row.createCell(5).setCellValue(item.getIsIo() != null ? Long.toString(item.getIsIo()) : "");
			row.createCell(6).setCellValue(item.getUpload());
			row.createCell(7).setCellValue(item.getId());
//			log.info(String.format("{written: '%s', remain: %d", item, items.size() - i - 1));
		}

		return workbook;
	}

}
