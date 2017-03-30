package com.ezomode.xmpp.service.impl;

import com.ezomode.xmpp.bean.CcsInMessage;
import com.ezomode.xmpp.service.PayloadProcessor;

import java.util.Collection;

/**
 * Handles a user registration request
 */
public class RegisterProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsInMessage msg) {
		// TODO: handle the user registration. Keep in mind that a user name can
		// have more reg IDs associated. The messages IDs should be uniques. 
	}

	@Override
	public Collection<String> getSupportedActions() {
		return null;
	}

}