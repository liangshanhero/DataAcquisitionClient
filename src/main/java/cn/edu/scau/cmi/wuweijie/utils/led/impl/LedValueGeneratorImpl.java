package cn.edu.scau.cmi.wuweijie.utils.led.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter;
import cn.edu.scau.cmi.wuweijie.utils.led.LedValueGenerator;

public class LedValueGeneratorImpl implements LedValueGenerator {

	private DataSource dataSource;

	@Override
	public Double generateValue(Ledmeter ledmeter, double fluctuation, int intervalSecond) {
		Double value = 0.0;
		try (Connection connection = getDataSource().getConnection();) {
			String sql = "select max(value) from ledmeterdata d where d.ledmeter in (select id from ledmeter m where m.number=? )";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ledmeter.getNumber());
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					value = resultSet.getDouble(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double averagePerDay = ledmeter.getTotalamout() / ledmeter.getTotaldays();
		double divisor = 24 * 3600 / intervalSecond;
		value += value * Math.random() * fluctuation * (averagePerDay / divisor);
		return value;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
