package com.ezomode.xmpp.service.impl;

import com.ezomode.xmpp.bean.CcsInMessage;
import com.ezomode.xmpp.bean.CcsOutMessage;
import com.ezomode.xmpp.server.CcsClient;
import com.ezomode.xmpp.server.MessageHelper;
import com.ezomode.xmpp.service.PayloadProcessor;
import com.ezomode.xmpp.util.Util;

import java.util.Collection;

/**
 * Handles an upstream message request
 */
public class MessageProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		CcsClient client = CcsClient.getInstance();
		String messageId = Util.getUniqueMessageId();
		String to = inMessage.getDataPayload().get(Util.PAYLOAD_ATTRIBUTE_RECIPIENT);

		// TODO: handle the data payload sent to the client device. Here, I just
		// resend the incoming message.
		CcsOutMessage outMessage = new CcsOutMessage(to, messageId, inMessage.getDataPayload());
		String jsonRequest = MessageHelper.createJsonOutMessage(outMessage);
		client.send(jsonRequest);
	}

	@Override
	public Collection<String> getSupportedActions() {
		return null;
	}

}