package minenaruto.narutoplugin.iditems;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import minenaruto.narutoplugin.main.Main;
 
public class DatabaseManager {
	private FileConfiguration config;
	private String host;
	private String dbname;
	private String user;
	private String GAME_TABLE;
	private boolean sqlite;
	private String password;
	private Connection connection;

	public DatabaseManager(Main main) {
		config = main.getConfig();
		host = config.getString("database.host");
		dbname = config.getString("database.dbname");
		user = config.getString("database.user");
		password = config.getString("database.password");
		sqlite = config.getBoolean("database.sqlite");
		GAME_TABLE = config.getString("database.gametable");
		openConnection();
		loadTables();
	}

	public Map<String, Integer> getTopFromCriteria(String criteria, int amount) {
		Map<String, Integer> top = new LinkedHashMap<String, Integer>();
		ResultSet resultSet = getResult("SELECT * FROM " + GAME_TABLE + ";");
		System.out.println("SELECT * FROM rootf." + GAME_TABLE + ";");
		try {
			while (resultSet.next()) {
				
				System.out.println("Atest" + resultSet.getString("id"));
		} 
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return top;
	}

	private void openConnection() {
		if (!isConnected()) {
			try {
			    if(sqlite == false) {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + dbname + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true", user, password);
				    }
				    else {
				    	Class.forName("org.sqlite.JDBC");
				    	connection = DriverManager.getConnection("jdbc:sqlite:" + Main.getInstance().getDataFolder() + File.separator + "database.db");
				    }
			} catch (SQLException ex) {
			} catch (ClassNotFoundException ex) {
			}
		}
	}

	private void loadTables() {
		if (isConnected()) {
			update("CREATE TABLE IF NOT EXISTS " + GAME_TABLE + "(id INT(16), iditem INT(4), durability INT(8), displayname VARCHAR(64), lore VARCHAR(256), PRIMARY KEY (id))");
		}
	}

	public void closeConnection() {
		if (isConnected()) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException ex) {
			}
		}
	}

	public void update(String query) {
		if (isConnected()) {
			try {
				connection.prepareStatement(query).executeUpdate();
			} catch (SQLException ex) {
			}
		}
	}

	public ResultSet getResult(String query) {
		if (isConnected()) {
			try {
				return connection.prepareStatement(query).executeQuery();
			} catch (SQLException ex) {
			}
		}
		return null;
	}

	public String getHost() {
		return host;
	}

	public String getDBName() {
		return dbname;
	}
	public String getGameTable() {
		return GAME_TABLE;
	}
	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean isConnected() {
		return connection != null;
	}
}
