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

public class Frame extends JFrame {

	int count = 1, count2=1;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTextField abbonamento;

	private static int cnt = 0;
	private static  String lettura = "";

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
	
    
	SerialTest main = new SerialTest();			
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
	

	abbonamento = new JTextField();
	abbonamento.setBounds(108, 145, 225, 26);
	contentPane.add(abbonamento);
	abbonamento.setColumns(10);
	
	abbonamento.setText("Pass NFC Tag");
	
	JButton btnLetturaSeriale = new JButton("Lettura Seriale");
	btnLetturaSeriale.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			lettura = main.ret;
			System.out.println(lettura.toString());
			if(lettura.equals("Abbonamento riconosciuto")) {
				abbonamento.setText("Abbonamento riconosciuto");
			}else {
				
				abbonamento.setText("Abbonamento da inserire");
			}
		}
	});
	btnLetturaSeriale.setBounds(148, 82, 117, 29);
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
