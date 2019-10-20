package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTestSuit {
	public TrelloFacade trelloFacade = new TrelloFacade();

	@Test
	public void fetchTrelloBoardsTest() {
		//Given
		//When
		trelloFacade.fetchTrelloBoards();
		//Then

	}
}
