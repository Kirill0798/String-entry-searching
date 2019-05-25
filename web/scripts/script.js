function selectFirstInput() {
    var firstInput = document.getElementById("first");
    selectInput(firstInput);
}
function selectSecondInput() {
    var secondInput = document.getElementById("second");
    selectInput(secondInput);
}
function selectInput(field) {
    field.style.border = "thin inset red";
    field.onfocus = function () {
        field.style.border = "thin inset #00000044";
    }
}