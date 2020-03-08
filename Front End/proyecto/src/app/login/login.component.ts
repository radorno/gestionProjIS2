import { FormGroup, FormControl, Validators} from '@angular/forms';
import { Component} from '@angular/core';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  constructor() { }


   miFormulario = new FormGroup({
     username : new FormControl('',Validators.required),
     userpassword: new FormControl('',Validators.required) 

   });


  

}
