function editContact(dataAtribut, contact) {
    alert("YES");
    var formData = {
        "lastname":$("#add_lastname").val(),
        "firstname":$("#add_firstname").val(),
        "middlename":$("#add_middlename").val(),
        "mobilePhone":$("#add_mobilePhone").val(),
        "homePhone":$("#add_homePhone").val(),
        "address":$("#add_address").val(),
        "email":$("#add_email").val()
    };
    var dataAttribute = dataAttribute;
    var contact = contact;
    var formData = new FormData();
    formData.append('contact', contact);
    formData.append('dataAttribute', dataAtribut);
    alert("YES");
    $.ajax({
        url: "/contacts/update",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            // Handle upload success
            console.log('Success contacts updated')
        },
        error: function () {
            // Handle upload error
            console.error('Error contacts updated');
        }
    });

}