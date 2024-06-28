package de.gummit.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.gummit.entity.RiftRemnant;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class RiftRemnantModel extends EntityModel<RiftRemnant> {

    private final ModelPart body;

    public RiftRemnantModel() {
        texWidth = 16;
        texHeight = 16;

        body = new ModelPart(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(RiftRemnant entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart body, float x, float y, float z) {
        body.xRot = x;
        body.yRot = y;
        body.zRot = z;
    }
}
