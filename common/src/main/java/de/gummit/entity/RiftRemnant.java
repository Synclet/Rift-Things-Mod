package de.gummit.entity;

import de.gummit.items.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class RiftRemnant extends FlyingMob {

    public static final String ENTITY_ID = "rift_remnant";

    public static final Integer LIFETIME = 1000;

    private Integer riftAge = 0;

    public RiftRemnant(EntityType<? extends FlyingMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public AttributeMap getAttributes() {
        return new AttributeMap(ModEntities.getDefaultAttributes()
                .add(Attributes.MAX_HEALTH, 1d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 20d)
                .build());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.riftAge = compoundTag.getInt("riftAge");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        compoundTag.putInt("riftAge", riftAge);
    }

    @Override
    public void tick() {
        super.tick();
        riftAge++;
        if (!this.level.isClientSide && riftAge >= LIFETIME) {
            this.remove();
            return;
        }
        if (this.level.isClientSide && Math.random() > 0.35f) {
            this.level.addParticle(
                    ParticleTypes.SQUID_INK,
                    this.getX() + (Math.random() - 0.5),
                    this.getY() + (Math.random() - 0.5),
                    this.getZ() + (Math.random() - 0.5),
                    0d, 0d, 0d);
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        if(damageSource.isMagic() || damageSource == DamageSource.OUT_OF_WORLD) {
            return super.hurt(damageSource, amount);
        }

        if(!(damageSource instanceof EntityDamageSource)) {
            return false;
        }
        EntityDamageSource eds = (EntityDamageSource) damageSource;
        if(!(eds.getDirectEntity() instanceof Player)) {
            return false;
        }
        ItemStack weapon = ((Player) eds.getDirectEntity()).getMainHandItem();
        List<Item> riftTools = Arrays.asList(ModItems.RIFT_SWORD.get().asItem(), ModItems.RIFT_PICKAXE.get().asItem(), ModItems.RIFT_AXE.get().asItem(), ModItems.RIFT_SHOVEL.get().asItem(), ModItems.RIFT_HOE.get().asItem());
        if(weapon != null && riftTools.contains(weapon.getItem())) {
            return super.hurt(damageSource, amount);
        }

        return false;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return new EntityDimensions(0.25f, 0.25f, true);
    }

    @Override
    public void knockback(float f, double d, double e) {
        // do nothing
    }

    @Override
    public void push(Entity entity) {
        // do nothing
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public static InteractionResult spawnOnDeath(LivingEntity entity, DamageSource source) {
        if (source.getEntity() instanceof Player && !(entity instanceof RiftRemnant) && Math.random() <= 0.05) {

            RiftRemnant rift = ModEntities.RIFT_REMNANT.get().create(entity.level);
            if (rift != null) {
                rift.setPos(entity.getX(), entity.getY() + 0.2, entity.getZ());
                entity.level.addFreshEntity(rift);
            }
        }
        return InteractionResult.PASS;
    }

}
