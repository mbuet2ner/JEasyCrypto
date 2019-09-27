import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import easycrypto.EasyCryptoAPI;

// https://code.google.com/archive/p/json-simple/
// http://www.geeksforgeeks.org/parse-json-java/

public class CryptoServer implements Runnable {

	private DatagramSocket socket;
	private byte[] incoming;
	private boolean running = true;
	
	public static void main(String[] args) {
		new CryptoServer().run();
	}

	public void run() {

		try {
			System.out.println("Launching CryptoServer...");
			socket = new DatagramSocket(10000);
			System.out.printf("Local addess is : %s\n", socket.getLocalAddress().getHostAddress());
			incoming = new byte[4096];
			DatagramPacket packet = new DatagramPacket(incoming, incoming.length);

			InetAddress sender = null;
			long id = 0;
			String response = null;
			String operation =  null;

			while (running) {
				try {
					System.out.println("Start to receive packets...");
					socket.receive(packet);
					System.out.println("Packet received!");
					String receivedData = new String(packet.getData(), 0, packet.getLength());
					sender = packet.getAddress();
					System.out.println("Sender is: " + sender.getHostAddress() + ":" + packet.getPort());
					System.out.println("Received raw data: " + receivedData);
					System.out.println("Parsing...");
					JSONObject root = (JSONObject) new JSONParser().parse(receivedData);
	
					id = ((Long) root.get("id")).longValue(); // id of the operation, for async operations.
					operation = (String) root.get("operation"); // request/response
					
					EasyCryptoAPI.Result result = null;
					
					if (operation.equalsIgnoreCase("encrypt")) {
						String method = (String) root.get("method"); // encrypt/decrypt
						String data = (String) root.get ("data"); // text to be handled
						result = EasyCryptoAPI.encrypt(data, method);
					} else if (operation.equalsIgnoreCase("decrypt")) {
						String method = (String) root.get("method"); // encrypt/decrypt
						String data = (String) root.get ("data"); // text to be handled
						result = EasyCryptoAPI.decrypt(data, method);
					} else if (operation.equalsIgnoreCase("capabilities")) {
						String methods = EasyCryptoAPI.methods();
						result = new EasyCryptoAPI.Result(EasyCryptoAPI.ResultCode.ESuccess, methods);
					} else {
						result = new EasyCryptoAPI.Result(EasyCryptoAPI.ResultCode.ENotSupported, "Operation not supported");
					}
					response = createResponse(operation, id, result);
				} catch (IOException ioe) {
					ioe.printStackTrace();
					response = createResponse(operation, id, new EasyCryptoAPI.Result(EasyCryptoAPI.ResultCode.EError, ioe.getLocalizedMessage()));
				} catch (ParseException e) {
					e.printStackTrace();
					response = createResponse(operation, id, new EasyCryptoAPI.Result(EasyCryptoAPI.ResultCode.EError, e.getLocalizedMessage()));
				} finally {
					if (null != response && null != sender) {
						System.out.println("Sending response: " + response);
						DatagramPacket sendPacket = new DatagramPacket(response.getBytes(), response.length());
						sendPacket.setAddress(sender);
						sendPacket.setPort(packet.getPort());
						try {
							socket.send(sendPacket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
			
		} finally {
			if (null != socket) {
				socket.close();
				socket = null;
			}
		}
	} // end run()
	
	private String createResponse(String op, long id, EasyCryptoAPI.Result result) {
		JSONObject toSend = new JSONObject();
		toSend.put("id", id);
		toSend.put("operation", op+"-response");
		toSend.put("result", result.resultCode().ordinal());
		toSend.put("data", result.result());
		return toSend.toJSONString();
	}
}
