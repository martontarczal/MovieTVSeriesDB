import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2UpdateMovie {
	public void updateMovie(String input1, String input2, String input3, String oldTitle) throws ClassNotFoundException, SQLException {
//		String input1 = "_";
//		String input2 = "1998";
//		String input3 = "new update dude";

		String insertOutput1 = input1.replaceAll("'", "''");
		String insertOutput2 = input2.replaceAll("'", "''");
		String insertOutput3 = input3.replaceAll("'", "''");
		String insertOutput4 = oldTitle.replaceAll("'", "''");

		String movieUpdateString = String.format("UPDATE movies SET title = '%s', ReleaseYear = '%s', DescriptionTest = '%s' WHERE title = '%s'", insertOutput1, insertOutput2, insertOutput3, insertOutput4);

		System.out.println(movieUpdateString);

		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieUpdateString);
		ps.executeUpdate();
	}
}
