package me.professionalskills.common.event;

import com.google.gson.Gson;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.professionalskills.ProfessionalSkills;
import me.professionalskills.common.career.Career;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;

import java.nio.charset.Charset;

/**
 * 交互通道
 */
public class CareerEvent {

    public static FMLEventChannel channel;
    public static Career career;
    public static Player player;

    public CareerEvent() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        CareerEvent.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(ProfessionalSkills.MODID);
        CareerEvent.channel.register(this);
        //career = new Career("controller", new Career.Skill(0, true, "普通攻击", 2, 10, 4, 1), new Career.Skill(1, true, "魔法印记", 1, 5, 4, 1), new Career.Skill(2, true, "唤电", 1, 4, 4, 1), new Career.Skill(3, true, "等离子光波", 2, 4, 4, 1), new Career.Skill(4, true, "弑裂破", 1, 2, 4, 1));
        player = new Player();
    }

    /**
     * 客户端收包
     * @param evt
     */
    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent evt) {
        Gson gson = new Gson();
        String json = new String(evt.packet.payload().array()).trim();
        System.out.println(json);
        PackID packID = gson.fromJson(json, PackID.class);
        System.out.println(packID);
        switch (packID.packid) {
            case 0:
                career = gson.fromJson(json, Career.class);
                career.loadCD();
                break;
            case 1:
                Skill skill0 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill0.id, skill0.name, skill0.skilld);
                break;
            case 2:
                Skill skill1 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill1.id, skill1.name, skill1.skilld);
                break;
            case 3:
                Skill skill2 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill2.id, skill2.name, skill2.skilld);
                break;
            case 4:
                Skill skill3 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill3.id, skill3.name, skill3.skilld);
                break;
            case 5:
                Skill skill4 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill4.id, skill4.name, skill4.skilld);
                break;
            case 6:
                Skill skill5 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill5.id, skill5.name, skill5.skilld);
                break;
            case 7:
                Skill skill6 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill6.id, skill6.name, skill6.skilld);
                break;
            case 8:
                Skill skill7 = gson.fromJson(json, Skill.class);
                Career.runSkill(skill7.id, skill7.name, skill7.skilld);
                break;
            case 9:
                SkillsEvent.skill12Boolean = false;
                break;
            case 10:
                player = gson.fromJson(json, Player.class);
                break;
        }
    }

    /**
     * 发送数据包
     * @param array
     */
    public static void sendMessage(byte[] array) {
        ByteBuf buf = Unpooled.wrappedBuffer(array);
        FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(buf), ProfessionalSkills.MODID);
        CareerEvent.channel.sendToServer(packet);
    }

    public static class PackID {
        public int packid;
    }

    /**
     * 插件发给全部玩家执行技能释放的属性类
     */
    public static class Skill {
        public int id;
        public String name;
        public int skilld;
    }

    /**
     *玩家属性类
     */
    public static class Player {
        public int attack;//攻击力
        public int coolingReduction;//冷却缩减
        public int attackSpeed;//攻击速度
        public int hp;//生命值
        public int critDamage;//暴击伤害
        public int armor;//护甲值
        public int critRate;//暴击几率
        public int movingSpeed;//移动速度
        public int spellPower;//法术强度
        public int zdl;//战斗力
        public Player(){
            attack = 10;
            coolingReduction = 15;
            attackSpeed = 20;
            hp = 20;
            critDamage = 100;
            armor = 0;
            critRate = 50;
            movingSpeed = 20;
            spellPower = 10;
            zdl = 11;
        }
    }
}
