package me.professionalskills.common.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import me.professionalskills.client.ClientProxy;
import me.professionalskills.client.gui.GuiSkill;
import me.professionalskills.common.career.Career;
import me.professionalskills.common.entity.AttackEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class KeyDownEvent {

    public KeyDownEvent() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void keyListener(InputEvent.KeyInputEvent event) {
        if (CareerEvent.career==null){
            return;
        }
        if (ClientProxy.skill0.isPressed()) {
            Career.runSkill(CareerEvent.career.skillID, Minecraft.getMinecraft().thePlayer.getDisplayName(), CareerEvent.career.skillLevel0);
        } else if (ClientProxy.skill1.isPressed()) {
            Career.runSkill(CareerEvent.career.skillID, Minecraft.getMinecraft().thePlayer.getDisplayName(), CareerEvent.career.skillLevel1);
        } else if (ClientProxy.skill2.isPressed()) {
            Career.runSkill(CareerEvent.career.skillID, Minecraft.getMinecraft().thePlayer.getDisplayName(), CareerEvent.career.skillLevel2);
        } else if (ClientProxy.skill3.isPressed()) {
            Career.runSkill(CareerEvent.career.skillID, Minecraft.getMinecraft().thePlayer.getDisplayName(), CareerEvent.career.skillLevel3);
        } else if (ClientProxy.skill4.isPressed()) {
            Career.runSkill(CareerEvent.career.skillID, Minecraft.getMinecraft().thePlayer.getDisplayName(), CareerEvent.career.skillLevel4);
        } else if (ClientProxy.skillsGui.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiSkill());
        }
    }

    public static int skill01 = 0;

    @SubscribeEvent
    public void tickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!AttackEntity.skill01Map.isEmpty()) {
            skill01++;
            if (skill01 >= 100) {
                skill01 = 0;
                AttackEntity.skill01Map.clear();
            }
        }
    }
}