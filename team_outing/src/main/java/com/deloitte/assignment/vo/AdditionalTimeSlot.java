package com.deloitte.assignment.vo;

import com.deloitte.assignment.model.ActivitySlot;

/*
 * Class for a group of tasks with a delimited size and extra time
 *
 */

public class AdditionalTimeSlot extends ActivitySlot {

	private Integer extraTime;

	public AdditionalTimeSlot(Integer size, Integer extraTime) {
		super(size);
		this.extraTime = extraTime;
	}
	
	/*
	 * Method to retrieve the available size
	 * 
	 * @return available size
	 */
	
	@Override
	public Integer getAvailableSize() {
		return super.getAvailableSize() + this.extraTime;
	}

	public Integer getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Integer extraTime) {
		this.extraTime = extraTime;
	}

}
