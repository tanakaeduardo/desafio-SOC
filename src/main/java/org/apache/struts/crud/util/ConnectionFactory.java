package org.apache.struts.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Models a Exam who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class ConnectionFactory {
	
	public static Connection recuperarConexao() {
		try {
			return DriverManager.
					getConnection("jdbc:mysql://localhost:3306/avaliacao?user=admin&password=admin");
		   
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
