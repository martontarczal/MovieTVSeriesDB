import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2DeleteMovie {
	public void deleteMovie(String inputSearch) throws SQLException, ClassNotFoundException {
		//in theory, no need for input validation as this will come from the DB itself, which cannot be anything but the ID
//		String inputSearch = "17";	
		
		//again, no need to check if movie exists since it won't be able to be deleted if it doesn't exist, but it's here just in case
		//String movieCheckIfExist = String.format("SELECT COUNT(1) FROM movies WHERE id = '%s'", inputSearch);
		
		String movieDeleteinput = String.format("DELETE FROM movies WHERE title = '%s'", inputSearch);
		
		System.out.println(movieDeleteinput);
		
		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieDeleteinput);
		ps.executeUpdate();
	}
}
