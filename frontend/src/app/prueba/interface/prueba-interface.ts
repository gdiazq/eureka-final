export interface User {
    id: number;
    nombre: string;
    correo: string;
    zona: Zone[];
}

export interface Zone {
    id: number;
    nombre: string;
}