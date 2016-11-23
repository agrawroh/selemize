package com.agrawroh.selemize.model;

public enum MandatoryColumnNames {
	SEL_COMMAND(0), SEL_VALUE(1), SEL_TARGET(2);

	/* Column Index */
	private int index;

	/* Private Constructor */
	private MandatoryColumnNames(int index) {
		/* Set Index */
		this.index = index;
	}

	/**
	 * Get Index Location
	 * 
	 * @return index
	 */
	public int getIndex() {
		/* Return Index */
		return index;
	}
}
