package searchengine.search;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class SearchGUI {

	private JFrame frame;
	private JTextField textField;
	JComboBox comboBox;
	JComboBox comboBox_1;
	static JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchGUI window = new SearchGUI();
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
	public SearchGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// frame.getContentPane().setBackground(new Color(204, 255, 51));
		frame.getContentPane().setFont(
				new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 11));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 10);
		frame.getContentPane().add(panel);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		// textField.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new
		// Color(176, 224, 230)));
		// textField.setForeground(new Color(0, 0, 128));
		// textField.setBackground(new Color(240, 255, 255));
		textField.setToolTipText("Enter a query..");
		textField.setBounds(10, 56, 168, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(
				191, 205, 219), null, null, null));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textArea.setText("");

				String query = "c.save";
				query = query.concat(" ");
				query = query.concat((String) comboBox.getSelectedItem());
				query = query.concat(" ");
				query = query.concat((String) comboBox_1.getSelectedItem());
				query = query.concat(" ");
				query = query.concat(textField.getText());

				try {
					client.main(query);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// textArea.setText(client.results);;

			}
		});
		btnSearch.setBounds(356, 55, 68, 23);
		frame.getContentPane().add(btnSearch);

		comboBox = new JComboBox();
		comboBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "list",
				"hash", "myhash", "bst", "avl" }));
		comboBox.setBounds(198, 56, 62, 20);
		frame.getContentPane().add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] { "AND",
				"OR", "NOT" }));
		comboBox_1.setBounds(281, 56, 56, 20);
		comboBox_1.setVisible(false);
		frame.getContentPane().add(comboBox_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 336, 141);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		// textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		textArea.setEnabled(false);
		textArea.setForeground(new Color(34, 33, 0));
		// textArea.setEditable(false);
		// textArea.setBorder(new LineBorder(new Color(64, 224, 208), 2));

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(262, 97, 17, 48);
		frame.getContentPane().add(scrollBar);

		JLabel lblMiniGoogle = new JLabel("Mini Google");
		lblMiniGoogle.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblMiniGoogle.setBounds(141, 21, 116, 24);
		frame.getContentPane().add(lblMiniGoogle);

	}
}
