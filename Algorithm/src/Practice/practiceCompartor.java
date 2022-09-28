package Practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class practiceCompartor {
	
	static class Pos{
		int row, col, sel;
		
		public Pos(int row, int col, int sel) {
			this.row = row;
			this.col = col;
			this.sel = sel;
		}
		
		int getRow( ) {
			return this.row;
		}
		
		int getCol( ) {
			return this.col;
		}
		int getSel() {
			return this.sel;
		}
	}
	
	static class PosComparator implements Comparator<Pos> {
		@Override
		public int compare(Pos p1, Pos p2) {
			if( p1.row > p2.row) {
				return 1;
			}
			else if(p1.row == p2.row) {
				if(p1.col < p2.col) {
					return 1;
				}
			}
			return -1;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Pos> posList = new ArrayList<>();
		PosComparator posComparator = new PosComparator();
		
//		Comparator<Pos> posComparator2 = Comparator.comparing(Pos::row);
//		listOfAnimal.stream().sorted(Comparator.comparing((Animal a) -> a.getNumOfLegs()).thenComparing((Animal a) -> a.getNumOfWings())).collect(Collectors.toList());
		posList.add(new Pos(2, 4, 1));
		posList.add(new Pos(0, 3, 0));
		posList.add(new Pos(2, 6, 2));
		posList.add(new Pos(2, 9, 1));
		posList.add(new Pos(0, 3, 3));
		posList.add(new Pos(1, 0, 2));
		posList.add(new Pos(0, 1, 1));
		posList.add(new Pos(2, 4, 5));
		posList.add(new Pos(1, 0, 0));
		posList.stream().sorted(Comparator.comparing((Pos p) -> p.getRow()).thenComparing((Pos p) -> p.getCol()).thenComparing((Pos p) -> p.getSel())).collect(Collectors.toList());
		
		
		for(Pos pos : posList) {
			System.out.println("행 : "+ pos.row + "열 : " + pos.col + "다른거 : " + pos.sel);
		}

	}

}
