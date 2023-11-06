package org.siloW;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.siloW.Main.findDuplicate;

class MainTest {
    String incorrectXml = "<c>name<c><cname</c>";
    String correctXml = "<c>name</c>";


    @Test
    void findDuplicates() {
        assertDoesNotThrow(()-> findDuplicate(incorrectXml,new TestClass()));
    }

}