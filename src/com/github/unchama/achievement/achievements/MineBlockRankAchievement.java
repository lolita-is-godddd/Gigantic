package com.github.unchama.achievement.achievements;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.github.unchama.achievement.GiganticAchievement;
import com.github.unchama.event.updateRankEvent;
import com.github.unchama.gigantic.PlayerManager;
import com.github.unchama.gui.ranking.mineblock.TotalMineBlockRankingMenuManager;
import com.github.unchama.player.GiganticPlayer;
import com.github.unchama.yml.DebugManager.DebugEnum;

/**整地ランキングに応じた実績クラス
*
* @author tar0ss
*
*/
public final class MineBlockRankAchievement extends GiganticAchievement implements Listener{

	private final int id;
	/**ランキングがこの値以上の時に実績を解除します
	 *
	 */
	private final long unlock_rank;

	public MineBlockRankAchievement(int id,long unlock_rank) {
		super();
		this.id = id;
		this.unlock_rank = unlock_rank;
	}

	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * @return unlock_num
	 */
	public long getUnlockRank() {
		return unlock_rank;
	}


	@EventHandler(priority = EventPriority.MONITOR)
	public void updateRankListener(updateRankEvent event){
		if(TotalMineBlockRankingMenuManager.class.isAssignableFrom(event.getRankClass())){
			String name = event.getName();
			GiganticPlayer gp = PlayerManager.getGiganticPlayer(name);
			int rank = event.getRank();
			if(gp != null && rank <= this.getUnlockRank()){
				this.unlockAchievement(gp);
			}else{
				debug.info(DebugEnum.ACHIEVEMENT, "locked");
			}
		}
	}



}