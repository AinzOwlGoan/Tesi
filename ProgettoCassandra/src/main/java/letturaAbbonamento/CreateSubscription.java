package letturaAbbonamento;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class CreateSubscription extends JFrame {

	private JPanel contentPane;
	private JTextField NameTextField;
	private JTextField CognometextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ParseException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateSubscription frame = new CreateSubscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateSubscription() {
		
		final SerialTest write = new SerialTest();			
		write.initialize();
		
		// non posso fare la lettura qua perché viene eseguito una sola volta
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		
		System.out.println("Started");
		setTitle("Creazione Abbonamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameTextField = new JTextField();
		NameTextField.setBounds(90, 6, 130, 26);
		contentPane.add(NameTextField);
		NameTextField.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(25, 11, 43, 16);
		contentPane.add(lblNome);
		
		CognometextField = new JTextField();
		CognometextField.setBounds(90, 40, 130, 26);
		contentPane.add(CognometextField);
		CognometextField.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(25, 45, 61, 16);
		contentPane.add(lblCognome);
		
		final JCheckBox chckbxZona = new JCheckBox("Zona 1");
		chckbxZona.setBounds(6, 98, 75, 23);
		contentPane.add(chckbxZona);
		
		final JCheckBox chckbxZona_1 = new JCheckBox("Zona 2");
		chckbxZona_1.setBounds(90, 98, 75, 23);
		contentPane.add(chckbxZona_1);
		
		final JCheckBox chckbxZona_2 = new JCheckBox("Zona 3");
		chckbxZona_2.setBounds(177, 98, 75, 23);
		contentPane.add(chckbxZona_2);
		
		final JCheckBox chckbxZona_3 = new JCheckBox("Zona 4");
		chckbxZona_3.setBounds(256, 98, 75, 23);
		contentPane.add(chckbxZona_3);
		
		final JCheckBox chckbxZona_4 = new JCheckBox("Zona 5");
		chckbxZona_4.setBounds(343, 98, 75, 23);
		contentPane.add(chckbxZona_4);
		

		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(64, 168, 338, 104);
		contentPane.add(textArea);
		
		JLabel lblDataScadenza = new JLabel("Data scadenza");
		lblDataScadenza.setBounds(298, 11, 104, 16);
		contentPane.add(lblDataScadenza);
		
		String[] mesi = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		JComboBox comboBox = new JComboBox(mesi);
		comboBox.setMaximumRowCount(12);
		comboBox.setBounds(274, 41, 72, 27);
		contentPane.add(comboBox);
		
		String[] anni = { "2019", "2020", "2021", "2022", "2023", "2024", "2025" };
		JComboBox comboBox_1 = new JComboBox(anni);
		comboBox_1.setMaximumRowCount(2025);
		comboBox_1.setBounds(343, 41, 90, 27);
		contentPane.add(comboBox_1);
		
		// mouse schiacciato devo quindi fare le operazioni di creazione dell'abbonamento
		JButton btnConfermaAbbonamento = new JButton("Conferma abbonamento");
		btnConfermaAbbonamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String zone = "AB";
				
				if(chckbxZona.isSelected()) {
					
					zone+="01";
					
				}
				if(chckbxZona_1.isSelected()) {
					
					zone+="02";
					
				}
				if(chckbxZona_2.isSelected()) {
					
					zone+="03";
					
				}
				if(chckbxZona_3.isSelected()) {
					
					zone+="04";
					
				}
				if(chckbxZona_4.isSelected()) {
					
					zone+="05";
					
				}
				write.settaAbbonamento(zone);
				write.settaNome(NameTextField.getText());
				write.settaCognome(CognometextField.getText());
				
				textArea.setText("Zone volute:"+zone);
				textArea.setText("Zone prese: "+write.scritturaZone);
				
				
					//textArea.setText("Abbonamento confermato");
				
				//ASAS AB0203 AB0203
				//ASAS AB0102 AB0102
				//ASAS AB010203 AB010203

			}
		});
		btnConfermaAbbonamento.setBounds(136, 134, 195, 29);
		contentPane.add(btnConfermaAbbonamento);
	}
}
