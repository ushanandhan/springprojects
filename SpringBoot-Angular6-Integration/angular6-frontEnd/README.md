![image](https://user-images.githubusercontent.com/8769673/62639904-1280b500-b95e-11e9-8131-fd80fee21cb2.png)

# Angular 6 Client Application
## 1. Create Angular 6 client application using Angular CLI
```script
$ npm install -g @angular/cli
$ ng new <<angularAppName>>
```
## 2. Components, Services, and Modules
Let's list out what are components, service, and modules we are going to create in this application. We will use Angular CLI to generate components, services because Angular CLI follows best practices and saves much of time.
1. Components
  * create-employee
  * employee-list
  * employee-details
2. Services
  * employee.service.ts - **Service for Http Client methods**
3. Modules
  * FormsModule
  * HttpClientModule
  * AppRoutingModule.
4. Class (Typescript class)
  * employee.ts: class Employee (id, firstName, lastName, emailId)  

In this next step, we will generate these components, classes, and services using Angular CLI.

## 3. Create Service & Components
Let's auto-generate service and components using Angular CLI
```script
$ ng g s employee
$ ng g c create-employee
$ ng g c employee-details
$ ng g c employee-list
```
Here is complete command and output for your reference:
```script
$ ng g s employee
CREATE src/app/employee.service.spec.ts (343 bytes)
CREATE src/app/employee.service.ts (137 bytes)

$ ng g c create-employee
CREATE src/app/create-employee/create-employee.component.html (34 bytes)
CREATE src/app/create-employee/create-employee.component.spec.ts (685 bytes)
CREATE src/app/create-employee/create-employee.component.ts (304 bytes)
CREATE src/app/create-employee/create-employee.component.css (0 bytes)
UPDATE src/app/app.module.ts (509 bytes)

$ ng g c employee-details
CREATE src/app/employee-details/employee-details.component.html (35 bytes)
CREATE src/app/employee-details/employee-details.component.spec.ts (692 bytes)
CREATE src/app/employee-details/employee-details.component.ts (308 bytes)
CREATE src/app/employee-details/employee-details.component.css (0 bytes)
UPDATE src/app/app.module.ts (629 bytes)

$ ng g c employee-list
CREATE src/app/employee-list/employee-list.component.html (32 bytes)
CREATE src/app/employee-list/employee-list.component.spec.ts (671 bytes)
CREATE src/app/employee-list/employee-list.component.ts (296 bytes)
CREATE src/app/employee-list/employee-list.component.css (0 bytes)
UPDATE src/app/app.module.ts (737 bytes)
```
We will use Bootstrap 4 for styling our application so let's integrate bootstrap 4 with angular 6.

## 4. Integrate Bootstrap with Angular
Use NPM to download Bootstrap & JQuery. Bootstrap and jQuery will be installed into the node_modules folder.

```script
$ npm install bootstrap jquery --save
```
Configure installed Bootstrap & JQuery in an angular.json file:

```javascript
...
 
"styles": [
  "src/styles.css",
  "node_modules/bootstrap/dist/css/bootstrap.min.css"
],
"scripts": [
  "node_modules/jquery/dist/jquery.min.js",
  "node_modules/bootstrap/dist/js/bootstrap.min.js"
]
 
...
```
Let's discuss each of the above generate components and service files and we will customize it as per our requirement.

## 5. Create Employee class employee.ts
Before defining the **EmployeeListComponent**, let’s define an **Employee** class for working with employees. create a new file **employee.ts** inside **src/app** folder and add the following code to it -
```javascript
export class Employee {
    id: number;
    firstName: string;
    lastName: string;
    emailId: string;
    active: boolean;
}
```
## 6. EmployeeService - employee-service.ts
The **EmployeeService** will be used to get the data from backend by calling spring boot APIs. Update the **employee.service.ts** file inside **src/app** directory with the following code to it -
```javascript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = '/api/v1/employees';

  constructor(private http: HttpClient) { }

  getEmployee(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createEmployee(employee: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, employee);
  }
  ...
}
```
## 7. EmployeeListComponent - employee-list.component.ts
Let's update the **EmployeeListComponent** component which will be used to display a list of employee, create a new employee, and delete an employee.

```javascript
import { Observable } from "rxjs";
import { EmployeeService } from "./../employee.service";
import { Employee } from "./../employee";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-employee-list",
  templateUrl: "./employee-list.component.html",
  styleUrls: ["./employee-list.component.css"]
})
export class EmployeeListComponent implements OnInit {
  employees: Observable<Employee[]>;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.employees = this.employeeService.getEmployeesList();
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
}
```
## 8. Create a template for EmployeeListComponent employee-list.component.html
Update **employee-list.component.html** file with the following code to it
```html
<div class="card">
    <div class="card-title">
        <h1>Employees</h1>
    </div>
    <div class="card-body">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr *ngFor="let employee of employees | async">
                    <td>{{employee.firstName}}</td>
                    <td>{{employee.lastName}}</td>
                    <td>{{employee.emailId}}</td>
                    <td><button (click)="deleteEmployee(employee.id)">Delete</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
```
## 9. CreateEmployeeComponent - create-employee.component.ts
**CreateEmployeeComponent** is used to create and handle a new employee form data. Add the following code to it -
```javascript
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  submitted = false;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
  }

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
  }

  save() {
    this.employeeService.createEmployee(this.employee)
      .subscribe(data => console.log(data), error => console.log(error));
    this.employee = new Employee();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
```
## 10. Create a template for EmployeeCreateComponent create-employee.component.html
```html
<h3>Create Employee</h3>
<div [hidden]="submitted" style="width: 400px;">
    <form (ngSubmit)="onSubmit()">
        <div class="form-group">
            <label for="name">First Name</label>
            <input type="text" class="form-control" id="firstName" required [(ngModel)]="employee.firstName" name="firstName">
        </div>

        <div class="form-group">
            <label for="name">Last Name</label>
            <input type="text" class="form-control" id="lastName" required [(ngModel)]="employee.lastName" name="lastName">
        </div>

        <div class="form-group">
            <label for="name">First Name</label>
            <input type="text" class="form-control" id="emailId" required [(ngModel)]="employee.emailId" name="emailId">
        </div>

        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>

<div [hidden]="!submitted">
    <h4>You submitted successfully!</h4>
</div>
```
## 11. EmployeeDetailsComponent- employee-details.component.ts
```javascript
import { Employee } from './../employee';
import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { EmployeeListComponent } from '../employee-list/employee-list.component';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  @Input() employee: Employee;

  constructor(private employeeService: EmployeeService, private listComponent: EmployeeListComponent) { }

  ngOnInit() {
  }
}
```
## 12. Create a template for EmployeeDetailsComponent employee-details.component.html
```html
<div *ngIf="employee">
    <div>
        <label>Name: </label> {{employee.firstName}}
    </div>
    <div>
        <label>Age: </label> {{employee.lastName}}
    </div>
    <div>
        <label>Active: </label> {{employee.emailId}}
    </div>
    <div>
        <label>Active: </label> {{employee.active}}
    </div>

    <span class="button is-small btn-primary" *ngIf='employee.active' (click)='updateActive(false)'>Inactive</span>

    <span class="button is-small btn-primary" *ngIf='!employee.active' (click)='updateActive(true)'>Active</span>

    <span class="button is-small btn-danger" (click)='deleteEmployee()'>Delete</span>

    <hr/>
</div>
```
## 13. AppRoutingModule - app-routing.module.ts
```javascript
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'employee', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'add', component: CreateEmployeeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```
## 14. AppComponent - app/app.component.ts
Defines the logic for the app's root component, named **AppComponent**. The view associated with this root component becomes the root of the view hierarchy as you add components and services to your app.
```javascript
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular 6 + Spring Boot 2 + Spring Data JPA + H2 + CRUD Tutorial';
}
```
## 15. app/app.component.html
Defines the HTML template associated with the root **AppComponent**.
```javascript
<div class="container">
    <h2>{{title}}</h2>
    <hr>

    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a routerLink="employees" class="btn btn-primary active" role="button" routerLinkActive="active">Employees</a>
            </li>
            <li class="nav-item" style="margin-left: 10px;">
                <a routerLink="add" class="btn btn-primary active" role="button" routerLinkActive="active">Add</a>
            </li>
        </ul>

    </nav>
    <router-outlet></router-outlet>
</div>
```
## 16. app/app.module.ts
Defines the root module, named **AppModule**, that tells Angular how to assemble the application. Initially declares only the **AppComponent**. As you add more components to the app, they must be declared here.
```javascript
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    CreateEmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```
## 16. Configure a proxy for your API calls with Angular CLI
In this example, I have used proxy to configure our Angular CLI dev-server proxy.
To set it up, we need to create a file **proxy.conf.json** at the root of our Angular CLI project. The content should look as follows:
### proxy.conf.json 
```javascript
{
  "/api/v1/employees": {
    "target": "http://localhost:8080",
    "secure": false
  }
}
```
## 17. Running Angular 6 Application
Let's run the above developed Angular App with a command: **npm start**
