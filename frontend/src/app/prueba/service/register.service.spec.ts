import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RegisterService } from './register.service';
import { environments } from '../../environments/environments';
import { Zone, User } from '../interface/prueba-interface';

describe('RegisterService', () => {
  let service: RegisterService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RegisterService],
    });

    service = TestBed.inject(RegisterService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifica que no haya solicitudes pendientes
  });

  it('debería obtener una lista de zonas (showZones)', () => {
    const mockZones: Zone[] = [
      { id: 1, nombre: 'Zona 1' },
      { id: 2, nombre: 'Zona 2' },
    ];

    service.showZones().subscribe((zones) => {
      expect(zones.length).toBe(2);
      expect(zones).toEqual(mockZones);
    });

    const req = httpMock.expectOne(`${environments.baseUrl}/zones`);
    expect(req.request.method).toBe('GET');

    req.flush(mockZones); // Responde con datos simulados
  });

  it('debería registrar un usuario (registerUser)', () => {
    const mockUser: User = { id: 1, nombre: 'Juan Pérez', email: 'juan.perez@example.com', zonaId: 1 };
    const userToRegister: User = { nombre: 'Juan Pérez', email: 'juan.perez@example.com', zonaId: 1 };

    service.registerUser(userToRegister).subscribe((user) => {
      expect(user).toEqual(mockUser);
    });

    const req = httpMock.expectOne(`${environments.baseUrl}/users`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(userToRegister);

    req.flush(mockUser); // Responde con datos simulados
  });

  it('debería manejar errores correctamente (showZones)', () => {
    const errorMessage = 'No se encontraron zonas';

    service.showZones().subscribe({
      next: () => fail('La solicitud debería haber fallado'),
      error: (error) => {
        expect(error.status).toBe(404);
        expect(error.statusText).toBe('Not Found');
      },
    });

    const req = httpMock.expectOne(`${environments.baseUrl}/zones`);
    expect(req.request.method).toBe('GET');

    req.flush(errorMessage, { status: 404, statusText: 'Not Found' }); // Simula un error 404
  });
});