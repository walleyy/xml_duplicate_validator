package org.siloW;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.siloW.Main.findDuplicate;

class MainTest {
    String incorrectXml = "name<c><c>name</c><c>names<c>";
    String correctXml = "<c>name</c>";


    @Test
    void findDuplicates() {
        assertDoesNotThrow(()-> findDuplicate(correctXml,new TestClass()));
    }

}