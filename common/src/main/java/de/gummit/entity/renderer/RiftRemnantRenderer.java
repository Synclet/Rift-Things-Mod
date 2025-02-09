package de.gummit.entity.renderer;

import de.gummit.RiftThingsMod;
import de.gummit.entity.RiftRemnant;
import de.gummit.entity.model.RiftRemnantModel;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class RiftRemnantRenderer extends MobEntityRenderer<RiftRemnant, RiftRemnantModel> {
    public RiftRemnantRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new RiftRemnantModel(), 0.25f);
    }

    @Override
    public Identifier getTexture(RiftRemnant entity) {
        return new Identifier(RiftThingsMod.MOD_ID, "textures/entity/" + RiftRemnant.ENTITY_ID + ".png");
    }
}
