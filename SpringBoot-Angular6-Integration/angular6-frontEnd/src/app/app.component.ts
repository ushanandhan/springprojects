import { Component } from '@angular/core';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular 6 + Spring Boot 2 + Spring Data JPA + H2 + CRUD Tutorial';

  constructor(private loginService:AuthenticationService) { }
}
