package Models.Abilities;

import Controllers.MainController;
import Models.Organisms.Organism;

public abstract class Ability {
    
    protected boolean active;
    protected int currentStateTime;
    protected int delay;


    public Ability(int delay) {
        active = false;
        currentStateTime = 1;
        this.delay = delay;
    }

    public Ability(int delay, boolean state, int currentStateTime) {
        this.active = state;
        this.currentStateTime = currentStateTime;
        this.delay = delay;
    }

    public boolean isActive() {
        return active;
    }

    public boolean canActivate() {
        
        if(active == false && delay < currentStateTime) {
            return true;
        }
        else {
            return false;
        }
    }

    public void activate() {
        
        if(canActivate()) {
            active = true;
            currentStateTime = 1;
        }
    }

    public int getDealy() {
        return delay;
    }
    
    public int getCurrentStateTime() {
        return currentStateTime;
    }

    public abstract void make(Organism owner);

    protected void incrementCurrentTime() {
        currentStateTime++;
        
        if(active && currentStateTime > delay) {
            deactivate();
            
        }
    }

    protected void deactivate() {
        currentStateTime = 1;
        active = false;

        var world = MainController.getWorld();
        var hero = world.getHero();
        world.getCommentator().reportAbilityTurnedOff(hero, this);
    }
}