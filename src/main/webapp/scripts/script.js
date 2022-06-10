
const wrongInputColor = '#800000';

function validate() {

    const usernameInput = document.getElementById("usernameInput");
    const emailInput = document.getElementById("emailInput");
    const passwordInput = document.getElementById("passwordInput");
    const repeatedPassword = document.getElementById("repeatedPassword");

    if (validateUsername(usernameInput)) {
        return false;
    }
    if (validateEmail(emailInput)) {
        return false;
    }
    if (validatePassword(passwordInput)) {
        return false;
    }
    return !validateRepeatedPassword(passwordInput, repeatedPassword);



    function validateUsername(usernameInput) {
        if (usernameInput.value.length < 4 || usernameInput.value.length > 20) {
            usernameInput.placeholder = 'Довжина імені має бути в межах від 4 до 20';
            usernameInput.style.color = wrongInputColor;
            return true;
        }
        const regex = new RegExp("^\\w+?$", "gi");
        const found = regex.test(usernameInput.value);
        console.log(found);
        if (!found) {
            usernameInput.placeholder = 'Допускаються лише символи латиницею та цифри';
            usernameInput.style.color = wrongInputColor;
            return true;
        }
        usernameInput.placeholder = 'Username';
        usernameInput.style.color = 'black';
        return false;
    }

    function validateEmail(emailInput) {
        const regex = new RegExp("\\w+@\\w+.\\w+", "g");
        const found = regex.test(emailInput.value);
        console.log(found);
        if (!found) {
            emailInput.placeholder = 'Не надто схоже на e-mail.';
            emailInput.style.color = wrongInputColor;
            return true;
        }
        emailInput.placeholder = 'E-Mail';
        emailInput.style.color = 'black';
        return false;
    }

    function validatePassword(passwordInput) {
        if (passwordInput.value.length < 6) {
            passwordInput.placeholder = 'Мінімальна довжина 6 символів!';
            passwordInput.style.color = wrongInputColor;
            return true;
        }
        const regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", "g");
        const found = regex.test(passwordInput.value);
        console.log(found);
        if (!found) {
            passwordInput.placeholder = 'Нема символів різних регістрів, цифр';
            passwordInput.style.color = wrongInputColor;
            return true;
        }

        passwordInput.placeholder = 'Password';
        passwordInput.style.color = 'black';
        return false;
    }

    function validateRepeatedPassword(passwordInput, passwordRepeated) {
        if (passwordInput.value !== passwordRepeated.value) {
            passwordRepeated.placeholder = 'Паролі не співпадають!';
            passwordRepeated.style.color = wrongInputColor;
            return true;
        }
        passwordRepeated.placeholder = 'Password';
        passwordRepeated.style.color = 'black';
        return false;
    }
}

function validateCard() {
    const numberInput = document.getElementById("numberInput");
    const tillInput = document.getElementById("tillInput");
    const cvvInput = document.getElementById("cvvInput");

    if (validateNumber(numberInput)) {
        return false;
    }
    if (validateTill(tillInput)) {
        return false;
    }
    return !validateCvv(cvvInput);


    function validateNumber(numberInput) {
        if (numberInput.value.length !== 16 || !isNumeric(numberInput.value)) {
            numberInput.placeholder = 'Довжина номеру картки - 16 цифр';
            numberInput.style.color = wrongInputColor;
            return true;
        }
        numberInput.placeholder = 'Number';
        numberInput.style.color = 'black';
        return false;
    }

    function validateTill(tillInput) {
        const regex = new RegExp("^\\d{2}\\/\\d{2}$", "g");
        const found = regex.test(tillInput.value);
        console.log(found);
        if (!found) {
            tillInput.placeholder = 'Формат **/**';
            tillInput.style.color = wrongInputColor;
            return true;
        }
        tillInput.placeholder = 'Till';
        tillInput.style.color = 'black';
        return false;
    }

    function validateCvv(cvvInput) {
        const regex = new RegExp("^\\d{3}$", "g");
        const found = regex.test(cvvInput.value);
        console.log(found);
        if (!found) {
            cvvInput.placeholder = '***';
            cvvInput.style.color = wrongInputColor;
            return true;
        }
        cvvInput.placeholder = 'Till';
        cvvInput.style.color = 'black';
        return false;
    }
}



function isNumeric(str) {
    if (typeof str != "string") {
        return false
    }
    return !isNaN(str) && !isNaN(parseFloat(str))
}