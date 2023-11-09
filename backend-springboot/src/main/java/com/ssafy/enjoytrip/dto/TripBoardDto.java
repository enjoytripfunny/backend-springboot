package com.ssafy.enjoytrip.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripBoardDto {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
//	private List<CommentDto> comments;
}
