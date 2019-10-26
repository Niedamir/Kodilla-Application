package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloMapperTest {
	public TrelloMapper trelloMapper = new TrelloMapper();

	@Test
	public void mapToBoards() {
		//Given
		List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
		List<TrelloListDto> trelloListDto = new ArrayList<>();
		trelloListDto.add(new TrelloListDto("1", "testList", false));
		trelloBoardDto.add(new TrelloBoardDto("1", "test", trelloListDto));
		//when
		List<TrelloBoard> output = trelloMapper.mapToBoards(trelloBoardDto);
		//then
		assertEquals("1", output.get(0).getId());
		assertEquals("test", output.get(0).getName());
	}
	@Test
	public void mapToBoardsDto() {
		//Given
		List<TrelloBoard> trelloBoard = new ArrayList<>();
		List<TrelloList> trelloList = new ArrayList<>();
		trelloList.add(new TrelloList("1", "testList", false));
		trelloBoard.add(new TrelloBoard("1", "test", trelloList));
		//when
		List<TrelloBoardDto> output = trelloMapper.mapToBoardsDto(trelloBoard);
		//then
		assertEquals("1", output.get(0).getId());
		assertEquals("test", output.get(0).getName());
	}
	@Test
	public void mapToList() {
		//Given
		List<TrelloListDto> trelloListDto = new ArrayList<>();
		trelloListDto.add(new TrelloListDto("1", "testList", false));
		//when
		List<TrelloList> output = trelloMapper.mapToList(trelloListDto);
		//then
		assertEquals("1", output.get(0).getId());
		assertEquals("testList", output.get(0).getName());
		assertEquals(false, output.get(0).isClosed());
	}

	@Test
	public void mapToListDto() {
		//Given
		List<TrelloList> trelloList = new ArrayList<>();
		trelloList.add(new TrelloList("1", "testList", false));
		//when
		List<TrelloListDto> output = trelloMapper.mapToListDto(trelloList);
		//then
		assertEquals("1", output.get(0).getId());
		assertEquals("testList", output.get(0).getName());
		assertEquals(false, output.get(0).isClosed());
	}
	@Test
	public void mapToCardDto() {
		//Given
		TrelloCard trelloCard = new TrelloCard("test", "test", "1", "1");
		//when
		TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
		//then
		assertEquals("test", trelloCardDto.getName());
		assertEquals("test", trelloCardDto.getDescription());
		assertEquals("1", trelloCardDto.getPos());
		assertEquals("1", trelloCardDto.getListId());
	}
	@Test
	public void mapToCard() {	//Given
		TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "1", "1");
		//when
		TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
		//then
		assertEquals("test", trelloCardDto.getName());
		assertEquals("test", trelloCardDto.getDescription());
		assertEquals("1", trelloCardDto.getPos());
		assertEquals("1", trelloCardDto.getListId());
	}
}