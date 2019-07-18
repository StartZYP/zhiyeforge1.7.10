package me.professionalskills.client.gui;

import me.professionalskills.ProfessionalSkills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SkillButton extends GuiButton {

    private static ResourceLocation skillButton1 = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/skillButton1.png");
    private static ResourceLocation skillButton2 = new ResourceLocation(ProfessionalSkills.MODID, "skills/guis/skillButton2.png");

    public SkillButton(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_) {
        super(p_i1020_1_, p_i1020_2_, p_i1020_3_, "");
        this.width = 20;
        this.height = 20;
    }

    @Override
    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_) {
        this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
        int k = this.getHoverState(this.field_146123_n);
        GL11.glPushMatrix();
        GL11.glEnable(3008);
        GL11.glColor4d(255, 255, 255, 1);
        if (k == 1) {
            mc.renderEngine.bindTexture(skillButton1);
            Gui.func_146110_a(xPosition, yPosition, 0, 0, 20, 20, 20, 20);
        }
        else if (k == 2) {
            mc.renderEngine.bindTexture(skillButton2);
            Gui.func_146110_a(xPosition, yPosition, 0, 0, 20, 20, 20, 20);
        }
        GL11.glDisable(3008);
        GL11.glPopMatrix();
    }
}
