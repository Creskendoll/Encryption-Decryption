import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class frame {
	private static JFrame window;
	private en_de ed = new en_de();
	private fileHandler fh = new fileHandler();
	final static JFileChooser fc = new JFileChooser();
	static int returnVal;
	File selectedFile;

	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
					new frame();
					frame.window.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the application.
	 */
	public frame() {
		initialize();					
	}

	/**
	 * Initialize the contents of the window.
	 */
	private void initialize() {
		window = new JFrame();
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.setForeground(Color.DARK_GRAY);
		window.setBackground(Color.WHITE);
		window.setBounds(100, 100, 450, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("");
		textArea.setBounds(12, 31, 198, 194);
		window.getContentPane().add(textArea);
		textArea.setLineWrap(true);

		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setToolTipText("");
		textArea_1.setBounds(230, 31, 198, 194);
		window.getContentPane().add(textArea_1);
		textArea_1.setLineWrap(true);

		
		JButton encrypt_button = new JButton("Encrypt");
		encrypt_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_1.setText(ed.encrypt(textArea.getText()));			
			}
		});
		encrypt_button.setBounds(12, 233, 117, 25);
		window.getContentPane().add(encrypt_button);
		
		JButton decrypt_button = new JButton("Decrypt");
		decrypt_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				textArea.setText(ed.decrypt(textArea_1.getText()));
			}
		});
		decrypt_button.setBounds(230, 233, 117, 25);
		window.getContentPane().add(decrypt_button);

		JButton open_file_button = new JButton("Open");
		open_file_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setDialogTitle("Open a txt file to decrypt.");
				returnVal = fc.showOpenDialog(window);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = fc.getSelectedFile();
					try {
						textArea_1.setText(fh.readFile(selectedFile.getAbsolutePath().toString()));
					} catch (Exception a) {
						JOptionPane.showMessageDialog(window, "Couldn't open file.");
						returnVal = fc.showOpenDialog(window);
					}
				}
			}
		});
		open_file_button.setBounds(12, 0, 117, 25);
		window.getContentPane().add(open_file_button);

		JButton save_file_button = new JButton("Save as .txt");
		save_file_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setDialogTitle("Select a location to save.");
				returnVal = fc.showOpenDialog(window);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = fc.getSelectedFile();
					if (selectedFile.getAbsolutePath().endsWith(".txt")) {
						try {
							fh.writeFile(textArea_1.getText().toString(), selectedFile.getAbsolutePath());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(window, "Invalid path.");
							returnVal = fc.showOpenDialog(window);
						}
					} else {
						JOptionPane.showMessageDialog(window, "Select a .txt file.");
						returnVal = fc.showOpenDialog(window);
					}
				}
			}
		});
		save_file_button.setBounds(297, 0, 131, 25);
		window.getContentPane().add(save_file_button);
	}
}
