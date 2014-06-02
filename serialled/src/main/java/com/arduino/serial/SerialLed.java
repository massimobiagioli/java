package com.arduino.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SerialLed {
	
	public static void main(String[] args) throws Exception {
		
		// Controlla parametri
		if (4 != args.length) {
			System.err.println("Numero argomenti errato! (Richiesti 4 paramtri)");
			return;
		}
		
		// Legge file delle properties		
		InputStream is = new FileInputStream("./config.properties");
		Properties props = new Properties();
		props.load(is);
		
		// Apre porta seriale		
		CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(props.getProperty("serial_port_id"));
		SerialPort serialPort = (SerialPort) portId.open("test", 5000);
		serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);		
		
		// Questa istruzione è necessaria perchè Arduino si riavvia dopo aver aperto la seriale
		Thread.sleep(2000);
		
		// Invia comando alla seriale
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(args[i]);
			if (i < 3) {
				sb.append(",");
			}
		}
		
		// Comando --> [LED1],[LED2],[LED3],[LED4]
		serialPort.getOutputStream().write(sb.toString().getBytes());
		
		// Chiude porta seriale
		serialPort.close();
	}
}
