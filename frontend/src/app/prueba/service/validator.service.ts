import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";

@Injectable({providedIn: 'root'})

export class ValidatorService {
    public nameUsuarioPattern: string = '([a-zA-Z]+) ([a-zA-Z]+)';
    public nameEmailPattern: string = "[a-zA-Z0-9!#$%&'*\/=?^_`\{\|\}~\+\-]([\.]?[a-zA-Z0-9!#$%&'*\/=?^_`\{\|\}~\+\-])+@[a-zA-Z0-9]([^@&%$\/\(\)=?¿!\.,:;]|\d)+[a-zA-Z0-9][\.][a-zA-Z]{2,4}([\.][a-zA-Z]{2})?";

    isValidField(form: FormGroup, field: string): boolean {
        const control = form.get(field);
        return control ? control.invalid && control.touched : false;
    }

    // Método específico para verificar si un campo tiene el error 'required'
    isFieldRequired(form: FormGroup, field: string): boolean {
        const control = form.get(field);
        return control ? control.hasError('required') && control.touched : false;
    }

    // Método específico para verificar si un campo tiene el error 'minlength'
    isFieldMinLength(form: FormGroup, field: string): boolean {
        const control = form.get(field);
        return control ? control.hasError('minlength') && control.touched : false;
    }

    // Método específico para verificar si un campo tiene el error 'pattern'
    isFieldPattern(form: FormGroup, field: string): boolean {
        const control = form.get(field);
        return control ? control.hasError('pattern') && control.touched : false;
    }

}