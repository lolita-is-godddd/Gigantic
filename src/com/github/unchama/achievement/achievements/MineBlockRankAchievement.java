package com.github.unchama.achievement.achievements;

import org.bukkit.event.Listener;

import com.github.unchama.achievement.GiganticAchievement;

/**整地ランキングに応じた実績クラス
*
* @author tar0ss
*
*/
public final class MineBlockRankAchievement extends GiganticAchievement implements Listener {

	/**ランキングがこの値以上の時に実績を解除します
	 *
	 */
	private final long unlock_rank;

	public MineBlockRankAchievement(int id, long unlock_rank) {
		super(id);
		this.unlock_rank = unlock_rank;
	}

	/**
	 * @return unlock_num
	 */
	public long getUnlockRank() {
		return unlock_rank;
	}

	/**ランキングリセットのため一度保留
	@EventHandler(priority = EventPriority.MONITOR)
	public void updateRankListener(updateRankEvent event) {
		if (TotalMineBlockRankingMenuManager.class.isAssignableFrom(event.getRankClass())) {
			String name = event.getName();
			GiganticPlayer gp = PlayerManager.getGiganticPlayer(name);
			int rank = event.getRank();
			if (gp != null && rank <= this.getUnlockRank()) {
				this.unlockAchievement(gp);
			}
		}
	}
	*/
	@Override
	public String getUnlockInfo() {
		return "累計整地量ランキングで" + this.getUnlockRank() + "位になる";
	}

	@Override
	public String getLockInfo() {
		return "累計整地量ランキングで" + this.getUnlockRank() + "位になる";
	}

	@Override
	public int getPoint() {
		return 10;
	}

	@Override
	public int getUsePoint() {
		return 0;
	}

	@Override
	public boolean isPurchasable() {
		return false;
	}

}
