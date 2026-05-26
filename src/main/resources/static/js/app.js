async function login(role){

const data={

email:
document.getElementById(
"email"
).value,

password:
document.getElementById(
"password"
).value

};

const response=

await fetch(
"/api/users/login",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(
data
)

}

);

const result=

await response.text();

if(
result.trim()!=="Login Success"
){

alert(
result
);

return;

}

const userResponse=

await fetch(
"/api/users"
);

const users=

await userResponse.json();

const currentUser=

users.find(

u=>

u.email===data.email

);

if(
!currentUser
){

alert(
"User not found"
);

return;

}

if(
currentUser.role!==role
){

alert(
"Wrong portal selected"
);

return;

}

sessionStorage.setItem(
"userId",
currentUser.id
);

sessionStorage.setItem(
"userRole",
currentUser.role
);

sessionStorage.setItem(
"userName",
currentUser.name
);

sessionStorage.setItem(
"userEmail",
currentUser.email
);

if(
role==="USER"
){

window.location=
"/user/dashboard.html";

}

else if(
role==="AGENT"
){

window.location=
"/agent/dashboard.html";

}

else if(
role==="ADMIN"
){

window.location=
"/admin/dashboard.html";

}

}


async function loadDashboard(){

const role=

sessionStorage.getItem(
"userRole"
);

const userId=

sessionStorage.getItem(
"userId"
);

const dashboard=

document.getElementById(
"dashboard"
);

if(
role==="ADMIN"
){

const response=

await fetch(
"/api/dashboard"
);

const data=

await response.json();

dashboard.innerHTML=

`

<div class="card">

<h3>

Total Users

</h3>

<p>

${data.totalUsers}

</p>

</div>

<div class="card">

<h3>

Total Tickets

</h3>

<p>

${data.totalTickets}

</p>

</div>

<div class="card">

<h3>

Resolved Tickets

</h3>

<p>

${data.resolvedTickets}

</p>

</div>

`;

}

else if(
role==="USER"
){

const response=

await fetch(
"/api/tickets"
);

const tickets=

await response.json();

const myTickets=

tickets.filter(

t=>

t.createdBy.id==
userId

);

const resolved=

myTickets.filter(

t=>

t.status==="RESOLVED"

).length;

const open=

myTickets.filter(

t=>

t.status!=="RESOLVED"

).length;

dashboard.innerHTML=

`

<div class="card">

<h3>

My Tickets

</h3>

<p>

${myTickets.length}

</p>

</div>

<div class="card">

<h3>

Resolved

</h3>

<p>

${resolved}

</p>

</div>

<div class="card">

<h3>

Open

</h3>

<p>

${open}

</p>

</div>

`;

}

else if(
role==="AGENT"
){

const response=

await fetch(

`/api/tickets/agent/${userId}`

);

const tickets=

await response.json();

const resolved=

tickets.filter(

t=>

t.status==="RESOLVED"

).length;

const open=

tickets.filter(

t=>

t.status!=="RESOLVED"

).length;

dashboard.innerHTML=

`

<div class="card">

<h3>

Assigned Tickets

</h3>

<p>

${tickets.length}

</p>

</div>

<div class="card">

<h3>

Resolved By Me

</h3>

<p>

${resolved}

</p>

</div>

<div class="card">

<h3>

Open Tickets

</h3>

<p>

${open}

</p>

</div>

`;

}

}

async function loadTickets(){

const response=
await fetch(
"/api/tickets"
);

const tickets=
await response.json();

let html="";

tickets.forEach(
t => {

html +=

`

<tr>

<td>${t.id}</td>

<td>${t.title}</td>

<td>${t.priority}</td>

<td>${t.status}</td>

<td>

${
t.assignedTo ?

t.assignedTo.name

:

"Not Assigned"

}

</td>

</tr>

`;

}

);

document
.getElementById(
"ticketTable"
)

.innerHTML=

html;

}

async function createTicket(){

const data={

title:
document.getElementById(
"title"
).value,

description:
document.getElementById(
"description"
).value,

priority:
document.getElementById(
"priority"
).value,

userId:
document.getElementById(
"userId"
).value

};

await fetch(
"/api/tickets",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(
data
)

}

);

loadTickets();

alert(
"Ticket Created"
);

}

async function assignTicket(){

const data={

ticketId:
document.getElementById(
"ticketId"
).value,

agentId:
document.getElementById(
"agentId"
).value

};

await fetch(
"/api/tickets/assign",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(
data
)

}

);

loadTickets();

alert(
"Ticket Assigned"
);

}

async function loadReport(){

const response=
await fetch(
"/api/reports"
);

const data=
await response.json();

document
.getElementById(
"report"
)

.innerHTML=

`

<p>Total Tickets:
${data.totalTickets}</p>

<p>Open Tickets:
${data.openTickets}</p>

<p>Resolved Tickets:
${data.resolvedTickets}</p>

<p>High Priority:
${data.highPriorityTickets}</p>

`;

}

async function updateStatus(){

const id=
document.getElementById(
"statusTicketId"
).value;

const status=
document.getElementById(
"status"
).value;

await fetch(

`/api/tickets/status/${id}?status=${status}`,

{

method:"PUT"

}

);

loadTickets();

alert(
"Status Updated"
);

}

async function addComment(){

const data={

ticketId:
document.getElementById(
"ticketId"
).value,

userId:
document.getElementById(
"userId"
).value,

message:
document.getElementById(
"message"
).value

};

await fetch(
"/api/comments",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(
data
)

}

);

alert(
"Comment Added"
);

loadComments();

}

async function loadComments(){

const response=
await fetch(
"/api/comments"
);

const comments=
await response.json();

let html="";

comments.forEach(

c => {

html +=

`

<div class="card">

<p>

Ticket:
${c.ticket.id}

</p>

<p>

User:
${c.user.name}

</p>

<p>

${c.message}

</p>

</div>

`;

}

);

document.getElementById(
"comments"
).innerHTML=
html;

}

async function loadAgentTickets(){

const agentId=

sessionStorage.getItem(
"userId"
);

const response=

await fetch(

`/api/tickets/agent/${agentId}`

);

const tickets=
await response.json();

let html="";

tickets.forEach(

t=>{

html +=

`

<div class="card">

<h3>

${t.title}

</h3>

<p>

Priority:
${t.priority}

</p>

<p>

Status:
${t.status}

</p>

<p>

Created By:
${t.createdBy.name}

</p>

</div>

`;

}

);

document
.getElementById(
"agentTickets"
)

.innerHTML=

html;

}


async function loadUsers(){

const response=
await fetch(
"/api/users"
);

const users=
await response.json();

let html="";

users.forEach(

u => {

html +=

`

<div class="card">

<h3>

${u.name}

</h3>

<p>

Email:
${u.email}

</p>

<p>

Role:
${u.role}

</p>

</div>

`;

}

);

document
.getElementById(
"users"
)

.innerHTML=

html;

}

async function loadAdminTickets(){

const response=
await fetch(
"/api/tickets"
);

const tickets=
await response.json();

let html="";

tickets.forEach(

t => {

html +=

`

<div class="card">

<h3>

${t.title}

</h3>

<p>

Priority:
${t.priority}

</p>

<p>

Status:
${t.status}

</p>

<p>

Assigned:
${
t.assignedTo ?

t.assignedTo.name

:

"None"

}

</p>

</div>

`;

}

);

document
.getElementById(
"adminTickets"
)

.innerHTML=

html;

}

function checkRole(
requiredRole
){

const role=
sessionStorage.getItem(
"userRole"
);

if(
role!==requiredRole
){

alert(
"Access Denied"
);

window.location=
"/index.html";

}

}

function logout(){

sessionStorage.clear();

window.location=
"/index.html";

}


function showUser(){

const name=
sessionStorage.getItem(
"userName"
);

const element=
document.getElementById(
"welcome"
);

if(
element
){

element.innerHTML=

"Welcome "
+
name;

}

}

function searchTickets(){

const value=

document
.getElementById(
"searchTicket"
)

.value

.toLowerCase();

const rows=

document.querySelectorAll(
"#ticketTable tr"
);

rows.forEach(

r=>{

if(
r.innerText
.toLowerCase()

.includes(
value
)

)

r.style.display="";

else

r.style.display=
"none";

}

);

}

async function createUserTicket(){

const userId=

sessionStorage.getItem(
"userId"
);

const data={

title:
document.getElementById(
"title"
).value,

description:
document.getElementById(
"description"
).value,

priority:
document.getElementById(
"priority"
).value,

userId:
userId

};

await fetch(
"/api/tickets",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(
data
)

}

);

alert(
"Ticket Submitted"
);

loadUserTickets();

}

async function loadUserTickets(){

const userId=

sessionStorage.getItem(
"userId"
);

const response=

await fetch(
"/api/tickets"
);

const tickets=

await response.json();

const myTickets=

tickets.filter(

t=>

t.createdBy.id==
userId

);

let html="";

myTickets.forEach(

t=>{

html +=

`

<tr>

<td>
${t.id}
</td>

<td>
${t.title}
</td>

<td>
${t.priority}
</td>

<td>
${t.status}
</td>

<td>

${
t.assignedTo ?

t.assignedTo.name

:

"Not Assigned"

}

</td>

</tr>

`;

}

);

document
.getElementById(
"ticketTable"
)

.innerHTML=

html;

}

