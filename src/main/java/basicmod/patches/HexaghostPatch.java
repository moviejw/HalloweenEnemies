package basicmod.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.exordium.HexaghostBody;
import com.megacrit.cardcrawl.monsters.exordium.HexaghostOrb;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.GhostlyFireEffect;
import com.megacrit.cardcrawl.vfx.combat.GhostIgniteEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

public class HexaghostPatch {
    @SpirePatch(
            clz = HexaghostOrb.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {float.class, float.class, int.class}
    )
    public static class HexaghostOrbColorPatch {
        @SpirePostfixPatch
        public static void Postfix(HexaghostOrb __instance, float x, float y, int index, @ByRef Color[] ___color) {
            ___color[0] = new Color(0.6f, 0.0f, 0.0f, 1.0f);
        }
    }

    @SpirePatch(
            clz = HexaghostBody.class,
            method = "update"
    )
    public static class HexaghostBodyFastUpdatePatch {

        @SpirePrefixPatch
        public static SpireReturn Prefix(
                HexaghostBody __instance,
                @ByRef float[] ___plasma1Angle,
                @ByRef float[] ___plasma2Angle,
                @ByRef float[] ___plasma3Angle,
                @ByRef float[] ___rotationSpeed,
                @ByRef com.megacrit.cardcrawl.vfx.BobEffect[] ___effect,
                @ByRef float[] ___targetRotationSpeed
        ) {
            ___effect[0].update();

            float delta = Gdx.graphics.getDeltaTime();

            ___plasma1Angle[0] += ___rotationSpeed[0] * 3.0f * delta;
            ___plasma2Angle[0] += (___rotationSpeed[0] / 2.0f) * 3.0f * delta;
            ___plasma3Angle[0] += (___rotationSpeed[0] / 3.0f) * 3.0f * delta;

            ___rotationSpeed[0] = MathHelper.fadeLerpSnap(___rotationSpeed[0], ___targetRotationSpeed[0]);

            ___effect[0].speed = ___rotationSpeed[0] * delta;

            return SpireReturn.Return(null);
        }
    }

    @SpirePatch(
            clz = GhostIgniteEffect.class,
            method = "update"
    )
    public static class GhostIgniteEffectColorPatch {
        @SpirePrefixPatch
        public static SpireReturn Prefix(
                GhostIgniteEffect __instance,
                @ByRef float[] ___x,
                @ByRef float[] ___y
        ) {
            float x = ___x[0];
            float y = ___y[0];

            for (int i = 0; i < 25; ++i) {
                AbstractDungeon.effectsQueue.add(new FireBurstParticleEffect(x, y));
                AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(x, y, new Color(0.6f, 0.0f, 0.0f, 1.0f)));
            }

            __instance.isDone = true;
            return SpireReturn.Return(null);
        }
    }

    @SpirePatch(
            clz = GhostlyFireEffect.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {float.class, float.class}
    )
    public static class GhostlyFireEffectColorPatch {

        @SpirePostfixPatch
        public static void Postfix(GhostlyFireEffect __instance, float x, float y, @ByRef Color[] ___color) {
            ___color[0] = new Color(0.6f, 0.0f, 0.0f, 1.0f);
        }
    }
}
