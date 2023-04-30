var cars={
    "BMW": {
        "1 серия": ['2004-2007', '2007-2011'],
        "2 серия": ['2003-2010', '2015-2020'],
        "3 серия": ['2015-2018', '2018-']
    },
    "Mercedes": {
        "CLS": ['2000-2010', '2015-2020'],
        "C-class": ['2003-2010', '2015-2020']
    },
    "Volvo": {
        "XC60": ['2000-2010', '2015-2020'],
        "XC90": ['2003-2010', '2015-2020']
    }
}

window.onload = function () {
    var first= document.getElementById('main_menu');
    var second= document.getElementById('sub_menu');
    var third= document.getElementById('third_submenu');

    second.disabled = true
    third.disabled = true

    second.style.display = 'block'
    third.style.display = 'block'

    for(var x in cars){
        // console.log(x);
        first.options[first.options.length] = new Option(x)
    }

    first.onchange = function(){
        second.length = 1
        third.length = 1
        second.disabled = false
        second.style.display = 'block'
        third.style.display = 'block'

        for(var y in cars[this.value]){
            // console.log(y);
            second.options[second.options.length] = new Option(y)
        }
    }


    second.onchange = function(){
        third.length = 1
        third.disabled = false
        third.style.display = 'block'
        z = cars[first.value][this.value]
        console.log(z);
        for(let i=0; i<z.length; i++){
            third.options[third.options.length] = new Option(z[i])
        }
    }
}



/*main.addEventListener('change', function (){
    var selected_option = cars[this.value];

    while (sub.options.length > 0) {
        sub.options.remove(0);
    }

    Array.from(selected_option).forEach(function (el) {
        let option = new Option(el, el);
        sub.appendChild(option);
    });
}); */