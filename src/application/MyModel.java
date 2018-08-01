package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class MyModel extends Observable {
	OutputStream out;
	InputStream in;
	boolean isConnected = false;
	String ip;
	int port;
	Socket socket;
	public MyModel() {
		out = null;
		in = null;
	}

	public boolean connect(String ip, int port) throws Exception {
		if (isConnected == true) {
			socket.close();
			socket = null;
		}
		this.ip = ip;
		this.port = port;
		this.socket = new Socket(ip, port);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		if (this.socket.isConnected())
			return true;
		return false;
	}

	public ArrayList<String> solve(ArrayList<String> problem) throws Exception {
		if (isConnected) {

			System.out.println("connected, trying to solve..");
			PrintWriter printer = new PrintWriter(out,true);
			for (int i = 0 ; i < problem.size() ; i ++) {
				printer.println(problem.get(i));
			}
			printer.println("done");
//				while (in.available() == 0) {
//					System.out.println("waiting..");
//				}
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			String resultLine = new String();
			ArrayList<String> clientSolution = new ArrayList<>();

			while ((resultLine = read.readLine()) != null) // read text until the "done" string from client..
			{
				System.out.println(resultLine);
				clientSolution.add(resultLine); // insert client output to my arrayString.
			}
			System.out.println("finished.");
			read.close();
			printer.close();
			return clientSolution;
		}
		return null;
	}
}
