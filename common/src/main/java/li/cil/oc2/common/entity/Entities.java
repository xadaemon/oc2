/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.common.util.RegistryUtils;
import li.cil.oc2.common.vm.MachineTier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.List;
import java.util.function.Function;

public final class Entities {
    private static final DeferredRegister<EntityType<?>> ENTITIES = RegistryUtils.getInitializerFor(Registries.ENTITY_TYPE);

    // --------------------------------------------------------------------- //

    public static final RegistrySupplier<EntityType<Robot>> ROBOT_TIER_1 = registerRobot("robot_tier_1");
    public static final RegistrySupplier<EntityType<Robot>> ROBOT = registerRobot("robot");
    public static final RegistrySupplier<EntityType<Robot>> ROBOT_TIER_3 = registerRobot("robot_tier_3");
    public static final RegistrySupplier<EntityType<Robot>> ROBOT_TIER_4 = registerRobot("robot_tier_4");

    /**
     * All robot entity types, ordered by tier.
     */
    public static final List<RegistrySupplier<EntityType<Robot>>> ROBOTS = List.of(ROBOT_TIER_1, ROBOT, ROBOT_TIER_3, ROBOT_TIER_4);

    // --------------------------------------------------------------------- //

    public static void initialize() {
    }

    public static EntityType<Robot> robot(final MachineTier tier) {
        return ROBOTS.get(tier.getLevel() - 1).get();
    }

    public static MachineTier robotTier(final EntityType<?> type) {
        for (final MachineTier tier : MachineTier.values()) {
            if (ROBOTS.get(tier.getLevel() - 1).get() == type) {
                return tier;
            }
        }
        return MachineTier.TIER_2;
    }

    // --------------------------------------------------------------------- //

    private static RegistrySupplier<EntityType<Robot>> registerRobot(final String name) {
        return register(name, Robot::new, MobCategory.MISC, b -> b.sized(14f / 16f, 14f / 16f).fireImmune().noSummon());
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> register(final String name, final EntityType.EntityFactory<T> factory, final MobCategory classification, final Function<EntityType.Builder<T>, EntityType.Builder<T>> customizer) {
        return ENTITIES.register(name, () -> customizer.apply(EntityType.Builder.of(factory, classification)).build(name));
    }
}
