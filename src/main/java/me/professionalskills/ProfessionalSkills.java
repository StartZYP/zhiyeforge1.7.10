package me.professionalskills;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.professionalskills.common.CommonProxy;
import me.professionalskills.common.event.CareerEvent;
import me.professionalskills.common.event.KeyDownEvent;
import me.professionalskills.common.event.SkillsEvent;

@Mod(modid = ProfessionalSkills.MODID, name = ProfessionalSkills.NAME, acceptedMinecraftVersions = "1.7.10")
public class ProfessionalSkills {
    public static final String MODID = "professionalskills";
    public static final String NAME = "professionalskills";

    @SidedProxy(clientSide = "me.professionalskills.client.ClientProxy",
            serverSide = "me.professionalskills.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ProfessionalSkills.MODID)
    public static ProfessionalSkills instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        new CareerEvent();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        new SkillsEvent();
        new KeyDownEvent();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
