package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditionelements.ConditionElement;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.timer.Timer;

/**
 * Created by kevin on 28/05/16.
 */
public interface Game {

    void addObject(ObjectInterface object);

    void addCommand(Command command);

    String executeCommand(String playerName, String commandString);

    void update();

    void setGameState(GameState gameState);

    GameState getGameState();

    void addTimer(Timer timer);

    void setWinCondition(String playerID, Condition winCondition);

    void setLostCondition(String playerID, Condition lostCondition);

    void addPlayer(ObjectInterface player);

    String getPlayerIDAvailable();

    void leavePlayer(String playerID);

    void setSender(Sender sender);

    void startLoop();
}
