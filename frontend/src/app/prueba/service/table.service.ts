import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { environments } from "../../environments/environments";

@Injectable({providedIn: 'root'})

export class TableService {

    private baseUrl: string = environments.baseUrl;

    constructor(private http: HttpClient) {}

    showResults(): Observable<any[]> {
        return this.http.get<any[]>(`${this.baseUrl}/users/count`);
    }
}