package me.professionalskills.common.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import me.professionalskills.ProfessionalSkills;
import net.minecraft.entity.Entity;

public class EntityLoader {
    private static int nextID = 0;

    public EntityLoader() {
        registerEntity(CustomEntity.class, "CustomEntity", 80, 3, true);
        registerEntity(AttackEntity.class, "AttackEntity", 80, 3, true);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
                                       int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, name, nextID++, ProfessionalSkills.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
}
