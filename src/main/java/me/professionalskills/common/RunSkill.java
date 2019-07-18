package me.professionalskills.common;

import me.professionalskills.common.career.Career;
import net.minecraft.entity.player.EntityPlayer;

public interface RunSkill {
    void runSkill(EntityPlayer playerMP, Career.Skill skill, float num);
}
