package com.robertx22.age_of_exile.mmorpg.registers.common;

import com.robertx22.age_of_exile.maps.MapBlock;
import com.robertx22.age_of_exile.mechanics.base.LeagueBlock;
import com.robertx22.age_of_exile.mmorpg.registers.deferred_wrapper.Def;
import com.robertx22.age_of_exile.mmorpg.registers.deferred_wrapper.RegObj;
import com.robertx22.age_of_exile.vanilla_mc.blocks.BlackHoleBlock;
import com.robertx22.age_of_exile.vanilla_mc.blocks.TotemBlock;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SlashBlocks {

    public static void init() {

    }

    // public static RegObj<RuneWordStationBlock> RUNEWORD = Def.block("runeword_station", () -> new RuneWordStationBlock());

    public static RegObj<BlackHoleBlock> BLACK_HOLE = Def.block("black_hole", () -> new BlackHoleBlock());
    public static RegObj<TotemBlock> BLUE_TOTEM = Def.block("blue_totem", () -> new TotemBlock());
    public static RegObj<TotemBlock> GREEN_TOTEM = Def.block("green_totem", () -> new TotemBlock());
    public static RegObj<TotemBlock> GUARD_TOTEM = Def.block("guard_totem", () -> new TotemBlock());
    public static RegObj<TotemBlock> PROJECTILE_TOTEM = Def.block("attack_totem", () -> new TotemBlock());
    public static RegObj<TotemBlock> THORN_BUSH = Def.block("thorn_bush", () -> new TotemBlock());
    public static RegObj<TotemBlock> MAGMA_FLOWER = Def.block("magma_flower", () -> new TotemBlock());
    public static RegObj<TotemBlock> FROST_FLOWER = Def.block("frost_flower", () -> new TotemBlock());
    public static RegObj<TotemBlock> TRAP = Def.block("trap", () -> new TotemBlock());
    public static RegObj<TotemBlock> GLYPH = Def.block("glyph", () -> new TotemBlock());
    public static RegObj<MapBlock> MAP = Def.block("teleporter", () -> new MapBlock());
    public static RegObj<LeagueBlock> LEAGUE = Def.block("league", () -> new LeagueBlock());


    static RegObj<Block> plant(String id) {
        return Def.block(id, () -> new BeetrootBlock(BlockBehaviour.Properties.of()
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)));
    }

}
