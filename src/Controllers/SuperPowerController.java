package Controllers;

public class SuperPowerController {
    
    public SuperPowerController() {
        var world = MainController.getWorld();
        var hero = world.getHero();

        if(hero != null) {
            var ability = hero.getAbility();
            
            if(ability.canActivate()) {
                ability.activate();
                world.getCommentator().reportSuperPowerActivation(hero);
            }
            else {
                if(ability.isActive()) {
                    world.getCommentator().reportAbilityTurned(hero, hero.getAbility());
                }
                else {
                    world.getCommentator().reportAbilityNotReady(hero, hero.getAbility());
                }
                
            }

        }
    }
}