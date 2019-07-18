package me.professionalskills.client.render;

import me.professionalskills.ProfessionalSkills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;

public class EntityRender extends Render {

    private static ResourceLocation darkPTexture = new ResourceLocation(ProfessionalSkills.MODID, "entity/darktexture.png");

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {

        Vec3 lok = entity.getLookVec();
        double x2 = entity.posX + lok.xCoord * 10;
        double y2 = entity.posY + lok.yCoord * 10;
        double z2 = entity.posZ + lok.zCoord * 10;

        GL11.glPushMatrix();
        int i = 15728880;
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
        DrawChannel((float) x, (float) y + (entity.height / 2), (float) z, (float) (x2), (float) (y2) + (entity.height / 2), (float) (z2), entityYaw, entity.rotationPitch);
        GL11.glPopMatrix();
    }

    public static void cylinderRender(float radius, float height) {
        Cylinder Cylinder = new Cylinder();
        Cylinder.setDrawStyle(GLU.GLU_FILL);
        Cylinder.setNormals(GLU.GLU_NONE);
        Cylinder.setTextureFlag(true);
        Cylinder.draw(radius, radius, height, 32, 32);
    }

    public static void Cylinder(float radius, float height, float alpha) {
        if (alpha == 1)
            GL11.glColor4d(0, 0.6015625, 0.8359375, alpha);
        else
            GL11.glColor4d(0, 0.6015625, 0.8359375, alpha);
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().getTextureManager().bindTexture(darkPTexture);
        cylinderRender(radius, height);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_NORMALIZE);
    }

//    public void Sphere(float sizee)
//    {
//        GlStateManager.pushMatrix();
//        GlStateManager.scale(sizee, sizee, sizee);
//        GlStateManager.color(0.0F, 1F, 0.0F, 0.5F);
//        GlStateManager.enableNormalize();
//        GlStateManager.depthMask(false);
//        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        Minecraft.getMinecraft().getTextureManager().bindTexture(darkPTexture);
//        GlStateManager.callList(ClientProxy.sphereIdOutside);
//        GlStateManager.callList(ClientProxy.sphereIdInside);
//        GlStateManager.depthMask(true);
//        GlStateManager.disableBlend();
//        GlStateManager.disableNormalize();
//        GlStateManager.popMatrix();
//    }

    public static void DrawChannel(float x1, float y1, float z1, float x2, float y2, float z2, float yaw, float pitch) {
        //System.out.println(x1+" "+y1+" "+z1+" "+x2+" "+y2+" "+z2);

        float dx = x2 - x1;
        float dy = y2 - y1;
        float dz = z2 - z1;
        float distance = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
//        System.out.println("111:" + x1 + ";" + y1 + ";" + z1);
//        System.out.println("222" + x2 + ";" + y2 + ";" + z2);
        try {
            GL11.glPushMatrix();
            GL11.glTranslatef(x1, y1, z1);
            GL11.glRotatef(-yaw * 0.017453292F * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(pitch * 0.017453292F * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
            Cylinder(0.008F, distance, 1F);
            Cylinder(0.01F, distance, 0.1001F);
            Cylinder(0.04F, distance, 0.1001F);
            Cylinder(0.07F, distance, 0.1001F);
            GL11.glPopMatrix();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

//		GlStateManager.popMatrix();
        //Cylinder(0.34F,distance, 0.1001F);
        //Cylinder(0.37F,distance, 0.1001F);
        //Cylinder(0.4F,distance, 0.1001F);


    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
