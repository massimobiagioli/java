package it.cowo42.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public class RestServer {

	static InputStream input;
	static OutputStream output;

	private int temp;
	private String serialReadedString = "";
	private boolean stringSend = false;
	private SerialPort serialPort;
	private CommPortIdentifier portId;
	
	public RestServer() {
		this.connect("COM4", "Test", 9600);
	}
	
	public boolean connect(String portName, String applicationName, int baudRate) {
		try {
			portId = CommPortIdentifier.getPortIdentifier(portName);

			serialPort = (SerialPort) portId.open(applicationName, 5000);
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();
			serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			System.out.println("Connected to port:" + serialPort.getName()
					+ " with baudrate of: " + serialPort.getBaudRate());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// Read byte from serial, when reach end string char return the received
	// string
	public String readSerial() throws Exception {
		while (input.available() > 0) {
			// Read byte from serial
			temp = input.read();
			// If it is 13 (ASCII character CR) is the end of string, so
			// generate it
			if (temp == 13) {
				stringSend = true;
				return serialReadedString;
			}
			// Read char and add to string (ignore ASCII 10, char LF)
			else if (temp != 10) {
				if (stringSend == true) {
					stringSend = false;
					serialReadedString = "";
				}
				serialReadedString += (char) temp;
			}
		}
		return null;
	}
	
	public void write(int value) throws IOException {
		output.write(("" + value).getBytes());
		output.flush();
	}
	
	public void disconnect() {
		if (serialPort != null) {
			// Close the port.
			serialPort.close();
		}
	}
	
	@GET
	@Path("/on/")
	@Produces("application/json")
	public Response turnOn(){
		boolean connect = this.connect("COM4", "Test", 9600);
		if (connect) {
			try {
				this.write(127);
			} catch (IOException e) {				
				e.printStackTrace();
			}
			//this.disconnect();
		}
		
		return Response.ok(connect+"").build();
	}
	
	@GET
	@Path("/speed/{pace}")
	@Produces("application/json")
	public Response speed(@PathParam(value="pace") Integer value) throws IOException, InterruptedException{
		output.write(("1," + value.toString()).getBytes());
		return Response.ok().build();
	}
	
	@GET
	@Path("/switch/")
	@Produces("application/json")
	public Response switchDirection() throws IOException, InterruptedException{
		output.write(("2,0").getBytes());
		return Response.ok().build();
	}
}
