package com.ezomode.xmpp.server;

import com.ezomode.xmpp.service.PayloadProcessor;
import com.ezomode.xmpp.service.impl.EchoProcessor;
import com.ezomode.xmpp.service.impl.MessageProcessor;
import com.ezomode.xmpp.service.impl.RegisterProcessor;
import com.ezomode.xmpp.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the creation of different payload processors based on the desired
 * action
 */
public class ProcessorFactory {

    private static Map<String, PayloadProcessor> processorsByAction = new HashMap<String, PayloadProcessor>() {{
        put(Util.BACKEND_ACTION_REGISTER, new RegisterProcessor());
        put(Util.BACKEND_ACTION_ECHO, new EchoProcessor());
        put(Util.BACKEND_ACTION_MESSAGE, new MessageProcessor());
    }};

    public static PayloadProcessor getProcessor(String action) {
        if (action == null) {
            throw new IllegalStateException("ProcessorFactory: Action must not be null");
        }

        if (processorsByAction.containsKey(action))
            return processorsByAction.get(action);

        throw new IllegalStateException("ProcessorFactory: Action " + action + " is unknown (you can add new action/processor pairs through the 'registerProcessor' method)");

    }

    public static <T extends PayloadProcessor> void registerProcessor(T proc) {

        proc.getSupportedActions().forEach(s -> processorsByAction.remove(s));
        proc.getSupportedActions().forEach(s -> processorsByAction.put(s, proc));
    }
}