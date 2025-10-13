package de.gummit.entity.renderer;

import de.gummit.RiftThingsMod;
import de.gummit.entity.RiftRemnant;
import de.gummit.entity.model.RiftRemnantModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RiftRemnantRenderer extends MobEntityRenderer<RiftRemnant, RiftRemnantModel> {

    public RiftRemnantRenderer(EntityRendererFactory.Context context) {
        super(context, new RiftRemnantModel(context), 0.25f);
    }

    @Override
    public Identifier getTexture(RiftRemnant entity) {
        return new Identifier(RiftThingsMod.MOD_ID, "textures/entity/" + RiftRemnant.ENTITY_ID + ".png");
    }
}