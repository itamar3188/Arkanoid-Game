import Animations.AnimationRunner;
import Levels.LevelOne;
import Levels.LevelThree;
import Levels.LevelTwo;
import biuoop.GUI;
import game_tools.GameFlow;
import Levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

//Itamar Cohen 318897089

/**
 * This is a basic implementation of the main class for running the game. It creates a new instance of the Game class,
 * initializes it and then runs it.
 * The Game class is responsible for initializing all of the game objects and running the game loop.
 * The initialize() method creates the GUI, initializes the game environment, adds the sprites and initializes the
 * game level. The run() method runs the game loop, which updates and draws the game objects until the game ends.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());
        LevelInformation one = new LevelOne();
        LevelInformation two = new LevelTwo();
        LevelInformation three = new LevelThree();
        List<LevelInformation> listOfLevels = new ArrayList<>();
        if (args.length == 0) {
            listOfLevels.add(one);
            listOfLevels.add(two);
            listOfLevels.add(three);
            gameFlow.runLevels(listOfLevels);
        } else {
            for (String arg : args) {
                switch (arg) {
                    case "1" -> listOfLevels.add(one);
                    case "2" -> listOfLevels.add(two);
                    case "3" -> listOfLevels.add(three);
                    default -> {
                    }
                }
            }
            gameFlow.runLevels(listOfLevels);
        }
    }
}