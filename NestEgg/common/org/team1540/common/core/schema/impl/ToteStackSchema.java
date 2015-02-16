package org.team1540.common.core.schema.impl;

import java.io.Serializable;

import org.team1540.common.core.schema.Schema;

public class ToteStackSchema extends Schema {
	private static final long serialVersionUID = 2L;

	public int oldHeight, newHeight;
	public boolean oldContainer, newContainer;
	public boolean coopertition;

	public ToteStackSchema(final int oh, final int nh, final boolean oc, final boolean nc, boolean c) {
		oldHeight = oh;
		newHeight = nh;
		oldContainer = oc;
		newContainer = nc;
		coopertition = c;
	}

	public enum ContainerState implements Serializable {
		OTHER_SIDE, TIPPED, COLLECTED;

		public static final long serialVersionUID = 2L;
	}
	
	public String export() {
		return oldHeight + "\u0004" + newHeight + "\u0004" + oldContainer + "\u0004" + newContainer + "\u0004" + coopertition;
	}
	
	public void initialize(String s) {
		String[] fields = s.split("\u0004");
		oldHeight = Integer.parseInt(fields[0]);
		newHeight = Integer.parseInt(fields[1]);
		oldContainer = Boolean.parseBoolean(fields[2]);
		newContainer = Boolean.parseBoolean(fields[3]);
		coopertition = Boolean.parseBoolean(fields[4]);
	}
}
