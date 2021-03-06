package com.citelic.game.entity.player.content.dialogue.impl.simplemessages;

import com.citelic.game.entity.player.content.dialogue.Dialogue;

public class SimplePlayerMessage extends Dialogue {

	@Override
	public void finish() {

	}

	@Override
	public void run(int interfaceId, int componentId) {
		end();
	}

	@Override
	public void start() {
		String[] messages = new String[parameters.length];
		for (int i = 0; i < messages.length; i++)
			messages[i] = (String) parameters[i];
		sendPlayerDialogue(9827, messages);
	}

}
