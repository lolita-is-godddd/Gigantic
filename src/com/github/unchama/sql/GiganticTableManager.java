package com.github.unchama.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.unchama.player.GiganticPlayer;
import com.github.unchama.sql.moduler.PlayerTableManager;


public class GiganticTableManager extends PlayerTableManager{

	public GiganticTableManager(Sql sql){
		super(sql);
	}
	/*
	@Override
	protected String addOriginalColumn() {
		String command = "";
		//playtick,seichiloadedflag add
		command += "add column if not exists playtick bigint unsigned default 0,"
				+ "add column if not exists seichi_loadead boolean default false,";
		return command;
	}

	@Override
	protected boolean newPlayer(GiganticPlayer gp) {
		GiganticManager m = gp.getManager(GiganticManager.class);
		return true;
	}

	@Override
	public void loadPlayer(GiganticPlayer gp,ResultSet rs){
		GiganticManager m = gp.getManager(GiganticManager.class);

		return;
	}

	@Override
	protected String savePlayer(GiganticPlayer gp) {

		return "";
	}
	*/





	@Override
	protected
	boolean newPlayer(GiganticPlayer gp) {
		return true;

/*
			//初見さんにLv1メッセージを送信
			p.sendMessage(SeichiAssist.config.getLvMessage(1));
			//初見さんであることを全体告知
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "【初見キタ】" + p.getName() + "のプレイヤーデータ作成完了");
			Util.sendEveryMessage(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+name+"さんは初参加です。整地鯖へヨウコソ！" + ChatColor.RESET +" - " + ChatColor.YELLOW + ChatColor.UNDERLINE +  "http://seichi.click");
			Util.sendEverySound(Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			//初見プレイヤーに木の棒、エリトラ、ピッケルを配布
			p.getInventory().addItem(new ItemStack(Material.STICK));
			p.getInventory().addItem(new ItemStack(Material.ELYTRA));
			p.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
			p.getInventory().addItem(new ItemStack(Material.DIAMOND_SPADE));
			MebiusListener.give(p);
*/

	}
	@Override
	protected String addOriginalColumn() {
		// TODO 自動生成されたメソッド・スタブ
		return "";
	}
	@Override
	public void loadPlayer(GiganticPlayer gp, ResultSet rs) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	protected String savePlayer(GiganticPlayer gp) {
		// TODO 自動生成されたメソッド・スタブ
		return "";
	}




}
