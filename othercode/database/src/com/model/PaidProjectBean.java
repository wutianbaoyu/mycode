package com.model;

import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;

public class PaidProjectBean {

	private int iPaidProjectNo;
	private String nvPaidProjectPositionName;
	private String nvPaidProjectName;
	private String nvPaidProjectMaker;
	private String nvPaidProjectRegistrant;
	private Date dPaidProjectRegTime;
	private Date dPaidProjectRevTime;
	private float dPaidProjectTotal;
	private int iState;
	private Vector<Vector<Object>> vector;

	/**
	 * @return the vector
	 */
	public Vector<Vector<Object>> getVector() {
		return vector;
	}
	/**
	 * @param vector the vector to set
	 */
	public void setVector(Vector<Vector<Object>> vector) {
		this.vector = vector;
	}
	/**
	 * @return the dPaidProjectRegTime
	 */
	public Date getdPaidProjectRegTime() {
		return dPaidProjectRegTime;
	}
	/**
	 * @param dPaidProjectRegTime the dPaidProjectRegTime to set
	 */
	public void setdPaidProjectRegTime(Date dPaidProjectRegTime) {
		this.dPaidProjectRegTime = dPaidProjectRegTime;
	}
	/**
	 * @return the dPaidProjectRevTime
	 */
	public Date getdPaidProjectRevTime() {
		return dPaidProjectRevTime;
	}
	/**
	 * @param dPaidProjectRevTime the dPaidProjectRevTime to set
	 */
	public void setdPaidProjectRevTime(Date dPaidProjectRevTime) {
		this.dPaidProjectRevTime = dPaidProjectRevTime;
	}
	/**
	 * @return the nvPaidProjectPositionName
	 */
	public String getNvPaidProjectPositionName() {
		return nvPaidProjectPositionName;
	}
	/**
	 * @param nvPaidProjectPositionName the nvPaidProjectPositionName to set
	 */
	public void setNvPaidProjectPositionName(String nvPaidProjectPositionName) {
		this.nvPaidProjectPositionName = nvPaidProjectPositionName;
	}
	/**
	 * @return the nvPaidProjectMaker
	 */
	public String getNvPaidProjectMaker() {
		return nvPaidProjectMaker;
	}
	/**
	 * @param nvPaidProjectMaker the nvPaidProjectMaker to set
	 */
	public void setNvPaidProjectMaker(String nvPaidProjectMaker) {
		this.nvPaidProjectMaker = nvPaidProjectMaker;
	}
	/**
	 * @return the nvPaidProjectRegistrant
	 */
	public String getNvPaidProjectRegistrant() {
		return nvPaidProjectRegistrant;
	}
	/**
	 * @param nvPaidProjectRegistrant the nvPaidProjectRegistrant to set
	 */
	public void setNvPaidProjectRegistrant(String nvPaidProjectRegistrant) {
		this.nvPaidProjectRegistrant = nvPaidProjectRegistrant;
	}

	/**
	 * @return the dPaidProjectTotal
	 */
	public float getdPaidProjectTotal() {
		return dPaidProjectTotal;
	}
	/**
	 * @param dPaidProjectTotal the dPaidProjectTotal to set
	 */
	public void setdPaidProjectTotal(float dPaidProjectTotal) {
		this.dPaidProjectTotal = dPaidProjectTotal;
	}
	/**
	 * @return the iState
	 */
	public int getiState() {
		return iState;
	}
	/**
	 * @param iState the iState to set
	 */
	public void setiState(int iState) {
		this.iState = iState;
	}
	/**
	 * @return the iPaidProjectNo
	 */
	public int getiPaidProjectNo() {
		return iPaidProjectNo;
	}
	/**
	 * @param iPaidProjectNo the iPaidProjectNo to set
	 */
	public void setiPaidProjectNo(int iPaidProjectNo) {
		this.iPaidProjectNo = iPaidProjectNo;
	}
	/**
	 * @return the nvPaidProjectName
	 */
	public String getNvPaidProjectName() {
		return nvPaidProjectName;
	}
	/**
	 * @param nvPaidProjectName the nvPaidProjectName to set
	 */
	public void setNvPaidProjectName(String nvPaidProjectName) {
		this.nvPaidProjectName = nvPaidProjectName;
	}
}
