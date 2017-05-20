function test() {
    var rows = document.querySelectorAll('table tr');

    for (var i = 1; i < rows.length; i++) {
        var cell = rows[i].childNodes;
        for (var j = 0; j < cell.length; j++) {
           // var id = ['id', 'fname', 'lname', 'mname', 'mphone', 'hphone', 'address', 'email'];
            var id = ['1', '2', '3', '4', '5', '6', '7', '8'];
            cell[j].setAttribute('data', id[j] + i);
            cell[j].setAttribute('contenteditable', 'true');
        }
    }
}

//getAttribute('id')
function removeSpace() {
    var checkData;
    for (var i = 0; i < document.querySelectorAll('table td').length; i++) {
        document.querySelectorAll('table td')[i].onblur = function (event) {
            var target = event.target;
            target.innerHTML = target.innerHTML.replace(/&nbsp;/g, '').replace(/ /g, '').replace(/(<br>)/g,"");
            if (checkData != event.target.innerHTML.replace(/&nbsp;/g, '').replace(/ /g, '').replace(/(<br>)/g,"")) {
                console.log(event.target.getAttribute('data') + ' = ' + event.target.innerHTML);
                console.log(event.target.getAttribute('data') + ' = ' + event.target.innerHTML);

               // editContact(event.target.innerHTML);
            }
        };
        document.querySelectorAll('table td')[i].onChange = function (event) {
            var target = event.target;
            target.innerHTML = target.innerHTML.replace(/&nbsp;/g, '').replace(/ /g, '').replace(/(<br>)/g,"");
        };

        // document.querySelectorAll('table td')[i].onkeyup = function (event) {
        //
        // }

    }

}


for (var i = 0; i < document.querySelectorAll('table td').length; i++) {
    document.querySelectorAll('table td')[i].onclick = function (event) {
        // console.log(event);
        alert(event.target.innerHTML);
    }
}

