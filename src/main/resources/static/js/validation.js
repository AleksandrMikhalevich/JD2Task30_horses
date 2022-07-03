function validateForm() {

    let y = document.forms["validation"]["type"].value;
    if (y === "") {
        alert("Введите масть лошади");
        return false;
    }

    let x = document.forms["validation"]["age"].value;
    if (x < 1 || x > 30 || (typeof x !== 'number' && isNaN(x))) {
        alert("Введите возраст правильно");
        return false;
    }

    let z = document.forms["validation"]["price"].value;
    if (z < 3000 || z > 100000 || (typeof z !== 'number' && isNaN(z))) {
        alert("Введите цену правильно");
        return false;
    }
}