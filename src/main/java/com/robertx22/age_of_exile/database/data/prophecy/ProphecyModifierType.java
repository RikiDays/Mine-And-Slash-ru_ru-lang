package com.robertx22.age_of_exile.database.data.prophecy;

import com.robertx22.age_of_exile.database.data.prophecy.starts.GearProphecy;
import com.robertx22.age_of_exile.database.registry.ExileDB;
import com.robertx22.age_of_exile.loot.blueprints.GearBlueprint;
import com.robertx22.age_of_exile.loot.blueprints.ItemBlueprint;

public enum ProphecyModifierType {

    GEAR_TYPE() {
        @Override
        public float chanceToSpawn() {
            return 15;
        }

        @Override
        public void set(ItemBlueprint b, String data) {
            if (b instanceof GearBlueprint gb) {
                gb.gearItemSlot.set(data);
            }
        }

        @Override
        public boolean canApplyTo(ProphecyStart start) {
            return start instanceof GearProphecy;
        }
    },
    GEAR_RARITY() {
        @Override
        public float chanceToSpawn() {
            return 75;
        }

        @Override
        public void set(ItemBlueprint b, String data) {
            if (b instanceof GearBlueprint gb) {
                gb.rarity.set(ExileDB.GearRarities().get(data));
            }
        }

        @Override
        public boolean canApplyTo(ProphecyStart start) {
            return start instanceof GearProphecy;
        }
    };

    public abstract float chanceToSpawn();

    public abstract void set(ItemBlueprint b, String data);

    public abstract boolean canApplyTo(ProphecyStart start);
}