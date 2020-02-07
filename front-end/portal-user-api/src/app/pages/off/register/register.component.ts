import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

// Services
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  errorMessage = '';

  errorMessages = {
    fullName: [
      { type: 'required', message: 'Nome Completo é obrigatório.' }
    ],
    email: [
      { type: 'required', message: 'E-mail é obrigatório.' },
      { type: 'pattern', message: 'Informe um e-mail válido.' }
    ],
    login: [
      { type: 'required', message: 'Nome para login é obrigatório.' },
    ],
    password: [
      { type: 'required', message: 'Senha é obrigatório.' },
      { type: 'minlength', message: 'Informe uma senha com mais de cinco caracteres.' }
    ]
  };

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService
  ) {
    if (this.authenticationService.currentTokenValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      fullName: ['', Validators.compose([
        Validators.required
      ])],
      email: ['', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
      ])],
      login: ['', Validators.compose([
        Validators.required,
      ])],
      password: ['', Validators.compose([
        Validators.minLength(5),
        Validators.required
      ])]
    });
  }

  submit() {
    const user = this.setUser();

    this.authenticationService.register(user)
      .pipe(first())
      .subscribe(
        (res) => {
          if (res.erros !== undefined && res.erros !== null) {
            this.errorMessage = res.erros;
          } else {
            if(res.message == undefined){
              this.router.navigate(['/login']);
            }
            this.errorMessage = res.message;

          }
        },
        e => {
          this.errorMessage = 'Erro ao realizar cadastro.!';
        });
  }

  private setUser() {
    return {
      fullName: this.registerForm.get('fullName').value,
      email: this.registerForm.get('email').value,
      login: this.registerForm.get('login').value,
      password: this.registerForm.get('password').value
    };
  }

}
