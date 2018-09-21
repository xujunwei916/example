function setTableChange(field) {
    var color = null;
    if (field.value == "I") {
        color = BGCOLORI;
    }
    else {
        color = BGCOLORD;
    }
    var TBODIES = field;
    while (TBODIES != null && TBODIES.tagName != "TABLE") {
        TBODIES = TBODIES.parentElement;
    }
    TBODIES = document.getElementsByName(TBODIES.getAttribute("id"));
    var elementOrder = getElementOrder(field) - 1;
    elementOrder = diffirentForRiskClass08(elementOrder, TBODIES[0].getAttribute("kindSub"));
    var oTBODY = null;
    var ind = 1;
    var serialno = 0;
    if (TBODIES[0].getAttribute("mode") != null) {
        ind = TBODIES[0].getAttribute("mode");
    }
    if (TBODIES[0].getAttribute("serialno") != null) {
        serialno = TBODIES[0].getAttribute("serialno");
    }
    for (var i = serialno;
         i < TBODIES.length;
         i++) {
        oTBODY = TBODIES[i].tBodies.item(0);
        if (elementOrder - oTBODY.rows.length / ind > 0) {
            elementOrder -= oTBODY.rows.length / ind;
        }
        else {
            break;
        }
    }
    for (var j = 0;
         j < ind;
         j++) {
        if (elementOrder == 0) {
            setRowColor(oTBODY, color);
        }
        else {
            var rowData = oTBODY.rows[elementOrder * ind - ind + j];
            setRowColor(rowData, color);
        }
    }
}