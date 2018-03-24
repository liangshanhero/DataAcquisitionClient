package cn.edu.scau.cmi.wuweijie.batch.writer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.edu.scau.cmi.wuweijie.entity.client.Syncdata;

/**
 * 通过反射数据写入Excel，对于大量数据效率可能较低
 * @author TESLA_CN
 *
 */
public abstract class ReflectItemWriter extends SyncdataItemWriter {

	@Override
	public XSSFWorkbook writeWorkbook(XSSFWorkbook workbook, List<? extends Syncdata> items) {
		XSSFSheet sheet = workbook.createSheet();
		
		//通过反射取得 Field 的名字并设置为列名，即设置表格第一行
		//根据官方文档，此处反射得到的数据域顺序没有特定顺序
		Field[] fields = Syncdata.class.getDeclaredFields();
		XSSFRow headers = sheet.createRow(0);
		for (int i = 0; i < fields.length; i++) {
			headers.createCell(i).setCellValue(fields[i].getName());
		}
		
		//反射读取实例 Field 的数据写入Excel中
		for (int i = 0; i < items.size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);
			Syncdata data = items.get(i);
			for (int j = 0; j < fields.length; j++) {
				String fieldName = fields[j].getName();
				String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Method getter = Syncdata.class.getDeclaredMethod(getterName);
					Object result = getter.invoke(data, new Object[]{});
					row.createCell(j).setCellValue(result != null ? result.toString() : "");
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
}
