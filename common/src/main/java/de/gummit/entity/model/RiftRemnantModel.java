package de.gummit.entity.model;

import de.gummit.entity.RiftRemnant;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class RiftRemnantModel extends EntityModel<RiftRemnant> {

    private final ModelPart body;

    public RiftRemnantModel(EntityRendererFactory.Context context) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).
                cuboid(-2f, -4f, -2f, 4f, 4f, 4f, false), ModelTransform.NONE);
        body = modelPartData.createPart(16 ,16);
        body.setPivot(0f, 24f, 0f);
    }

    @Override
    public void setAngles(RiftRemnant entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}