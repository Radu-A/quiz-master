const tipoSelect = document.getElementById('tipoPregunta');
const sectionVF = document.getElementById('sectionVF');
const sectionSU = document.getElementById('sectionSU');
const sectionSM = document.getElementById('sectionSM');

function showSection(type) {
    sectionVF.classList.toggle('active', type === 'VERDADERO_FALSO');
    sectionSU.classList.toggle('active', type === 'SELECCION_UNICA');
    sectionSM.classList.toggle('active', type === 'SELECCION_MULTIPLE');
}

function createFixedOptions(section, count) {
    var listId = section === 'SU' ? 'optionsListSU' : 'optionsListSM';
    var list = document.getElementById(listId);
    var rows = list.querySelectorAll('.option-row');
    rows.forEach(function(row) { row.remove(); });

    for (var i = 0; i < count; i++) {
        var row = document.createElement('div');
        row.className = 'option-row';

        var numSpan = document.createElement('span');
        numSpan.className = 'option-number';
        numSpan.textContent = String.fromCharCode(65 + i);

        var input = document.createElement('input');
        input.type = 'text';
        input.className = 'form-input';
        input.name = 'opcion_' + i;
        input.placeholder = 'Opción ' + String.fromCharCode(65 + i);
        input.required = true;

        var iconWrap = document.createElement('div');

        if (section === 'SU') {
            iconWrap.className = 'option-radio-wrap';
            var radio = document.createElement('input');
            radio.type = 'radio';
            radio.name = 'opcionCorrecta';
            radio.value = String(i);
            radio.id = 'radio_' + i;
            if (i === 0) radio.required = true;

            var mark = document.createElement('label');
            mark.className = 'option-mark';
            mark.setAttribute('for', 'radio_' + i);
            mark.title = 'Marcar como respuesta correcta';

            iconWrap.appendChild(radio);
            iconWrap.appendChild(mark);
        } else {
            iconWrap.className = 'option-checkbox-wrap';
            var cb = document.createElement('input');
            cb.type = 'checkbox';
            cb.name = 'opcionesCorrectas';
            cb.value = String(i);
            cb.id = 'cb_' + i;

            var check = document.createElement('label');
            check.className = 'option-check';
            check.setAttribute('for', 'cb_' + i);
            check.title = 'Marcar como respuesta correcta';

            iconWrap.appendChild(cb);
            iconWrap.appendChild(check);
        }

        row.appendChild(numSpan);
        row.appendChild(input);
        row.appendChild(iconWrap);
        list.appendChild(row);
    }
}

tipoSelect.addEventListener('change', function() {
    var type = this.value;
    showSection(type);
    if (type === 'SELECCION_UNICA') {
        createFixedOptions('SU', 4);
    }
    if (type === 'SELECCION_MULTIPLE') {
        createFixedOptions('SM', 5);
    }
});

document.getElementById('formPregunta').addEventListener('submit', function(e) {
    var tipo = tipoSelect.value;
    if (tipo === 'SELECCION_UNICA') {
        var selected = document.querySelector('input[name="opcionCorrecta"]:checked');
        if (!selected) {
            e.preventDefault();
            alert('Selecciona la respuesta correcta para las opciones de selección única.');
            return;
        }
    }
    if (tipo === 'SELECCION_MULTIPLE') {
        var selected = document.querySelectorAll('input[name="opcionesCorrectas"]:checked');
        if (selected.length === 0) {
            e.preventDefault();
            alert('Selecciona al menos una respuesta correcta para las opciones de selección múltiple.');
            return;
        }
    }
    if (tipo === 'VERDADERO_FALSO') {
        var vfSelected = document.querySelector('input[name="respuestaCorrecta"]:checked');
        if (!vfSelected) {
            e.preventDefault();
            alert('Selecciona si la respuesta es Verdadero o Falso.');
            return;
        }
    }
});
