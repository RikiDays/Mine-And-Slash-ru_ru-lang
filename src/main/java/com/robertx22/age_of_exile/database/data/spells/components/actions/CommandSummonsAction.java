package com.robertx22.age_of_exile.database.data.spells.components.actions;

import com.robertx22.age_of_exile.database.data.spells.components.MapHolder;
import com.robertx22.age_of_exile.database.data.spells.map_fields.MapField;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.SpellCtx;
import com.robertx22.age_of_exile.uncommon.utilityclasses.AllyOrEnemy;
import com.robertx22.age_of_exile.uncommon.utilityclasses.EntityFinder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

import java.util.Arrays;
import java.util.Collection;

public class CommandSummonsAction extends SpellAction {
    public CommandSummonsAction() {
        super(Arrays.asList());
    }

    @Override
    public void tryActivate(Collection<LivingEntity> targets, SpellCtx ctx, MapHolder data) {

        float radius = data.getOrDefault(MapField.RADIUS, 10D).floatValue();

        if (ctx.target == null) {
            return;
        }


        for (LivingEntity en : EntityFinder.start(ctx.caster, LivingEntity.class, ctx.pos)
                .finder(EntityFinder.SelectionType.RADIUS)
                .searchFor(AllyOrEnemy.pets)
                .radius(radius).build()) {

            if (en instanceof Mob mob) {
                mob.setTarget(ctx.target);
            }
        }


    }

    public MapHolder create() {
        MapHolder dmg = new MapHolder();
        dmg.type = GUID();
        return dmg;
    }

    @Override
    public String GUID() {
        return "command_summons";
    }
}