package Models.Commentators;

import Models.Abilities.Ability;
import Models.Organisms.Organism;
import Models.Utilities.Point;

public interface Commentator {
    
    void speak(String message);
    void reportBirthday(Organism firstParent, Organism secondParent, Organism child);
    void reportDeath(Organism dead, Organism killer);
    void reportFight(Organism attacker, Organism defender);
    void reportMove(Organism organism, Point nextPosition);
    void reportReproductionAttempt(Organism active, Organism passive);
    void reportSpread(Organism organism, Point seedlingsPlace);
    void reportPoisoning(Organism poisoned, Organism poisoning); 
    void reportNextRound(int roundNumber);
    void reportBlock(Organism blocking, Organism blocked);
    void reportStayInPlace(Organism obj);
    void reportEscape(Organism escaping, Organism attacker);
    void reportBoost(Organism boosted, Organism booster, int value);
    void reportMoveAttempt(Organism moving, Point destination);
    void reportSuperPowerActivation(Organism powerOwner);
    void reportAbilityNotReady(Organism abilityOwner, Ability ability);
    void reportAbilityTurned(Organism abilityOwner, Ability ability);
    void reportAbilityTurnedOff(Organism owner, Ability ability);
    void reportLoad(String fromPath);
}