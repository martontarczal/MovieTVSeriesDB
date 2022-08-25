import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static JButton editButton;
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
		rowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//mouselistener, double-click on a title displays title, year, description
		rowList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
//		        	JList list = (JList)evt.getSource();	DELETE LINE
//		            System.out.println(list.getSelectedValue());	DELETE LINE
//		            String input = searchtf.getText();	DELETE LINE
		        	
		        	
		        	//detailsFrame start:
		        	frame.setEnabled(false);
					JFrame detailsFrame = new JFrame("Title Details");
					detailsFrame.setSize(500, 500);
					detailsFrame.setResizable(false);
					
					JPanel detailsPanelText = new JPanel(new GridLayout(3, 1));
					detailsPanelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JPanel detailsPanelInput = new JPanel(new GridBagLayout());
					detailsPanelInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JPanel detailsPanelButtons = new JPanel(new GridBagLayout());
					detailsPanelButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					GridBagConstraints gbc = new GridBagConstraints();
					
					JLabel titleLabel = new JLabel("Title:", SwingConstants.CENTER/*RIGHT*/);
					JLabel yearLabel = new JLabel("Year of release:", SwingConstants.CENTER/*RIGHT*/);
					JLabel descLabel = new JLabel("Description:", SwingConstants.CENTER/*RIGHT*/);
					
//					JTextField newTitleField = new JTextField(SwingConstants.CENTER);
//					newTitleField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//					JTextField newYearField = new JTextField(SwingConstants.CENTER);
//					newYearField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//					JTextField newDescField = new JTextField(SwingConstants.CENTER);
//					newDescField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					
					JTextArea detailsTitleArea = new JTextArea();
					detailsTitleArea.setLineWrap(true);
					detailsTitleArea.setWrapStyleWord(true);
					detailsTitleArea.setEditable(false);
					JTextArea detailsYearArea = new JTextArea();
					detailsYearArea.setLineWrap(true);
					detailsYearArea.setWrapStyleWord(true);
					detailsYearArea.setEditable(false);
					JTextArea detailsDescArea = new JTextArea();
					detailsDescArea.setLineWrap(true);
					detailsDescArea.setWrapStyleWord(true);
					detailsDescArea.setEditable(false);
					
					
					JButton detailsEdit = new JButton("Edit");
					detailsEdit.setPreferredSize(new Dimension(100, 25));
					detailsEdit.setMaximumSize(new Dimension(100, 25));
					JButton detailsClose = new JButton("Close");
					detailsClose.setPreferredSize(new Dimension(100, 25));
					detailsClose.setMaximumSize(new Dimension(100, 25));
					
					/*
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
								
								//remove next line if ready
//								H2InsertMovie movieinsert = new H2InsertMovie();
								
								//check if year is empty, if yes, check if its valid
								if(newYearTextValidated.isEmpty()) {
									insertTitleFinal(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
									detailsFrame.dispose();	//frame should only dispose if title doesnt exist yet
									
									String input = "";
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
									rowList.setListData(convertedString);
								}
								else {
									Pattern pattern = Pattern.compile("^(19|20)[0-9][0-9]$", Pattern.CASE_INSENSITIVE);
								    
								    Matcher matcher = pattern.matcher(newYearTextValidated);
								    
								    boolean matchFound = matcher.find();
								    
								    if(matchFound) {
//								      System.out.println("DATE MATCHES");
								      insertTitleFinal(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
								      detailsFrame.dispose();	//frame should only dispose if title doesnt exist yet
								      
								      String input = "";
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
										rowList.setListData(convertedString);
								    } else {
//								      System.out.println("date does not match");
								    	String message = "Year is in the wrong format.";
										JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
								    }
								}
								frame.setEnabled(true);
							}
						}
					});
					*/
					
					detailsClose.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							detailsFrame.dispose();
				            frame.setEnabled(true);
						}
					});
					
					//Insert Panel settings and components added
					detailsFrame.setLayout(new BorderLayout());
					detailsPanelText.setBackground(Color.white);
					detailsPanelText.setPreferredSize(new Dimension(175, 400));
					detailsPanelText.setMaximumSize(new Dimension(175, 400));
					
					detailsPanelText.add(titleLabel);
					detailsPanelText.add(yearLabel);
					detailsPanelText.add(descLabel);
					
					detailsPanelInput.setBackground(Color.white);
					detailsPanelInput.setPreferredSize(new Dimension(325, 400));
					detailsPanelInput.setMaximumSize(new Dimension(325, 400));
					
					
					gbc.ipadx = 290;
					gbc.ipady = 45;	//JTextArea height!
					gbc.insets = new Insets(0,0,50,0);;
					gbc.gridx = 0;
					gbc.gridy = 0;
					detailsPanelInput.add( new JScrollPane( detailsTitleArea ), gbc);
					gbc.insets = new Insets(0,0,0,0);;
					gbc.gridx = 0;
					gbc.gridy = 1;
					detailsPanelInput.add( new JScrollPane( detailsYearArea ), gbc);
					gbc.insets = new Insets(50,0,0,0);;
					gbc.gridx = 0;
					gbc.gridy = 2;
					detailsPanelInput.add( new JScrollPane( detailsDescArea ), gbc);
					
					detailsPanelButtons.setBackground(Color.white);
					detailsPanelButtons.setPreferredSize(new Dimension(100, 100));
					detailsPanelButtons.setMaximumSize(new Dimension(100, 100));
					
					gbc.ipadx = 0;
					gbc.ipady = 0;
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.insets = new Insets(0,0,0,50);;
					detailsPanelButtons.add(detailsEdit, gbc);
					detailsPanelButtons.add(detailsClose);
					
					
					detailsFrame.add(detailsPanelText, BorderLayout.WEST);
					detailsFrame.add(detailsPanelInput, BorderLayout.EAST);
					detailsFrame.add(detailsPanelButtons, BorderLayout.SOUTH);
					
					detailsFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					detailsFrame.setLocationRelativeTo(null);
					detailsFrame.setVisible(true);
					detailsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							detailsFrame.dispose();
				            frame.setEnabled(true);
					    }
					});
					//detailsFrame end
					
		            String input = (String) list.getSelectedValue();
					
					H2ReadMovies moviereaddetails = new H2ReadMovies();
					try {
						arraylistToConvert = moviereaddetails.readMovieDetails(input);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

					convertedString = new String[arraylistToConvert.size()];
					for (int i = 0; i < arraylistToConvert.size(); i++) {
						convertedString[i] = arraylistToConvert.get(i);
					}
					
//					rowList.setListData(convertedString);	DELETE LINE
					
					
					//change this here to displaying details in new window!!!:
					for(int i = 0; i < convertedString.length; i++) {
						System.out.println(i + ": " + convertedString[i] + "\n");
					}
					detailsTitleArea.setText(convertedString[0]);
					detailsYearArea.setText(convertedString[1]);
					detailsDescArea.setText(convertedString[2]);
		        }
		    }
		});

		
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
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//code here
			}
		});
		

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
		searchtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//if enter is pressed in the JTextField, search DB as well!
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
					
					rowList.setListData(convertedString);
				}
			}
		});

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
				JFrame insertFrame = new JFrame("Insert a New Title");
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
							
							//remove next line if ready
//							H2InsertMovie movieinsert = new H2InsertMovie();
							
							//check if year is empty, if yes, check if its valid
							if(newYearTextValidated.isEmpty()) {
								insertTitleFinal(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
								insertFrame.dispose();	//frame should only dispose if title doesnt exist yet
								
								String input = "";
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
								rowList.setListData(convertedString);
							}
							else {
								Pattern pattern = Pattern.compile("^(19|20)[0-9][0-9]$", Pattern.CASE_INSENSITIVE);
							    
							    Matcher matcher = pattern.matcher(newYearTextValidated);
							    
							    boolean matchFound = matcher.find();
							    
							    if(matchFound) {
//							      System.out.println("DATE MATCHES");
							      insertTitleFinal(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
							      insertFrame.dispose();	//frame should only dispose if title doesnt exist yet
							      
							      String input = "";
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
									rowList.setListData(convertedString);
							    } else {
//							      System.out.println("date does not match");
							    	String message = "Year is in the wrong format.";
									JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
							
							
							//remove following if ready
							/*
							//check for duplicate titles:
							try {
								boolean insertCheckRes = movieinsert.checkDuplicate(newTitleTextValidated);
								
								if(insertCheckRes == true) {
									String message = "Title already exists.";
									JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
								}
								else {
									//title doesnt exist yet, add to DB
									System.out.println("title doesnt exist yet, added to DB");
									
//									movieinsert.insertNewMovie(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
									
									insertFrame.dispose();	//frame should only dispose if title doesnt exist yet
								}
							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
							*/
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
		editButton = new JButton("Edit");
		editButton.setBackground(Color.pink);
		editButton.setForeground(Color.WHITE);
		editButton.setFocusPainted(false);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//code here
			}
		});
		
		
		//REMOVE Button:
		removeButton = new JButton("Remove");
		removeButton.setBackground(Color.pink);
		removeButton.setForeground(Color.WHITE);
		removeButton.setFocusPainted(false);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rowList.getSelectedValue() != null)
				{
					String jlisttext = rowList.getSelectedValue().toString();
					
					H2DeleteMovie movieremove = new H2DeleteMovie();
					
					frame.setEnabled(false);
					int res = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to remove this title?","Remove Title?",
			    	         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			    	         new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
			    	
			    	if (res == JOptionPane.YES_OPTION) {
			    		try {
							movieremove.deleteMovie(jlisttext);
							
							//update JList rowList after a deletion:
							String input = "";
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
							rowList.setListData(convertedString);
							
							
							String message = "Title has been successfully removed.";
							JOptionPane.showMessageDialog(new JFrame(), message, "Deletion Successful", JOptionPane.PLAIN_MESSAGE);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
			            frame.setEnabled(true);
			         } else if (res == JOptionPane.NO_OPTION) {
				            frame.setEnabled(true);
			         } else if (res == JOptionPane.CLOSED_OPTION) {
				            frame.setEnabled(true);
			         }
				}
			}
		});
		
		
		buttonPanel = new JPanel(new GridLayout(16, 1, 10, 20));
		buttonPanel.setBackground(Color.white);

		displayPanel.add(displayTextPanel);
		displayPanel.add(buttonPanel);
		buttonPanel.add(insertButton);
		buttonPanel.add(editButton);
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
//	private void insertTitleFinal(String inputTitleStr, String inputYearStr, String inputDescStr) {
	private void insertTitleFinal(String newTitleTextValidated, String newYearTextValidated, String newDescTextValidated) {
		H2InsertMovie movieinsert = new H2InsertMovie();
		
		try {
			boolean insertCheckRes = movieinsert.checkDuplicate(newTitleTextValidated);
			
			if(insertCheckRes == true) {
				String message = "Title already exists.";
				JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				//title doesnt exist yet, add to DB
				System.out.println("title doesnt exist yet, added to DB");
				
				movieinsert.insertNewMovie(newTitleTextValidated, newYearTextValidated, newDescTextValidated);
				
				//remove following 1 line if ready
//				insertFrame.dispose();	//frame should only dispose if title doesnt exist yet
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}
