package com.crud.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {
	private Connection conn;
	private static DbManager dbManagerInstance;

	private DbManager() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", "rlewandowski");
		connectionProps.put("password", "admin!@ADMIN");
		conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/task_crud?serverTimezone=Europe/Warsaw" +
				"&useSSL=False",
			connectionProps);
	}

	public static DbManager getInstance() throws SQLException {
		if (dbManagerInstance == null) {
			dbManagerInstance = new DbManager();
		}
		return dbManagerInstance;
	}

	public Connection getConnection() {
		return conn;
	}
}