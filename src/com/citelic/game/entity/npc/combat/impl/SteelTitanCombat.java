package com.citelic.game.entity.npc.combat.impl;

import com.citelic.game.engine.Engine;
import com.citelic.game.entity.Animation;
import com.citelic.game.entity.Entity;
import com.citelic.game.entity.Graphics;
import com.citelic.game.entity.npc.NPC;
import com.citelic.game.entity.npc.combat.CombatScript;
import com.citelic.game.entity.npc.combat.NPCCombatDefinitions;
import com.citelic.game.entity.npc.impl.familiar.Familiar;
import com.citelic.utility.Utilities;

public class SteelTitanCombat extends CombatScript {

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int distanceX = target.getX() - npc.getX();
		int distanceY = target.getY() - npc.getY();
		boolean distant = false;
		int size = npc.getSize();
		Familiar familiar = (Familiar) npc;
		int damage = 0;
		if (distanceX > size || distanceX < -1 || distanceY > size
				|| distanceY < -1)
			distant = true;
		if (familiar.titanSpec) {// priority over regular attack
			npc.setNextAnimation(new Animation(8190));
			target.setNextGraphics(new Graphics(1449));
			familiar.titanSpec = false;
			if (distant) {// range hit
				delayHit(
						npc,
						2,
						target,
						getRangeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.RANGE, target)),
						getRangeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.RANGE, target)),
						getRangeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.RANGE, target)),
						getRangeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.RANGE, target)));
			} else {// melee hit
				delayHit(
						npc,
						1,
						target,
						getMeleeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.MELEE, target)),
						getMeleeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.MELEE, target)),
						getMeleeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.MELEE, target)),
						getMeleeHit(
								npc,
								getRandomMaxHit(npc, 244,
										NPCCombatDefinitions.MELEE, target)));
			}
		} else {
			if (distant) {
				int attackStage = Utilities.getRandom(1);// 2
				switch (attackStage) {
				case 0:// magic
					damage = getRandomMaxHit(npc, 255,
							NPCCombatDefinitions.MAGE, target);
					npc.setNextAnimation(new Animation(7694));
					Engine.sendProjectile(npc, target, 1451, 34, 16, 30, 35,
							16, 0);
					delayHit(npc, 2, target, getMagicHit(npc, damage));
					break;
				case 1:// range
					damage = getRandomMaxHit(npc, 244,
							NPCCombatDefinitions.RANGE, target);
					npc.setNextAnimation(new Animation(8190));
					Engine.sendProjectile(npc, target, 1445, 34, 16, 30, 35,
							16, 0);
					delayHit(npc, 2, target, getRangeHit(npc, damage));
					break;
				}
			} else {// melee
				damage = getRandomMaxHit(npc, 244, NPCCombatDefinitions.MELEE,
						target);
				npc.setNextAnimation(new Animation(8183));
				delayHit(npc, 1, target, getMeleeHit(npc, damage));
			}
		}
		return defs.getAttackDelay();
	}

	@Override
	public Object[] getKeys() {
		return new Object[] { 7344, 7343 };
	}
}
