package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
	@Autowired
	private AdminConfig adminConfig;
	@Autowired
	@Qualifier("templateEngine")
	private TemplateEngine templateEngine;

	public String buildTrelloCardEmail(String message) {
		Context context = new Context();
		context.setVariable("message", message);
		context.setVariable("tasks_url", "http://localhost:8800/crud");
		context.setVariable("button", "Visit website");
		context.setVariable("admin_name", adminConfig.getAdminName());
		context.setVariable("topic", "trello Card Created");
		context.setVariable("goodbye", "best regards");
		context.setVariable("companyName", adminConfig.getCompanyName());
		context.setVariable("companyMail", adminConfig.getCompanyMail());
		context.setVariable("companyPhone", adminConfig.getCompanyPhone());
		return templateEngine.process("mail/created-trello-card-mail", context);
	}
}
