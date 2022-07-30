import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBase {
	public static Connection conn;

	public ConnectionBase() {}
	
	public static Connection getConnection()throws ClassNotFoundException,SQLException
    {
		String jdbcURL = "jdbc:h2:/home/frenzy/eclipse-workspace/JavaH2Movies/urlfile";
		String jdbcUsername = "sa";
		String jdbcPassword = "1234";
		
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);	//establish connection
			if(conn==null){
                System.out.println("Connection cannot be established");
            }
			else {
				System.out.println("Connected to H2 Embedded DB.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
    }
	
	/*
	//old code, just ignore for now
	String jdbcURL = "jdbc:h2:/home/frenzy/eclipse-workspace/JavaH2Movies/urlfile";
	String jdbcUsername = "sa";
	String jdbcPassword = "1234";

	try {
		Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);	//establish connection
		System.out.println("Connected to H2 Embedded DB.");
		
		H2InsertMovie movieins = new H2InsertMovie();
		movieins.insertNewMovie();

		
		//Print Movies table contents:
		int count = 0;
		while (resultSet.next()) {
			count++;
			
			int ID = resultSet.getInt("ID");
			String title = resultSet.getString("title");
			System.out.println("Movie #" + count + ": " + ID + ", " + title);
		}
		
		connection.close();		//close connection

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	Connection connvariable;
	connvariable = ConnectionBase.getConnection();
	*/

}
