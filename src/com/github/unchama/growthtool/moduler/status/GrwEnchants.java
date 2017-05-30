package com.github.unchama.growthtool.moduler.status;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;

import com.github.unchama.gigantic.Gigantic;
import com.github.unchama.growthtool.moduler.tool.GrwTool;
import com.github.unchama.yml.DebugManager;
import com.github.unchama.yml.DebugManager.DebugEnum;

/**
 * Growth Toolのエンチャント設定を保持するためのオブジェクト。<br />
 * 1種類のGrowth Toolにつき1つ生成される。<br />
 * Growth Toolのエンチャントにはこのオブジェクトを介してアクセスできる。<br />
 */
public final class GrwEnchants extends LinkedHashMap<Enchantment, GrwEnchantData> {
	// debug Instance
	private static final DebugManager debug = Gigantic.yml.getManager(DebugManager.class);

	// Growth ToolがLv1の時点におけるデフォルトエンチャント
	private final Map<Enchantment, Integer> defaultEnchant;;

	/**
	 * コンストラクタ。LinkedHashMap<Enchantment, GrwEnchantData>を初期化する。<br />
	 */
	public GrwEnchants(Map<Enchantment, Integer> defaultEnchant) {
		super();
		this.defaultEnchant = Collections.unmodifiableMap(new LinkedHashMap<Enchantment, Integer>(defaultEnchant));
	}

	/**
	 * エンチャント情報を登録する。<br />
	 * 特定のエンチャントに対して、最大エンチャントレベルと解放アイテムレベルを設定する。<br />
	 *
	 * @param enchant 対象エンチャント
	 * @param maxEnchantLv 対象エンチャントの最大エンチャントレベル
	 * @param premiseItemLv 対象エンチャントが付与可能となる解放アイテムレベル
	 */
	public final void put(Enchantment enchant, int maxEnchantLv, int premiseItemLv) {
		if (enchant == null) {
			debug.warning(DebugEnum.GROWTHTOOL, "[GrwEnchants] enchantがnullのためDURABILITYとして扱います。");
			enchant = Enchantment.DURABILITY;
		}
		if (maxEnchantLv <= 0) {
			debug.warning(DebugEnum.GROWTHTOOL, "[GrwEnchants] maxEnchantLvが" + String.valueOf(maxEnchantLv) + "のため1として扱います。");
			maxEnchantLv = 1;
		}
		if (premiseItemLv <= 0) {
			debug.warning(DebugEnum.GROWTHTOOL, "[GrwEnchants] premiseItemLvが" + String.valueOf(premiseItemLv) + "のため1として扱います。");
			premiseItemLv = 1;
		}
		put(enchant, new GrwEnchantData(maxEnchantLv, premiseItemLv));
	}

	/**
	 * デフォルトのエンチャントを付与する。<br />
	 * 無効なGrowth Toolを引数とした場合、何も行わずに返却する。<br />
	 *
	 * @param grwtool デフォルトエンチャント付与対象のGrowth Toolオブジェクト
	 */
	public final Map<Enchantment, Integer> addDefaultEnchant() {
		return defaultEnchant;
/* TODO 仕様変更
 * 		if (grwtool == null) {
			debug.warning(DebugEnum.GROWTHTOOL, "[GrwEnchants] デフォルトエンチャント付与対象がnullのため何も行いません。");
			return;
		}
		for (Enchantment enchant : grwtool.getEnchantments().keySet()) {
			grwtool.removeEnchantment(enchant);
		}
		grwtool.addUnsafeEnchantments(defaultEnchant);
*/
	}
	/**
	 * レベルアップ時にランダムなエンチャントを1Lv付与する。<br />
	 * 無効なGrowth Toolを引数とした場合、何も行わずに返却する。<br />
	 *
	 * @param grwtool エンチャント付与対象のGrowth Toolオブジェクト
	 */
	public final void addEnchant(GrwTool grwtool) {
/* TODO 仕様変更
		if (grwtool == null) {
			debug.warning(DebugEnum.GROWTHTOOL, "[GrwEnchants] エンチャント付与対象がnullのため何も行いません。");
			return;
		}
		final Map<Enchantment, Integer> enchant = new HashMap<Enchantment, Integer>();
		final int currentItemLv = grwtool.getItemLv();
		// 設定されているエンチャント一覧から、付与可能条件を満たしているものをリストアップする
		for (Map.Entry<Enchantment, GrwEnchantData> entry : entrySet()) {
			final int currentEnchantLv = grwtool.getEnchantmentLevel(entry.getKey());
			if (entry.getValue().canAddEnchantment(currentItemLv, currentEnchantLv)) {
				enchant.put(entry.getKey(), currentEnchantLv);
			}
		}
		// 付与
		if (enchant.size() <= 0) {
			// TODO システムメッセージとして対象エンチャント無しを通知する
		} else {
			Map.Entry<Enchantment, Integer> entry = enchant.entrySet().iterator().next();
			grwtool.addUnsafeEnchantment(entry.getKey(), entry.getValue());
		}
		*/
	}
}
