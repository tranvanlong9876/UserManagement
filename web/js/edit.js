/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function enablePasswordText() {
    var checkBox = document.getElementById("check-change-password");
    
    if(checkBox.checked == true) {
        document.querySelector(".change-password").classList.remove('disabled');
        document.querySelector(".confirm-password").classList.remove('disabled');
    } else {
        document.querySelector(".change-password").classList.add('disabled');
        document.querySelector(".confirm-password").classList.add('disabled');
    }
}

function chooseFile() {
    var file = document.getElementById("new-fileName");
    var fileType = file.files[0]["type"];
    var form = new FormData();
    var oldPhotoLink = document.getElementById("oldPhotoLink").value;
    form.append("image", file.files[0]);
    if (fileType.split("/")[0] == "image") {
        var settings = {
            url: "https://api.imgbb.com/1/upload?key=9c8d0e3f2cad7ce002d4e106ca08c370",
            method: "POST",
            timeout: 0,
            processData: false,
            mimeType: "multipart/form-data",
            contentType: false,
            data: form
        };

        $.ajax(settings).done(function (response) {
            var jx = JSON.parse(response);
            var img = jx.data.url;
            document.getElementById("show-new-photo").setAttribute("src", img);
            document.getElementById("currentPhotoLink").value = img;
        });
        
        document.getElementById("file-status").innerHTML = "Check Your Image Before Submission";
    } else {
        document.getElementById("file-status").innerHTML = "File must be an image!";
        document.getElementById("show-new-photo").setAttribute("src", oldPhotoLink);
        document.getElementById("currentPhotoLink").value = oldPhotoLink;
    }
}
