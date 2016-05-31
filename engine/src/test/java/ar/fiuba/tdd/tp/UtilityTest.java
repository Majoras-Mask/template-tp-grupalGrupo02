package ar.fiuba.tdd.tp;


import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by kevin on 28/05/16.
 */
public class UtilityTest {

    @Test
    public void getTagObjectGroupsTest() {
        String stringExample = "pick (object)";
        List<String> list = Utility.getTagObjectGroups(stringExample);
        assertTrue(list.size() == 1);
        assertEquals("(object)", list.get(0));
    }

    @Test
    public void getTagObjectGroupsTest2() {
        String stringExample = "pick (object) (object2)";
        List<String> list = Utility.getTagObjectGroups(stringExample);
        assertTrue(list.size() == 2);
        assertEquals("(object)", list.get(0));
        assertEquals("(object2)", list.get(1));
    }

    @Test
    public void getGroupsTest() {
        List<String> list = Utility.getGroups("pick (.*) (.*)", "pick stick key");
        assertEquals(2, list.size());
        assertEquals("stick", list.get(0));
        assertEquals("key", list.get(1));
    }

    @Test
    public void getObjectGroupsTest() {
        String regexCommand = "pick (object) (wow)";
        String actualCommand = "pick stick key";

        HashMap<String,String> map = Utility.getObjectGroups(regexCommand, actualCommand);
        assertEquals(2, map.size());
        assertEquals("stick", map.get("(object)"));
        assertEquals("key", map.get("(wow)"));
    }

    @Test
    public void makeCommandARegexTest() {
        String regexCommand = "pick (object) (wow)";
        assertEquals("pick (.*) (.*)", Utility.makeCommandARegex(regexCommand));
    }

}