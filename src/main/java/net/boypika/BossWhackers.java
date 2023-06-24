package net.boypika;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

public class BossWhackers implements ModInitializer {
	public static final ToolItem WITHER_WHACKER = new SwordItem(ToolMaterials.DIAMOND, 396, -3.5f, new FabricItemSettings().rarity(Rarity.UNCOMMON));
	public static final ToolItem DRAGON_WHACKER = new SwordItem(ToolMaterials.NETHERITE, 795,-3.9f, new FabricItemSettings().fireproof().rarity(Rarity.EPIC));
	public static final ToolItem WARDEN_WHACKER = new SwordItem(ToolMaterials.NETHERITE, 495, -3.6f, new FabricItemSettings().fireproof().rarity(Rarity.RARE));

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.add(WITHER_WHACKER);
			entries.add(DRAGON_WHACKER);
			entries.add(WARDEN_WHACKER);
		});
		Registry.register(Registries.ITEM, new Identifier("bosswhackers", "wither_whacker"), WITHER_WHACKER);
		Registry.register(Registries.ITEM, new Identifier("bosswhackers", "dragon_whacker"), DRAGON_WHACKER);
		Registry.register(Registries.ITEM, new Identifier("bosswhackers", "warden_whacker"), WARDEN_WHACKER);
		System.out.println("[1.19.3 - 1.20.1] Boss Whackers Init");
	}
}