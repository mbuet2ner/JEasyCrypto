import org.json.simple.JSONObject;

public interface ReaderObserver {
	public void handleResponse(JSONObject response) throws InterruptedException;
}
