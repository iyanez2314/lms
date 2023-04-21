
    const api = "https://open-api.herokuapp.com/api/ai-assistance";
    const askiAiBtnElem = document.querySelector(".ask-ai-btn");
    const codeInput = document.querySelector("#code");
    const usersDescription = document.querySelector("#description");
    const aiResponse = document.querySelector("#ai-response");

     function makePostRequestToAi() {
         const encodedCodeSnippet = encodeURI(codeInput.value);
         fetch(api, {
             method: "POST",
             headers: {
                 "Content-Type": "application/json",
             },
             body: JSON.stringify({
                 "prompt": usersDescription.value,
                 "codeSnippet": encodedCodeSnippet
             })
         })
             .then(response => response.json())
             .then(data => {
                 aiResponse.value = data;
             })
             .catch(error => console.error(error))
    }

    askiAiBtnElem.addEventListener("click", makePostRequestToAi)
