package wordle;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class WordleWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordleWindow window = new WordleWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WordleWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[422px,grow]", "[25px][220px]"));
		
		JTextArea txtrWelcomeToWordle = new JTextArea();
		txtrWelcomeToWordle.setBackground(Color.ORANGE);
		txtrWelcomeToWordle.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		txtrWelcomeToWordle.setText("Welcome to Wordle!");
		frame.getContentPane().add(txtrWelcomeToWordle, "cell 0 0,alignx center,aligny top");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "cell 0 1,growx");
		textField.setColumns(5);
	}

}
