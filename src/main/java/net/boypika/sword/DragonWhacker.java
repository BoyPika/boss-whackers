package net.boypika.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;

public class DragonWhacker extends SwordItem {
    public DragonWhacker(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity){
            target.damage(target.getRecentDamageSource(), 100000000000f);
        }
        else {
            attacker.sendMessage(Text.translatable("text.item.bosswhackers.event_fail_message.dragon_whacker"));
            target.heal(1f);
        }
        return true;
    }
}
