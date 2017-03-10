package com.github.unchama.player.mana;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.github.unchama.gigantic.PlayerManager;
import com.github.unchama.player.GiganticPlayer;
import com.github.unchama.player.moduler.DataManager;
import com.github.unchama.player.moduler.Initializable;
import com.github.unchama.player.moduler.UsingSql;
import com.github.unchama.player.seichilevel.SeichiLevelManager;
import com.github.unchama.sql.ManaTableManager;
import com.github.unchama.util.Util;

public class ManaManager extends DataManager implements Initializable, UsingSql{

	private double m;
	private double max;
	BossBar manabar;
	ManaTableManager tm;

	public ManaManager(GiganticPlayer gp) {
		super(gp);
		tm = sql.getManager(ManaTableManager.class);
	}

	@Override
	public void init() {
		this.updateMaxMana();
		Player player = PlayerManager.getPlayer(gp);
		display(player);
	}

	@Override
	public void save(Boolean loginflag) {
		tm.save(gp, loginflag);
	}

	/**
	 * 現在マナをマナバーに表示します
	 *
	 * @param player
	 * @param level
	 */
	public void display(Player player) {
		if (manabar != null)
			removeBar();

		if (max == 0)
			return;

		manabar = Bukkit.getServer().createBossBar(
				ChatColor.AQUA + "" + ChatColor.BOLD + "マナ(" + Util.Decimal(m)
						+ "/" + max + ")", BarColor.BLUE, BarStyle.SOLID);

		double progress = 0;
		if (m / max > 1.0) {
			progress = 1.0;
		} else {
			progress = m / max;
		}

		manabar.setProgress(progress);
		manabar.addPlayer(player);
	}
	/**最大マナに占める現在マナの割り合いを更新します．
	 * 現在マナが最大マナを超えている場合1となります．
	 * @return
	 */
	private void updateBar(){
		manabar.setProgress( m / max > 1.0 ? 1.0 : m / max);
	}
	/**iだけマナを増加させます．maxを超えた場合はmaxとなります．
	 *
	 * @param i
	 * @param level
	 */
	public void increase(double i){
		this.m += i;
		if(m > max) m = max;
		this.updateBar();
	}
	/**dだけマナを減少させます．０以下にはなりません．
	 *
	 * @param d
	 */
	public void decrease(double d){
		this.m -= d;
		if(m < 0) m = 0;
		this.updateBar();
	}
	/**hのマナを持っている時はtrueを出力します．
	 *
	 * @param h
	 * @return
	 */
	public boolean hasMana(double h){
		return m >= h ? true : false;
	}
	/**
	 * 現在のバーを削除します
	 */
	public void removeBar() {
		try {
			manabar.removeAll();
		} catch (NullPointerException e) {
		}
	}

	/**
	 * 現在マナを取得します．
	 *
	 * @return
	 */
	public double getMana() {
		return this.m;
	}

	/**
	 * 現在マナを設定します．
	 *
	 * @param mana
	 */
	public void setMana(double mana) {
		this.m = mana;
	}

	/**マナ値を最大マナにします．（全回復）
	 *
	 */
	public void fullMana(){
		this.m = this.max;
	}

	/**
	 * 現在のレベルに応じてMaxManaを変更します．
	 *
	 */
	public void updateMaxMana() {
		int level = gp.getManager(SeichiLevelManager.class).getLevel();
		this.max = SeichiLevelManager.levelmap.get(level).getMaxMana();
	}

	/**ユーザーのレベルアップ時に実行します．
	 *
	 */
	public void Levelup(){
		this.updateMaxMana();
		this.fullMana();
		Player player = PlayerManager.getPlayer(gp);
		this.display(player);
	}

}
