package com.robertx22.age_of_exile.capability.player.helper;

import com.robertx22.age_of_exile.database.data.spells.components.Spell;
import com.robertx22.age_of_exile.saveclasses.skill_gem.SkillGemData;
import com.robertx22.age_of_exile.saveclasses.spells.SpellCastingData;
import com.robertx22.age_of_exile.uncommon.datasaving.StackSaving;
import com.robertx22.age_of_exile.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.library_of_exile.wrappers.ExileText;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocketedGem {

    MyInventory inv;

    public SpellCastingData.InsertedSpell spell;

    public int skillGem = 0;

    public SocketedGem(MyInventory inv, SpellCastingData.InsertedSpell spell, int skillGem) {
        this.inv = inv;
        this.spell = spell;
        this.skillGem = skillGem;
    }


    public int getMaxLinks() {
        if (getSkillData() == null) {
            return 0;
        } else {
            return getSkillData().links;
        }
    }

    public void removeSupportGemsIfTooMany(Player p) {

        if (getSupportDatas().size() > getMaxLinks()) {
            for (ItemStack s : this.getSupports()) {
                PlayerUtils.giveItem(s.copy(), p);
                s.shrink(100);
            }
            p.sendSystemMessage(ExileText.ofText("You can't equip that many Support Gems! You can increase the number of slots by leveling the skill.").get());
        }


        HashMap<String, Integer> map = new HashMap<>();

        boolean toomany = false;

        for (SkillGemData data : this.getSupportDatas()) {
            if (data.getSupport() == null) {
                continue;
            }
            map.put(data.getSupport().id, map.getOrDefault(data.getSupport().id, 0) + 1);
            if (map.get(data.getSupport().id) > 1) {
                toomany = true;
                break;
            }
            if (data.getSupport().isOneOfAKind()) {
                String id = data.getSupport().one_of_a_kind;
                map.put(id, map.getOrDefault(id, 0) + 1);

                if (map.get(id) > 1) {
                    toomany = true;
                    break;
                }
            }
        }

        if (toomany) {
            for (ItemStack s : this.getSupports()) {
                PlayerUtils.giveItem(s.copy(), p);
                s.shrink(100);
                p.sendSystemMessage(ExileText.ofText("You can't use multiple of those Support Gems.").get());

            }
        }

    }


    public int getHotbarSlot() {
        return skillGem / (GemInventoryHelper.SUPPORT_GEMS_PER_SKILL + 1); // todo is this correct
    }

    public Spell getSpell() {
        var data = getSkillData();
        if (data != null) {
            return data.getSpell();
        }
        return null;
    }


    public SkillGemData getSkillData() {
        return this.spell.getData();
    }

    public List<ItemStack> getSupports() {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 1 + skillGem; i < skillGem + GemInventoryHelper.SUPPORT_GEMS_PER_SKILL + 1; i++) {
            list.add(inv.getItem(i));
        }
        return list;
    }

    public List<SkillGemData> getSupportDatas() {
        List<SkillGemData> list = new ArrayList<>();
        for (ItemStack s : getSupports()) {
            SkillGemData d = StackSaving.SKILL_GEM.loadFrom(s);
            if (d != null && d.getSupport() != null) {
                list.add(d);
            }
        }
        return list;
    }

    public float getManaCostMulti() {

        float multi = 1;

        for (SkillGemData data : this.getSupportDatas()) {
            multi *= data.getSupport().manaMulti;

        }

        return multi;
    }

}
