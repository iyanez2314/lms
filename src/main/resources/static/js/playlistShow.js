const buttonElem = document.getElementById("edit-button");
const playlistnameElem = document.getElementById("playlist-name");
const editTitleForm = document.getElementById("edit-title-form")
const deletePlaylistButtonElem = document.querySelector(".delete-playlist-btn");

document.querySelectorAll(".close-delete-toggle").forEach(button => {
    button.addEventListener("click", toggleDeleteModal)
})

document.querySelector("#exit-edit-btn").addEventListener("click", toggleForm);
deletePlaylistButtonElem.addEventListener("click", toggleDeleteModal);

function toggleDeleteModal() {
    // Grab the elements from the DOM
    const playlistModal = document.getElementById('playlist-modal');
    const playlistModalBackDrop = document.getElementById('modal-backdrop');

    // We check to see if the elements on the page contain "hidden"
    const playlistModalClassList = playlistModal.classList.contains("hidden");
    const playlistModalBackDropClassList = playlistModalBackDrop.classList.contains("hidden");


    if (playlistModalClassList && playlistModalBackDropClassList) {
        // If both elements are hidden, show them.
        playlistModal.classList.remove("hidden");
        playlistModalBackDrop.classList.remove("hidden");
    } else if (!playlistModalClassList && !playlistModalBackDropClassList) {
        // If both elements are visible, hide them.
        playlistModal.classList.add("hidden");
        playlistModalBackDrop.classList.add("hidden");
    }
}

// Come back to his to make these functions better
function toggleForm(){
    playlistnameElem.classList.toggle('hidden');
    editTitleForm.classList.toggle('hidden');
    buttonElem.classList.toggle('hidden');
}

buttonElem.addEventListener("click", toggleForm);