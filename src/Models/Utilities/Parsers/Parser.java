package Models.Utilities.Parsers;

import Controllers.MainController;
import Models.Abilities.Ability;
import Models.Organisms.Organism;
import Models.Utilities.Point;
import Models.Worlds.HexWorld;
import Models.Worlds.SquaredWorld;
import Models.Worlds.World;

public abstract class Parser {
    
    public Parser() {

    }

    public String organismToString(Object obj) {
        
        Organism org = (Organism) obj;
        
        StringBuffer representation = new StringBuffer();
        representation.append(getOrganismName(org));
        representation.append("/");
        representation.append(org.getPosition().toString() + "/");
        representation.append(org.getBirthRound() + "/");
        representation.append(org.getInitiative() + "/");
        representation.append(org.getLives() + "/");
        representation.append(org.getStrength());
        
        return representation.toString();
    }

    public String worldToString(World obj) {
        String type = "World";
        if(obj instanceof SquaredWorld) {
            type = "SquaredWorld";
        }
        else if(obj instanceof HexWorld) {
            type = "HexWorld";
        }

        return type + "/" + obj.getWidth() + "/" + obj.getHeight() + "/" + obj.getRoundNumber();
    }

    public Object stringToWorld(String string) throws ParseErrorException {
        
        var properties = string.split("/");
        if(properties.length != 4) {
            throw new ParseErrorException("Wrong world properiets.");
        }
        try {
            var classType = getClassByName(properties[0]);
            if(classType == null) {
                System.out.println("classTypeis null");
            }
            var constructor = classType.getConstructor(int.class, int.class);
            
            int width = Integer.parseInt(properties[1]);
            int height = Integer.parseInt(properties[2]);
            int roundNumber = Integer.parseInt(properties[3]);
            
            World world = (World)constructor.newInstance(width, height);
            world.setRoundNumber(roundNumber);
            return world;
        }
        catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            System.out.println("No such method exception.");
        }
        catch (SecurityException e) {
            System.out.println(e.getMessage());
            System.out.println("Security exception.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Other.");
        }
        return null;
    }
    
    public Object stringToOrganism(String string) throws ParseErrorException {
        
        Organism result = null;

        var properties = string.split("/");
        if(properties.length != 6 && properties.length != 3) {
            throw new ParseErrorException("Wrong number of parameters in string.");
        }
        
        if(properties.length == 3 || properties.length == 6) {
            try {
                var classType = getClassByName(properties[0]);
                if(classType == null) {
                    System.out.println(properties[0]);
                    throw new ParseErrorException("Wrong Organism name.");
                }

                var constructor = classType.getConstructor(World.class, Point.class, int.class);
                
                var position = new Point(properties[1]);
    
                int birthRound;
    
                try {
                    birthRound = Integer.parseInt(properties[2]);
                }
                catch (NumberFormatException eFormatException) {
                    throw new ParseErrorException("Wrong format (not integers in Organism properties).");
                }
    
                var world = MainController.getWorld();
                
                if(world == null) {
                    throw new NullPointerException("World is null.");
                }
                
                var obj = constructor.newInstance(world, position, birthRound);
                
                result = (Organism) obj;
               
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getLocalizedMessage());
            }

        }
        
        if(properties.length == 6) {
            int initiative;
            int lives;
            int strength;
            try {

                initiative = Integer.parseInt(properties[3]);

                lives = Integer.parseInt(properties[4]);

                strength = Integer.parseInt(properties[5]);
            }
            catch (NumberFormatException eFormatException) {
                throw new ParseErrorException("Wrong format (not integers in Organism properties).");
            }

            result.setInitiative(initiative);
            result.setLives(lives);
            result.setStrength(strength);
        }

        return result;
    }

    public String abilityToString(Ability ability) {
        StringBuffer result = new StringBuffer();
        result.append("Ability/");
        result.append(getAbilityName(ability) + "/");
        result.append(ability.getDealy() + "/");
        result.append(ability.isActive() + "/");
        result.append(ability.getCurrentStateTime());

        return result.toString();
    }

    public Object stringToAbility(String expression) {
        var properties = expression.split("/");

        if(properties.length != 5) return null;

        var classType = getClassByName(properties[1]);
        try{
            var constructor = classType.getConstructor(int.class, boolean.class, int.class);
            
            var state = properties[3].equals("false") ? false : true;
            var delay = Integer.parseInt(properties[2]);
            var currentTime = Integer.parseInt(properties[4]);

            var object = constructor.newInstance(delay, state, currentTime);
            
            return object;
        }
        catch (Exception e) {
            System.out.println("Ability parse failed.");
            return null;
        }
    }

    protected abstract String getOrganismName(Organism org);
    protected abstract String getAbilityName(Ability ab);
    protected abstract Class<?> getClassByName(String name);
}