package com.example;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		
		// Apre porta seriale
		CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("COM4");
		SerialPort serialPort = (SerialPort) portId.open("test", 5000);
		serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);		
		
		// Questa istruzione è necessaria perchè Arduino si riavvia dopo aver aperto la seriale
		Thread.sleep(2000);
		
		// Invia comandi alla seriale
		for (int i = 0; i < 255; i++) {
			serialPort.getOutputStream().write(("SPD:" + i + "\n").getBytes());
			Thread.sleep(200);
		}
		
	}

}
