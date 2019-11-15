package letturaAbbonamento;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;

public class Frame extends JFrame {

	int count = 1, count2=1;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTextField abbonamento;

	private static int cnt = 0;
	private static  String lettura = "";
	private static  String zone = "";
	private static String zz = "";
	private static int[] zonemap = new int[5];
	/**
	 * Launch the application.
	 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				
				Frame frame = new Frame();
				frame.setVisible(true);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
public Frame() {
	setTitle("Verifica abbonamento");
	
    
	final SerialTest main = new SerialTest();			
	main.initialize();
	
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
	
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	final JCheckBox chckbxZona_0 = new JCheckBox("Zona 1");
	chckbxZona_0.setBounds(6, 6, 75, 23);
	contentPane.add(chckbxZona_0);
	
	final JCheckBox chckbxZona_1 = new JCheckBox("Zona 2");
	chckbxZona_1.setBounds(84, 6, 75, 23);
	contentPane.add(chckbxZona_1);
	
	final JCheckBox chckbxZona_2 = new JCheckBox("Zona 3");
	chckbxZona_2.setBounds(159, 6, 75, 23);
	contentPane.add(chckbxZona_2);
	
	final JCheckBox chckbxZona_3 = new JCheckBox("Zona 4");
	chckbxZona_3.setBounds(236, 6, 75, 23);
	contentPane.add(chckbxZona_3);
	
	final JCheckBox chckbxZona_4 = new JCheckBox("Zona 5");
	chckbxZona_4.setBounds(316, 6, 75, 23);
	contentPane.add(chckbxZona_4);
	

	abbonamento = new JTextField();
	abbonamento.setBounds(108, 145, 225, 26);
	contentPane.add(abbonamento);
	abbonamento.setColumns(10);
	
	abbonamento.setText("Pass NFC Tag");
	
	JButton btnLetturaSeriale = new JButton("Lettura Abbonamento");
	btnLetturaSeriale.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			lettura = main.ret;
			zone = main.zones;
			
					
			System.out.println(lettura.toString());
			System.out.println(zone.toString());
		
			int j = 0;
			if(chckbxZona_0.isSelected()) {
				zonemap[j] = 1;
				j++;
			}
			if(chckbxZona_1.isSelected()) {
				zonemap[j] = 2;
				j++;
			}
			if(chckbxZona_2.isSelected()) {
				zonemap[j] = 3;
				j++;
			}
			if(chckbxZona_3.isSelected()) {
				zonemap[j] = 4;
				j++;
			}
			if(chckbxZona_4.isSelected()) {
				zonemap[j] = 5;
				j++;
			}
			
			
			int check = 0;
			if(lettura.equals("Abbonamento riconosciuto")) {
				
				
				// da un lato ho le zone selezionate e dall'altro ho le zone dell'abbonamento, ora devo fare il controllo
				for(int i=0; i<zone.length(); i++) {
					
					zz+=zone.charAt(i);
				}
				
				int reallength = 0;
				for(int i = 0; i<zonemap.length; i++) {
					if(zonemap[i] != 0)
						reallength++;
				}
				
			/*	System.out.println("STAMPA ZONE INIZIO:");
				System.out.println("LUNGHEZZA ZONE :"+Integer.toString(zonemap.length));
				for(int i = 0; i<zonemap.length;i++)
				{
					
					String actualzone = Integer.toString(zonemap[i]);
					System.out.println("ZONA: "+actualzone);
				}
				System.out.print("STAMPA ZONE FINE"); */
				for(int i = 0; i<reallength; i++) {
					
					String actualzone = Integer.toString(zonemap[i]);
					
					if(zz.contains(actualzone))
					{
						check = 1;
						break;
					}
						
				}			
				if(check == 1)
				{
					abbonamento.setText("Abbonamento riconosciuto");
				}
				else {
					
					//abbonamento.setText(zz);
					abbonamento.setText("Zone non valide");
				}
				
			}else {
				
				abbonamento.setText("Abbonamento da inserire");
			}
		}
	});
	btnLetturaSeriale.setBounds(148, 84, 169, 29);
	contentPane.add(btnLetturaSeriale);
	

	addWindowListener(new WindowAdapter() {
		@Override
		public void windowOpened(WindowEvent e) {
			
			lettura = main.ret;
			System.out.println(lettura.toString());
			if(lettura.equals("Abbonamento riconosciuto")) {
				abbonamento.setText("Abbonamento riconosciuto");
			}else {
				
				abbonamento.setText("Abbonamento da inserire");
			}
		}

	});
}

	/**
	 * Create the frame.
	 */

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
