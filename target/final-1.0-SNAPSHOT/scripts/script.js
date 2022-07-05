function increment(input) {
    const counter = document.getElementById(input);
    if (counter.value === '') {
        counter.value = '0';
    }
    counter.value = parseInt(counter.value) + 1;
}

function decrement(input) {
    const counter = document.getElementById(input);
    if (counter.value === '') {
        counter.value = '0';
    }
    counter.value = parseInt(counter.value) - 1 < 0 ? 0 : parseInt(counter.value) - 1;
}


function isNumeric(str) {
    if (typeof str != "string") {
        return false
    }
    return !isNaN(str) && !isNaN(parseFloat(str))
}