/*
 * 职位分类表
 */

package com.model;

public class PositionClassificationBean {

	private int iPositionClassificationNo;
	private String nvPositionClassificationName;
	/**
	 * @return the iPositionClassificationNo
	 */
	public int getiPositionClassificationNo() {
		return iPositionClassificationNo;
	}
	/**
	 * @param iPositionClassificationNo the iPositionClassificationNo to set
	 */
	public void setiPositionClassificationNo(int iPositionClassificationNo) {
		this.iPositionClassificationNo = iPositionClassificationNo;
	}
	/**
	 * @return the nvPositionClassificationName
	 */
	public String getNvPositionClassificationName() {
		return nvPositionClassificationName;
	}
	/**
	 * @param nvPositionClassificationName the nvPositionClassificationName to set
	 */
	public void setNvPositionClassificationName(String nvPositionClassificationName) {
		this.nvPositionClassificationName = nvPositionClassificationName;
	}
}
