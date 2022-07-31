import java.sql.SQLException;

public class H2EmbeddedDB {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// following object can insert new movie into DB, no check for duplicates yet!
//		H2InsertMovie movieins = new H2InsertMovie();
//		movieins.insertNewMovie();

		// following object can read all movies in DB
		H2ReadMovies movieread = new H2ReadMovies();
		movieread.readMovies();

		// following object can delete an existing movie in the DB
//		H2DeleteMovie moviedel = new H2DeleteMovie();
//		moviedel.deleteMovie();

		// following object can update an existing movie in the DB
//		H2UpdateMovie movieupdate = new H2UpdateMovie();
//		movieupdate.updateMovie();
		
		// following object starts the GUI of the Movie/Tv series DB
		DBGUI guiBegin = new DBGUI();
		guiBegin.guiStart();
	}
}
