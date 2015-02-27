package org.team1540.common.core.schema.impl;

import java.util.ArrayList;
import java.util.List;

import org.team1540.common.core.schema.Schema;
import org.team1540.common.core.schema.impl.ToteStackSchema.ContainerState;

public class StandSchema extends Schema {
	private static final long serialVersionUID = 4L;

	public List<ToteStackSchema> stacks;

	public ContainerState[] containerStates;

	public int litterContainer, litterLandfill;
	
	public int containersRemovedStep;

	// number of stacks knocked over, number of containers knocked over, number of times they significantly interfered with a team member
	public int errorsAlpha, errorsBeta, errorsDelta;

	public boolean leftContainerAuto, middleContainerAuto, rightContainerAuto,
	leftToteAuto, middleToteAuto, rightToteAuto;
	public boolean leftContainerAutoTried, middleContainerAutoTried, rightContainerAutoTried,
	leftToteAutoTried, middleToteAutoTried, rightToteAutoTried;
	
	
	public int question1 = 4, question2 = 4, question3 = 4, question4 = 3, question5 = 0;

	public boolean stackedTotes, endedInAuto;

	public String autoNotes;
	
	public String generalNotes;
	
	public String teamName = null;
	
	public int teamNumber;
	
	public int matchNumber;
	
	public String loginName = "";
	
	public String competition = "Default";
	
	public String toPrintableString() {
		StringBuilder toReturn = new StringBuilder();
		
		toReturn.append("Team "+teamNumber+" "+teamName+" Match "+matchNumber+" "+competition+" ");
		toReturn.append("Scouted By "+loginName+" ");
		toReturn.append("AUTO: Stacked "+stackedTotes+" Moved "+endedInAuto+" Totes "+leftToteAuto+" "+middleToteAuto+" "+rightToteAuto+" Containers "+leftContainerAuto+" "+middleContainerAuto+" "+rightContainerAuto+" ");
		toReturn.append("TELEOP: Stacks ");
		for (ToteStackSchema t : stacks) {
			toReturn.append(" "+t.toReadableString()+" ");
		}
		toReturn.append("ERRORS: Totes "+errorsAlpha+" Containers "+errorsBeta+" Interfere "+errorsDelta+" ");
		toReturn.append("CONTAINERS OFF: "+containersRemovedStep+" ");
		toReturn.append("LITTER: Landfill "+litterLandfill+" Container "+litterContainer+" ");
		
		return toReturn.toString();
	}
	
	public StandSchema() {
		stacks = new ArrayList<ToteStackSchema>();
		containerStates = new ContainerState[4];
	}
	
	public String export() {
		StringBuilder b = new StringBuilder(256);
		b.append(litterContainer 		+ "\u0244");
		b.append(litterLandfill  		+ "\u0244");
		b.append(containersRemovedStep 	+ "\u0244");
		b.append(errorsAlpha 			+ "\u0244");
		b.append(errorsBeta 			+ "\u0244");
		b.append(errorsDelta 			+ "\u0244");
		b.append(leftContainerAuto 		+ "\u0244");
		b.append(middleContainerAuto 	+ "\u0244");
		b.append(rightContainerAuto 	+ "\u0244");
		b.append(leftToteAuto 			+ "\u0244");
		b.append(middleToteAuto 		+ "\u0244");
		b.append(rightToteAuto 			+ "\u0244");
		b.append(leftContainerAutoTried 		+ "\u0244");
		b.append(middleContainerAutoTried 	+ "\u0244");
		b.append(rightContainerAutoTried 	+ "\u0244");
		b.append(leftToteAutoTried 			+ "\u0244");
		b.append(middleToteAutoTried 		+ "\u0244");
		b.append(rightToteAutoTried 			+ "\u0244");
		b.append(question1 				+ "\u0244");
		b.append(question2 				+ "\u0244");
		b.append(question3 				+ "\u0244");
		b.append(question4 				+ "\u0244");
		b.append(question5 				+ "\u0244");
		b.append(stackedTotes			 + "\u0244");
		b.append(endedInAuto 			+ "\u0244");
		b.append(teamName 				+ "\u0244");
		b.append(teamNumber 				+ "\u0244");
		b.append(matchNumber 			+ "\u0244");
		b.append(containerStates[0] 	+ "\u0244");
		b.append(containerStates[1] 	+ "\u0244");
		b.append(containerStates[2] 	+ "\u0244");
		b.append(containerStates[3] 	+ "\u0244");
		b.append(competition + "\u0244");
		synchronized (stacks) { for (ToteStackSchema ts : stacks) {
			b.append(ts.export() + "\u0245");
		} }
		b.append("\u0246");
		
		return b.toString();
	}
	
	public static StandSchema create(String str) {
		StandSchema scheme = new StandSchema();
		String d1;
		String d2;
		
		String[] fields = str.split("\u0003");
		if (fields.length > 10) {
			d1 = "\u0003";
			d2 = "\u0005";
		}
		else {
			d1 = "\u0244";
			d2 = "\u0245";
			
			fields = str.split(d1);
		}
		
		scheme.litterContainer = Integer.parseInt(fields[0]);
		scheme.litterLandfill = Integer.parseInt(fields[1]);
		scheme.containersRemovedStep = Integer.parseInt(fields[2]);
		scheme.errorsAlpha = Integer.parseInt(fields[3]);
		scheme.errorsBeta = Integer.parseInt(fields[4]);
		scheme.errorsDelta = Integer.parseInt(fields[5]);
		scheme.leftContainerAuto = Boolean.parseBoolean(fields[6]);
		scheme.middleContainerAuto = Boolean.parseBoolean(fields[7]);
		scheme.rightContainerAuto = Boolean.parseBoolean(fields[8]);
		scheme.leftToteAuto = Boolean.parseBoolean(fields[9]);
		scheme.middleToteAuto = Boolean.parseBoolean(fields[10]);
		scheme.rightToteAuto = Boolean.parseBoolean(fields[11]);
		scheme.leftContainerAutoTried = Boolean.parseBoolean(fields[12]);
		scheme.middleContainerAutoTried = Boolean.parseBoolean(fields[13]);
		scheme.rightContainerAutoTried = Boolean.parseBoolean(fields[14]);
		scheme.leftToteAutoTried = Boolean.parseBoolean(fields[15]);
		scheme.middleToteAutoTried = Boolean.parseBoolean(fields[16]);
		scheme.rightToteAutoTried = Boolean.parseBoolean(fields[17]);
		scheme.question1 = Integer.parseInt(fields[18]);
		scheme.question2 = Integer.parseInt(fields[19]);
		scheme.question3 = Integer.parseInt(fields[20]);
		scheme.question4 = Integer.parseInt(fields[21]);
		scheme.question5 = Integer.parseInt(fields[22]);
		scheme.stackedTotes = Boolean.parseBoolean(fields[23]);
		scheme.endedInAuto = Boolean.parseBoolean(fields[24]);
		scheme.teamName = fields[25];
		scheme.teamNumber = Integer.parseInt(fields[26]);
		scheme.matchNumber = Integer.parseInt(fields[27]);

		for (int i = 0; i < 4; i++) {
			ContainerState cs = null;
			if (fields[28 + i].equalsIgnoreCase("OTHER_SIDE")) cs = ContainerState.OTHER_SIDE;
			if (fields[28 + i].equalsIgnoreCase("TIPPED")) cs = ContainerState.TIPPED;
			if (fields[28 + i].equalsIgnoreCase("COLLECTED")) cs = ContainerState.COLLECTED;

			scheme.containerStates[i] = cs;
		}
		
		scheme.competition = fields[32];
		
		String[] stacs = fields[33].split(d2);
		synchronized (scheme.stacks) { for (int i=0; i<stacs.length-1; i++) {
			final ToteStackSchema schemes = new ToteStackSchema(0, 0, false, false, false);
			schemes.initialize(stacs[i]);
			scheme.stacks.add(schemes);
		} }
		
		return scheme;
	}
}
