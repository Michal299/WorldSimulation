package Controllers;

import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Utilities.Parsers.PolishParser;
import Models.Worlds.TakenPlaceException;
import Models.Worlds.World;

import Views.Panels.ChooseOrganismPanel;

public class AddOrganismController {
    
    public AddOrganismController(Point position) {
        
        World world = MainController.getWorld();
        
        if(world.getOrganismFromSurface(position) != null) {
            return;
        }
        
        var dialog = new ChooseOrganismPanel();
        
        Organism added = null;
        
        
        if(dialog.getResult() == null) {
            return;
        }
        var parser = new PolishParser();
        String strOrg = dialog.getResult() + "/" + position.toString() + "/" + world.getRoundNumber(); 
        try{
            added = (Organism) parser.stringToOrganism(strOrg);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(added != null) {
            try {
                world.addOrganism(added);
                var board = MainController.getMainFrame().getBoard();
                board.updateBoard(world.toColors());
                MainController.getMainFrame().repaint();
            }
            catch (TakenPlaceException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
}