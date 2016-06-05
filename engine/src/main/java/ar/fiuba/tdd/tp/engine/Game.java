package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.conditions.GameCondition;
import ar.fiuba.tdd.tp.engine.commands.game.GameCommand;

import java.util.*;

public abstract class Game {
    private Map<Integer, GameCondition> winCondition = new HashMap<>();
    private Map<Integer, GameCondition> loseCondition = new HashMap<>();
    private Map<Integer, List<GameCommand>> commands = new HashMap<>();
    private int playerLimit;
    private GameState gameState;

    public Game(int playerLimit) {
        this.playerLimit = playerLimit;
        gameState = GameState.Ready;
    }

    private String processInput(int playerID, String input) {
        String response = input;
        Iterator iterator = commands.get(playerID).iterator();
        while (iterator.hasNext() && response.equals(input)) {
            GameCommand gameCommand = (GameCommand) iterator.next();
            if (gameCommand.checkCommand(input)) {
                response = gameCommand.doCommand(gameCommand.parseCommand(input));
            }
        }
        return response.equals(input) ? "invalid command" : response;
    }

    public void addWinCondition(int playerID, GameCondition winCondition) {
        this.winCondition.put(playerID, winCondition);
    }

    public void addLoseCondition(int playerID, GameCondition loseCondition) {
        this.loseCondition.put(playerID, loseCondition);
    }

    public String command(int playerID, String string) {
        //TODO cuidado si no se inicializó a los players
        if (gameState == GameState.InProgress) {
            String response = processInput(playerID, string);
            seekGameStateChanges();
            if (gameState == GameState.InProgress) {
                return response;
            }
        }
        return gameState.toString();
    }

    private void seekGameStateChanges() {
        //TODO ver que gane/pierda un jugador, no el juego
        for (Integer playerID : winCondition.keySet()) {
            if (winCondition.get(playerID).check()) {
                setGameState(GameState.Won);
                return;
            }
            if (loseCondition.get(playerID).check()) {
                setGameState(GameState.Lost);
                return;
            }
        }
    }

    public void setCommand(int playerID, GameCommand command) {
        if (commands.containsKey(playerID)) {
            commands.get(playerID).add(command);
            return;
        }
        List<GameCommand> commands = new ArrayList<>();
        commands.add(command);
        this.commands.put(playerID, commands);
    }

    public void joinPlayer(int playerID) {
        if (playerLimitReached()) {
            return;
        }
        this.addPlayer(playerID);
        //TODO ver si se empieza apenas se une uno solo
        setGameState(GameState.InProgress);
    }

    public abstract void addPlayer(int playerID);

    public String getWelcomeMessage() {
        //TODO change this
        return "Welcome message.";
    }

    public void setGameState(GameState state) {
        this.gameState = state;
    }

    public boolean playerLimitReached() {
        return (commands.size() >= playerLimit);
    }


}
