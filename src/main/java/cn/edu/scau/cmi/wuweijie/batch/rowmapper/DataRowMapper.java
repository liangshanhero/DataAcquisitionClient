package cn.edu.scau.cmi.wuweijie.batch.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.edu.scau.cmi.wuweijie.entity.client.Data;

public class DataRowMapper implements RowMapper<Data> {

	@Override
	public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
		Data data = new Data();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metaData.getColumnName(i);
			switch (columnName) {
			case "ID":
				data.setId(rs.getInt(i));
				continue;
			case "site":
				data.setSite(rs.getString(i));
				continue;
			case "time2":
				data.setTime(rs.getTimestamp(i));
				continue;
			}
			data.getValues().put(columnName, rs.getObject(i));
		}
		return data;
	}

}
