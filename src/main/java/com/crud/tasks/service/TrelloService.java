package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mapping.Alias.ofNullable;

@Service
public class TrelloService {
	private static String SUBJECT = "Created Trello Card";
	@Autowired
	private TrelloClient trelloClient;
	@Autowired
	private SimpleEmailService emailService;
	@Autowired
	private AdminConfig adminConfig;

	public List<TrelloBoardDto> fetchTrelloBoards() {
		return trelloClient.getTrelloBoards();
	}

	public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {
		CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
		ofNullable(newCard).isPresent(card -> emailService.send(new Mail(
			adminConfig.getAdminMail(),
			"",
			SUBJECT,
			"New Card: " + card.getName() + " has been created on Your Trello acoount")));
		return newCard;
	}
}
