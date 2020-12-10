package Controllers;

import java.io.BufferedReader;
import java.io.FileReader;

import Models.Abilities.Ability;
import Models.Commentators.PolishCommentator;
import Models.Organisms.Organism;
import Models.Organisms.Animals.Human;
import Models.Utilities.Parsers.PolishParser;
import Models.Worlds.HexWorld;
import Models.Worlds.SquaredWorld;
import Models.Worlds.World;
import Views.Boards.HexBoard;
import Views.Boards.SquaredBoard;
import Views.Panels.LoadPanel;

public class LoadController {
    
    public LoadController() {
        
        var panel = new LoadPanel();
        var path = panel.getPath();
        BufferedReader reader = null;
        var parser = new PolishParser();
        World world = null;
        var frame = MainController.getMainFrame();
        
        try {
            
            reader = new BufferedReader(new FileReader(path));
            
            String line = null;
            line = reader.readLine();
            
            if(line != null) {
                world = (World) parser.stringToWorld(line);
                if(world == null) {
                    return;
                }
                
                MainController.addWorld(world);
                world.addCommentator(new PolishCommentator(frame.getCommentsArea()));
            }

            while(true) {
                
                line = reader.readLine();
                if(line == null || line.length() == 0 || line.contains("Ability")) {
                    break;
                }   
                
                Organism obj = (Organism) parser.stringToOrganism(line);
                if(obj instanceof Human) {
                    var hero = (Human) obj;
                    world.addHero(hero);
                }
                else {
                    world.addOrganism(obj);
                }
                
            }
            
            if(line != null && line.length() != 0) {
                var ability = (Ability)parser.stringToAbility(line);
                world.getHero().setAbility(ability);
            } 
            if(world instanceof SquaredWorld) {
                var board = new SquaredBoard(world.getWidth(), world.getHeight(), 20);
                frame.setBoard(board);
                board.updateBoard(world.toColors());
            }
            else if(world instanceof HexWorld) {
                var board = new HexBoard(world.getWidth(), world.getHeight(), 15);
                frame.setBoard(board);
                board.updateBoard(world.toColors());
            }

            frame.getCommentsArea().setText("");
            world.getCommentator().reportLoad(path);
            frame.getMenuPanel().activateButtons();
            frame.revalidate();

        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                }
                catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }
}