package com.github.unchama.achievement.achievements;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.github.unchama.achievement.GiganticAchievement;
import com.github.unchama.event.ChainJoinIncrementEvent;

public final class ChainJoinAchievement extends GiganticAchievement implements Listener{
	private final int id;
	/**連続ログイン日数がこの値以上の時に実績を解除します
	 *
	 */
	private final long unlock_chain;

	public ChainJoinAchievement(int id,long unlock_chain) {
		super();
		this.id = id;
		this.unlock_chain = unlock_chain;
	}

	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * @return unlock_chain
	 */
	public long getUnlockChain() {
		return unlock_chain;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void ChainJoinIncrementListener(ChainJoinIncrementEvent event) {
		if (event.getNextAll() >= this.getUnlockChain())
			this.unlockAchievement(event.getGiganticPlayer());
	}
}