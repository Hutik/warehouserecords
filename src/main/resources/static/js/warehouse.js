const INDEXES_API = "http://192.168.0.104:8080/indexes";

var target = new URL(INDEXES_API);

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
                    <td>${index.category}</td>
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

        /*
        <th scope="col">Index</th>
        <th scope="col">Code</th>
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Category</th>
        <th scope="col">Quantity</th>
        */

function processOkResponse(response = {}) {
    if (response.ok) {
      return response.json();
    }
    throw new Error(`Status not 200 (${response.status})`);
  }

// .addEventListener('click', (event) =>{});
