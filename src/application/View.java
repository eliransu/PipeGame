package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class View implements Initializable, Observer{
	@FXML
	public PipeDisplayer pipeDisplayer;
	@FXML
	MenuItem load, solve, check;
	@FXML
	Button connect;
	@FXML
	TextField ip, port;
	MyViewModel vm;
	char[][] txtToArray;
	int x;
	int y;
	public View() {
		this.pipeDisplayer = new PipeDisplayer();
		ArrayList<ImageView> images = new ArrayList<>();
	}

	public void setViewModel(MyViewModel vm) {
		this.vm = vm;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pipeDisplayer.drawing();
		loadClickHandler();
		solveClickHandler();
		connect.setOnMouseClicked(event -> {
			System.out.println("ip: " + ip.getText() + " port: " + port.getText());
			vm.connect(ip.getText(),port.getText());
			});
	}

	public void loadClickHandler() {
		load.setOnAction(new EventHandler<ActionEvent>() {
			FileChooser fc = new FileChooser();
			@Override
			public void handle(ActionEvent event) {
				fc.setInitialDirectory(new File("D:/"));
				File f = fc.showOpenDialog(null);
				if (f != null) {
					System.out.println(f.getAbsolutePath() + " is the result");
					setMargins(f);
					try {
						fieldData(f);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				pipeDisplayer.drawBoard(txtToArray);
			}
		});
	}

	public void solveClickHandler() {
		solve.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vm.connect(ip.getText(),port.getText());
				ArrayList<String> result = vm.solve(pipeDisplayer.getBoard());
				if (result != null) {
					for (String s : result) {
						if (s.equals("done")) {
							System.out.println("done ....");
							return;
						}
						System.out.println("s is : " + s);
						String[] splitted = s.split(",");
						Integer x = Integer.parseInt(splitted[0]);
						Integer y = Integer.parseInt(splitted[1]);
						Integer times = Integer.parseInt(splitted[2]);
						pipeDisplayer.changeBoard(x, y, times);
					}
				}
				}
		});
	}

	public void setMargins(File f) {
		int x = 0;
		int y = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			String line = new String();
			while ((line = bf.readLine())!= null) {
				y++;
				x = line.length();
			}
			bf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}

	public void fieldData(File f) throws Exception {
		BufferedReader bf = new BufferedReader(new FileReader(f));
		txtToArray = new char[y][x];
		String line = new String();
		int k = 0;

		while ((line = bf.readLine())!= null) {
			for (int i = 0 ; i < line.length(); i++) {
				txtToArray[k][i] = line.charAt(i);
			}
			k++;
		}
		bf.close();
		for (int i = 0 ; i < txtToArray.length; i ++) {
			for (int j = 0 ; j < txtToArray[0].length; j ++) {
				System.out.print(txtToArray[i][j]);
			}

			System.out.println();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == vm) {

		}

	}
}
