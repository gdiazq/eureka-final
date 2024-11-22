import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './register.component';
import { RegisterService } from '../../service/register.service';
import { ValidatorService } from '../../service/validator.service';
import { of, throwError } from 'rxjs';
import swal from 'sweetalert2';

describe('RegisterComponent', () => {
    let component: RegisterComponent;
    let fixture: ComponentFixture<RegisterComponent>;
    let registerService: jasmine.SpyObj<RegisterService>;
    let validatorService: jasmine.SpyObj<ValidatorService>;

    beforeEach(() => {
        const registerServiceSpy = jasmine.createSpyObj('RegisterService', ['showZones', 'registerUser']);
        const validatorServiceSpy = jasmine.createSpyObj('ValidatorService', ['isFieldRequired', 'isFieldMinLength', 'isFieldPattern']);

        TestBed.configureTestingModule({
            imports: [ReactiveFormsModule],
            declarations: [RegisterComponent],
            providers: [
                { provide: RegisterService, useValue: registerServiceSpy },
                { provide: ValidatorService, useValue: validatorServiceSpy }
            ]
        }).compileComponents();

        fixture = TestBed.createComponent(RegisterComponent);
        component = fixture.componentInstance;
        registerService = TestBed.inject(RegisterService) as jasmine.SpyObj<RegisterService>;
        validatorService = TestBed.inject(ValidatorService) as jasmine.SpyObj<ValidatorService>;
    });

    // 1. Prueba para verificar la inicialización del formulario
    it('should create a form with three controls', () => {
        expect(component.registerForm.contains('nombreUsuario')).toBeTrue();
        expect(component.registerForm.contains('correoUsuario')).toBeTrue();
        expect(component.registerForm.contains('idZona')).toBeTrue();
    });

    // 3. Prueba cuando el formulario es válido
    it('should add a user when the form is valid', () => {
        const userMock = { nombre: 'Juan Pérez', email: 'juan.perez@example.com', zonaId: 1 };
        registerService.registerUser.and.returnValue(of(userMock));
    
        component.registerForm.setValue({
            nombreUsuario: 'Juan Pérez',
            correoUsuario: 'juan.perez@example.com',
            idZona: 1
        });

        spyOn(swal, 'fire');

        component.onSubmit();

        expect(registerService.registerUser).toHaveBeenCalledWith(userMock);
        expect(component.users).toContain(userMock);
        expect(component.recordAdded).toBeTrue();
        expect(swal.fire).toHaveBeenCalledWith(jasmine.objectContaining({
            title: 'Registro añadido',
            text: 'El usuario se ha añadido correctamente',
            icon: 'success'
        }));
    });

    // 4. Prueba cuando el formulario es inválido
    it('should not add a user when the form is invalid', () => {
        component.registerForm.setValue({
            nombreUsuario: '',
            correoUsuario: '',
            idZona: ''
        });

        spyOn(swal, 'fire');

        component.onSubmit();

        expect(registerService.registerUser).not.toHaveBeenCalled();
        expect(swal.fire).toHaveBeenCalledWith(jasmine.objectContaining({
            title: 'Error',
            text: 'Por favor, completa todos los del formulario',
            icon: 'error'
        }));
    });
});