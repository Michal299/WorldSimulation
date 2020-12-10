package Models.Worlds;

import java.util.Vector;
import java.awt.Color;

import Models.Commentators.Commentator;
import Models.Organisms.Organism;
import Models.Organisms.Animals.Human;
import Models.Utilities.Point;

public abstract class World {
    
    protected final int width;
    protected final int height;

    protected int roundNumber;

    protected Vector<Organism> organisms;
    protected Vector<Organism> childs;
    protected Organism[][] surface;

    protected Commentator commentator;
    protected Human hero; 

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        roundNumber = 0;

        organisms = new Vector<Organism>(width * height);
        childs = new Vector<Organism>();
        surface = new Organism[width][height];

        commentator = null;
        hero = null;
    }

    public void setRoundNumber(int value) {
        this.roundNumber = value;
    }

    public void makeRound() {

        roundNumber++;
        commentator.reportNextRound(roundNumber);

        prepareOrganisms();

        for(var organism : organisms) {
            if(organism.isAlive()) {
                organism.action();
            }
        }

        burryTheDeads();
        mergeChilds();
    }

    public void prepareOrganisms() {
        

        organisms.sort((o1, o2) -> {
            if(o1.getInitiative() > o2.getInitiative()) {
                return -1;
            }
            else if(o1.getInitiative() < o2.getInitiative()) {
                return 1;
            }
            else if(o1.getAge(roundNumber) > o2.getAge(roundNumber)) {
                return -1;
            }
            else if(o1.getAge(roundNumber) < o2.getAge(roundNumber)) {
                return 1;
            }
            else {
                return 0;
            }
        });
    }

    public void addOrganism(Organism organism) throws TakenPlaceException {
        organisms.add(organism);
        var position = organism.getPosition();

        if(surface[position.getX()][position.getY()] == null) {
            surface[position.getX()][position.getY()] = organism;
        }
        else {
            throw new TakenPlaceException("You cannot add organism in place occupied by other organism.");
        }
    }

    public void addChild(Organism child) throws TakenPlaceException {
        childs.add(child);
        var position = child.getPosition();
        if(surface[position.getX()][position.getY()] == null) {
            surface[position.getX()][position.getY()] = child;
        }
        else {
            throw new TakenPlaceException("You cannot add child in place occupied by other organism.");
        }
    }

    public void addHero(Human object) throws TakenPlaceException {
        hero = object;
        organisms.add(hero);

        var position = hero.getPosition();

        if(surface[position.getX()][position.getY()] == null) {
            surface[position.getX()][position.getY()] = hero;
        }
        else {
            throw new TakenPlaceException("You cannot add child in place occupied by other organism.");
        }
    }

    public void removeFromSurface(Organism removed) {
        var position = removed.getPosition();
        surface[position.getX()][position.getY()] = null;
    }

    public void addCommentator(Commentator commentator) {
        this.commentator = commentator;
    }

    public Commentator getCommentator() throws NullPointerException {
        if(commentator != null) {
            return commentator;
        }
        else {
            throw new NullPointerException();
        }
    }

    public Organism getOrganismFromSurface(Point position) {
        return surface[position.getX()][position.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    /**
    *   @return summary number of the living and dead organism including childs number
    */
    public int getOrganismsNumber() {
        return organisms.size() + childs.size();
    }
    
    public Vector<Organism> getOrganismsList() {
        var result = new Vector<Organism>();
        result.addAll(organisms);
        result.addAll(childs);
        return result;
    }
    /**
     * Function returns places only in "straight" line from position point, without turns.
     * It isn't include places between for example if range is 2 and position is (0, 0) it couldn't returns 
     * (0,1) or (1,0).
     * @param position of place that we want take surrounding places.
     * @return vector of points surrounding position. If there is no such place return empty vector.
     */
    public abstract Vector<Point> getPlacesAround(Point position, int range);

    /**
     * 
     * @param position of place that we want take surrounding free places.
     * @return vector of free points surrounding position. If there is no such place return empty vector.
     */
    public Vector<Point> getFreePlacesAround(Point position) {
        
        var places = getPlacesAround(position, 1);
        for(int i = 0; i < places.size(); i++) {
            if(getOrganismFromSurface(places.get(i)) != null) {
                places.remove(i);
                i--;
            }
        }
        return places;
    }

    public Human getHero() {
        return hero;
    }

    public Vector<Point> findAll(Class<?> looking) {
        var result = new Vector<Point>();
        
        for(var organism : organisms) {
            if(organism.getClass().equals(looking)) {
                result.add(organism.getPosition());
            }
        }
        for(var organism : childs) {
            if(organism.getClass().equals(looking)) {
                result.add(organism.getPosition());
            }
        }
        
        return result;
    }
    
    /**
     * Change organims's position on surface from previous to actual position.
     * If there is already antother organisms then throw exception.
     */
    public void updateOrganism(Organism organism, Point previousPosition) throws TakenPlaceException {
        var position = organism.getPosition();
        if(surface[position.getX()][position.getY()] != null) {
            throw new TakenPlaceException("Organism want to update on taken place on surface!");
        }
        surface[position.getX()][position.getY()] = organism;
        surface[previousPosition.getX()][previousPosition.getY()] = null;
    }

    public Color[][] toColors() {
        var colors = new Color[width][height];
        
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                
                if(surface[x][y] != null) {
                    colors[x][y] = surface[x][y].getColor();
                }
            }
        }

        return colors;
    }


    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(surface[x][y] != null) {
                    string.append(surface[x][y].getSymbol());
                }
                else {
                    string.append('.');
                }
            }
            string.append('\n');
        }
        return string.toString();
    }

    private void burryTheDeads() {
        for(int i = 0; i < organisms.size(); i++) {
            if(!organisms.get(i).isAlive()) {
                
                if(organisms.get(i) == hero) {
                    hero = null;
                }

                organisms.remove(i);
                i--;
            }
        }
    }
    
    private void mergeChilds() {
        organisms.addAll(childs);
        childs.removeAllElements();
    }
}