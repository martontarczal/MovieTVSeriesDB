import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class H2ReadMovies {
	public ArrayList<String> readMovies(String inputSearch) throws ClassNotFoundException, SQLException {	
//		String inputSearch = "re";
		ArrayList<String> outArray = new ArrayList<String>();
//		inputSearch = inputSearch.replace("%", "\\%");
//		inputSearch = inputSearch.replace("_", "\\_");
//		inputSearch = inputSearch.replace(" ", "\\ ");
//		inputSearch = inputSearch.replaceAll("'", "''");
		
		inputSearch = validateInput(inputSearch);
		
		String inputForFunction = "%" + inputSearch + "%";
		
		String movieReadAllString = String.format("SELECT * FROM movies WHERE title iLIKE '%s' OR ReleaseYear iLIKE '%s' OR DescriptionTest iLIKE '%s' ORDER BY LCASE(title)", inputForFunction, inputForFunction, inputForFunction);
		
		System.out.println(movieReadAllString);

		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieReadAllString);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String releaseyear = rs.getString("releaseyear");
			String descriptiontest = rs.getString("descriptiontest");
			System.out.println(id + ", " + title + ", " + releaseyear + ", " + descriptiontest);

			outArray.add(title);
		}
		
		for (int i = 0; i < outArray.size(); i++) {
			System.out.print(outArray.get(i) + "\n");
		}
		
		return outArray;
	}

	public String validateInput(String inputParameter) {
		inputParameter = inputParameter.replace("%", "\\%");
		inputParameter = inputParameter.replace("_", "\\_");
		inputParameter = inputParameter.replace(" ", "\\ ");
		inputParameter = inputParameter.replaceAll("'", "''");
		return inputParameter;
	}
	
	public ArrayList<String> readMovieDetails(String titleSearch) throws ClassNotFoundException, SQLException {	
		ArrayList<String> outArray = new ArrayList<String>();
		
		titleSearch = validateInput(titleSearch);
		
		String inputForFunction = "%" + titleSearch + "%";
		
		String movieDetailsString = String.format("SELECT * FROM movies WHERE title iLIKE '%s'", inputForFunction);
		
//		System.out.println(movieDetailsString);

		Connection connvariable;
		connvariable = ConnectionBase.getConnection();

		PreparedStatement ps = connvariable.prepareStatement(movieDetailsString);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String releaseyear = rs.getString("releaseyear");
			String descriptiontest = rs.getString("descriptiontest");
//			System.out.println(id + ", " + title + ", " + releaseyear + ", " + descriptiontest);

			outArray.add(title);
			outArray.add(releaseyear);
			outArray.add(descriptiontest);
		}
		
//		for (int i = 0; i < outArray.size(); i++) {
//			System.out.print(outArray.get(i) + "\n");
//		}
		
		return outArray;
	}
}
