package Management;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Server {
	void start(ClientHandler ch);
	void stop();
	void runServer() throws IOException;
}
