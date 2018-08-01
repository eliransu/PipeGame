package Management;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class MyServer implements Server {
	boolean stop;
	int port = 0;
	ClientHandler ch;
	Logger logger = Logger.getLogger(MyServer.class.getName());
	@Override
	public void start(ClientHandler ch) {
			this.ch = ch;
			new Thread(()->{
				try {
					runServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
	public MyServer(int port) {
			this.port = port;
			this.start(new PipeClientHandler());
		}

	@Override
	public void stop() {
		this.stop = true;
		
	}

	@Override
	public void runServer() throws IOException {
		ServerSocket serverSocket=new ServerSocket(port);
		//serverSocket.setSoTimeout(10000);
		logger.info("listenning on port: " + port);
		while (!stop)
		{
			try {
				Socket clientSocket = serverSocket.accept();
				logger.info("sayed that client connected. ");
				try {
					this.start(new PipeClientHandler());
					ch.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
					clientSocket.close();
					serverSocket.close();
					this.stop();
					}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
			}
		}
	}
