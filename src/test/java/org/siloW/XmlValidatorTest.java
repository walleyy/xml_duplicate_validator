package org.siloW;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.siloW.XmlValidator.findDuplicate;

class XmlValidatorTest {
    String incorrectXml = "name<c><c>name</c><c>names<c>";
    String incorrectXml2 = "<c><c>name</c><c>names<c>";
    String incorrectXml3 = "<c>name<cL>names</cL>";
    String correctXml = "<c>name</c>";

    @Test
    void findDuplicates() {
        assertDoesNotThrow(()-> findDuplicate(incorrectXml3,new TestClass()));
    }

}