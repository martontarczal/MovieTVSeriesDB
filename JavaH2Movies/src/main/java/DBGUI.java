import java.awt.*;
import javax.swing.*;

public class DBGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final Color PURPLE = new Color(102,0,153);

	static JFrame frame;
	static JPanel mainPanel;
	
	static JPanel searchPanel;
    static JLabel mainLabel;
    static JButton b1, b2, b3;
    static JTextField searchtf;
    
    static JPanel displayPanel;
    static JTextArea displayTextArea;

	
	public void guiStart() {
		frame = new JFrame("My Movie & TV Series Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 800);
		frame.setLocationRelativeTo(null);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		searchPanel = new JPanel();
//		mainLabel = new JLabel("WOT?");
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
//		searchPanel.setSize(1500, 36);
		searchPanel.setPreferredSize(new Dimension(1500, 36));
		searchPanel.setMaximumSize(new Dimension(1500, 36));
		searchPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PURPLE));
		
//		frame.add(searchPanel);
		
		
		
		
		//displayPanel begins here:
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.white);
		displayPanel.setLayout(new GridBagLayout());

		
//		displayPanel.add(Box.createRigidArea(new Dimension(100,100)));
		displayTextArea = new JTextArea("Some text bullshit\nSome more text bullshit.");
		displayTextArea.setEditable(false);
		displayTextArea.setLineWrap(true);
		displayTextArea.setWrapStyleWord(true);
		displayTextArea.setPreferredSize(new Dimension(1400, 650));
		displayTextArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, PURPLE));
		displayPanel.add(displayTextArea);
		
		
		
		
//		frame.add(displayPanel);
		
		
		//test
		
		
		
		
		mainPanel.add(searchPanel);
		mainPanel.add(displayPanel);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
}
