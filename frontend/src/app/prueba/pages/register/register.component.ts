import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { RegisterService } from '../../service/register.service';
import { ValidatorService } from '../../service/validator.service';

import { User, Zone } from '../../interface/prueba-interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup = this.fb.group({
    idNombreUsuario: ['', Validators.required],
    nombreUsuario: ['', [Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.nameUsuarioPattern)]],
    correoUsuario: ['', [Validators.required, Validators.email]],
    idZona: ['', Validators.required],
    zonaUsuario: ['', Validators.required]
  });

  public users: User[] = [];
  public zones: Zone[] = [];
  public selectedZone: Zone | null = null;
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

  loadRecordZone(): void {
    const selectedId = this.registerForm.value.idZona;
    if (selectedId) {
      this.registerService.showZoneById(selectedId).subscribe(
        (data: Zone) => {
          this.selectedZone = data;
          this.registerForm.patchValue({
            idZona: data.id,
            zonaUsuario: data.nombre
          });
        },
        (error) => {
          console.log("Error en conseguir la zona por id", error);
        }
      );
    }
  }

  addRecord(user: User): void {
    this.registerService.registerUser(user).subscribe(
      (data: User[]) => {
        this.users = data;
        this.recordAdded = true;
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
      return;
    }
    
    const idUser = this.registerForm.value.idNombreUsuario;
    const user: User = {
      id: idUser,
      nombre: this.registerForm.value.nombreUsuario,
      correo: this.registerForm.value.correoUsuario,
      zona : [
        {
          id: this.registerForm.value.idZona,
          nombre: this.registerForm.value.zonaUsuario
        }
      ]
    }
    this.addRecord(user);

  }

}
