package edu.team1540.recycle.compititon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.team1540.recycle.file.DieggnosticsIO;

public class Schedule {
	public List<int[]> schedule = new ArrayList<int[]>();
	
	public Schedule(int size) {
		for (int i=0; i<size; i++) schedule.add(null);
	}
	
	public Schedule() {
		
	}
	
	public void addMatch(int[] match, int index) {
		if (match.length != 6) throw new RuntimeException("Invalid match");
		
		while (schedule.size() < (index)+1) schedule.add(null);
		schedule.set(index, Arrays.copyOf(match, 6));
	}
	
	public static Schedule load(String file) {
		Schedule s = new Schedule();
		
		String contents = DieggnosticsIO.getFileContents(file);
		String[] lines = contents.split("\\r?\\n");
		for (String matc : lines) {
			System.out.println(matc);
			String[] matchline = matc.split(":");
			String match = matchline[1];
			match = match.substring(1, match.length()-2); // cull brackets
			String[] teams = match.split(",");
			int[] nMatch = new int[6];
			for (int i=0; i<teams.length; i++) {
				nMatch[i] = Integer.parseInt(teams[i].replaceAll("\\s","")); // remove whitespace and parse int
			}
			s.addMatch(nMatch, Integer.parseInt(matchline[0]));
		}
		
		return s;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		for (int[] match : schedule) {
			if (match == null) continue;
			
			b.append("[ " + match[0] + ", ");
			b.append(match[1] + ", ");
			b.append(match[2] + ", ");
			b.append(match[3] + ", ");
			b.append(match[4] + ", ");
			b.append(match[5] + " ]\n");
		}
		
		return b.toString();
	}
}
