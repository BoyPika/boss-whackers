package net.boypika;

import net.boypika.config.ModConfig;
import net.boypika.sword.DragonWhacker;
import net.boypika.sword.WardenWhacker;
import net.boypika.sword.WitherWhacker;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;
import top.offsetmonkey538.monkeyconfig538.ConfigManager;

public class BossWhackers implements ModInitializer {
	public static String MOD_ID = "bosswhackers";
	public static SwordItem WITHER_WHACKER;
	public static SwordItem DRAGON_WHACKER;
	public static SwordItem WARDEN_WHACKER;
	public static void registerItems() {
		if (config().WitherWhacker){
			WITHER_WHACKER = new WitherWhacker(ToolMaterials.DIAMOND, -3, -3.5f, new FabricItemSettings().rarity(Rarity.UNCOMMON));
		}
		if (config().DragonWhacker){
			DRAGON_WHACKER = new DragonWhacker(ToolMaterials.NETHERITE, -4,-3.9f, new FabricItemSettings().fireproof().rarity(Rarity.EPIC));
		}
		if (config().WardenWhacker){
			WARDEN_WHACKER = new WardenWhacker(ToolMaterials.NETHERITE, -4, -3.6f, new FabricItemSettings().fireproof().rarity(Rarity.RARE));
		}
	}

	@Override
	public void onInitialize() {
		ConfigManager.init(new ModConfig(), MOD_ID);
		registerItems();
		if (config().WitherWhacker){
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "wither_whacker"), WITHER_WHACKER);
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(Items.NETHERITE_SWORD, WITHER_WHACKER));
		}
		if (config().DragonWhacker) {
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "dragon_whacker"), DRAGON_WHACKER);
			if(config().WitherWhacker){
				ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(WITHER_WHACKER, DRAGON_WHACKER));
			} else {
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(Items.NETHERITE_SWORD, DRAGON_WHACKER));
			}
		}
		if (config().WardenWhacker) {
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "warden_whacker"), WARDEN_WHACKER);
			if (!config().WitherWhacker && !config().DragonWhacker){
				ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(Items.NETHERITE_SWORD, WARDEN_WHACKER));
			} else if (!config().WitherWhacker && config().DragonWhacker || config().WitherWhacker && config().DragonWhacker) {
				ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(DRAGON_WHACKER, WARDEN_WHACKER));
			} else if (config().WitherWhacker && !config().DragonWhacker) {
				ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(WITHER_WHACKER, WARDEN_WHACKER));
			}
		}
		System.out.println("[1.19.3 - 1.20.2] Boss Whackers Init");
	}
	public static ModConfig config() {
		return (ModConfig) ConfigManager.get(MOD_ID);
	}
}