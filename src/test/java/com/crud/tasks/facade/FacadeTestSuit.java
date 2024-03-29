package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTestSuit {
	@InjectMocks
	private TrelloFacade trelloFacade;
	@Mock
	private TrelloService trelloService;
	@Mock
	private TrelloValidator trelloValidator;
	@Mock
	private TrelloMapper trelloMapper;

	@Test
	public void fetchTrelloBoardsTest() {
		//Given
		List<TrelloListDto> trelloLists = new ArrayList<>();
		trelloLists.add(new TrelloListDto("1", "test_list", false));
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
		List<TrelloList> mappedTrelloLists = new ArrayList<>();
		mappedTrelloLists.add(new TrelloList("1", "test_list", false));
		List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
		mappedTrelloBoards.add(new TrelloBoard( "1", "test", mappedTrelloLists));

		when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
		when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
		when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
		when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
		//When
		List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
		//Then
		assertNotNull(trelloBoardDtos);
		assertEquals(0, trelloBoardDtos.size());
	}
	@Test
	public void createdCardTest() {
		//Given
		CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("1", "testName", "url");
		TrelloCardDto trelloCard = new TrelloCardDto("testName", "testDesc", "testPOS", "1");
		TrelloCard mappedTrelloCard = new TrelloCard("testName", "testDesc", "testPOS", "1");

		when(trelloService.createTrelloCard(trelloCard)).thenReturn(createdTrelloCard);
		when(trelloMapper.mapToCard(trelloCard)).thenReturn(mappedTrelloCard);
		when(trelloMapper.mapToCardDto(mappedTrelloCard)).thenReturn(trelloCard);
		//When
		CreatedTrelloCardDto trelloCardDto = trelloFacade.createdCard(trelloCard);
		//Then
		assertNotNull(trelloCardDto);
		assertEquals("1", trelloCardDto.getId());
		assertEquals("testName", trelloCardDto.getName());
	}
}
