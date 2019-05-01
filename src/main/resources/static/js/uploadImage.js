function uploadImage() {
    var formData = new FormData();
    var nameInputField = $('#name-input');
    var authorInputField = $('#author-input');
    var licenseInputField = $('#license-input');
    var fileField = document.querySelector("input[type='file']");

    if(nameInputField[0].checkValidity() === false || authorInputField[0].checkValidity() === false
        || licenseInputField[0].checkValidity() === false || fileField.checkValidity() === false) {
        var message = "The following fields cannot be left blank:\n";
        if(fileField.checkValidity() === false) {
            message = message + "Choose a file\n";
        }
        if (nameInputField[0].checkValidity() === false) {
            message = message + "Photograph Name\n";
        }
        if (authorInputField[0].checkValidity() === false) {
            message = message + "Author Name\n";
        }
        if (licenseInputField[0].checkValidity() === false) {
            message = message + "License\n";
        }
        alert(message);
        return;
    }

    formData.append('metadata', new Blob([JSON.stringify({
        author: authorInputField.val(),
        name: nameInputField.val(),
        license: licenseInputField.val()
    })], {type: 'application/json'}));
    formData.append('rawdata', fileField.files[0]);


}
