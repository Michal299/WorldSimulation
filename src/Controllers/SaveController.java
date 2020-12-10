package Controllers;

import java.io.FileWriter;
import java.io.IOException;

import Models.Utilities.Parsers.PolishParser;

import Views.Panels.SavePanel;

public class SaveController {
    
    public SaveController() {
        
        var panel = new SavePanel();
        var path = panel.getPath();
        
        FileWriter writer = null;
        try {
            var world = MainController.getWorld();

            writer = new FileWriter(path);

            var parser = new PolishParser();
            
            writer.write(parser.worldToString(world) + "\n");
            
            var objectsToSave = MainController.getWorld().getOrganismsList();

            for(var object : objectsToSave) {
                writer.write(parser.organismToString(object) + "\n");
            }

            if(world.getHero() != null) {
                var ability = world.getHero().getAbility();
                writer.write(parser.abilityToString(ability));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            if(writer != null) {
                try {
                    writer.close();
                }
                catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }       
    }
}