package me.professionalskills.common.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import me.professionalskills.ProfessionalSkills;
import me.professionalskills.common.career.Career;
import me.professionalskills.common.entity.AttackEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class  SkillsEvent {

    private static ResourceLocation coolDown = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/coolDown.png");
    private static ResourceLocation noLearn = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/coolDown.png");
    public static ResourceLocation skills;
    public static ResourceLocation skill0;
    public static ResourceLocation skill1;
    public static ResourceLocation skill2;
    public static ResourceLocation skill3;
    public static ResourceLocation skill4;

    private int skill0CoolDown = 0;
    private int skill1CoolDown = 0;
    private int skill2CoolDown = 0;
    private int skill3CoolDown = 0;
    private int skill4CoolDown = 0;

    public static int time0 = 0;
    public static int time1 = 0;
    public static int time2 = 0;
    public static int time3 = 0;
    public static int time4 = 0;

    public static int cd0 = -1;
    public static int cd1 = -1;
    public static int cd2 = -1;
    public static int cd3 = -1;
    public static int cd4 = -1;

    public static boolean passive = false;

    public static Map<Entity, Integer> skill02Map = new HashMap<Entity, Integer>();

    public static Map<Entity, Integer> skill04Map = new HashMap<Entity, Integer>();

    public static Map<Entity, Float> skill10Map = new HashMap<Entity, Float>();

    public static Map<Entity, Integer> skill12Map = new HashMap<Entity, Integer>();
    public static boolean skill12Boolean = false;

    public static Map<Entity, Integer> skill13Map = new HashMap<Entity, Integer>();

    public static Map<Entity, Integer> skill14Map = new HashMap<Entity, Integer>();
    public static Map<Entity, Float> skill141Map = new HashMap<Entity, Float>();

    public SkillsEvent() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    /**
     * 界面冷却计时以及技能特效的计时器
     * @param event
     */
    @SubscribeEvent
    public void coolDownEvent(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        if (time0 >= 0)
            time0--;

        if (time1 >= 0)
            time1--;

        if (time2 >= 0)
            time2--;

        if (time3 >= 0)
            time3--;

        if (time4 >= 0)
            time4--;

//        System.out.println("1:"+skill1CoolDown);
//        System.out.println("2:"+time1);
//        System.out.println("3:"+cd1);
//        System.out.println("4:"+ (22 * time1 / cd1));

        skill0CoolDown = 22 * time0 / cd0;

        skill1CoolDown = 22 * time1 / cd1;

        skill2CoolDown = 22 * time2 / cd2;

        skill3CoolDown = 22 * time3 / cd3;

        skill4CoolDown = 22 * time4 / cd4;

        if (skill0CoolDown <= 0)
            time0 = -1;
        if (skill1CoolDown <= 0)
            time1 = -1;
        if (skill2CoolDown <= 0)
            time2 = -1;
        if (skill3CoolDown <= 0)
            time3 = -1;
        if (skill4CoolDown <= 0)
            time4 = -1;

        for (Entity entity : skill04Map.keySet())
            skill04Map.put(entity, skill04Map.get(entity) + 1);

        for (Entity entity : skill10Map.keySet())
            skill10Map.put(entity, skill10Map.get(entity) + 1.5F);

        for (Entity entity : skill12Map.keySet())
            skill12Map.put(entity, skill12Map.get(entity) + 1);

        for (Entity entity : skill13Map.keySet())
            skill13Map.put(entity, skill13Map.get(entity) + 1);

        for (Entity entity : skill14Map.keySet())
            skill14Map.put(entity, skill14Map.get(entity) + 1);

        for (Entity entity : skill141Map.keySet())
            skill141Map.put(entity, skill141Map.get(entity) - 3);

    }

    /**
     * 全部技能特效的渲染
     * @param event
     */
    @SubscribeEvent
    public void renderHud(RenderGameOverlayEvent.Pre event) {
        if (CareerEvent.career != null) {
            Minecraft mc = Minecraft.getMinecraft();
            FontRenderer fontRenderer = mc.fontRenderer;

            SkillsEvent.skills = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/skills" + CareerEvent.career.skillID + ".png");
            SkillsEvent.skill0 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill" + CareerEvent.career.skillID + "0.png");
            if (!SkillsEvent.passive)
                SkillsEvent.skill1 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill" + CareerEvent.career.skillID + "1.png");
            else
                SkillsEvent.skill1 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill" + CareerEvent.career.skillID + "11.png");
            SkillsEvent.skill2 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill" + CareerEvent.career.skillID + "2.png");
            SkillsEvent.skill3 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill" + CareerEvent.career.skillID + "3.png");
            SkillsEvent.skill4 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill" + CareerEvent.career.skillID + "4.png");
            SkillsEvent.noLearn = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/" + CareerEvent.career.skillID + ".png");

            //技能框
            GL11.glPushMatrix();
            GL11.glEnable(3008);
            GL11.glColor4d(255, 255, 255, 1);
            mc.renderEngine.bindTexture(skills);
            Gui.func_146110_a(0, event.resolution.getScaledHeight() / 2 - 111, 0, 0, 59, 220, 59, 222);
            GL11.glDisable(3008);
            GL11.glPopMatrix();

            //技能0
            GL11.glPushMatrix();
            GL11.glEnable(3008);
            GL11.glColor4d(255, 255, 255, 1);
            mc.renderEngine.bindTexture(SkillsEvent.skill0);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 74, 0, 0, 22, 22, 22, 22);
            mc.renderEngine.bindTexture(coolDown);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 74, 0, 0, skill0CoolDown, 22, 22, 22);
            if (!CareerEvent.career.skillLevel0.learn) {
                mc.renderEngine.bindTexture(noLearn);
                Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 74, 0, 0, 22, 22, 22, 22);
            }
            GL11.glDisable(3008);
            GL11.glPopMatrix();

            //技能1
            GL11.glPushMatrix();
            GL11.glEnable(3008);
            GL11.glColor4d(255, 255, 255, 1);
            mc.renderEngine.bindTexture(SkillsEvent.skill1);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 43, 0, 0, 22, 22, 22, 22);
            mc.renderEngine.bindTexture(coolDown);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 43, 0, 0, skill1CoolDown, 22, 22, 22);
            if (!CareerEvent.career.skillLevel1.learn) {
                mc.renderEngine.bindTexture(noLearn);
                Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 43, 0, 0, 22, 22, 22, 22);
            }
            GL11.glDisable(3008);
            GL11.glPopMatrix();
            //技能2
            GL11.glPushMatrix();
            GL11.glEnable(3008);
            GL11.glColor4d(255, 255, 255, 1);
            mc.renderEngine.bindTexture(SkillsEvent.skill2);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 12, 0, 0, 22, 22, 22, 22);
            mc.renderEngine.bindTexture(coolDown);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 12, 0, 0, skill2CoolDown, 22, 22, 22);
            if (!CareerEvent.career.skillLevel2.learn) {
                mc.renderEngine.bindTexture(noLearn);
                Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 - 12, 0, 0, 22, 22, 22, 22);
            }
            GL11.glDisable(3008);
            GL11.glPopMatrix();
            //技能3
            GL11.glPushMatrix();
            GL11.glEnable(3008);
            GL11.glColor4d(255, 255, 255, 1);
            mc.renderEngine.bindTexture(SkillsEvent.skill3);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 + 19, 0, 0, 22, 22, 22, 22);
            mc.renderEngine.bindTexture(coolDown);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 + 19, 0, 0, skill3CoolDown, 22, 22, 22);
            if (!CareerEvent.career.skillLevel3.learn) {
                mc.renderEngine.bindTexture(noLearn);
                Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 + 19, 0, 0, 22, 22, 22, 22);
            }
            GL11.glDisable(3008);
            GL11.glPopMatrix();
            //技能4
            GL11.glPushMatrix();
            GL11.glEnable(3008);
            GL11.glColor4d(255, 255, 255, 1);
            mc.renderEngine.bindTexture(SkillsEvent.skill4);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 + 50, 0, 0, 22, 22, 22, 22);
            mc.renderEngine.bindTexture(coolDown);
            Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 + 50, 0, 0, skill4CoolDown, 22, 22, 22);
            if (!CareerEvent.career.skillLevel4.learn) {
                mc.renderEngine.bindTexture(noLearn);
                Gui.func_146110_a(19, event.resolution.getScaledHeight() / 2 + 50, 0, 0, 22, 22, 22, 22);
            }
            GL11.glDisable(3008);
            GL11.glPopMatrix();

            String career = String.format("§c§lLV." + CareerEvent.career.skillLevel0.level);
            fontRenderer.drawStringWithShadow(career, 22, event.resolution.getScaledHeight() / 2 - 81, 0xFFFFFF);

            String skill1 = String.format("§c§lLV." + CareerEvent.career.skillLevel1.level);
            fontRenderer.drawStringWithShadow(skill1, 22, event.resolution.getScaledHeight() / 2 - 50, 0xFFFFFF);

            String skill2 = String.format("§c§lLV." + CareerEvent.career.skillLevel2.level);
            fontRenderer.drawStringWithShadow(skill2, 22, event.resolution.getScaledHeight() / 2 - 19, 0xFFFFFF);

            String skill3 = String.format("§c§lLV." + CareerEvent.career.skillLevel3.level);
            fontRenderer.drawStringWithShadow(skill3, 22, event.resolution.getScaledHeight() / 2 + 12, 0xFFFFFF);

            String skill4 = String.format("§c§lLV." + CareerEvent.career.skillLevel4.level);
            fontRenderer.drawStringWithShadow(skill4, 22, event.resolution.getScaledHeight() / 2 + 43, 0xFFFFFF);

            mc.renderEngine.bindTexture(Gui.icons);
        }
    }

    @SubscribeEvent()
    public void renderSkill01(RenderLivingEvent.Post event) {
        if (AttackEntity.skill01Map.containsKey(event.entity)) {
            ResourceLocation texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill01/" + AttackEntity.skill01Map.get(event.entity) + ".png");
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); //混合模式设定
            GL11.glColor4f(1, 1, 1, 1);
            GL11.glColor4f(255, 255, 255, 1);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glTranslated(event.x, event.y, event.z);
            GL11.glRotatef(Minecraft.getMinecraft().thePlayer.rotationYaw, 0.0F, -1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glTranslatef(-0.5F, -3.3F, 0.0F);
            Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }

        for (Entity entity : skill02Map.keySet()) {
            if (entity == event.entity) {
                ResourceLocation texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill02/" + new Random().nextInt(4) + ".png");
                float down = 1.7f;
                skill02Map.put(entity, skill02Map.get(entity) + 1);
                if (skill02Map.get(entity) > 0 && skill02Map.get(entity) <= 8) {
                    down = 1.5f;
                } else if (skill02Map.get(entity) > 8 && skill02Map.get(entity) <= 16) {
                    down = 1.4f;
                } else if (skill02Map.get(entity) > 16 && skill02Map.get(entity) <= 24) {
                    down = 1.1f;
                } else if (skill02Map.get(entity) > 24 && skill02Map.get(entity) <= 40) {
                    down = 0.9f;
                } else if (skill02Map.get(entity) > 40) {
                    skill02Map.remove(entity);
                }
                if (texture != null) {
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(Minecraft.getMinecraft().thePlayer.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2.5F, 2F);
                    GL11.glTranslatef(-0.5F, -down, 0.0F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }


        for (Entity player : skill04Map.keySet()) {
            if (skill04Map.get(player) >= 29) {
                int size = 3;
                List<Entity> list = Minecraft.getMinecraft().theWorld.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - size, player.posY - size, player.posZ - size, player.posX + size, player.posY + size, player.posZ + size), null);
                String entitys = "";
                for (Entity entity : list) {
                    if (!entitys.equals("")) {
                        if (!entity.getCommandSenderName().equals(player.getCommandSenderName()))
                            entitys = entitys + "," + entity.getEntityId();
                    } else {
                        if (!entity.getCommandSenderName().equals(player.getCommandSenderName()))
                            entitys = "" + entity.getEntityId();
                    }
                    if (SkillsEvent.passive)
                        if (!entity.getCommandSenderName().equals(player.getCommandSenderName())) {
                            if (AttackEntity.skill01Map.containsKey(entity)) {
                                if (AttackEntity.skill01Map.get(entity) != 4) {
                                    AttackEntity.skill01Map.put((EntityLivingBase) entity, AttackEntity.skill01Map.get(entity) + 1);
                                } else {
                                    event.entity.worldObj.playSoundAtEntity(event.entity, ProfessionalSkills.MODID + ":skill01", 1.0F, 1.0F);
                                    String message = "{\"type\":\"startskill\",\"id\":1,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":" + entity.getEntityId() + "}";
                                    CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                                    AttackEntity.skill01Map.put((EntityLivingBase) entity, 1);
                                }
                            } else {
                                AttackEntity.skill01Map.put((EntityLivingBase) entity, 1);
                            }
                        }
                    KeyDownEvent.skill01 = 1;
                    skill04Map.remove(player);
                }
                String message = "{\"type\":\"startskill\",\"id\":4,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\",\"entityid\":[" + entitys + "]}";
                System.out.println(message);
                CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
            }

            if (player.getCommandSenderName().equals(event.entity.getCommandSenderName())) {
                if (skill04Map.get(player) != null) {
                    ResourceLocation skill04 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill04/" + skill04Map.get(player) / 10 + ".png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill04);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2.5F, 2F);
                    GL11.glTranslatef(-0.5F, -0.5F, -0.05F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }

        for (Entity player : skill10Map.keySet()) {
            if (skill10Map.get(player) >= 20) {
                skill10Map.remove(player);
            } else {
                if (player.getCommandSenderName().equals(event.entity.getCommandSenderName())) {
                    ResourceLocation skill10Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill10/0.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill10Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(event.entity.rotationYaw + 0.5F, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2.5F, 2F);
                    GL11.glTranslatef(-(skill10Map.get(player) / 10), -0.9F, 0.0F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }

        for (Entity player : skill12Map.keySet()) {
            if (skill12Map.get(player) >= 100) {
                skill12Map.remove(player);
            } else {
                if (player.getCommandSenderName().equals(event.entity.getCommandSenderName())) {
                    int num;
                    if (skill12Map.get(player) <= 29) {
                        num = skill12Map.get(player) / 10;
                    } else num = 2;
                    ResourceLocation skill12Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill12/" + num + ".png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill12Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(player.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2.5F, 2F);
                    GL11.glTranslatef(-0.5F, -1.3F, -0.1F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    ResourceLocation dunTexture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill12/dun.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(dunTexture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(player.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(1F, 1.2F, 1F);
                    GL11.glTranslatef(-0.5F, -1.1F, 0.28F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    ResourceLocation g0Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill12/g0.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(g0Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(player.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2F, 2F);
                    GL11.glTranslatef(-0.5F, -1.1F, 0F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    ResourceLocation g1Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill12/g1.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(g1Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(player.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2F, 2F);
                    GL11.glTranslatef(-0.5F, -0.5F, 0.5F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }

        for (Entity player : skill13Map.keySet()) {
            if (skill13Map.get(player) >= 60) {
                skill13Map.remove(player);
            } else {
                if (player.getCommandSenderName().equals(event.entity.getCommandSenderName())) {
                    ResourceLocation skill13Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill13/0.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill13Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(event.entity.rotationYaw + 0.5F, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2.5F, 2F);
                    GL11.glTranslatef(-1.2F, -0.9F, 0.0F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    ResourceLocation skill13Texture1 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill13/0.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill13Texture1);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(event.entity.rotationYaw + 0.5F, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2.5F, 2F);
                    GL11.glTranslatef(-0.5F, -0.9F, 0.8F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }

        for (Entity player : skill14Map.keySet()) {
            if (skill14Map.get(player) >= 60) {
                skill14Map.remove(player);
            } else {
                if (player.getCommandSenderName().equals(event.entity.getCommandSenderName())) {
                    ResourceLocation skill14Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill14/skill14.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill14Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(event.entity.rotationYaw + 0.5F, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(3F, 3F, 3F);
                    GL11.glTranslatef(-0.5F, -0.5F, -0.35F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    ResourceLocation skill140Texture = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill14/skill140.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill140Texture);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(event.entity.rotationYaw + 0.5F, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(3F, 3F, 3F);
                    GL11.glTranslatef(-0.5F, -0.5F, -0.05F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    float angle = 20;
                    if (skill141Map.get(player) != null) {
                        if (skill141Map.get(player) >= 10)
                            angle = skill141Map.get(player);
                        else {
                            skill141Map.remove(player);
                            angle = 20;
                        }
                    }

                    ResourceLocation skillSword = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill14/sword.png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skillSword);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(player.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(90, 0.0F, 0.0F, -1.0F);
                    GL11.glRotatef(angle, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(0.4F, 4F, 0.4F);
                    GL11.glTranslatef(-2F, 0F, 1.4F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();

                    ResourceLocation skill0 = new ResourceLocation(ProfessionalSkills.MODID, "skills/skill14/" + new Random().nextInt(10) + ".png");
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Minecraft.getMinecraft().renderEngine.bindTexture(skill0);
                    GL11.glTranslated(event.x, event.y, event.z);
                    GL11.glRotatef(player.rotationYaw, 0.0F, -1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(2F, 2F, 2F);
                    GL11.glTranslatef(-0.5F, -1.2F, -0.4F);
                    Gui.func_146110_a(0, 0, 0.0F, 0.0F, 1, 1, 1.0F, 1.0F);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }
    }
}