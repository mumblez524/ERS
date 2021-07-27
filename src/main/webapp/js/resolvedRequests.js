const reimbursementsList = (reims, root) => { // reimbursement list component

    reims.forEach(reim => {
        var table = document.getElementById("myTable");
        var row = table.insertRow(-1);
        var reqID = row.insertCell(0);
        var ID = row.insertCell(1);
        var name = row.insertCell(2);
        var amount = row.insertCell(3);
        var reason = row.insertCell(4);
        var status = row.insertCell(5);
        var resolver = row.insertCell(6);
        reqID.innerHTML = `${reim.requestID}`;
        ID.innerHTML = `${reim.employeeID}`;
        name.innerHTML = `${reim.employeeName}`
        amount.innerHTML = `$${reim.amount}`;
        reason.innerHTML = `${reim.reason}`;
        status.innerHTML = `${reim.status}`;
        resolver.innerHTML = `${reim.managerID}`;
    })
}

const noContent = (message, root) => { // no content component
    root.innerHTML = ''; // clear out the root
    const element = document.createElement('p');
    p.classList.add('error', 'no-content');
    p.innerHTML = message;
    root.appendChild(p);
}

document.addEventListener('DOMContentLoaded', async() => {
    const listRoot = document.querySelector('#reimbursements');
    //fetch all reimbursements for the initial render
    //TODO make sure the user is logged in and add the user token to the 'Authorization' header
    const reimsResp = await fetch('http://localhost:8080/api/resolvedRequests');
    const reims = await reimsResp.json();
    console.log(reims);
    if(reims.length) {
        reimbursementsList(reims, listRoot);
    } else {
        noContent('This user doesn\'t have any reimbursements', listRoot)
    }
})