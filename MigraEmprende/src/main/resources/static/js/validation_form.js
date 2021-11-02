const formulario = document.getElementById('userform');
const inputs = document.querySelectorAll('#userform input');

const expressions = {
    username: /^[a-zA-Z0-9\_\-]{4,16}$/, //letras, numeros, guion y guion bajo
    name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // letras y espacios, puede llevar acentos
    lastname: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // letras y espacios, puede llevar acentos
    pass: /^.{4,12}$/, // 4 a 12 dígitos
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
}

const validationForm = (e) =>{
    switch (e.target.name){
        case "username":
            if(expressions.username.test(e.target.value)){
                document.getElementById('name__group').classList.remove('form_group-error');
                document.getElementById('name__group').classList.add('form_group-right');
                document.querySelector('#name_group i').classList.add('bi bi-check-circle-fill');
                document.querySelector('#name_group i').classList.remove('bi bi-exclamation-diamond-fill');
                document.querySelector('#name_group .group_errorname').classList.remove('group_errorname-activo');
            }else{
                document.getElementById('name__group').classList.add('form_group-error');
                document.getElementById('name__group').classList.remove('form_group-right');
                document.querySelector('#name_group i').classList.remove('bi bi-check-circle-fill');
                document.querySelector('#name_group i').classList.add('bi bi-exclamation-diamond-fill');
                document.querySelector('#name_group .group_errorname').classList.add('group_errorname-activo');
            }
        break;

        case "userlastname":
            
        break;
        case "user":
            
        break;
        case "email":
            
        break;
        case "pass":
            
        break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup', validationForm);
    input.addEventListener('blur', validationForm);
});

formulario.addEventListener('submit', (e) => {
    e.preventDefault();//quita la funcion del boton
});