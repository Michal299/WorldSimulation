package Models.Commentators;

import javax.swing.JTextArea;

import Models.Abilities.Ability;
import Models.Organisms.Organism;
import Models.Utilities.Point;

public class PolishCommentator implements Commentator {

    private JTextArea placeForComments;

    public PolishCommentator(JTextArea placeForComments) {
        this.placeForComments = placeForComments;
    }

    @Override
    public void speak(String message) {
        placeForComments.append(message + "\n");
    }

    @Override
    public void reportBirthday(Organism firstParent, Organism secondParent, Organism child) {
        String message = firstParent.toString() + " razem z " + secondParent.toString() + " stworzyli potomka "
                + child.toString();
        speak(message);
    }

    @Override
    public void reportDeath(Organism dead, Organism killer) {
        String message = dead.toString() + " zabite przez " + killer.toString();
        speak(message);
    }

    @Override
    public void reportFight(Organism attacker, Organism defender) {
        String message = attacker.toString() + " atakuje " + defender.toString();
        speak(message);
    }

    @Override
    public void reportMove(Organism organism, Point nextPosition) {
        String message = organism.toString() + " przemieszcza się na " + nextPosition.toString();
        speak(message);
    }

    @Override
    public void reportReproductionAttempt(Organism active, Organism passive) {
        String message = active.toString() + " stara sie rozmnozyc z " + passive.toString();
        speak(message);
    }

    
    @Override
    public void reportSpread(Organism organism, Point seedlingsPlace) {
        String message = organism.toString() + " rozprzestrzenia się na " + seedlingsPlace.toString();
        speak(message);

    }

    @Override
    public void reportPoisoning(Organism poisoned, Organism poisoning) {
        String message = poisoned.toString() + " został otruty przez " + poisoning.toString();
        speak(message);
    }

    @Override
    public void reportNextRound(int roundNumber) {
        String message = "\n Czas na runde numer " + roundNumber + "!\n";
        speak(message);

    }

    @Override
    public void reportBlock(Organism blocking, Organism blocked) {
        String message = blocking.toString() + " zablokował atak " + blocked.toString();
        speak(message);
    }

    @Override
    public void reportStayInPlace(Organism obj) {
        String message = obj.toString() + " pozostaje w miejscu.";
        speak(message);
    }

    @Override
    public void reportEscape(Organism escaping, Organism attacker) {
        String message = escaping.toString() + " ucieka przed atakiem " + attacker.toString();
        speak(message);
    }

    @Override
    public void reportBoost(Organism boosted, Organism booster, int value) {
        String message = booster.toString() + " zwieksza siłę " + boosted.toString() + " o " + value + " punktów.";
        speak(message);
    }

    @Override
    public void reportMoveAttempt(Organism moving, Point destination) {
        String message = moving.toString() + " spróbuje przemieścić się na " + destination.toString();
        speak(message);
    }

    @Override
    public void reportSuperPowerActivation(Organism powerOwner) {
        String message = powerOwner.toString() + " aktywował super moc.";
        speak(message);
    }

    @Override
    public void reportAbilityNotReady(Organism abilityOwner, Ability ability) {
        String message = abilityOwner.toString() + " nie odnowił jeszcze swojej umiejetności. \n";
        message += "Pozostało jeszcze " + (ability.getDealy() - ability.getCurrentStateTime() + 1) + " rund do odnowienia.";
        speak(message);

    }

    @Override
    public void reportAbilityTurned(Organism abilityOwner, Ability ability) {
        String message = abilityOwner.toString() + " ma aktualnie włączoną umiejętność. \n";
        message += "Pozostało jeszcze " + (ability.getDealy() - ability.getCurrentStateTime() + 1) + " rund.";
        speak(message);

    }

    @Override
    public void reportAbilityTurnedOff(Organism owner, Ability ability) {
        String message = owner.toString() + " wyczerpał umiejętność. \n";
        message += "Czas regeneracji wynosi " + (ability.getDealy()) + " rund.";
        speak(message);
    }

    @Override
    public void reportLoad(String fromPath) {
        String message = "Wczytano nowy stan świata z pliku: " + fromPath;
        speak(message);

    }
    
}