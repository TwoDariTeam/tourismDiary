package com.team.twodari.board.entity;

public enum BoardLocation {
	SEOUL,JEJU ,EMPTY;

	public static BoardLocation getLocation(String location) {
		BoardLocation boardLocation;
		try {
			boardLocation = BoardLocation.valueOf(location.toUpperCase());
		} catch (IllegalArgumentException e) {
			boardLocation = BoardLocation.EMPTY; // 기본값 설정
		}
		return boardLocation;
	}


}
