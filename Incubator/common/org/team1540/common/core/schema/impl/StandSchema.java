package org.team1540.common.core.schema.impl;

import java.util.ArrayList;
import java.util.List;

import org.team1540.common.core.schema.Schema;
import org.team1540.common.core.schema.impl.ToteStackSchema.ContainerState;

public class StandSchema extends Schema {
	private static final long serialVersionUID = 3L;

	public List<ToteStackSchema> stacks;

	public ContainerState[] containerStates;

	public int litterContainer, litterLandfill;
	
	public int containersRemovedStep;
	
	// number of stacks knocked over, number of containers knocked over, number of times they significantly interfered with a team member
	public int errorsAlpha, errorsBeta, errorsDelta;

	public boolean leftContainerAuto, middleContainerAuto, rightContainerAuto,
	leftToteAuto, middleToteAuto, rightToteAuto;
	
	public int question1, question2, question3, question4, question5;

	public boolean stackedTotes, endedInAuto;

	public String autoNotes;
	
	public String generalNotes;
	
	public String teamName = null;
	
	public int teamNumber;
	
	public int matchNumber;
	
	public String loginName = "";

	public StandSchema() {
		stacks = new ArrayList<ToteStackSchema>();
		containerStates = new ContainerState[4];
	}
}
