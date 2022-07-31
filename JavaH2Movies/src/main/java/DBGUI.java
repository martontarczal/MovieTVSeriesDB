import java.awt.*;
import javax.swing.*;

public class DBGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Color PURPLE = new Color(102,0,153);

	private static JFrame frame;
	private static JPanel mainPanel;
	
	private static JPanel searchPanel;
	private static JButton b1, b2, b3;
	private static JTextField searchtf;
    
	private static JPanel displayPanel;
	private static JPanel displayTextPanel;
	private static JTextArea displayTextArea;
    
	private static JPanel buttonPanel;
	private static JButton insertButton;
	private static JButton updateButton;
	private static JButton removeButton;
	
	//new shit
    private static JScrollPane listScrollPane = new JScrollPane();
    private static String[] stringArray = {"Testing", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff", "This", "Stuff"};
    private static JList rowList = new JList(stringArray);

	
	public void guiStart() {
		frame = new JFrame("My Movie & TV Series Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 800);
		frame.setLocationRelativeTo(null);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		searchPanel = new JPanel();
		b1 = new JButton("Movies");
		b1.setBackground(PURPLE);
		b1.setForeground(Color.WHITE);
		b1.setFocusPainted(false);
		
		b2 = new JButton("TV Series");
		b2.setBackground(PURPLE);
		b2.setForeground(Color.WHITE);
		b2.setFocusPainted(false);
		
		b3 = new JButton("Search");
		b3.setBackground(Color.pink);
		b3.setForeground(Color.WHITE);
		b3.setFocusPainted(false);
		
		searchtf = new JTextField("Search...");
		searchtf.setPreferredSize(new Dimension(450, 26));
		
		searchPanel.add(b1);
		searchPanel.add(b2);
		searchPanel.add(searchtf);
		searchPanel.add(b3);
		
		searchPanel.setBackground(Color.white);
		searchPanel.setPreferredSize(new Dimension(1500, 36));
		searchPanel.setMaximumSize(new Dimension(1500, 36));
		searchPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PURPLE));
		
		
		
		
		
		//displayPanel begins here:
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.white);
		displayPanel.setLayout(new FlowLayout(1,2, getDefaultCloseOperation()));
		
		displayTextPanel = new JPanel();
		displayTextPanel.setBackground(Color.white);
		displayTextPanel.setPreferredSize(new Dimension(1405, 764));
		displayTextPanel.setMaximumSize(new Dimension(1405, 764));

		
//		displayTextArea = new JTextArea("Some text bullshit\nSome more text bullshit.");
//		displayTextArea.setEditable(false);
//		displayTextArea.setLineWrap(true);
//		displayTextArea.setWrapStyleWord(true);
//		displayTextArea.setPreferredSize(new Dimension(1405, 715));
//		displayTextArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, PURPLE));
//		
//		displayTextPanel.add(displayTextArea);
		
		//displayTextArea replaced with selectable string array list
		rowList.setVisibleRowCount(2);
        listScrollPane.setViewportView(rowList);
        displayTextPanel.setLayout(new BorderLayout());
        displayTextPanel.add(listScrollPane);
		
		insertButton = new JButton("Insert");
		insertButton.setBackground(Color.pink);
		insertButton.setForeground(Color.WHITE);
		insertButton.setFocusPainted(false);
		
		updateButton = new JButton("Update");
		updateButton.setBackground(Color.pink);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFocusPainted(false);
		
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
		frame.setVisible(true);
	}
}
