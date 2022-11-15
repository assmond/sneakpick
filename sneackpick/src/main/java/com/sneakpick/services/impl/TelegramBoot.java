package com.sneakpick.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.sneakpick.services.ProductService;

@Service
public class TelegramBoot extends TelegramLongPollingBot {

	@Value("${telegram.bot.name}")
	private String telegram_bot_name;
	@Value("${telegram.bot.key}")
	private String telegram_bot_key;

	@Autowired
	private ProductService productService;

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage()) {
			var msg = update.getMessage();
			var chatId = msg.getChatId();
			try {
				var reply = msg.getText().contains("Hi") ? "/products" : "Sneakpick: Hi";
				sendNotification(String.valueOf(chatId), reply);

				var reply2 = msg.getText().contains("/products") ? productService.findAll().toString() + " "
						: "Sneakpick: try this one /products";
				sendNotification(String.valueOf(chatId), reply2);
			} catch (TelegramApiException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void sendNotification(String chatId, String msg) throws TelegramApiException {
		var response = new SendMessage(chatId, msg);
		execute(response);
	}

	@Override
	public String getBotUsername() {
		return this.telegram_bot_name;
	}

	@Override
	public String getBotToken() {
		return this.telegram_bot_key;
	}
}
