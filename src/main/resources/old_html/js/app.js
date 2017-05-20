$(function () {
    var editingContactId;

    if(document.location.pathname == "/") {
        getAllContact();
    }

    if(document.location.search == "?error" && document.location.pathname == "/login") {
        alert("You enter invalid password or username")
    }

    $("#registration-form").submit(function (event) {
        var formData = {
            "username":$("#username").val(),
            "firstname":$("#firstname").val(),
            "lastname":$("#lastname").val(),
            "middlename":$("#middlename").val(),
            "password":$("#password").val()
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
            "lastname":$("#add_lastname").val(),
            "firstname":$("#add_firstname").val(),
            "middlename":$("#add_middlename").val(),
            "mobilePhone":$("#add_mobilePhone").val(),
            "homePhone":$("#add_homePhone").val(),
            "address":$("#add_address").val(),
            "email":$("#add_email").val()
        };

        if(!validateContact(formData)){
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
            "contactId":editingContactId,
            "lastname":$("#edit_lastname").val(),
            "firstname":$("#edit_firstname").val(),
            "middlename":$("#edit_middlename").val(),
            "mobilePhone":$("#edit_mobilePhone").val(),
            "homePhone":$("#edit_homePhone").val(),
            "address":$("#edit_address").val(),
            "email":$("#edit_email").val()
        };



        if(!validateContact(formData)){
            event.preventDefault();
            return;
        }

        var childNodes = $("#"+editingContactId)[0].childNodes;
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
        var contactId = { "contactId" :$(this).closest('.table-row').attr( "id")};
        $(this).closest('.table-row').remove();
        $.post("/contacts/delete", contactId);

    });

    $("#table-body").on('click', '.edit-contact', function () {
        editingContactId = $(this).closest('.table-row').attr( "id");
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
        var regExp =  /^[a-zA-Z0-9]+$/i;
        if((!regExp.test(formData.username)) || (formData.username.length < 3)) {
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

        var inputValue = "<tr class=\"table-row\" id=\"" + data.contactId + "\">" +
            "<td scope=\"table-row\">" + data.lastname + "</td>" +
            "<td>" + data.firstname + "</td>" +
            "<td>" + data.middlename + "</td>" +
            "<td>" + data.mobilePhone + "</td>" +
            "<td>" + data.homePhone + "</td>" +
            "<td>" + data.address + "</td>" +
            "<td>" + data.email + "</td>" +
            "<td><button class=\"delete-contact\"><span class=\"glyphicon glyphicon-trash\"></span></button> </td>" +
            "<td><button class=\"edit-contact\" data-toggle=\"modal\" data-target=\"#editModal\"><span class=\"glyphicon glyphicon-pencil\"></span></button> </td>" +
            "</tr>";

        //   document.getElementById("lastName").innerHTML = data.toString();
        // document.getElementById("lastName").innerHTML = data.lastname;
        // document.getElementById("firstName").innerHTML = data.firstname;
        // document.getElementById("middleName").innerHTML = data.middlename;
        // document.getElementById("mobilePhone").innerHTML = data.mobilePhone;
        // document.getElementById("homePhone").innerHTML = data.homePhone;
        // document.getElementById("address").innerHTML = data.address;
        // document.getElementById("email").innerHTML = data.email;
       $("table tbody").append(inputValue);
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
            if(!regExp.test(formData.email)) {
                alert("Please enter correct email");
                return false;
            }
        }
        return true;
    }

});