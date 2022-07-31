import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2ReadMovies {
	public void readMovies() throws ClassNotFoundException, SQLException {	
		String inputSearch = "";
//		inputSearch = inputSearch.replace("%", "\\%");
//		inputSearch = inputSearch.replace("_", "\\_");
//		inputSearch = inputSearch.replace(" ", "\\ ");
//		inputSearch = inputSearch.replaceAll("'", "''");
		
		inputSearch = validateInput(inputSearch);
		
		String inputForFunction = "%" + inputSearch + "%";
		
		String movieReadAllString = String.format("SELECT DISTINCT * FROM movies WHERE title iLIKE '%s' OR ReleaseYear iLIKE '%s' OR DescriptionTest iLIKE '%s'", inputForFunction, inputForFunction, inputForFunction);
		
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
            
            //cont. from here with String[] shit!
        }

	}

	public String validateInput(String inputParameter) {
		inputParameter = inputParameter.replace("%", "\\%");
		inputParameter = inputParameter.replace("_", "\\_");
		inputParameter = inputParameter.replace(" ", "\\ ");
		inputParameter = inputParameter.replaceAll("'", "''");
		return inputParameter;
	}
}
