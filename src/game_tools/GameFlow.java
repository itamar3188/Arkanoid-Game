package game_tools;

import Animations.AnimationRunner;
import Levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private GUI gui;
    // private Counter lives;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.gui = ar.getGui();
        this.keyboardSensor = ks;
        this.animationRunner = new AnimationRunner(this.gui);
        //Initialize the game score starting from the wanted amount
        this.score = new Counter(0);
    }


    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int levelsConter = 0;
        //For every level in the list, initialize and run it according to the game rules//
        for (LevelInformation levelInfo : levels) {
            //create level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score);
            level.initialize();
            if (levelsConter == (levels.size() - 1)) {
                level.setFinal(true);
            }
            while (level.getNumBalls() > 0 && level.getNumBlocks() > 0) {
                level.run();
            }
            if (level.getNumBalls() == 0) {
                break;
            }
            levelsConter++;
        }
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    GUI getGui() {
        return this.gui;
    }
}
