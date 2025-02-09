package de.gummit.entity.model;

import de.gummit.entity.RiftRemnant;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class RiftRemnantModel extends EntityModel<RiftRemnant> {

    private final ModelPart body;

    public RiftRemnantModel() {
        textureWidth = 16;
        textureHeight = 16;

        body = new ModelPart(this);
        body.setPivot(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addCuboid(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setAngles(RiftRemnant entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart body, float x, float y, float z) {
        body.pivotX = x;
        body.pivotY = y;
        body.pivotZ = z;
    }
}
