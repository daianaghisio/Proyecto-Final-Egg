const formulario = document.getElementById('form-register');
const inputs = document.querySelectorAll('#form-register input');

const expressions = {
    username: /^[a-zA-Z0-9\_\-]{4,16}$/, //letras, numeros, guion y guion bajo
    name: /^[a-zA-ZÁ-ÿ\s]{1,40}$/, // letras y espacios, puede llevar acentos
    lastname: /^[a-zA-ZÁ-ÿ\s]{1,40}$/, // letras y espacios, puede llevar acentos
    pass: /^.{1,12}$/, // 4 a 12 dígitos
    email: /^[a-zA-Z0-9_.+-]+@[a-ZA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
}