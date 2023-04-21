
const profilePicElem = document.getElementById("profile-pic");
const client = filestack.init("AhmkLILcZQLGXSg430xAhz");
let videoId;
// This will attach an event listener to  the buttons that are on the videos
document.querySelectorAll('.add-to-playlist-btn').forEach(button => {
    // When said button is clicked it will attach the video id to the global variable and call the open modal function
    button.addEventListener('click', function () {
        // videoId = this.getAttribute('data-video-id');
        const videoTitle = this.getAttribute('data-video-title');
        const videoUrl = this.getAttribute('data-video-url');
        const videoThumbNail = this.getAttribute('data-video-thumbNail');
        // Maybe do a post method to an endpoint that would save the video object to my db when they click
        submitNewVideo(videoTitle, videoUrl, videoThumbNail);
        toggleModal();
    });
});

// This will look for all the playlist buttons that are on the page and attach an event listener
document.querySelectorAll('.playlist-buttons').forEach(button => {
    button.addEventListener('click', function (e) {
        e.preventDefault(); // Prevent the form submission

        const playlistId = this.getAttribute('data-playlist-id');
        const videoIdInput = document.getElementById('videoId');
        const playlistIdInput = document.getElementById('playlistId');

        videoIdInput.value = videoId;
        playlistIdInput.value = playlistId;


        // Manually submit the form after setting the values
        document.getElementById('playlist-form').submit();
    });
});

document.querySelector("#close-modal").addEventListener("click", toggleModal)

profilePicElem.addEventListener("click", () => {
    client.picker({
        onUploadDone: (res) => {
            console.log(res)
            postProfilePicData(res)
        },
    }).open();
});

function submitNewVideo(videoTitle, videoUrl, videoThumbnail){
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
    fetch("/test/vid", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify({
            "video_title": videoTitle,
            "video_url": videoUrl,
            "thumbnail_url": videoThumbnail
        }),
    })
        .then(response => response.json())
        .then(result => {
            console.log(result)
            videoId = result.video_id
        })
        .catch(error => console.log("error", error))
}

function postProfilePicData(res){
    const profilePictureUrl = res.filesUploaded[0].url
    const usersIdElem = document.getElementById('usersId')
    const usersId = usersIdElem.getAttribute("data-user-id")
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
    fetch("/test/profilepic-upload", {
        method: "POST",
        headers : {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken,
        },
        body: JSON.stringify({
            "message": profilePictureUrl,
            "userId": usersId
        })
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error))
}


function toggleModal(){
    const playlistModal = document.getElementById('playlist-modal');
    const playListModalBackDrop = document.getElementById('modal-backdrop');


    const playlistModalClassList = playlistModal.classList.contains("hidden");
    const playlistModalBackDropClassList = playListModalBackDrop.classList.contains("hidden");

    if(playlistModalClassList && playlistModalBackDropClassList) {
        playlistModal.classList.remove("hidden");
        playListModalBackDrop.classList.remove("hidden");
    } else if (!playlistModalClassList && !playlistModalBackDropClassList){
        playlistModal.classList.add("hidden");
        playListModalBackDrop.classList.add("hidden")
    }

}