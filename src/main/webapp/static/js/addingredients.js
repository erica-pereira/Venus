let counter = 1;
let limit = 30;
function addInput(divName){
    if (counter == limit)  {
        alert("You have reached the limit of adding " + counter + " ingredients");
    }
    else {
        let newdiv = document.createElement('div');
        newdiv.innerHTML = "Ingredient " + (counter + 1) + ' <br>                                    <input style="margin-top: 5px" type="text" class="form-control" th:field="*{ingredients}">\n';
        document.getElementById(divName).appendChild(newdiv);
        counter++;
    }
}