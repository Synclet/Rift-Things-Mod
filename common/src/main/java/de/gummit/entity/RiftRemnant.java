package de.gummit.entity;

import de.gummit.items.ModItems;
import dev.architectury.event.EventResult;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class RiftRemnant extends MobEntity {

    public static final String ENTITY_ID = "rift_remnant";

    public static final Integer LIFETIME = 1000;
    public static final String AGE_NBT_KEY = "riftAge";

    private Integer riftAge = 0;

    public RiftRemnant(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        setPersistent();
    }

    @Override
    public AttributeContainer getAttributes() {
        return new AttributeContainer(ModEntities.getDefaultAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 20d)
                .build());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        this.riftAge = tag.getInt(AGE_NBT_KEY);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putInt(AGE_NBT_KEY, riftAge);
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.getWorld().isClient) {
            riftAge++;
            if(riftAge >= LIFETIME) {
                this.remove(RemovalReason.DISCARDED);
                return;
            }
        }
        if (this.getWorld().isClient && Math.random() > 0.35f) {
            this.getWorld().addParticle(
                    ParticleTypes.SQUID_INK,
                    this.getX() + (Math.random() - 0.5),
                    this.getY() + (Math.random() - 0.5),
                    this.getZ() + (Math.random() - 0.5),
                    0d, 0d, 0d);
        }
    }

    @Override
    public boolean damage(DamageSource damageSource, float amount) {
        if(damageSource.isOf(DamageTypes.MAGIC) || damageSource.isOf(DamageTypes.INDIRECT_MAGIC) || damageSource.isOf(DamageTypes.OUT_OF_WORLD)) {
            return super.damage(damageSource, amount);
        }

        if(!(damageSource.getAttacker() instanceof PlayerEntity)) {
            return false;
        }
        ItemStack weapon = ((PlayerEntity) damageSource.getAttacker()).getMainHandStack();
        ItemStack offWeapon = ((PlayerEntity) damageSource.getAttacker()).getOffHandStack();
        List<Item> riftTools = Arrays.asList(ModItems.RIFT_SWORD.get(), ModItems.RIFT_PICKAXE.get(), ModItems.RIFT_AXE.get(), ModItems.RIFT_SHOVEL.get(), ModItems.RIFT_HOE.get());
        if((weapon != null && riftTools.contains(weapon.getItem())) || (offWeapon != null && riftTools.contains(offWeapon.getItem()))) {
            return super.damage(damageSource, amount);
        }

        return false;
    }

    @Nullable
    @Override
    public EntityAttributeInstance getAttributeInstance(EntityAttribute attribute) {
        if(this.getAttributes().hasAttribute(attribute)) {
            return super.getAttributeInstance(attribute);
        }
        EntityAttributeInstance result = new EntityAttributeInstance(attribute, v -> {});
        result.setBaseValue(0);
        return result;
    }

    @Override
    public double getAttributeValue(EntityAttribute attribute) {
        if(this.getAttributes().hasAttribute(attribute)) {
            return super.getAttributeValue(attribute);
        }
        return 0;
    }

    @Override
    public double getAttributeBaseValue(EntityAttribute attribute) {
        if(this.getAttributes().hasAttribute(attribute)) {
            return super.getAttributeBaseValue(attribute);
        }
        return 0;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return new EntityDimensions(0.25f, 0.25f, true);
    }

    @Override
    protected void knockback(LivingEntity target) {
        // do nothing
    }

    @Override
    public void pushAwayFrom(Entity entity) {
        // do nothing
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public static EventResult spawnOnDeath(LivingEntity entity, DamageSource source) {
        if (source.getAttacker() instanceof PlayerEntity && !(entity instanceof RiftRemnant) && Math.random() <= 0.05) {

            RiftRemnant rift = ModEntities.RIFT_REMNANT.get().create(entity.getWorld());
            if (rift != null) {
                rift.updatePosition(entity.getX(), entity.getY() + 0.2, entity.getZ());
                entity.getWorld().spawnEntity(rift);
            }
        }
        return EventResult.pass();
    }

}