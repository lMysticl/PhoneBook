$(function () {
    var editingContactId;

    if (document.location.pathname == "/") {

        getAllContact();
    }

    if (document.location.search == "?error" && document.location.pathname == "/login") {
        alert("You enter invalid password or username")
    }

    $("#registration-form").submit(function (event) {
        var formData = {
            "username": $("#username").val(),
            "firstname": $("#firstname").val(),
            "lastname": $("#lastname").val(),
            "middlename": $("#middlename").val(),
            "password": $("#password").val()
        };

        if (!validateRegistrationForm(formData)) {
            event.preventDefault();
            return;
        }

        $.post("/registration", formData)
            .done(function (data) {
                $(location).attr("href", "/login")
            })
            .fail(function (data) {
                alert(data.responseText)
            });
        event.preventDefault();
    });

    $("#add-contact-form").submit(function (event) {
        var formData = {
            "lastname": $("#add_lastname").val(),
            "firstname": $("#add_firstname").val(),
            "middlename": $("#add_middlename").val(),
            "mobilePhone": $("#add_mobilePhone").val(),
            "homePhone": $("#add_homePhone").val(),
            "address": $("#add_address").val(),
            "email": $("#add_email").val()
        };

        if (!validateContact(formData)) {
            event.preventDefault();
            return;
        }

        $.post("/contacts/add", formData)
            .done(function (data) {
                insertDataToTable(data);
            })
            .fail(function () {
                alert("Something has gone wrong");
            });
        event.preventDefault();
    });

    $("#edit-contact-form").submit(function (event) {
        var formData = {
            "contactId": editingContactId,
            "lastname": $("#edit_lastname").val(),
            "firstname": $("#edit_firstname").val(),
            "middlename": $("#edit_middlename").val(),
            "mobilePhone": $("#edit_mobilePhone").val(),
            "homePhone": $("#edit_homePhone").val(),
            "address": $("#edit_address").val(),
            "email": $("#edit_email").val()
        };


        if (!validateContact(formData)) {
            event.preventDefault();
            return;
        }

        var childNodes = $("#" + editingContactId)[0].childNodes;
        childNodes[0].innerHTML = formData.lastname;
        childNodes[1].innerHTML = formData.firstname;
        childNodes[2].innerHTML = formData.middlename;
        childNodes[3].innerHTML = formData.mobilePhone;
        childNodes[4].innerHTML = formData.homePhone;
        childNodes[5].innerHTML = formData.address;
        childNodes[6].innerHTML = formData.email;

        $.post("/contacts/update", formData)
            .done(function (data) {
                alert("Contact edit")
            })
            .fail(function () {
                alert("Something has gone wrong");
            });

        event.preventDefault();

    });

    $("#table-body").on('click', '.delete-contact', function () {
        var contactId = {"contactId": $(this).closest('.table-row').attr("id")};
        $(this).closest('.table-row').remove();
        $.post("/contacts/delete", contactId);

    });

    $("#table-body").on('click', '.edit-contact', function () {
        editingContactId = $(this).closest('.table-row').attr("id");
        var childNodes = $(this).closest('.table-row')[0].childNodes;

        $("#edit_lastname").val(childNodes[0].innerHTML);
        $("#edit_firstname").val(childNodes[1].innerHTML);
        $("#edit_middlename").val(childNodes[2].innerHTML);
        $("#edit_mobilePhone").val(childNodes[3].innerHTML);
        $("#edit_homePhone").val(childNodes[4].innerHTML);
        $("#edit_address").val(childNodes[5].innerHTML);
        $("#edit_email").val(childNodes[6].innerHTML);
    });


    function validateRegistrationForm(formData) {
        var regExp = /^[a-zA-Z0-9]+$/i;
        if ((!regExp.test(formData.username)) || (formData.username.length < 3)) {
            alert("Username must contain English characters only, at least 3 characters, without special characters");
            return false;
        }
        if (formData.password.length < 5) {
            alert("Password must contain at least 5 characters");
            return false;
        }
        if (formData.firstname.length < 5) {
            alert("Firstname must contain at least 5 characters");
            return false;
        }
        if (formData.lastname.length < 5) {
            alert("Lastname must contain at least 5 characters");
            return false;
        }
        if (formData.middlename.length < 5) {
            alert("Middlename must contain at least 5 characters");
            return false;
        }
        return true;
    }

    function getAllContact() {
        $.get("/contacts/get-all")
            .done(function (data) {
                data.forEach(function (item) {
                    insertDataToTable(item)
                });
            })
            .fail(function (data) {
                alert(data.responseText)
            })
    }

    function insertDataToTable(data) {
        if (data.homePhone == null || data.homePhone == "") {
            data.homePhone = "-";
        }
        if (data.address == null || data.address == "") {
            data.address = "-";
        }
        if (data.email == null || data.email == "") {
            data.email = "-";
        }
        // var inputValue = "<tr class=\"table-row\" id=\"" + data.contactId + "\">" +
        //     "<td scope=\"table-row\">" + data.lastname + "</td>" +
        //     "<td>" + data.firstname + "</td>" +
        //     "<td>" + data.middlename + "</td>" +
        //     "<td>" + data.mobilePhone + "</td>" +
        //     "<td>" + data.homePhone + "</td>" +
        //     "<td>" + data.address + " </td>" +
        //     "<td>" + data.email +
        //     "<button class=\"delete-contact pull-right\"><span class=\"glyphicon glyphicon-trash\"></span></button> " +
        //     "<button class=\"edit-contact pull-right\" data-toggle=\"modal\" data-target=\"#editModal\"><span class=\"glyphicon glyphicon-pencil\"></span></button></td>" +
        //     "</tr>";
        //
        // $("table tbody").append(inputValue);

        // var x = document.createElement("INPUT");
        // x.setAttribute("type", "checkbox");
        // x.setAttribute("value", "abc");
        // x.setAttribute("name", "xyz");
        //
        // document.body.appendChild(x);

        var myTable = $('#example').DataTable();
        //TODO
        myTable.row.add([

            data.contactId,
            data.firstname,
            data.lastname,
            data.middlename,
            data.mobilePhone,
            data.homePhone,
            data.address,
            data.email
        ]).draw();


        test();

        //  removeSpace();


        // $("#edit-contact-form").submit(function (event) {
        //     var formData = {
        //         "contactId":editingContactId,
        //         "lastname":$("#edit_lastname").val(),
        //         "firstname":$("#edit_firstname").val(),
        //         "middlename":$("#edit_middlename").val(),
        //         "mobilePhone":$("#edit_mobilePhone").val(),
        //         "homePhone":$("#edit_homePhone").val(),
        //         "address":$("#edit_address").val(),
        //         "email":$("#edit_email").val()
        //     };
        //
        //
        //
        //     if(!validateContact(formData)){
        //         event.preventDefault();
        //         return;
        //     }
        //
        //     var childNodes = $("#"+editingContactId)[0].childNodes;
        //     childNodes[0].innerHTML = formData.lastname;
        //     childNodes[1].innerHTML = formData.firstname;
        //     childNodes[2].innerHTML = formData.middlename;
        //     childNodes[3].innerHTML = formData.mobilePhone;
        //     childNodes[4].innerHTML = formData.homePhone;
        //     childNodes[5].innerHTML = formData.address;
        //     childNodes[6].innerHTML = formData.email;
        //
        //     $.post("/contacts/update", formData)
        //         .done(function (data) {
        //             alert("Contact edit")
        //         })
        //         .fail(function () {
        //             alert("Something has gone wrong");
        //         });
        //
        //     event.preventDefault();
        //
        // });

        var checkData;

        for (var i = 0; i < document.querySelectorAll('table td').length; i++) {
            document.querySelectorAll('table td')[i].onblur = function (event) {
                //   }
                event.target.innerHTML = event.target.innerHTML.replace(/&nbsp;/g, '').replace(/ /g, '').replace(/(<br>)/g, "");
                // if(checkData != event.target.innerHTML.replace(/&nbsp;/g,'').replace(/ /g,'').replace(/(<br>)/g,"")){
                // console.log(event.target.getAttribute('data') + ' = ' + event.target.innerHTML);
                getData(event);

            };
            // document.querySelectorAll('table td')[i].onChange = function(event){
            //     checkData = event.target.innerHTML.replace(/&nbsp;/g,'').replace(/ /g,'').replace(/(<br>)/g,"");
            // }

        }

    }


    function validateContact(formData) {
        if (formData.firstname.length < 4) {
            alert("Firstname must contain at least 4 characters");
            return false;
        }
        if (formData.lastname.length < 4) {
            alert("Lastname must contain at least 4 characters");
            return false;
        }
        if (formData.lastname.length < 4) {
            alert("Middlename must contain at least 4 characters");
            return false;
        }
        if (!(formData.email == null) && !(formData.email == "-") && !(formData.email == "")) {
            var regExp = /^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$/i;
            if (!regExp.test(formData.email)) {
                alert("Please enter correct email");
                return false;
            }
        }
        return true;
    }

});


function getData(event) {
    var target = $(event.target);
    var parentRowData = target.parent('tr').children();
    var formData = {
        "contactId": parentRowData.eq(0).html(),
        "lastname": parentRowData.eq(1).html(),
        "firstname": parentRowData.eq(2).html(),
        "middlename": parentRowData.eq(3).html(),
        "mobilePhone": parentRowData.eq(4).html(),
        "homePhone": parentRowData.eq(5).html(),
        "address": parentRowData.eq(6).html(),
        "email": parentRowData.eq(7).html()
    };
    // console.log(formData);

    $.post("/contacts/update", formData)
        .done(function (data) {
            //alert("Contact edit");
            console.log("Contact edit");
        })
        .fail(function () {
            // alert("Something has gone wrong");
        });

}

$(document).ready(function() {
    var table = $('#example').DataTable();

    $('#example tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

    $('#deleteContact').click( function () {
        alert( table.rows('.selected').data().length +' row(s) selected' );
      //  table.rows('.selected').data().deleteRow();

        for (var i=0;i<table.rows('.selected').data().length;i++){
            console.log(table.rows('.selected').data()[i][0]);
            var contactId = {"contactId": table.rows('.selected').data()[i][0]};
            $.post("/contacts/delete", contactId);
        }

        table.rows('.selected').remove();
        table.draw();





        //   var rows = document.querySelectorAll('table tr');
      //   $.each(rows, function( index, value ) {
      //       $('table tr').filter("[data-row-id='" + value + "']").remove();
      //   });

       //  var contactId = {"contactId": $(this).closest('.table-row').attr("id")};
        // $(this).closest('.table-row').remove();
     //   $.post("/contacts/delete", contactId);
    } );
} );



function updateDataTableSelectAllCtrl(table){
    var $table             = table.table().node();
    var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
    var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
    var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

    // If none of the checkboxes are checked
    if($chkbox_checked.length === 0){
        chkbox_select_all.checked = false;
        if('indeterminate' in chkbox_select_all){
            chkbox_select_all.indeterminate = false;
        }

        // If all of the checkboxes are checked
    } else if ($chkbox_checked.length === $chkbox_all.length){
        chkbox_select_all.checked = true;
        if('indeterminate' in chkbox_select_all){
            chkbox_select_all.indeterminate = false;
        }

        // If some of the checkboxes are checked
    } else {
        chkbox_select_all.checked = true;
        if('indeterminate' in chkbox_select_all){
            chkbox_select_all.indeterminate = true;
        }
    }
}

// $(document).ready(function (){
//     // Array holding selected row IDs
//     var rows_selected = [];
//     var table = $('#example').DataTable({
//         'ajax': {
//             'url': '/lab/articles/jquery-datatables-checkboxes/ids-arrays.txt'
//         },
//         'columnDefs': [{
//             'targets': 0,
//             'searchable': false,
//             'orderable': false,
//             'width': '1%',
//             'className': 'dt-body-center',
//             'render': function (data, type, full, meta){
//                 return '<input type="checkbox">';
//             }
//         }],
//         'order': [[1, 'asc']],
//         'rowCallback': function(row, data, dataIndex){
//             // Get row ID
//             var rowId = data[0];
//
//             // If row ID is in the list of selected row IDs
//             if($.inArray(rowId, rows_selected) !== -1){
//                 $(row).find('input[type="checkbox"]').prop('checked', true);
//                 $(row).addClass('selected');
//             }
//         }
//     });
//
//     // Handle click on checkbox
//     $('#example tbody').on('click', 'input[type="checkbox"]', function(e){
//         var $row = $(this).closest('tr');
//
//         // Get row data
//         var data = table.row($row).data();
//
//         // Get row ID
//         var rowId = data[0];
//
//         // Determine whether row ID is in the list of selected row IDs
//         var index = $.inArray(rowId, rows_selected);
//
//         // If checkbox is checked and row ID is not in list of selected row IDs
//         if(this.checked && index === -1){
//             rows_selected.push(rowId);
//
//             // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
//         } else if (!this.checked && index !== -1){
//             rows_selected.splice(index, 1);
//         }
//
//         if(this.checked){
//             $row.addClass('selected');
//         } else {
//             $row.removeClass('selected');
//         }
//
//         // Update state of "Select all" control
//         updateDataTableSelectAllCtrl(table);
//
//         // Prevent click event from propagating to parent
//         e.stopPropagation();
//     });
//
//     // Handle click on table cells with checkboxes
//     $('#example').on('click', 'tbody td, thead th:first-child', function(e){
//         $(this).parent().find('input[type="checkbox"]').trigger('click');
//     });
//
//     // Handle click on "Select all" control
//     $('thead input[name="select_all"]', table.table().container()).on('click', function(e){
//         if(this.checked){
//             $('#example tbody input[type="checkbox"]:not(:checked)').trigger('click');
//         } else {
//             $('#example tbody input[type="checkbox"]:checked').trigger('click');
//         }
//
//         // Prevent click event from propagating to parent
//         e.stopPropagation();
//     });
//
//     // Handle table draw event
//     table.on('draw', function(){
//         // Update state of "Select all" control
//         updateDataTableSelectAllCtrl(table);
//     });
//
//     // Handle form submission event
//     $('#frm-example').on('submit', function(e){
//         var form = this;
//
//         // Iterate over all selected checkboxes
//         $.each(rows_selected, function(index, rowId){
//             // Create a hidden element
//             $(form).append(
//                 $('<input>')
//                     .attr('type', 'hidden')
//                     .attr('name', 'id[]')
//                     .val(rowId)
//             );
//         });
//     });
//
// });











// $(function () {
//     $('#addContact').bind("change keyup input click", function () {
//         add100(event);
//     });
// });
//
//
// function add100(event) {
//
//         var target = $(event.target);
//         var parentRowData = target.parent('tr').children();
//         var formData = {
//             "lastname": parentRowData.eq(1).html(),
//             "firstname": parentRowData.eq(2).html(),
//             "middlename": parentRowData.eq(3).html(),
//             "mobilePhone": parentRowData.eq(4).html(),
//             "homePhone": parentRowData.eq(5).html(),
//             "address": parentRowData.eq(6).html(),
//             "email": parentRowData.eq(7).html()
//         };
//
//
//         $.post("/contacts/add", formData)
//             .done(function (data) {
//                 insertDataToTable(data);
//             })
//             .fail(function () {
//                 alert("Something has gone wrong");
//             });
//         event.preventDefault();
//
// }