import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2UpdateMovie {
	public void updateMovie() throws ClassNotFoundException, SQLException {
		String input1 = "_";
		String input2 = "1998";
		String input3 = "new update dude";

		String insertOutput1 = input1.replaceAll("'", "''");
		String insertOutput2 = input2.replaceAll("'", "''");
		String insertOutput3 = input3.replaceAll("'", "''");
		
		String inputID = "21";

		String movieUpdateString = String.format("UPDATE movies SET title = '%s', ReleaseYear = '%s', DescriptionTest = '%s' WHERE id = '%s'", insertOutput1, insertOutput2, insertOutput3, inputID);

		System.out.println(movieUpdateString);

		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieUpdateString);
		ps.executeUpdate();
	}
}
