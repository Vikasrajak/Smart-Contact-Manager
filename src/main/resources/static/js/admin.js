/*

document.querySelector("#image_file_input").addEventListener("change",function(event){
	
	let file = event.traget.file[0];
	let reader  = new FileReader();
	reader.onload = function(){
		
		document.querySelector("#upload_image_preview")
		.setAttribute("src",reader.result);
	};
	
	reader.readAsDataURL(file);
});*/

console.log("vikas kya kar raha hai bhai");
document.querySelector("#image_file_input").addEventListener("change", function(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function() {
        const imgPreview = document.querySelector("#upload_image_preview");
        imgPreview.setAttribute("src", reader.result);
        imgPreview.style.display = "block";
    };

    if (file) {
        reader.readAsDataURL(file);
    }
});
