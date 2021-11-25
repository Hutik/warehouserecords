var url = window.location.href.replace('http://', '').split('/')[0];
const INDEXES = "http://"+url+"/indexes";
const CATEGORIES = "http://"+url+"/categories";

var target = new URL(INDEXES);

fetch(new URL(CATEGORIES))
    .then(processOkResponse)
    .then(categories => {
        var ooc = categories.map(category => {
            return `<option value='${category.id}'>${category.name}</option>`
        });

        document.getElementById('selectCategory').innerHTML=ooc;
        document.getElementById('categoryFO').innerHTML=ooc;
    });


fetch(target)
        .then(processOkResponse)
        .then(indexes => {
            var i = 0;
            
            document.getElementById('rows').innerHTML = indexes.map(index => {
                i++;
                return `<tr>
                    <th scope="row">${index.index}</th>
                    <td>${index.code}</td>
                    <td>${index.name}</td>
                    <td>${index.description}</td>
                    <td>${index.category.name}</td>
                    <td>${index.quantity}</td>
                </tr>
            `}).join('\n');

            for(;i<10;i++){
                document.getElementById('rows').innerHTML+=`
                    <tr>
                        <th scope="row"></br></th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>`;
            }

        });

function processOkResponse(response = {}) {
    if (response.ok) {
      return response.json();
    }
    throw new Error(`Status not 200 (${response.status})`);
  }
