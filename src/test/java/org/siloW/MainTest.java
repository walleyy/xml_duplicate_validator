package org.siloW;

import static org.junit.jupiter.api.Assertions.*;
import static org.siloW.Main.findDuplicate;

class MainTest {

    public static class B{
        String b;

        public B(){}
    }

    @org.junit.jupiter.api.Test
    void findDuplicates() {
        String xml = "<b>name<b>";
        assertDoesNotThrow(()-> findDuplicate(xml,new B()));
    }
}