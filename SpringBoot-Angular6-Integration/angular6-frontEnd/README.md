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

```script
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
```script
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
```script
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
