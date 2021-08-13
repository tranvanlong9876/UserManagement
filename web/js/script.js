/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addFile() {
    var file = document.getElementById("file");
    var fileType = file.files[0]["type"];
    var form = new FormData();
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
            document.getElementById("img-setting").setAttribute("src", img);
            document.getElementById("link-photo").value = img;
        });
        
        document.getElementById("img-setting").style.display = "flex";
        document.getElementById("file-status").innerHTML = "Check Your Image Before Submission";
        //document.getElementById("check-valid").setAttribute("onsubmit", "return true");
    } else {
        document.getElementById("img-setting").style.display = "none";
        document.getElementById("file-status").innerHTML = "File must be an image!";
        //document.getElementById("check-valid").setAttribute("onsubmit", "return false");
        document.getElementById("link-photo").value = "";
    }
}