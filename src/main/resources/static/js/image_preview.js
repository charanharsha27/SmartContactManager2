console.log("in image preview console");

function imagePreview(input,target) {
    let file = input.files[0];
    let reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onload = function () {
        let img = document.getElementById(target);
        img.src = reader.result;
    }
}