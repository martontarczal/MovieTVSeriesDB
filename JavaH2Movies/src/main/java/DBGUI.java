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
				
				JPanel insertPanelText = new JPanel(new GridLayout(3, 1));
				insertPanelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JPanel insertPanelInput = new JPanel(new GridBagLayout());
				insertPanelInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JPanel insertPanelButtons = new JPanel(new GridBagLayout());
				insertPanelButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				GridBagConstraints gbc = new GridBagConstraints();
				
				JLabel titleLabel = new JLabel("Add new title:", SwingConstants.CENTER/*RIGHT*/);
				JLabel yearLabel = new JLabel("Add year of release:", SwingConstants.CENTER/*RIGHT*/);
				JLabel descLabel = new JLabel("Add description:", SwingConstants.CENTER/*RIGHT*/);
				
				//textfields, buttons here!
				JTextField newTitleField = new JTextField(SwingConstants.CENTER);
				newTitleField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JTextField newYearField = new JTextField(SwingConstants.CENTER);
				newYearField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JTextField newDescField = new JTextField(SwingConstants.CENTER);
				newDescField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				JButton insertOK = new JButton("OK");
				insertOK.setPreferredSize(new Dimension(100, 25));
				insertOK.setMaximumSize(new Dimension(100, 25));
				JButton insertCancel = new JButton("Cancel");
				insertCancel.setPreferredSize(new Dimension(100, 25));
				insertCancel.setMaximumSize(new Dimension(100, 25));
				
				//insertbuttons actionlistener stuff:
				insertOK.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {						
						String newTitleText = newTitleField.getText();
						String newYearText = newYearField.getText();
						String newDescText = newDescField.getText();
						
						if(newTitleText.isEmpty()) {
							String message = "Title cannot be left empty.";
							JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							String newTitleTextValidated = inputValidate(newTitleText);
							String newYearTextValidated = inputValidate(newYearText);
							String newDescTextValidated = inputValidate(newDescText);
							
							System.out.println(newTitleTextValidated);
							System.out.println(newYearTextValidated);
							System.out.println(newDescTextValidated);
							
							//check if title already exists here
							//ONLY THEN add it to DB!
							
							
							H2InsertMovie movieinsert = new H2InsertMovie();
							//uncomment when Inserting is FULLY functional!
//							try {
//								movieinsert.insertNewMovie(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
//							} catch (ClassNotFoundException | SQLException e1) {
//								e1.printStackTrace();
//							}
							insertFrame.dispose();
							frame.setEnabled(true);
						}
					}
				});
				
				
				insertCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int res = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to close this window?","Close Window?",
				    	         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				    	         new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				    	
				    	if (res == JOptionPane.YES_OPTION) {
				            insertFrame.dispose();
				            frame.setEnabled(true);
				         } else if (res == JOptionPane.NO_OPTION) {
				        	 //close warning, do nothing
				         } else if (res == JOptionPane.CLOSED_OPTION) {
				        	//close warning, do nothing
				         }
					}
				});
				
				
				//Insert Panel settings and components added
				insertFrame.setLayout(new BorderLayout());
				insertPanelText.setBackground(Color.white);
				insertPanelText.setPreferredSize(new Dimension(175, 400));
				insertPanelText.setMaximumSize(new Dimension(175, 400));
				
				insertPanelText.add(titleLabel);
				insertPanelText.add(yearLabel);
				insertPanelText.add(descLabel);
				
				insertPanelInput.setBackground(Color.white);
				insertPanelInput.setPreferredSize(new Dimension(325, 400));
				insertPanelInput.setMaximumSize(new Dimension(325, 400));
				
				
				gbc.ipadx = 300;
				gbc.insets = new Insets(0,0,100,0);;
				gbc.gridx = 0;
				gbc.gridy = 0;
				insertPanelInput.add(newTitleField, gbc);
				gbc.insets = new Insets(0,0,0,0);;
				gbc.gridx = 0;
				gbc.gridy = 1;
				insertPanelInput.add(newYearField, gbc);
				gbc.insets = new Insets(100,0,0,0);;
				gbc.gridx = 0;
				gbc.gridy = 2;
				insertPanelInput.add(newDescField, gbc);
				
				insertPanelButtons.setBackground(Color.white);
				insertPanelButtons.setPreferredSize(new Dimension(100, 100));
				insertPanelButtons.setMaximumSize(new Dimension(100, 100));
				
				gbc.ipadx = 0;
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0,0,0,50);;
				insertPanelButtons.add(insertOK, gbc);
				insertPanelButtons.add(insertCancel);
				
				
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
				            insertFrame.dispose();
				            frame.setEnabled(true);
				         } else if (res == JOptionPane.NO_OPTION) {
				        	//close warning, do nothing
				         } else if (res == JOptionPane.CLOSED_OPTION) {
				        	//close warning, do nothing
				         }
				    }
				});
			}
		});
		
		//UPDATE Button:
		updateButton = new JButton("Update");
		updateButton.setBackground(Color.pink);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFocusPainted(false);
		
		
		//REMOVE Button:
		removeButton = new JButton("Remove");
		removeButton.setBackground(Color.pink);
		removeButton.setForeground(Color.WHITE);
		removeButton.setFocusPainted(false);
		
		
		
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
		    		System.exit(0);
		         } else if (res == JOptionPane.NO_OPTION) {
		        	//close warning, do nothing
		         } else if (res == JOptionPane.CLOSED_OPTION) {
		        	//close warning, do nothing
		         }
		    }
		});
		
		frame.setVisible(true);
	}
	
	//input validation for Insert:
	private String inputValidate(String inputStr) {
		inputStr = inputStr.replace("\"","\\\"");
		inputStr = inputStr.replace("\\","\\\\");
		
		return inputStr;
	}
}
