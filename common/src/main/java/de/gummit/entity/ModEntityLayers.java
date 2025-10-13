package de.gummit.entity;

import de.gummit.RiftThingsMod;
import de.gummit.entity.model.RiftRemnantModel;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModEntityLayers {
    public static EntityModelLayer RIFT_REMNANT = new EntityModelLayer(new Identifier(RiftThingsMod.MOD_ID, RiftRemnant.ENTITY_ID), "body");

    public static void initClient(BiConsumer<EntityModelLayer, Supplier<TexturedModelData>> consumer) {
        consumer.accept(RIFT_REMNANT, RiftRemnantModel::getTexturedModelData);
    }



}
