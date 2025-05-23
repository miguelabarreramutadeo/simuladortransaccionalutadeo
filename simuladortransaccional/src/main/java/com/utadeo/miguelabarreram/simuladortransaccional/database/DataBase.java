package com.utadeo.miguelabarreram.simuladortransaccional.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
class DataBase {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	DataBase(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	String encrypted(String value) {
		try {
			Connection cn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement st = cn.prepareStatement("select sec_tools_enc(?); ");
			st.setString(1, value);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				value = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
}