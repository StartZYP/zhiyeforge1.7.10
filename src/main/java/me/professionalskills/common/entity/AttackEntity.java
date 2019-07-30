package me.professionalskills.common.entity;

import me.professionalskills.ProfessionalSkills;
import me.professionalskills.common.event.CareerEvent;
import me.professionalskills.common.event.KeyDownEvent;
import me.professionalskills.common.event.SkillsEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 探测实体，用来探测攻击目标
 */
public class AttackEntity extends EntityThrowable {

    private int field_145788_c = -1;
    private int field_145786_d = -1;
    private int field_145787_e = -1;
    private Block field_145785_f;
    private int ticksInGround;
    private int ticksInAir;
    private EntityLivingBase thrower;

    private int life = 0;

    int skillID = -1;
    int id = 0;
    boolean isPlayer = false;
    public static Map<EntityLivingBase, Integer> skill01Map = new HashMap<EntityLivingBase, Integer>();

    public AttackEntity(World p_i1776_1_) {
        super(p_i1776_1_);
        this.setSize(2, 2);
    }

    /**
     *
     * @param par1World
     * @param par2EntityLivingBase
     * @param skillID 职业id
     * @param id 技能id
     * @param speed 速度
     * @param life 实体存在时间
     */
    public AttackEntity(World par1World, EntityLivingBase par2EntityLivingBase, int skillID, int id, float speed, int life) {
        super(par1World, par2EntityLivingBase);
        this.thrower = par2EntityLivingBase;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + (double) par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        float f = 0.4F;
        this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
        this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
        this.motionY = (double) (-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float) Math.PI) * f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed/10, 1.0F);
        this.setSize(2, 2);
        this.skillID = skillID;
        this.id = id;
        this.isPlayer = true;
        this.life = life;
    }

    public AttackEntity(World par1World, EntityLivingBase par2EntityLivingBase, int skillID, int id, boolean isPlayer) {
        super(par1World, par2EntityLivingBase);
        this.setSize(2, 2);
        this.skillID = skillID;
        this.id = id;
        this.isPlayer = isPlayer;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            switch (skillID) {
                case 0:
                    switch (id) {
                        case 0:
                            if (isPlayer) {
                                String message = "{\"type\":\"startskill\",\"id\":0,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                            }
                            skill01Open(mop);
                            break;
                        case 1:
                            break;
                        case 2:
                            if (isPlayer) {
                                String message = "{\"type\":\"startskill\",\"id\":2,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                            }
                            SkillsEvent.skill02Map.put(mop.entityHit, 0);
                            skill01Open(mop);
                            break;
                        case 3:
                            if (isPlayer) {
                                String message = "{\"type\":\"startskill\",\"id\":3,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                            }
                            skill01Open(mop);
                            break;
                        case 4:
                            break;
                    }
                    break;
                case 1:
                    switch (id) {
                        case 0:
                            if (isPlayer) {
                                String message = "{\"type\":\"startskill\",\"id\":0,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                            }
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            if (isPlayer) {
                                String message = "{\"type\":\"startskill\",\"id\":3,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                            }
                            break;
                        case 4:
                            if (isPlayer) {
                                String message = "{\"type\":\"startskill\",\"id\":4,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                            }
                            break;
                    }
                    break;
            }
            System.out.println("==========================================");
            System.out.println(mop.entityHit);
            System.out.println("id = " + id);
            System.out.println("skillID = " + skillID);
            System.out.println("==========================================");
        }
    }

    private void skill01Open(MovingObjectPosition mop) {
        if (SkillsEvent.passive)
            if (mop.entityHit instanceof EntityLivingBase) {
                if (skill01Map.containsKey(mop.entityHit)) {
                    if (skill01Map.get(mop.entityHit) != 4) {
                        skill01Map.put((EntityLivingBase) mop.entityHit, skill01Map.get(mop.entityHit) + 1);
                    } else {
                        mop.entityHit.worldObj.playSoundAtEntity(mop.entityHit, ProfessionalSkills.MODID + ":skill01", 1.0F, 1.0F);
                        String message = "{\"type\":\"startskill\",\"id\":1,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + mop.entityHit.getEntityId() + "}";
                        CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                        skill01Map.put((EntityLivingBase) mop.entityHit, 1);
                    }
                } else {
                    skill01Map.put((EntityLivingBase) mop.entityHit, 1);
                }
                KeyDownEvent.skill01 = 1;
            }
    }

    @Override
    public void onUpdate() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();

        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        if (this.inGround) {
            if (this.worldObj.getBlock(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200) {
                    this.setDead();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
            this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
            this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null) {
            vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        Entity entity = null;
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;
        EntityLivingBase entitylivingbase = this.getThrower();

        for (int j = 0; j < list.size(); ++j) {
            Entity entity1 = (Entity) list.get(j);

            if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
                float f = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double) f, (double) f, (double) f);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                if (movingobjectposition1 != null) {
                    double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                    if (d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }

        if (movingobjectposition != null) {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal) {
                this.setInPortal();
            } else {
                this.onImpact(movingobjectposition);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f2 = 0.99F;
        float f3 = this.getGravityVelocity();

        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                float f4 = 0.25F;
                this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double) f4, this.posY - this.motionY * (double) f4, this.posZ - this.motionZ * (double) f4, this.motionX, this.motionY, this.motionZ);
            }

            f2 = 0.8F;
        }

        this.motionX *= (double) f2;
        this.motionY *= (double) f2;
        this.motionZ *= (double) f2;
        this.motionY -= (double) f3;
        this.setPosition(this.posX, this.posY, this.posZ);

        life--;
        if (life <= 0) this.setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0.001F;
    }
}
