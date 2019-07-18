package me.professionalskills.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.professionalskills.ProfessionalSkills;
import me.professionalskills.client.render.RenderEntity;
import me.professionalskills.common.CommonProxy;
import me.professionalskills.common.entity.AttackEntity;
import me.professionalskills.common.entity.TestEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {
    public static final KeyBinding skill0 = new KeyBinding("普通攻击", Keyboard.KEY_X, "技能按键");
    public static final KeyBinding skill1 = new KeyBinding("技能1", Keyboard.KEY_C, "技能按键");
    public static final KeyBinding skill2 = new KeyBinding("技能2", Keyboard.KEY_V, "技能按键");
    public static final KeyBinding skill3 = new KeyBinding("技能3", Keyboard.KEY_B, "技能按键");
    public static final KeyBinding skill4 = new KeyBinding("技能4", Keyboard.KEY_N, "技能按键");
    public static final KeyBinding skillsGui = new KeyBinding("技能升级界面", Keyboard.KEY_L, "技能按键");

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientRegistry.registerKeyBinding(skill0);
        ClientRegistry.registerKeyBinding(skill1);
        ClientRegistry.registerKeyBinding(skill2);
        ClientRegistry.registerKeyBinding(skill3);
        ClientRegistry.registerKeyBinding(skill4);
        ClientRegistry.registerKeyBinding(skillsGui);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(AttackEntity.class, new RenderEntity());
        RenderingRegistry.registerEntityRenderingHandler(TestEntity.class, new RenderEntity());
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
