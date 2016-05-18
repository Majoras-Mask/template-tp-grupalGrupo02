package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenDoor2Test {
    private final OpenDoor2Builder builder = new OpenDoor2Builder();
    private Game game;

    @Before
    public void init() {
        game = builder.build();
    }

    @Test
    public void winGame() {
        game.command("open box");
        game.command("pick key");
        assertEquals("You won the game!", game.command("open door"));
    }

    @Test
    public void alreadyWonGameOpenDoor() {
        game.command("open box");
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("open door"));
    }

    @Test
    public void alreadyWonGamePickKey() {
        game.command("open box");
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("pick key"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        game.command("open box");
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("look around"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        game.command("open box");
        game.command("pick key");
        game.command("open door");
        assertEquals("You won the game!", game.command("something"));
    }

    @Test
    public void invalidPick() {
        assertEquals("Can't do pick on something", game.command("pick something"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", game.command("something"));
    }

    @Test
    public void cantOpenDoorWithoutKey() {
        assertEquals("Can't do open on door", game.command("open door"));
    }

    @Test
    public void lookAround() {
        assertEquals("room1 has door-box-player", game.command("look around"));
    }

    @Test
    public void lookAroundAfterBoxOpened() {
        game.command("open box");
        assertEquals("room1 has door-box-key-player", game.command("look around"));
    }

    @Test
    public void lookAroundAfterBoxOpenedAndAfterPickKey() {
        game.command("open box");
        game.command("pick key");
        assertEquals("room1 has door-box-player", game.command("look around"));
    }

    @Test
    public void pickBox(){
        assertEquals("Can't do pick on box",game.command("pick box"));
    }

    @Test
    public void pickBoxAfterOpened(){
        game.command("opeb box");
        assertEquals("Can't do pick on box",game.command("pick box"));
    }

    @Test
    public void pickBoxAfterOpenedAndAfterPickedKey(){
        game.command("open box");
        game.command("pick key");
        assertEquals("Can't do pick on box",game.command("pick box"));
    }

    @Test
    public void pickDoor(){
        assertEquals("Can't do pick on door",game.command("pick door"));
    }

    @Test
    public void pickKeyThatIDontSee(){
        assertEquals("Can't do pick on key",game.command("pick key"));
    }

    @Test
    public void openKey(){
        game.command("open box");
        assertEquals("Can't do open on key",game.command("open key"));
    }

    @Test
    public void pickKeyAfterPickedKey(){
        game.command("open box");
        game.command("pick key");
        assertEquals("Can't do pick on key",game.command("pick key"));
    }

//    @Test
//    public void openBoxAllreadyOpened(){
//        game.command("open box");
//        assertEquals("Can't do open on box",game.command("open box"));
//    }


}
