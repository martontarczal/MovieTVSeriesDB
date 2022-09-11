import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	}
	
	public boolean checkDuplicate(String inputParameter) throws SQLException, ClassNotFoundException {
		inputParameter = inputParameter.replace("%", "\\%");
		inputParameter = inputParameter.replace("_", "\\_");
		inputParameter = inputParameter.replace(" ", "\\ ");
		inputParameter = inputParameter.replaceAll("'", "''");
		
		String movieCheckDupeString = String.format("SELECT 1 FROM movies WHERE title iLIKE '%s'", inputParameter);
		
		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieCheckDupeString);
		
		ResultSet rs = ps.executeQuery();
		
		//counts number of rows in resultset to see if title exists or not
		int count = 0;
		while (rs.next()) {
		    ++count;
		}

		if (count == 0) {
//		    System.out.println("No records found");
		    return false;
		}
		else {
//			System.out.println("Records found: " + count);
			return true;
		}
	}
}
