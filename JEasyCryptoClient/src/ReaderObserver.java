import org.json.JSONObject;

public interface ReaderObserver {
	public void handleResponse(JSONObject response) throws InterruptedException;
}
