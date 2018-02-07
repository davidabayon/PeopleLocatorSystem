package com.pointwest.pls.bean;

public class Seat {
	private String seatId;
	private Building seatBuilding;
	private String seatFloorNumber;
	private String seatQuadrant;
	private String seatRowNumber;
	private String seatColumnNumber;
	private String seatLocalNumber;
	private String seatName;

	public Seat() {
		this.seatBuilding = new Building();
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public Building getSeatBuilding() {
		return seatBuilding;
	}

	public String getSeatFloorNumber() {
		return seatFloorNumber;
	}

	public void setSeatFloorNumber(String seatFloorNumber) {
		this.seatFloorNumber = seatFloorNumber;
	}

	public String getSeatQuadrant() {
		return seatQuadrant;
	}

	public void setSeatQuadrant(String seatQuadrant) {
		this.seatQuadrant = seatQuadrant;
	}

	public String getSeatRowNumber() {
		return seatRowNumber;
	}

	public void setSeatRowNumber(String seatRowNumber) {
		this.seatRowNumber = seatRowNumber;
	}

	public String getSeatColumnNumber() {
		return seatColumnNumber;
	}

	public void setSeatColumnNumber(String seatColumnNumber) {
		this.seatColumnNumber = seatColumnNumber;
	}

	public String getSeatLocalNumber() {
		return seatLocalNumber;
	}

	public void setSeatLocalNumber(String seatLocalNumber) {
		this.seatLocalNumber = seatLocalNumber;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

}