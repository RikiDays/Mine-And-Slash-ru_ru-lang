package com.robertx22.age_of_exile.database.data.stats.name_regex;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.saveclasses.item_classes.tooltips.TooltipStatWithContext;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import com.robertx22.age_of_exile.uncommon.localization.Formatter;
import com.robertx22.age_of_exile.uncommon.utilityclasses.NumberUtils;
import com.robertx22.age_of_exile.uncommon.utilityclasses.StringUTIL;
import com.robertx22.library_of_exile.utils.CLOC;
import net.minecraft.ChatFormatting;

public abstract class StatNameRegex {

    public static StatNameRegex BASIC = new BasicStatRegex();
    public static StatNameRegex BASIC_LOCAL = new BasicLocalStatRegex().setAddPlus(false);
    public static StatNameRegex JUST_NAME = new JustNameRegex();

    public static String VALUE = "VALUE";

    public static String MIN_VALUE = "MIN_VALUE";
    public static String MAX_VALUE = "MAX_VALUE";

    public static String NAME = "STAT_NAME";

    public boolean addPlus = true;

    public static String VALUEAndNAMESeparator = " ";

    protected StatNameRegex setAddPlus(boolean bool) {
        this.addPlus = bool;
        return this;

    }

    public ChatFormatting statColor(Stat stat) {
        return ChatFormatting.GRAY;
    }

    public ChatFormatting numberColor(ChatFormatting format, Stat stat, float val) {

        if (format != null) {
            return format;
        }

        if (val > 0) {
            return ChatFormatting.GREEN;
        } else {
            return ChatFormatting.RED;
        }
    }

    public abstract String getStatNameRegex(ChatFormatting format, ModType type, Stat stat, float v1);

    public String translate(ChatFormatting format, TooltipStatWithContext ctx, ModType type, float v1, Stat stat) {

        String plusminus = v1 > 0 && addPlus ? "+" : "";

        String perc = "";

        if (type.isPercent() || stat.IsPercent()) {
            perc = "%";
        }
        String add = "";


        if (type == ModType.MORE) {
            if (v1 > 0) {
                add = stat.getMultiUseType().prefixWord.locName().getString();
            } else {
                add = stat.getMultiUseType().prefixLessWord.locName().getString();
            }
        }

        String v1s = NumberUtils.formatForTooltip(v1);

        if (stat.is_long) {
            String txt = CLOC.translate(stat.locName());

            txt = txt.replace(Stat.VAL1, plusminus + v1s); // todo dont think i need to add % here because i add it whenever needed in the manual long tooltip

            return txt;
        }


        String str = statColor(stat) + getStatNameRegex(format, type, stat, v1);


        str = str.replace(VALUE, numberColor(format, stat, v1) + plusminus + v1s + perc + VALUEAndNAMESeparator + ChatFormatting.RESET + statColor(stat));


        String[] processedReplacement = StringUTIL.processStrings(add, stat.locName().getString());

        str = str.replace(NAME, Formatter.SPECIAL_CALC_STAT.locName((Object[]) processedReplacement).getString());

        str = Stat.format(str);

        return str;

    }

}
