package encryption;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import letturaAbbonamento.CreateSubscription;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(172, 36, 130, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(172, 89, 130, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//aes a = new aes();
				String psw = password.getText();
				
				String check = aes.encrypt(psw);
				
				if(check.equals("ACCESS")) {
					
					try
			        {    
			            String target = new String("../ProgettoCassandra/avvio.sh");
			            Runtime rt = Runtime.getRuntime();
			            Process proc = rt.exec(target);
			            CreateSubscription cf = new CreateSubscription();
			            cf.setVisible(true);
			            
			            
			        } catch (Throwable t)
			        {
			            t.printStackTrace();
			        } 
				}
			}
		});
		btnLogin.setBounds(172, 154, 117, 29);
		contentPane.add(btnLogin);
		
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(99, 41, 70, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(99, 94, 61, 16);
		contentPane.add(lblPassword);
	}
}
