import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {AboutYouCompanyComponent} from './about-you-company/about-you-company.component';
import { EmailValidation, PasswordValidation, RepeatPasswordEStateMatcher, RepeatPasswordValidator } from './validators';
import {AuthService} from '../auth.service';
import {MatCheckboxChange} from '@angular/material/checkbox';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  isEditable = false;
  @Input()
  companyStep: boolean;
  candidateStep: boolean;
  selectedProfile: string;
  isLinear = false;
  private error = '';
  hidePassword = true;
  hideRetypePassword = true;
  form: any;
  passwordsMatcher = new RepeatPasswordEStateMatcher();
  exitEmail: boolean;
  errorEmail: boolean;
  errorFormatEmail: boolean;
  registerStep: boolean;
  checkEmail: boolean;
  id: string;
  profile: string;
  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) {
    this.selectedProfile = null;
    this.form = this.formBuilder.group({
      email: new FormControl('', EmailValidation),
      password: new FormControl('', PasswordValidation),
      passwordAgain: new FormControl(''),
      checkEmail: new FormControl(''),
      acceptTerms: new FormControl('', [Validators.requiredTrue])
    }, {validator: RepeatPasswordValidator});
    this.exitEmail = false;
    this.errorEmail = true;
    this.registerStep = true;
    this.errorFormatEmail = true;
  }

  ngOnInit() {
  }

  clickOnNext() {
    this.authService.getAreaOfInterest().subscribe()
    if (this.selectedProfile == null) {
      console.log('Select');
      this.error = 'Choose one option';
      this.error = 'Choose one option';
    } else {
      this.error = '';
      console.log(this.selectedProfile);
    }
  }

  clickOnCreateAccount() {

    console.log('Write about you');
    const email = this.form.get('email').value;
    const password = this.form.get('password').value;
    const passwordAgain = this.form.get('passwordAgain').value;
    this.id = email;
    if (password === passwordAgain ) {
      if (this.selectedProfile === 'COMPANY') {
        this.companyStep = true;
        this.candidateStep = false;
        this.profile = '/internship-app/company-profile';
        console.log(this.companyStep + '-' + this.candidateStep);
      } else {
        this.companyStep = false;
        this.candidateStep = true;
        this.profile = '/internship-app/candidate-profile';
        console.log(this.companyStep + '-' + this.candidateStep);
      }
      this.authService.getCompany(email).subscribe((res) => {
        console.log(res);
        this.exitEmail = false;
        this.errorEmail = false;
      }, (error) => {
        this.authService.getCandidate(email).subscribe((res) => {
          console.log(res);
          this.exitEmail = false;
          this.errorEmail = false;
        }, (error1) => {
          this.exitEmail = true;
          this.errorEmail = true;
          this.authService.register(email, password, this.selectedProfile)
            .subscribe((res) => {
              this.errorFormatEmail = true;
              console.log(res);
              this.registerStep = false;
            }, (error2) => {
              this.registerStep = true;
              console.log('eroare');
              console.log(error.statusText);
              this.errorFormatEmail = false;
            });
        });
      });
    }
  }
}

