// main.js 
// custom javascripts to handle upload, etc.

function confirmUpload() {
    var fileName = document.getElementById("fileUpload").value;

    alert('File ' + fileName + ' was uploaded successfully.');
    window.location.href = "result.html";
}

function scorePrompt() {
    var score = prompt("Enter the minimum score required for similarities")
}

function threshPrompt() {
    var threshold = prompt("Enter the threshold for consensus definition")
}
