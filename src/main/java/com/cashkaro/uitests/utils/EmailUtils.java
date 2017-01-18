package com.cashkaro.uitests.utils;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailUtils {

	public String getMail(String user, String password, String subject) {

		Folder inbox = null;
		Store store = null;
		String mailbody = null;

		try {
			// login to email
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			store.connect("smtp.gmail.com", user, password);

			// get inbox
			inbox = store.getFolder("INBOX");

			// open inbox in read only mode
			inbox.open(Folder.READ_ONLY);

			// get all messages
			Message[] messages = inbox.getMessages();

			// get the mail matching given subject
			for (int i = messages.length - 1; i > 0; i--) {

				Message message = messages[i];

				if (message.getSubject().contains(subject)) {

					Multipart multiPart = (Multipart) message.getContent();

					BodyPart bodyPart = multiPart.getBodyPart(0);

					mailbody = bodyPart.getContent().toString();
					break;
				}
			}

			if (mailbody == null) {
				return null;
			}

		} catch (NoSuchProviderException e) {

			e.printStackTrace();

		} catch (MessagingException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// close the folder objects
			try {
				if (inbox != null) {

					try {

						if (inbox.isOpen()) {

							inbox.close(true);

						}
					} catch (MessagingException e) {

						e.printStackTrace();

					}
				}

				// close the store objects
				store.close();

			} catch (MessagingException e) {

				e.printStackTrace();

			}
		}

		return mailbody;
	}

}
