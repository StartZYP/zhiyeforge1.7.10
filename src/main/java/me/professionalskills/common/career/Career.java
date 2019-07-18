package me.professionalskills.common.career;

import me.professionalskills.ProfessionalSkills;
import me.professionalskills.client.particle.CustomParticle;
import me.professionalskills.client.particle.CustomParticle1;
import me.professionalskills.common.RunSkill;
import me.professionalskills.common.entity.AttackEntity;
import me.professionalskills.common.entity.TestEntity;
import me.professionalskills.common.event.CareerEvent;
import me.professionalskills.common.event.SkillsEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;

import java.nio.charset.Charset;

/**
 * 职业类
 */
public class Career {

    public String career;

    public int skillID;

    public int level = 1;

    public int fight = 100;

    public Skill skillLevel0, skillLevel1, skillLevel2, skillLevel3, skillLevel4;

    public Career(String career, Skill skill0, Skill skill1, Skill skill2, Skill skill3, Skill skill4) {
        this.career = career;
        this.skillLevel0 = skill0;
        this.skillLevel1 = skill1;
        this.skillLevel2 = skill2;
        this.skillLevel3 = skill3;
        this.skillLevel4 = skill4;
        SkillsEvent.cd0 = skill0.coolDown * 20;
        SkillsEvent.cd1 = skill1.coolDown * 20;
        SkillsEvent.cd2 = skill2.coolDown * 20;
        SkillsEvent.cd3 = skill3.coolDown * 20;
        SkillsEvent.cd4 = skill4.coolDown * 20;
        if (career.equals("战域控制者")) this.skillID = 0;
        else if (career.equals("战域戮火")) this.skillID = 1;
    }

    public void loadCD(){
        SkillsEvent.cd0 = skillLevel0.coolDown * 20;
        SkillsEvent.cd1 = skillLevel1.coolDown * 20;
        SkillsEvent.cd2 = skillLevel2.coolDown * 20;
        SkillsEvent.cd3 = skillLevel3.coolDown * 20;
        SkillsEvent.cd4 = skillLevel4.coolDown * 20;
    }

    /**
     * 本地玩家释放技能
     *
     * @param skillID 技能id
     * @param name    玩家名称
     * @param skill   技能
     */
    public static void runSkill(int skillID, String name, Career.Skill skill) {
        EntityPlayer playerMP = Minecraft.getMinecraft().thePlayer;
        System.out.println(skill.toString());
        if (skillID == 0) {
            switch (skill.id) {
                case 0:
                    if (skill.learn) {
                        float num = skill.around / 1.0F;
                        TestEntity testEntity = new TestEntity(playerMP.getEntityWorld(), playerMP, skill, num, 5, new RunSkill() {
                            @Override
                            public void runSkill(EntityPlayer playerMP, Career.Skill skill, float num) {
                                if (SkillsEvent.time0 == -1) {
                                    SkillsEvent.time0 = skill.coolDown * 20;//设置冷却
                                    if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
                                        Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill00",1.0f,1.0f,false);
                                        float angle = (-playerMP.rotationYaw / 180F) * 3.141593F;
                                        Minecraft.getMinecraft().effectRenderer.addEffect(new CustomParticle(playerMP.getEntityWorld(), playerMP.posX - 0.2f * MathHelper.cos(angle), playerMP.posY - 0.6f, playerMP.posZ + 0.2f * MathHelper.sin(angle), playerMP));
                                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 0, 0, num / 10, 20));
                                    }
                                }
                            }
                        });
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(testEntity);
                    }
                    break;
                case 1:
                    if (skill.learn)
                    if (name.equals(Minecraft.getMinecraft().thePlayer.getDisplayName())) {
                        if (SkillsEvent.time1 == -1) {
                            SkillsEvent.time1 = skill.coolDown * 20;
                            Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill01",1.0f,1.0f,false);
                            SkillsEvent.passive = !SkillsEvent.passive;
                        }
                    }
                    break;
                case 2:
                    if (skill.learn) {
                        float num = skill.around / 1.0F;
                        TestEntity testEntity = new TestEntity(playerMP.getEntityWorld(), playerMP, skill, num, 5, new RunSkill() {
                            @Override
                            public void runSkill(EntityPlayer playerMP, Career.Skill skill, float num) {
                                if (SkillsEvent.time2 == -1) {
                                    SkillsEvent.time2 = skill.coolDown * 20;
                                    Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill02",1.0f,1.0f,false);
                                    Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer, 0, 2, num / 20, 20));
                                }
                            }
                        });
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(testEntity);
                    }
                    break;
                case 3:
                    if (skill.learn) {
                        float num = skill.around / 1.0F;
                        TestEntity testEntity = new TestEntity(playerMP.getEntityWorld(), playerMP, skill, num, 5, new RunSkill() {
                            @Override
                            public void runSkill(EntityPlayer playerMP, Career.Skill skill, float num) {
                                if (SkillsEvent.time3 == -1) {
                                    SkillsEvent.time3 = skill.coolDown * 20;
                                    if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
                                        float angle = (-playerMP.rotationYaw / 180F) * 3.141593F;
                                        Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill03",1.0f,1.0f,false);                                        Minecraft.getMinecraft().effectRenderer.addEffect(new CustomParticle1(playerMP.getEntityWorld(), playerMP.posX - 0.2f * MathHelper.cos(angle), playerMP.posY - 0.6f, playerMP.posZ + 0.2f * MathHelper.sin(angle), playerMP));
                                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 0, 3, num / 10, 20));
                                    }
                                }
                            }
                        });
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(testEntity);
                    }
                    break;
                case 4:
                    if (skill.learn)
                        if (SkillsEvent.time4 == -1) {
                            SkillsEvent.time4 = skill.coolDown * 20;
                            Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill04",1.0f,1.0f,false);                            SkillsEvent.skill04Map.put(playerMP, 0);
                        }
                    break;
            }
        } else if (skillID == 1) {
            switch (skill.id) {
                case 0:
                    if (skill.learn) {
                        float num = skill.around / 0.5F;
                        TestEntity testEntity = new TestEntity(playerMP.getEntityWorld(), playerMP, skill, num, 4, new RunSkill() {
                            @Override
                            public void runSkill(EntityPlayer playerMP, Career.Skill skill, float num) {
                                if (SkillsEvent.time0 == -1) {
                                    Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill10",1.0f,1.0f,false);                                    SkillsEvent.time0 = skill.coolDown * 20;
                                    SkillsEvent.skill10Map.put(playerMP, 5F);
                                    Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 1, 0, num / 10, 13));
                                }
                            }
                        });
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(testEntity);
                    }
                    break;
                case 1:
                    if (skill.learn)
                        if (SkillsEvent.time1 == -1) {
                            SkillsEvent.time1 = skill.coolDown * 20;
                            SkillsEvent.passive = !SkillsEvent.passive;
                            Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill01",1.0f,1.0f,false);                            String message = "{\"type\":\"startskill\",\"id\":1,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\"}";
                            CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                        }
                    break;
                case 2:
                    if (skill.learn)
                        if (SkillsEvent.time2 == -1) {
                            playerMP.worldObj.playSoundAtEntity(playerMP, ProfessionalSkills.MODID + ":skill12", 1.0F, 1.0F);
                            SkillsEvent.time2 = skill.coolDown * 20;
                            SkillsEvent.skill12Map.put(playerMP, 0);
                            SkillsEvent.skill12Boolean = true;
                            Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill12",1.0f,1.0f,false);                            String message = "{\"type\":\"startskill\",\"id\":2,\"name\":\"" + Minecraft.getMinecraft().thePlayer.getDisplayName() + "\"}";
                            CareerEvent.sendMessage(message.getBytes(Charset.forName("UTF-8")));
                        }
                    break;
                case 3:
                    if (skill.learn) {
                        float num = skill.around / 0.6F;
                        TestEntity testEntity = new TestEntity(playerMP.getEntityWorld(), playerMP, skill, num, 4, new RunSkill() {
                            @Override
                            public void runSkill(EntityPlayer playerMP, Career.Skill skill, float num) {
                                if (SkillsEvent.time3 == -1) {
                                    Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill13",1.0f,1.0f,false);                                    SkillsEvent.time3 = skill.coolDown * 20;
                                    SkillsEvent.skill13Map.put(playerMP, 0);
                                    Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 1, 3, num / 10, 16));
                                }
                            }
                        });
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(testEntity);
                    }
                    break;
                case 4:
                    if (skill.learn) {
                        float num = skill.around / 0.8F;
                        TestEntity testEntity = new TestEntity(playerMP.getEntityWorld(), playerMP, skill, num, 5, new RunSkill() {
                            @Override
                            public void runSkill(EntityPlayer playerMP, Career.Skill skill, float num) {
                                if (SkillsEvent.time4 == -1) {
                                    Minecraft.getMinecraft().theWorld.playSound(playerMP.posX,playerMP.posY,playerMP.posZ,ProfessionalSkills.MODID + ":skill14",1.0f,1.0f,false);                                    SkillsEvent.time4 = skill.coolDown * 20;
                                    SkillsEvent.skill14Map.put(playerMP, 0);
                                    SkillsEvent.skill141Map.put(playerMP, 75F);
                                    Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 1, 4, num / 10, 18));
                                }
                            }
                        });
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(testEntity);
                    }
                    break;
            }
        }
    }

    /**
     * 插件发给玩家，让除了自己的玩家释放技能
     *
     * @param skillID 职业id
     * @param name    玩家名称
     * @param id      技能id
     */
    public static void runSkill(int skillID, String name, int id) {
        EntityPlayer playerMP = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(name);
        if (skillID == 0) {
            switch (id) {
                case 0:
                    if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
                        float angle = (-playerMP.rotationYaw / 180F) * 3.141593F;
                        Minecraft.getMinecraft().effectRenderer.addEffect(new CustomParticle(playerMP.getEntityWorld(), playerMP.posX - 0.2f * MathHelper.cos(angle), playerMP.posY - 0.6f, playerMP.posZ + 0.2f * MathHelper.sin(angle), playerMP));
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 0, 0, false));
                    }
                    break;
                case 1:
                    break;
                case 2:
                    Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer, 0, 2, false));
                    break;
                case 3:
                    if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
                        float angle = (-playerMP.rotationYaw / 180F) * 3.141593F;
                        Minecraft.getMinecraft().effectRenderer.addEffect(new CustomParticle1(playerMP.getEntityWorld(), playerMP.posX - 0.2f * MathHelper.cos(angle), playerMP.posY - 0.6f, playerMP.posZ + 0.2f * MathHelper.sin(angle), playerMP));
                        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 0, 3, false));
                    }
                    break;
                case 4:
                    SkillsEvent.skill04Map.put(playerMP, 0);
                    break;
            }
        } else if (skillID == 1) {
            switch (id) {
                case 0:
                    playerMP.worldObj.playSoundAtEntity(playerMP, ProfessionalSkills.MODID + ":skill10", 1.0F, 1.0F);
                    SkillsEvent.skill10Map.put(playerMP, 5F);
                    Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new AttackEntity(playerMP.getEntityWorld(), playerMP, 1, 0, false));
                    break;
                case 1:
                    break;
                case 2:
                    playerMP.worldObj.playSoundAtEntity(playerMP, ProfessionalSkills.MODID + ":skill12", 1.0F, 1.0F);
                    SkillsEvent.skill12Map.put(playerMP, 0);
                    break;
                case 3:
                    playerMP.worldObj.playSoundAtEntity(playerMP, ProfessionalSkills.MODID + ":skill13", 1.0F, 1.0F);
                    SkillsEvent.skill13Map.put(playerMP, 0);
                    break;
                case 4:
                    playerMP.worldObj.playSoundAtEntity(playerMP, ProfessionalSkills.MODID + ":skill14", 1.0F, 1.0F);
                    SkillsEvent.skill14Map.put(playerMP, 0);
                    SkillsEvent.skill141Map.put(playerMP, 75F);
                    break;
            }
        }
    }

    public static class Skill {

        public int id;//技能id
        public boolean learn;//是否学习技能
        public String name;//技能名称
        public int level;//技能等级
        public int maxlevel;//技能最高等级
        public int around;//技能范围
        public int coolDown;//技能冷却

        @Override
        public String toString() {
            return "Skill{" +
                    "id=" + id +
                    ", learn=" + learn +
                    ", name='" + name + '\'' +
                    ", level=" + level +
                    ", maxlevel=" + maxlevel +
                    ", around=" + around +
                    ", coolDown=" + coolDown +
                    '}';
        }

        public Skill(int id, boolean learn, String name, int level, int maxlevel, int around, int coolDown) {
            this.id = id;
            this.learn = learn;
            this.name = name;
            this.level = level;
            this.maxlevel = maxlevel;
            this.around = around;
            this.coolDown = coolDown;
        }
    }
}
