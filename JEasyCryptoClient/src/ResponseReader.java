import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//Java-Json Library
import org.json.JSONObject;
import org.json.JSONException;

public class ResponseReader extends Thread {

	private DatagramSocket socket = null;
	private ReaderObserver observer = null;
	private byte[] incoming = null;

	public ResponseReader(DatagramSocket s, ReaderObserver o) {
		socket = s;
		observer = o;
	}

	@Override
	public void run() {
		super.run();
		incoming = new byte[4096];
		while (!Thread.currentThread().isInterrupted()) {
			DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
			System.out.println(packet.toString());
			try {
				System.out.println("Starting to receive responses...");
				socket.receive(packet);
				System.out.println("Response received.");
				String receivedData = new String(packet.getData(), 0, packet.getLength() ,"UTF-8");
				System.out.println("Received raw data: " + receivedData);
				System.out.println("Parsing...");
				JSONObject root = new JSONObject(receivedData);
				System.out.println("Parsed, handing JSON object to observer.");
				observer.handleResponse(root);

			} catch (IOException | JSONException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stopReading() {
		interrupt();
	}

}
