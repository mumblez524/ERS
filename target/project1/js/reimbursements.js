
const infoList = (reims) => { // info list component

    reims.forEach(reim => {

        var table = document.getElementById("myTable");
        var row = table.insertRow(-1);
        var ID = row.insertCell(0);
        var amount = row.insertCell(1);
        var reason = row.insertCell(2);
        var status = row.insertCell(3);
        var resolver = row.insertCell(4);
        ID.innerHTML = `${reim.employeeID}`;
        amount.innerHTML = `${reim.amount}`;
        reason.innerHTML = `${reim.reason}`;
        status.innerHTML = `${reim.status}`;
        resolver.innerHTML = `${reim.managerID}`;
    })
}

const noContent = (message) => { // no content component
    root.innerHTML = ''; // clear out the root
    const element = document.createElement('p');
    p.classList.add('error', 'no-content');
    p.innerHTML = message;
    root.appendChild(p);
}

document.addEventListener('DOMContentLoaded', async() => {
    const infoResp = await fetch('http://localhost:8080/api/reimbursements');
    const info = await infoResp.json();
    console.log(info);
    if(info.length) {
        infoList(info);
    } else {
        noContent('This user doesn\'t have any reimbursements')
    }
})