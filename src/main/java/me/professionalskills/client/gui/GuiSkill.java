package me.professionalskills.client.gui;

import me.professionalskills.ProfessionalSkills;
import me.professionalskills.common.career.Career;
import me.professionalskills.common.event.CareerEvent;
import me.professionalskills.common.event.SkillsEvent;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.nio.charset.Charset;

public class GuiSkill extends GuiScreen {
    private static ResourceLocation skill = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/skillGui.png");

    @Override
    public void initGui() {
        buttonList.add(new SkillButton(0, this.width / 2 + 130, height / 2 - 60));
        buttonList.add(new SkillButton(1, this.width / 2 + 130, height / 2 - 34));
        buttonList.add(new SkillButton(2, this.width / 2 + 130, height / 2 - 6));
        buttonList.add(new SkillButton(3, this.width / 2 + 130, height / 2 + 20));
        buttonList.add(new SkillButton(4, this.width / 2 + 130, height / 2 + 47));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (!button.enabled) return;
        switch (button.id) {
            case 0:
                String message1 = "{\"type\":\"skillup\",\"id\":0,\"name\":\"" + mc.thePlayer.getDisplayName() + "\"}";
                CareerEvent.sendMessage(message1.getBytes(Charset.forName("UTF-8")));
                break;
            case 1:
                String message2 = "{\"type\":\"skillup\",\"id\":1,\"name\":\"" + mc.thePlayer.getDisplayName() + "\"}";
                CareerEvent.sendMessage(message2.getBytes(Charset.forName("UTF-8")));
                break;
            case 2:
                String message3 = "{\"type\":\"skillup\",\"id\":2,\"name\":\"" + mc.thePlayer.getDisplayName() + "\"}";
                CareerEvent.sendMessage(message3.getBytes(Charset.forName("UTF-8")));
                break;
            case 3:
                String message4 = "{\"type\":\"skillup\",\"id\":3,\"name\":\"" + mc.thePlayer.getDisplayName() + "\"}";
                CareerEvent.sendMessage(message4.getBytes(Charset.forName("UTF-8")));
                break;
            case 4:
                String message5 = "{\"type\":\"skillup\",\"id\":4,\"name\":\"" + mc.thePlayer.getDisplayName() + "\"}";
                CareerEvent.sendMessage(message5.getBytes(Charset.forName("UTF-8")));
                break;
        }
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        GL11.glPushMatrix();
        GL11.glEnable(3008);
        GL11.glColor4d(255, 255, 255, 1);
        mc.renderEngine.bindTexture(skill);
        Gui.func_146110_a(this.width / 2 - 175, this.height / 2 - 95, 0, 0, 350, 190, 350, 190);
        GL11.glDisable(3008);
        GL11.glPopMatrix();

        drawSkills(SkillsEvent.skill0, this.width / 2 - 30, this.height / 2 - 61);
        drawSkills(SkillsEvent.skill1, this.width / 2 - 30, this.height / 2 - 34);
        drawSkills(SkillsEvent.skill2, this.width / 2 - 30, this.height / 2 - 7);
        drawSkills(SkillsEvent.skill3, this.width / 2 - 30, this.height / 2 + 20);
        drawSkills(SkillsEvent.skill4, this.width / 2 - 30, this.height / 2 + 47);


        FontRenderer fontRenderer = mc.fontRenderer;
        drawSkill(CareerEvent.career.skillLevel0, width / 2 - 5, height / 2 - 54, fontRenderer);
        drawSkill(CareerEvent.career.skillLevel1, width / 2 - 5, height / 2 - 27, fontRenderer);
        drawSkill(CareerEvent.career.skillLevel2, width / 2 - 5, height / 2, fontRenderer);
        drawSkill(CareerEvent.career.skillLevel3, width / 2 - 5, height / 2 + 27, fontRenderer);
        drawSkill(CareerEvent.career.skillLevel4, width / 2 - 5, height / 2 + 54, fontRenderer);


        fontRenderer.drawStringWithShadow(String.format("§l" + mc.thePlayer.getDisplayName()), width / 2 - 136, height / 2 - 66, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.career.level), width / 2 - 70, height / 2 - 66, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.zdl), width / 2 - 126, height / 2 - 51, 0xFFFFFF);

        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.critDamage), width / 2 - 126, height / 2 - 11, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.critRate), width / 2 - 126, height / 2 + 1, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.attackSpeed), width / 2 - 126, height / 2 + 13, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.movingSpeed), width / 2 - 126, height / 2 + 24, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.coolingReduction), width / 2 - 126, height / 2 + 36, 0xFFFFFF);

        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.attack), width / 2 - 86, height / 2 - 11, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.hp), width / 2 - 86, height / 2 + 1, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.armor), width / 2 - 91, height / 2 + 13, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l" + CareerEvent.player.spellPower), width / 2 - 80, height / 2 + 24, 0xFFFFFF);

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    private void drawSkill(Career.Skill skill, int width, int height, FontRenderer fontRenderer) {
        fontRenderer.drawStringWithShadow(String.format("§e§l" + skill.name), width, height, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l当前等级：" + skill.level), width + 70, height - 5, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(String.format("§l最大等级：" + skill.maxlevel), width + 70, height + 5, 0xFFFFFF);
    }

    private void drawSkills(ResourceLocation resourceLocation, int width, int height) {
        GL11.glPushMatrix();
        GL11.glEnable(3008);
        GL11.glColor4d(255, 255, 255, 1);
        mc.renderEngine.bindTexture(resourceLocation);
        Gui.func_146110_a(width, height, 0, 0, 22, 22, 22, 22);
        GL11.glDisable(3008);
        GL11.glPopMatrix();
    }
}
