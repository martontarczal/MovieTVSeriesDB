import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2InsertMovie {

	public void insertNewMovie(String input1, String input2, String input3) throws ClassNotFoundException, SQLException {
//		String input1 = "'% _";
//		String input2 = "2023";
//		String input3 = "test movie";

		String insertOutput1 = input1.replaceAll("'", "''");
		String insertOutput2 = input2.replaceAll("'", "''");
		String insertOutput3 = input3.replaceAll("'", "''");

		String movieInsertString = String.format("INSERT INTO movies (title, ReleaseYear, DescriptionTest) VALUES ('%s', '%s', '%s')", insertOutput1, insertOutput2, insertOutput3);

		System.out.println(movieInsertString);

		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieInsertString);
		ps.executeUpdate();

		/*
		 * try { Connection connection = H2JDBCUtils.getConnection(); } catch
		 * (SQLException e) { e.printStackTrace(); }
		 * 
		 * String sql = "INSERT INTO movies (title) values ('Predator');";
		 * 
		 * Statement statement = connection.createStatement(); int rows =
		 * statement.executeUpdate(sql);
		 * 
		 * if (rows > 0) { System.out.println("Row inserted."); }
		 * 
		 * sql = "SELECT * FROM movies";
		 * 
		 * ResultSet resultSet = statement.executeQuery(sql);
		 */
	}
}
