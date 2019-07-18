package me.professionalskills.client.particle;


import me.professionalskills.client.render.EntityRender;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class CustomParticle extends EntityFX {
    EntityLivingBase player;
    float x;
    float y;
    float z;
    Vec3 vec3d;
    float yaw;
    float piatch;

    float length = 1;

    public CustomParticle(World worldIn, double posXIn, double posYIn, double posZIn, EntityLivingBase player) {
        super(worldIn, posXIn, posYIn, posZIn);
        this.player = player;
        vec3d = Vec3.createVectorHelper(player.getLookVec().xCoord,
                player.getLookVec().yCoord,
                player.getLookVec().zCoord);
        yaw = player.rotationYaw;
        piatch = player.rotationPitch;
        float angle = (-player.rotationYaw / 180F) * 3.141593F;
        interpPosX = prevPosX = posX = player.posX - 0.2f * MathHelper.cos(angle);
        interpPosY = prevPosY = posY = player.posY - 0.6f;
        interpPosZ = prevPosZ = posZ = player.posZ + 0.2f * MathHelper.sin(angle);
    }

    @Override
    public void renderParticle(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
//        System.out.println(player.posX + ";" + player.posY + ";" + player.posZ);
//        System.out.println(x + ";" + y + ";" + z);
        float f5 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) p_70539_2_ - interpPosX);
        float f6 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) p_70539_2_ - interpPosY);
        float f7 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) p_70539_2_ - interpPosZ);
        GL11.glPushMatrix();
        int i = 15728880;
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
//不移动的
//        System.out.println(vec3d.xCoord + "x;" + f5);
//        System.out.println(vec3d.yCoord + "y;" + f6);
//        System.out.println(vec3d.zCoord + "z;" + f7);
//        EntityRender.DrawChannel((float) (f5+ vec3d.xCoord+x), (float) (f6+vec3d.yCoord+y + 1), (float) ( f7+vec3d.zCoord+z),
//                (float) (f5+vec3d.xCoord*10+x ), (float) (f6+vec3d.yCoord*10+y) + 1,
//                (float) (f7+vec3d.zCoord+z ), yaw, piatch);

//        EntityRender.DrawChannel(
//                (float) (f5+player.getLookVec().xCoord+x),
//                (float) (f6+player.getLookVec().yCoord+y+1),
//                (float) (f7+player.getLookVec().zCoord+z),
//                (float) (f5+player.getLookVec().xCoord*10+x),
//                (float) (f6+player.getLookVec().yCoord*10+y+1) ,
//                (float) (f7+player.getLookVec().zCoord*10+z), player.rotationYaw,player.rotationPitch);
//


        EntityRender.DrawChannel(
                (float) (f5),
                (float) (f6 + 2.2f),
                (float) (f7),
                (float) (f5 + vec3d.xCoord * length),
                (float) (f6 + vec3d.yCoord * length + y + 0.5f),
                (float) (f7 + vec3d.zCoord * length), yaw, piatch);
        GL11.glPopMatrix();
    }

    @Override
    public void onUpdate() {
        length = length + 1;
        if (length >= 15)
            this.setDead();
    }
}
