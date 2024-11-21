import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import swal from 'sweetalert2';

import { RegisterService } from '../../service/register.service';
import { ValidatorService } from '../../service/validator.service';

import { User, Zone } from '../../interface/prueba-interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup = this.fb.group({
    nombreUsuario: ['', [Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.nameUsuarioPattern)]],
    correoUsuario: ['', [Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.nameEmailPattern)]],
    idZona: ['', Validators.required]
  });

  public users: User[] = [];
  public zones: Zone[] = [];
  public recordAdded: boolean = false;

  constructor( 
    private fb: FormBuilder,
    private registerService: RegisterService,
    private validatorService: ValidatorService
  ) {}

  isFieldRequired(field: string): boolean {
    return this.validatorService.isFieldRequired(this.registerForm, field);
  }

  isFieldMinLength(field: string): boolean {
    return this.validatorService.isFieldMinLength(this.registerForm, field);
  }

  isFieldPattern(field: string): boolean {
    return this.validatorService.isFieldPattern(this.registerForm, field);
  }

  ngOnInit(): void {
    this.loadRecords();
  }

  loadRecords(): void {
    this.registerService.showZones().subscribe(
      (data: Zone[]) => {
        this.zones = data;
      },
      (error) => {
        console.log("Error en conseguir todas las zonas", error);
      }
    );
  }

  addRecord(user: User): void {
    this.registerService.registerUser(user).subscribe(
      (data: User) => {
        this.users.push(data);
        this.recordAdded = true;
        swal.fire({
          title: 'Registro añadido',
          text: 'El usuario se ha añadido correctamente',
          icon: 'success'
        });
        this.registerForm.reset();
      },
      (error) => {
        console.log("Error al añadir el registro", error);
        this.recordAdded = false;
        this.registerForm.reset();
      }
    );  
  }

  onSubmit(): void {
    this.registerForm.markAllAsTouched();

    if (this.registerForm.invalid) {
      swal.fire({
        title: 'Error',
        text: 'Por favor, completa todos los del formulario',
        icon: 'error'
      });
      return this.registerForm.reset();
    }
    
    const user: User = {
      nombre: this.registerForm.value.nombreUsuario,
      email: this.registerForm.value.correoUsuario,
      zonaId : this.registerForm.value.idZona
    }
    this.addRecord(user);

  }

}
