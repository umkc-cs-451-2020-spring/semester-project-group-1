import { MatFormFieldModule } from '@angular/material/form-field';
import { Component, OnInit } from '@angular/core';
import {FormControl, Validators, FormGroup} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-screen',
  templateUrl: './login-screen.component.html',
  styleUrls: ['./login-screen.component.scss']
})


export class LoginScreenComponent implements OnInit {
  constructor(private router: Router) {}
  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    userID: new FormControl(''),
    password: new FormControl(''),
  });

  email = new FormControl('', [Validators.required, Validators.email]);
  hide = true;

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  toDashboard(){
    this.router.navigateByUrl('/app/dashboard');
  }

  swapHide(){
    this.hide = !this.hide;
  }

}
