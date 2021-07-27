
const reimbursementsList = (emps, root) => { // reimbursement list component
    emps.forEach(emp => {
        var table = document.getElementById("myTable");
        var row = table.insertRow(-1);
        var ID = row.insertCell(0);
        var name = row.insertCell(1);
        var job = row.insertCell(2);
        var dept = row.insertCell(3);
        var salary = row.insertCell(4);
        var address = row.insertCell(5);
        ID.innerHTML = `${emp.employeeID}`;
        name.innerHTML = `${emp.employeeName}`;
        job.innerHTML = `${emp.jobTitle}`;
        dept.innerHTML = `${emp.department}`;
        salary.innerHTML = `${emp.yearlySalary}`;
        address.innerHTML = `${emp.address.city}, ${emp.address.state}`;
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
    const reimsResp = await fetch('http://localhost:8080/api/allEmployees');
    const reims = await reimsResp.json();
    console.log(reims);
    if(reims.length) {
        reimbursementsList(reims, listRoot);
    } else {
        noContent('This user doesn\'t have any reimbursements', listRoot)
    }
})