import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class DBGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Color PURPLE = new Color(102, 0, 153);

	// main frame & panel
	private static JFrame frame;
	private static JPanel mainPanel;

	// upper panel that has the two modes (Movies & TV Series) & searching
	private static JPanel searchPanel;
	private static JButton b1, b2, b3;
	private static JTextField searchtf;
	
	// lower panel that displays search results
	private static JPanel displayPanel;
	private static JPanel displayTextPanel;

	// right-side panel that has the buttons
	private static JPanel buttonPanel;
	private static JButton insertButton;
	private static JButton updateButton;
	private static JButton removeButton;

	// scrollpane that enables usage of scrollable & selectable list of titles
	private static JScrollPane listScrollPane = new JScrollPane();

	private static ArrayList<String> arraylistToConvert = new ArrayList<String>();
//    private static String[] stringArray = {"Testing", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff"};
	private static String[] convertedString;

	public void guiStart() throws ClassNotFoundException, SQLException {
		// This should probably be in the GUI functionality class		
//		H2ReadMovies movieread = new H2ReadMovies();
//		arraylistToConvert = movieread.readMovies();
//
//		convertedString = new String[arraylistToConvert.size()];
//		for (int i = 0; i < arraylistToConvert.size(); i++) {
//			convertedString[i] = arraylistToConvert.get(i);
//		}
//		JList rowList = new JList(convertedString);
		// GUI Functionality stuff ends here!
		
		
		
		//Reads ALL movies and shows them in JList
		String everything = "";
		H2ReadMovies movieread = new H2ReadMovies();
		arraylistToConvert = movieread.readMovies(everything);
		convertedString = new String[arraylistToConvert.size()];
		for (int i = 0; i < arraylistToConvert.size(); i++) {
			convertedString[i] = arraylistToConvert.get(i);
		}
		JList rowList = new JList(convertedString);

		
		//main frame starts here:
		frame = new JFrame("My Movie & TV Series Database");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(1500, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);


		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		searchPanel = new JPanel();
		b1 = new JButton("Movies");
		b1.setBackground(PURPLE);
		b1.setForeground(Color.WHITE);
		b1.setFocusPainted(false);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//code here
			}
		});

		b2 = new JButton("TV Series");
		b2.setBackground(PURPLE);
		b2.setForeground(Color.WHITE);
		b2.setFocusPainted(false);
//		b2.addActionListener(new ButtonClickListener());

		b3 = new JButton("Search");
		b3.setBackground(Color.pink);
		b3.setForeground(Color.WHITE);
		b3.setFocusPainted(false);
		b3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			String input = "re";
			String input = searchtf.getText();
			
			H2ReadMovies movieread = new H2ReadMovies();
			try {
				arraylistToConvert = movieread.readMovies(input);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			convertedString = new String[arraylistToConvert.size()];
			for (int i = 0; i < arraylistToConvert.size(); i++) {
				convertedString[i] = arraylistToConvert.get(i);
			}
//			JList rowList = new JList(convertedString);
//			
//			rowList.setVisibleRowCount(2);
//			listScrollPane.setViewportView(rowList);
			
			rowList.setListData(convertedString);
		}
	});

		searchtf = new JTextField();
		searchtf.setPreferredSize(new Dimension(450, 26));

		searchPanel.add(b1);
		searchPanel.add(b2);
		searchPanel.add(searchtf);
		searchPanel.add(b3);

		searchPanel.setBackground(Color.white);
		searchPanel.setPreferredSize(new Dimension(1500, 36));
		searchPanel.setMaximumSize(new Dimension(1500, 36));
		searchPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PURPLE));
		
		
		// displayPanel begins here:
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.white);
		displayPanel.setLayout(new FlowLayout(1, 2, getDefaultCloseOperation()));

		displayTextPanel = new JPanel();
		displayTextPanel.setBackground(Color.white);
		displayTextPanel.setPreferredSize(new Dimension(1405, 764));
		displayTextPanel.setMaximumSize(new Dimension(1405, 764));

		rowList.setVisibleRowCount(2);
		listScrollPane.setViewportView(rowList);
		displayTextPanel.setLayout(new BorderLayout());
		displayTextPanel.add(listScrollPane);
		
		
		insertButton = new JButton("Insert");
		insertButton.setBackground(Color.pink);
		insertButton.setForeground(Color.WHITE);
		insertButton.setFocusPainted(false);
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				JFrame insertFrame = new JFrame("Insert a new title");
				insertFrame.setSize(500, 500);
				insertFrame.setResizable(false);
				
				JPanel insertPanelText = new JPanel();
				JPanel insertPanelInput = new JPanel();
				JPanel insertPanelButtons = new JPanel();
				
				JLabel titleLabel = new JLabel("Add new title:");
				JLabel yearLabel = new JLabel("Add year of release:");
				JLabel descLabel = new JLabel("Add description:");
				
				//textfields, buttons here!
				
				insertFrame.setLayout(new BorderLayout());
				insertPanelText.setBackground(Color.black);
				insertPanelText.setPreferredSize(new Dimension(250, 400));
				insertPanelText.setMaximumSize(new Dimension(250, 400));
				
				insertPanelInput.setBackground(Color.red);
				insertPanelInput.setPreferredSize(new Dimension(250, 400));
				insertPanelInput.setMaximumSize(new Dimension(250, 400));
				
				insertPanelButtons.setBackground(Color.green);
				insertPanelButtons.setPreferredSize(new Dimension(100, 100));
				insertPanelButtons.setMaximumSize(new Dimension(100, 100));
				
				
				//insertframe components + close operations:
				insertFrame.add(insertPanelText, BorderLayout.WEST);
				insertFrame.add(insertPanelInput, BorderLayout.EAST);
				insertFrame.add(insertPanelButtons, BorderLayout.SOUTH);
				
				insertFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				insertFrame.setLocationRelativeTo(null);
				insertFrame.setVisible(true);
				insertFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				    	int res = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to close this window?","Close Window?",
				    	         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				    	         new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				    	
				    	if (res == JOptionPane.YES_OPTION) {
//				            System.out.println("Selected Yes!");
				            insertFrame.dispose();
				            frame.setEnabled(true);
				         } else if (res == JOptionPane.NO_OPTION) {
//				            System.out.println("Selected No!");
				         } else if (res == JOptionPane.CLOSED_OPTION) {
//				            System.out.println("Window closed without selecting!");
				         }
				    }
				});
			}
		});
		

		updateButton = new JButton("Update");
		updateButton.setBackground(Color.pink);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFocusPainted(false);
//		updateButton.setActionCommand("Update");
//		updateButton.addActionListener(new ButtonClickListener());

		removeButton = new JButton("Remove");
		removeButton.setBackground(Color.pink);
		removeButton.setForeground(Color.WHITE);
		removeButton.setFocusPainted(false);
//		removeButton.setActionCommand("Remove");
//		removeButton.addActionListener(new ButtonClickListener());

		buttonPanel = new JPanel(new GridLayout(16, 1, 10, 20));
		buttonPanel.setBackground(Color.white);

		displayPanel.add(displayTextPanel);
		displayPanel.add(buttonPanel);
		buttonPanel.add(insertButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(removeButton);

		mainPanel.add(searchPanel);
		mainPanel.add(displayPanel);
		frame.add(mainPanel);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	int res = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to close this window?","Close Window?",
		    	         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		    	         new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
		    	
		    	if (res == JOptionPane.YES_OPTION) {
//		            System.out.println("Selected Yes!");
		            System.exit(0);
		         } else if (res == JOptionPane.NO_OPTION) {
//		            System.out.println("Selected No!");
		         } else if (res == JOptionPane.CLOSED_OPTION) {
//		            System.out.println("Window closed without selecting!");
		         }
		    }
		});
		
		frame.setVisible(true);
	}

//	public void actionPerformed(ActionEvent e) {
//		String command = e.getActionCommand();
//
//		if (command.equals("Movies")) {
//			System.out.println("movies btn clicked");
//		} else if (command.equals("TV Series")) {
//			System.out.println("tv series btn clicked");
//		} else if (command.equals("Search")) {
//			System.out.println("search btn clicked");
//
//			H2ReadMovies movieread = new H2ReadMovies();
//			try {
//				arraylistToConvert = movieread.readMovies();
//			} catch (ClassNotFoundException | SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			// convertedString = new String[arraylistToConvert.size()];
//			// for (int i = 0; i < arraylistToConvert.size(); i++) {
//			// convertedString[i] = arraylistToConvert.get(i);
//			// }
//
//			// newstring = new String[arraylistToConvert.size()];
//			// for (int i = 0; i < arraylistToConvert.size(); i++) {
//			// newstring[i] = arraylistToConvert.get(i);
//			// }
//
//		} else if (command.equals("Insert")) {
//			System.out.println("insert btn clicked");
//		} else if (command.equals("Update")) {
//			System.out.println("update btn clicked");
//		} else if (command.equals("Remove")) {
//			System.out.println("remove btn clicked");
//		}
//	}
}
