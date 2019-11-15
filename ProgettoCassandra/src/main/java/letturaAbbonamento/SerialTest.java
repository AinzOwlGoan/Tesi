package letturaAbbonamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener;
import it.databasemaster.cassandra.ProgettoCassandra.Abbonamento;
import it.databasemaster.cassandra.ProgettoCassandra.CassandraConnector;

import java.util.Enumeration;

import com.datastax.driver.core.utils.UUIDs;


public class SerialTest implements SerialPortEventListener {
	SerialPort serialPort;
	
// mettere un db nosql
// oppure trovare un modo per crittografare
	public static String ret = "R";
	public static String zones = "JJ";
	public static String scritturaZone = "AS";
	public static String scritturaNome = "";
	public static String scritturaCognome = "";
	
	public static boolean ch = false;
	public static byte []zoneatt;
		/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/cu.usbmodem143301", // Mac OS X
			"COM3", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	

	public void initialize() {
                // the next line is for Raspberry Pi and 
                // gets us into the while loop and was suggested here was suggested https://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
          //      System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	public void settaAbbonamento(String zone) {
		
		scritturaZone = zone;
		
		//System.out.println("SETTAZONE DICE:"+scritturaZone);
	}
	public void settaNome(String nome) {
		
		scritturaNome = nome;
		
	}
	public void settaCognome(String cognome) {
		
		scritturaCognome = cognome;
		

	}
	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			 try { //decommentare
				String val = "conversion";
				byte []b = val.getBytes();
				
				String inputLine=input.readLine();
				System.out.println(inputLine);
				int a = 0;
				// To Modify
			//	System.out.print("TESTTTT"+inputLine+" lungaaaa "+inputLine.length());
				if(inputLine.contains("AB")) 
				{
					//if(inputLine.substring(0,2).contains("AB")) 
					
						int []zoneDivision = new int[inputLine.length()/2];
						// sapendo la lunghezza posso sapere quante zone ci sono 
						// ricordandosi che ogni zona occupa 2 caratteri se è lungo 10 -> 4 zone
						int numerozone = (inputLine.length()-2)/2;
						int j = 0;
						int inizio = 3;
						int fine = inputLine.length();
						for(int i = 3; i<inputLine.length(); i++) {
							
							char valoreI = inputLine.charAt(i);
							if((valoreI == '0') && (ch == false)) {
								inizio = i;
								ch = true;
								}
							if((valoreI == 'A')&& (ch == true)) {
								fine = i;
								break;
							}
							
						}
						System.out.println("PARTENZA: "+inizio+" FINE: "+fine);
						for(int i = inizio; i<fine; i+=2) {
							
							char partial1 = inputLine.charAt(i);
							char partial2 = inputLine.charAt(i+1);
							
							// vado ad identificare la zona
							String zone = Character.toString(partial1) + Character.toString(partial2);
							
							// ottengo la zona!
							zoneDivision[j] = Integer.parseInt(zone);
							
							j++;
					} zones="";
					for(int i = 0; i< zoneDivision.length; i++) {
						zones += zoneDivision[i];
					}
					
					ret = "Abbonamento riconosciuto";
					
					output.write(b);
					
				}
				
				if(ret.equals("Abbonamento riconosciuto")) {
					ret = "Abbonamento riconosciuto";
				}
				System.out.println(a != 0 ? a+"Ritorno: "+ret:"");
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}  //fine decommento
		/*zoneatt = scritturaZone.getBytes();
		try {
			
			
			String inputLine=input.readLine();
			System.out.println(inputLine);
			
		
			if(inputLine.equals("Successo"))
				ch = true;
			
			
			output.write(zoneatt);
			
			if(ch == true) {
				
				final CassandraConnector client = new CassandraConnector();
				 
				final String ipAddress = "localhost";
				final int port = 9042;
				
				client.connect(ipAddress, port);
				Abbonamento ab = new Abbonamento(client);
			System.out.println("INIZIO DB interno");
			
			ab.Abbonato(
					UUIDs.timeBased(), 
					scritturaNome.toString(), 
					scritturaCognome.toString(), 
					new String("Casa mia"), 
					scritturaZone.toString()
					
					);
			System.out.println("FINE DB");
			System.out.println(client.esegui());
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}} */
		
	}
		// Ignore all the other eventTypes, but you should consider the other ones.
	

	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				
				
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		
		final CassandraConnector client = new CassandraConnector();
		 
		final String ipAddress = args.length > 0 ? args[0] : "localhost";
		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
		System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		client.connect(ipAddress, port);
		Abbonamento ab = new Abbonamento(client);
		t.start();
		
		System.out.println("Started");
	}
}