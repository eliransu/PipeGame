package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PipeDisplayer extends GridPane {
	HashMap<Character,String> map;
	int x;
	int y;
	char[][] board;
	ArrayList<ImageView> images = new ArrayList<>();

	public PipeDisplayer() {
		this.map = new HashMap<>();
		map.put('-', "D:/pictures/minus.png");
		map.put('|', "D:/pictures/page.png");
		map.put('7', "D:/pictures/7.png");
		map.put('F', "D:/pictures/f.png");
		map.put('L', "D:/pictures/L.png");
		map.put('J', "D:/pictures/j.png");
		map.put('g', "D:/pictures/end.png");
		map.put('s', "D:/pictures/start.png");
		map.put(' ', "D:/pictures/block.png");
	}

	public void drawBoard(char[][] board) {
		this.board = board;
		this.x = board.length;
		this.y = board[0].length;
		this.drawing();
	}

	public void drawing() {
		for (int i = 0 ; i < x; i ++) {
			for (int j = 0 ; j < y ; j ++) {
				ImageView img = new ImageView();
				img.setImage(new Image("file:"+map.get(board[i][j])));
				if (board[i][j] != 'g' && board[i][j] != 's' && board[i][j] != ' ') {
					int thisI = i, thisJ = j;
				img.setOnMouseClicked(event ->{
					char c = getNextCharacter(img);
					board[thisI][thisJ] = c;
					img.setImage(new Image("file:"+map.get(c)));
				});
				}
				this.add(img, j, i);
				}
			}
		}
	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("x: " + x + " y: " + y);
	}
	public void handlePipeEvent() {
	}

	public char getNextCharacter(ImageView img) {
		Image image = img.getImage();
		String source = image.impl_getUrl();
		System.out.println(source);
		switch(source) {
		case "file:D:/pictures/minus.png":
			return '|';
		case "file:D:/pictures/page.png":
			return '-';
		case "file:D:/pictures/f.png":
			return '7';
		case "file:D:/pictures/L.png":
			return 'F';
		case "file:D:/pictures/7.png":
			return 'J';
		case "file:D:/pictures/j.png":
			return 'L';
		default:
			return 'x';

		}

	}

	public char[][] getBoard() {
		return board;
	}

	public void changeBoard(int x, int y, int rounds) {
		for (int i = 0 ; i < rounds; i ++) {
			System.out.println("there!");
			board[x][y] = charNextChar(board[x][y]);
			drawing();
		}

	}

	public char charNextChar(char c) {
		switch (c) {
		case '-':
			return '|';
		case '|':
			return '-';
		case 'F':
			return '7';
		case '7':
			return 'J';
		case 'J':
			return 'L';
		case 'L':
			return 'F';
		default:
			return 'x';
		}
	}




}
