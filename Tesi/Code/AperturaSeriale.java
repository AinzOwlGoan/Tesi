private static final String PORT_NAMES[] = { 
			"/dev/cu.usbmodem143301", // Mac OS X
			"COM3", // Windows
	};
private static final int DATA_RATE = 9600;

serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);
