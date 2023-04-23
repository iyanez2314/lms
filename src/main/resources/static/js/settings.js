const videoElem = document.getElementById("profile-picture");
const client = filestack.init("AhmkLILcZQLGXSg430xAhz");

videoElem.addEventListener("click", () => {
    client.picker({
        onUploadDone: (res) => {
            console.log(res);
            postUpdatedPicture(res);
        },
    }).open();
});

function postUpdatedPicture(res){
    const profilePicUrl = res.filesUploaded[0].url;
    const profilePictureElem = document.getElementById('users-profile-picture');
    const usersId = profilePictureElem.getAttribute('data-user-id');
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
    fetch("/test/profilepic-upload", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify({
            "message": profilePicUrl,
            "userId": usersId
        })
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error))

    location.reload();
}