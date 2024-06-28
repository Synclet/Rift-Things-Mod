package de.gummit.entity.renderer;

import de.gummit.RiftThingsMod;
import de.gummit.entity.RiftRemnant;
import de.gummit.entity.model.RiftRemnantModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RiftRemnantRenderer extends MobRenderer<RiftRemnant, RiftRemnantModel> {
    public RiftRemnantRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new RiftRemnantModel(), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(RiftRemnant entity) {
        return new ResourceLocation(RiftThingsMod.MOD_ID, "textures/entity/" + RiftRemnant.ENTITY_ID + ".png");
    }
}
