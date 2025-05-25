// function fetchScript(url, method) {
async function fetchData() {
    try{
        const response = await fetch('/newPersons', {
            method: 'POST',
            body: JSON.stringify(
                {
                    firstName: 'ali',
                    lastName: 'alipour',
                    email: 'www.aaa@.com',
                }
            ),
            headers: {
                'Content-Type': 'application/json',
            }
        });
        function deletePersonById(id) {
            fetch("/newPersons/" + id, {
                method: "DELETE",
            });
            alert("Delete Person : " + id);
            document.location.replace("/newPersons/");
        }

        console.log(response);
        console.log(response.body);

        if(!response.ok){
            throw new Error(response.statusText);
        }

        // const contentDiv = document.getElementById('contentDiv');
        // contentDiv.innerHTML = response.body;

        // todo : has error
        // const json = await response.json();
        // console.log("Info"  + JSON.stringify(json));
        console.log(response.text());

    }catch(error){
        console.log("Error" + error);
    }
}