package cn.edu.scau.cmi.wuweijie.batch.writer;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemWriter;

import cn.edu.scau.cmi.wuweijie.entity.client.Syncdata;

/**
 * 为 Syncdata 所准备的批处理 Writer，将 批处理的相关数据 写入 xlsx 文件（Office2007或更高版本） <br>
 * NOTE: 这是一个抽象类，具体如何读取数据并写入 xlsx 的实例需要具体实现
 * 
 * @author TESLA_CN
 *
 */
public abstract class SyncdataItemWriter implements ItemWriter<Syncdata> {

	private SimpleDateFormat fileNameDateFormat;

	private SimpleDateFormat iDateFormat;

	private String dataCompany;

	private String dataProject;

	private String dataType;

	/**
	 * xlsx 文件保存的具体目录
	 */
	private String exportDir;

	/**
	 * 用于记录已处理写入的数据的文件
	 */
	private String batchRecordFile;

	@Override
	public void write(List<? extends Syncdata> items) throws Exception {
		XSSFWorkbook workbook = writeWorkbook(getWorkbook(), items);

		// 文件命名：公司名称_项目名称_数据类型_格式化的时间戳(系统当前时间).xlsx
		// 样例：天缔能源公司_华农热水项目_热水数据_20170930224130.xlsx
		String outputFileName = String.format("%s_%s_%s_%s.xlsx", getDataCompany(), getDataProject(), getDataType(),
				getFileNameDateFormat().format(getDate()));
		
		// 将 XSSFWorkbook 实例写入到文件
		try (FileOutputStream out = new FileOutputStream(getExportDir() + outputFileName)) {
			workbook.write(out);
		}
		
		// 已处理的最后一条数据Id，记录作为下次调度批处理任务的参数
		int latestId = items.get(items.size() - 1).getId();
		for (Syncdata item : items) {
			latestId = Integer.max(latestId, item.getId());
		}
		try (FileOutputStream out = new FileOutputStream(getBatchRecordFile())) {
			Properties props = new Properties();
			props.setProperty("syncdata.lastId", Integer.toString(latestId));
			props.store(out, "");
		}
	}

	/**
	 * 数据读取并写入 <br>
	 * NOTE: 需要具体实现
	 * 
	 * @param workbook
	 *            被写入的 xlsx 实例
	 * @param items
	 *            需要处理并写入 xlsx 的数据
	 * @return
	 */
	public abstract XSSFWorkbook writeWorkbook(XSSFWorkbook workbook, List<? extends Syncdata> items);

	/**
	 * 本抽象方法由 Spring 框架实现，无需自行实现
	 * 
	 * @return 一个全新的 XSSFWorkbook 实例
	 */
	public abstract XSSFWorkbook getWorkbook();

	/**
	 * 本抽象方法由 Spring 框架实现，无需自行实现
	 * 
	 * @return 一个当前 Date 实例
	 */
	public abstract Date getDate();

	public SimpleDateFormat getFileNameDateFormat() {
		return fileNameDateFormat;
	}

	public void setFileNameDateFormat(SimpleDateFormat fileNameDateFormat) {
		this.fileNameDateFormat = fileNameDateFormat;
	}

	public SimpleDateFormat getIDateFormat() {
		return iDateFormat;
	}

	public void setIDateFormat(SimpleDateFormat iDateFormat) {
		this.iDateFormat = iDateFormat;
	}


	public String getDataCompany() {
		return dataCompany;
	}

	public void setDataCompany(String dataCompany) {
		this.dataCompany = dataCompany;
	}

	public String getDataProject() {
		return dataProject;
	}

	public void setDataProject(String dataProject) {
		this.dataProject = dataProject;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getExportDir() {
		return exportDir;
	}

	public void setExportDir(String exportDir) {
		this.exportDir = exportDir;
	}

	public String getBatchRecordFile() {
		return batchRecordFile;
	}

	public void setBatchRecordFile(String batchRecordFile) {
		this.batchRecordFile = batchRecordFile;
	}

}
