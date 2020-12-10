package Models.Utilities.Parsers;

import Models.Abilities.Ability;
import Models.Abilities.Purification;
import Models.Organisms.Organism;
import Models.Organisms.Animals.Antelope;
import Models.Organisms.Animals.CyberSheep;
import Models.Organisms.Animals.Fox;
import Models.Organisms.Animals.Human;
import Models.Organisms.Animals.Sheep;
import Models.Organisms.Animals.Turtle;
import Models.Organisms.Animals.Wolf;
import Models.Organisms.Plants.Belladonna;
import Models.Organisms.Plants.Grass;
import Models.Organisms.Plants.Guarana;
import Models.Organisms.Plants.SosnowskyHogweed;
import Models.Organisms.Plants.SowThistle;
import Models.Worlds.HexWorld;
import Models.Worlds.SquaredWorld;

public class PolishParser extends Parser {

    public PolishParser() {

    }

    @Override
    protected String getOrganismName(Organism org) {
        if (org instanceof Wolf) {
            return "Wilk";
        } else if (org instanceof Sheep) {
            return "Owca";
        } else if (org instanceof Fox) {
            return "Lis";
        } else if (org instanceof Turtle) {
            return "Żółw";
        } else if (org instanceof Antelope) {
            return "Antylopa";
        } else if (org instanceof CyberSheep) {
            return "Cyber owca";
        } else if (org instanceof Grass) {
            return "Trawa";
        } else if (org instanceof SowThistle) {
            return "Mlecz";
        } else if (org instanceof Guarana) {
            return "Guarana";
        } else if (org instanceof Belladonna) {
            return "Wilcze jagody";
        } else if (org instanceof SosnowskyHogweed) {
            return "Barszcz sosnowskiego";
        } else if (org instanceof Human) {
            return "Człowiek";
        } else {
            return null;
        }
    }

    @Override
    protected Class<?> getClassByName(String name) {
        if (name.equals("Wilk")) {
            return Wolf.class;
        } else if (name.equals("Owca")) {
            return Sheep.class;
        } else if (name.equals("Żółw")) {
            return Turtle.class;
        } else if (name.equals("Antylopa")) {
            return Antelope.class;
        } else if (name.equals("Lis")) {
            return Fox.class;
        } else if (name.equals("Cyber owca")) {
            return CyberSheep.class;
        } else if(name.equals("Człowiek")) {
            return Human.class;
        } else if (name.equals("Trawa")) {
            return Grass.class;
        } else if (name.equals("Mlecz")) {
            return SowThistle.class;
        } else if (name.equals("Guarana")) {
            return Guarana.class;
        } else if (name.equals("Wilcze jagody")) {
            return Belladonna.class;
        } else if (name.equals("Barszcz sosnowskiego")) {
            return SosnowskyHogweed.class;
        } else if (name.equals("SquaredWorld")) {
            return SquaredWorld.class;
        } else if (name.equals("HexWorld")) {
            return HexWorld.class;
        } else if(name.equals("Całopalenie")) {
            return Purification.class;
        } else {
            return null;
        }
    }

    @Override
    protected String getAbilityName(Ability ab) {
        if(ab instanceof Purification) {
            return "Całopalenie";
        }
        else {
            return null;
        }
    }
}