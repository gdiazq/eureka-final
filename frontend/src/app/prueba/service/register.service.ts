import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { environments } from "../../environments/environments";

import { Zone, User } from "../interface/prueba-interface";



@Injectable({providedIn: 'root'})

export class RegisterService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient ) {}

    showZones(): Observable<Zone[]> {
        return this.http.get<Zone[]>(`${this.baseUrl}/zones`);
    }

    showZoneById(id: number): Observable<Zone> {
        return this.http.get<Zone>(`${this.baseUrl}/zones/${id}`);
    }

    registerUser(user: User): Observable<User[]> {
        return this.http.post<User[]>(`${this.baseUrl}/users`, user);
    }

}