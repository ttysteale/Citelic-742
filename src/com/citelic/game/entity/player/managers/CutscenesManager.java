package com.citelic.game.entity.player.managers;

import com.citelic.game.entity.player.Player;
import com.citelic.game.entity.player.content.cutscenes.Cutscene;
import com.citelic.game.entity.player.content.cutscenes.CutscenesHandler;

public final class CutscenesManager {

	private Player player;
	private Cutscene cutscene;

	/*
	 * cutscene play stuff
	 */

	public CutscenesManager(Player player) {
		this.player = player;
	}

	public boolean hasCutscene() {
		return cutscene != null;
	}

	public void logout() {
		if (hasCutscene())
			cutscene.logout(player);
	}

	public boolean play(Object key) {
		if (hasCutscene()) {
			return false;
		}
		Cutscene cutscene = (Cutscene) (key instanceof Cutscene ? key
				: CutscenesHandler.getCutscene(key));
		if (cutscene == null) {
			return false;
		}
		cutscene.createCache(player);
		this.cutscene = cutscene;
		return true;
	}

	public void process() {
		if (cutscene == null)
			return;
		if (cutscene.process(player))
			return;
		cutscene = null;
	}

}
