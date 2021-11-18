const formulario = document.getElementById('userform');

const inputs = document.querySelectorAll('#userform input');

const expressions = {
    user: /^[a-zA-Z0-9\_\-]{4,16}$/, //letras, numeros, guion y guion bajo
    name: /^[a-zA-ZÀ-ÿ\s]{4,40}$/, // letras y espacios, puede llevar acentos
    //lastname: /^[a-zA-ZÀ-ÿ\s]{4,40}$/, // letras y espacios, puede llevar acentos
    pass: /^.{4,12}$/, // 4 a 12 dígitos
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
}

const areas = {
    user: false,
    name: false,
    pass: false,
    email: false
}

const validationForm = (e) => {
    switch (e.target.name){
        case "name":
            validationArea(expressions.name, e.target, 'name');
        break;
        case "user":
            validationArea(expressions.user, e.target, 'user');
        break;
        case "email":
            validationArea(expressions.email, e.target, 'email');
        break;
        case "pass":
            validationArea(expressions.pass, e.target, 'pass');
            validarPassword2();
        break;
        case "pass2":
            validarPassword2();
        break;
    }
}

const validationArea = (expression, input, area) => {
    if(expression.test(input.value)){
        document.getElementById(`group_${area}`).classList.remove('form_group-error');
        document.getElementById(`group_${area}`).classList.add('form_group-right');
        document.querySelector(`#group_${area} .group__errorname`).classList.remove('group__errorname-activo');
        
        document.getElementById(`${area}`).classList.remove('fm');
        document.getElementById(`${area}`).classList.add('fm-right');

        areas[area] = true;

        document.getElementById('msj-error').classList.remove('msj-error-activo');

    }else{
        document.getElementById(`group_${area}`).classList.add('form_group-error');
        document.getElementById(`group_${area}`).classList.remove('form_group-right');
        document.querySelector(`#group_${area} .group__errorname`).classList.add('group__errorname-activo');

        document.getElementById(`${area}`).classList.add('fm');
        document.getElementById(`${area}`).classList.remove('fm-right');

        areas[area] = false;
    }
}

const validarPassword2 = () => {
    const inputPassword1 = document.getElementById('pass');
    const inputPassword2 = document.getElementById('pass2');

    if(inputPassword1.value !== inputPassword2.value){
        document.getElementById(`group_pass2`).classList.add('form_group-error');
        document.getElementById(`group_pass2`).classList.remove('form_group-right');
        document.querySelector(`#group_pass2 .group__errorname`).classList.add('group__errorname-activo');
        document.getElementById(`pass2`).classList.add('fm');
        document.getElementById(`pass2`).classList.remove('fm-right');
        areas['pass'] = false;
    }else{
        document.getElementById(`group_pass2`).classList.remove('form_group-error');
        document.getElementById(`group_pass2`).classList.add('form_group-right');
        document.querySelector(`#group_pass2 .group__errorname`).classList.remove('group__errorname-activo');
        document.getElementById(`pass2`).classList.remove('fm');
        document.getElementById(`pass2`).classList.add('fm-right');
        document.getElementById('msj-error').classList.remove('msj-error-activo');
        areas['pass'] = true;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup', validationForm);
    input.addEventListener('blur', validationForm);
});

/*Ultima versión */
formulario.addEventListener('submit', (e) => {
    e.preventDefault();//quita la funcion del boton

    if(areas.name && areas.user && areas.email && areas.pass){
        //formulario.reset();

        document.getElementById('mensaje-exito').classList.add('mensaje-exito-activo');
        setTimeout(() =>{
            document.getElementById('mensaje-exito').classList.remove('mensaje-exito-activo');
            formulario.submit();
        }, 2000);

        document.querySelectorAll('.form_group-right').forEach((icono) =>{
            icono.classList.remove('form_group-right');
        });

        document.getElementById('msj-error').classList.remove('msj-error-activo');

        //formulario.reset();  
    }else{
        document.getElementById('msj-error').classList.add('msj-error-activo');
    }
});
