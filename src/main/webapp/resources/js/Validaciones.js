function upperCase(e) {
    var texto = noespacios(e.value.toUpperCase());
    e.value = texto;
}

function mayus(e) {
    e.value = e.value.toUpperCase();
}

function justNumbers(e)
{
    var key = window.Event ? e.which : e.keyCode
    return ((key >= 48 && key <= 57) || (key == 8))
}

function validarTexto(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla === 8)
        return true;
    patron = /[A-Za-z\s]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function validarTextoNumero(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla === 8)
        return true;
    patron = /[A-Za-z0-9\s]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function noespacios(e) {
    let regexp = / +/g;
    texto = e.value.replace(regexp, "");
    e.value = texto;
}

function validaDNI(dni) {
    var ex_regular_dni;
    ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
    if (ex_regular_dni.test(dni) == true) {
        alert('Dni corresponde');
    } else {
        alert('Dni erroneo, formato no válido');
    }
}



function ValidarDNI(dni) {
    dni = dni.value;
//  numero = dni.substr(0,dni.length-1);
//  let = dni.substr(dni.length-1,1);
//  numero = numero % 23;
//  letra='TRWAGMYFPDXBNJZSQVHLCKET';
//  letra=letra.substring(numero,numero+1);

    if (dni == '00000000') {
        showGrowl();
    }
//  if (letra != let) {
//      showGrowl();
//      alert();
//  }

}


//CampoRequerido function
function requiredField(e) {
    if (e.value.toString().trim().length < 1) {
//red border
        PF('growlId').renderMessage({"summary": "El campo es obligatorio",
            "detail": "",
            "severity": "error"});
        $("#j_idt50_container").css("z-index", "2000");
        $(e).addClass("ui-state-error");
    } else if (e.type == "email") {
        if (e.value.indexOf("@") != -1 && e.value.indexOf(".") != -1) {
//green border
            e.style.borderColor = "#2ecc71";
        } else {
//red border
            e.style.borderColor = "#e74c3c";
        }
    } else {
//green border
        $(e).removeClass("ui-state-error");
    }
}

function validateMask(e) {
    if (isNaN(e.value)) {
//red border
        PF('growlId').renderMessage({"summary": "El campo es obligatorio",
            "detail": "",
            "severity": "error"});
        $(e).addClass("ui-state-error");
    } else {
//green border
        $(e).removeClass("ui-state-error");
    }
}

function showGrowl(e) {
    PF('growlWV').renderMessage({"summary": "Datos Incorrectos",
        "detail": "No existes DNI 00000000",
        "severity": "warn"});
}




//EFECTOS PANTALLA LOGIN


