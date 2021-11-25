var url = window.location.href.replace('http://', '').split('/')[0];
const INDEXES = "http://"+url+"/indexes";
const CATEGORIES = "http://"+url+"/categories";

document.getElementById("nav-link-home").className=document.getElementById("nav-link-home").className.replace(' active', '');
document.getElementById("nav-link-warehouse").className+=' active';

fetch(new URL(CATEGORIES))
    .then(processOkResponse)
    .then(categories => {
        var ooc = categories.map(category => {
            return `<option value='${category.id}'>${category.name}</option>`
        });

        ooc=`<option value=''></option>`+ooc;

        document.getElementById('selectCategory').innerHTML=ooc;
        document.getElementById('categoryFO').innerHTML=ooc;
    });

function checkIndexes(event){
    event.preventDefault();
    
    var target = new URL(INDEXES);

    var index = document.getElementById('inputIndex').value;
    var code = document.getElementById('inputCode').value;
    var name = document.getElementById('inputName').value;
    var description = document.getElementById('inputDescription').value;
    var category = document.getElementById('selectCategory').value;

    if(index!=''){
        target.href+='/'+index;
    }else if(code!=''||name!=''||description!=''||category!=''){
        target.searchParams.append('code', code);
        target.searchParams.append('name', name);
        target.searchParams.append('description', description);
        target.searchParams.append('categoryId', category);
    }

    fetch(target)
        .then(processOkResponse)
        .then(indexes => {
            var i = 0;
            
            document.getElementById('rows').innerHTML = indexes.map(index => {
                i++;
                return `<tr ondblclick="startEditing()">
                    <th scope="row">${index.index}</th>
                    <td>${index.code}</td>
                    <td>${index.name}</td>
                    <td>${index.description}</td>
                    <td>${(index.category==null)? '':index.category.name}</td>
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
    }

function startEditing(){
    document.getElementById('greyBackground').className=document.getElementById('greyBackground').className.replace(' invisible', ' visible');
    document.getElementById('editForm').className=document.getElementById('editForm').className.replace(' invisible', ' visible');
}

function stopEditing(){
    document.getElementById('greyBackground').className=document.getElementById('greyBackground').className.replace(' visible', ' invisible');
    document.getElementById('editForm').className=document.getElementById('editForm').className.replace(' visible', ' invisible');
}

function processOkResponse(response = {}) {
    if (response.ok) {
      return response.json();
    }
    throw new Error(`Status not 200 (${response.status})`);
  }
