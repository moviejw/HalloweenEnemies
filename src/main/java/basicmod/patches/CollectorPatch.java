package basicmod.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.monsters.city.TorchHead;
import com.megacrit.cardcrawl.monsters.exordium.HexaghostOrb;
import javassist.CtBehavior;

public class CollectorPatch {
    @SpirePatch(
            clz = TheCollector.class,
            method = "update"
    )
    public static class CollectorUpdatePatch {

        @SpireInsertPatch(
                rloc = 1
        )
        public static SpireReturn<Void> Insert(TheCollector __instance) {
            return SpireReturn.Return();
        }
    }

    @SpirePatch(
            clz = TorchHead.class,
            method = "update"
    )
    public static class TorchHeadUpdatePatch {

        @SpireInsertPatch(
                rloc = 1
        )
        public static SpireReturn<Void> Insert(TorchHead __instance) {
            return SpireReturn.Return();
        }
    }
}
