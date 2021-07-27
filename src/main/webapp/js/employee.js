
const infoList = (emps, root) => { // info list component
    root.innerHTML = ''; //clear the list so we don't render duplicates
    emps.forEach(emp => {
        const i = document.createElement('li');
        i.classList.add('list-item');
        i.setAttribute('data-id', emp.employeeID);
        i.innerText = `ID: ${emp.employeeID}
         Name: ${emp.employeeName}
         Job Title: ${emp.jobTitle}
         Department #: ${emp.department}
         Yearly Salary: ${emp.yearlySalary}
         City: ${emp.address.city}
         State: ${emp.address.state}`;
        root.appendChild(i);
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
    const listRoot = document.querySelector('#employeeInfo');
    const infoResp = await fetch('http://localhost:8080/api/employees');
    const info = await infoResp.json();
    console.log(info);
    if(info.length) {
        infoList(info, listRoot);
    } else {
        noContent('This user doesn\'t have any reimbursements', listRoot)
    }
})