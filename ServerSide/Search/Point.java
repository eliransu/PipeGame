package Search;

public class Point {
	private int x,y;
	private int rounds;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
		this.rounds=0;
	}
	public Point(int x,int y,int rounds) {
		this.x=x;
		this.y=y;
		this.rounds=rounds;
	}
	public int getX() {return this.x;}
	public int getY() {return y;}
	public int getRounds() {return rounds;}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
		{
			System.out.println(other.x);
			return false;
		}
		if (y != other.y)
		{
			System.out.println(other.y);
			return false;
		}
		return true;
	} 

	
}