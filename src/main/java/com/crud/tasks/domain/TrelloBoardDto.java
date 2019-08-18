package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBoardDto {
	@Value("${trello.api.endpoint.prod}")
	private String trelloApiEndpoint;
	@Value("${trello.app.key}")
	private String trelloAppKey;
	@Value("${trello.app.token}")
	private String trelloToken;
	@Value("${trello.app.username}")
	private String trelloUsername;

	@Autowired
	private RestTemplate restTemplate;
	private List<TrelloBoardDto> getTrelloBoards() {
		URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
			.queryParam("key", trelloAppKey)
			.queryParam("token", trelloToken)
			.queryParam("fields", "name,id").build().encode().toUri();
		TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
		if (boardsResponse != null) {
			return Arrays.asList(boardsResponse);
		}
		return new ArrayList<>();
	}
}