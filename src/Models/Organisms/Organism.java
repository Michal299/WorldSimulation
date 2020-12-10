package Models.Organisms;

import Models.Utilities.Point;
import Models.Worlds.World;

import java.awt.Color;


public abstract class Organism {
    
    protected World world;
    protected Point position;
    protected final int birthRound;
    protected int strength;
    protected int initiative;
    protected int lives;
    protected char symbol;
    protected Color color;

    public Organism(World world, Point position, int birthRound, int strength, int initiative, int lives, char symbol, Color color) {
        this.world = world;
        this.position = (Point)position.clone();
        this.birthRound = birthRound;
        this.strength = strength;
        this.initiative = initiative;
        this.lives = lives;
        this.symbol = symbol;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }
    public int getBirthRound() {
        return birthRound;
    }
    public int getStrength() {
        return strength;
    }
    public int getInitiative() {
        return initiative;
    }
    public int getLives() {
        return lives;
    }
    public char getSymbol() {
        return symbol;
    }
    public Color getColor() {
        return color;
    }
    public int getAge(int currentRound) {
        return currentRound - birthRound;
    }
    public World getWorld() {
        return world;
    }
    public boolean isAlive() {
        if(lives > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setStrength(int value) {
        this.strength = value;
    }
    public void setInitiative(int value) {
        this.initiative = value;
    }
    public void setPosition(Point value) {
        this.position.setX(value.getX());
        this.position.setY(value.getY());
    }
    public void setLives(int value) {
        this.lives = value;
    }
    public void decrementLives(int howMany) {
        this.lives -= howMany;
    }

    @Override
    public String toString() {
        return symbol + position.toString();
    }

    public abstract void action();
    public abstract void collision(Organism attacker);
}