import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Internship } from './data/Internship';
import { Application } from './data/Application';
import {ApplicationDTO} from './data/ApplicationDTO';
import {BehaviorSubject, Observable} from 'rxjs';
import {InternshipDTO} from './data/InternshipDTO';

@Injectable({
  providedIn: 'root'
})
export class InternshipService {

  apiUrl = 'http://localhost:3000/api/secure/internship/all';
  apiUrlAdd = 'http://localhost:3000/api/secure/application';

  applicationSubject: BehaviorSubject<ApplicationDTO[]> = new BehaviorSubject([]);

  constructor(private _http: HttpClient) { }

  getInternships(){
    return this._http.get<InternshipDTO[]>(this.apiUrl);
  }

  addApplication(application: Application){

    let postData = {
      idCandidate: application.idCandidate,
      idInternship: application.idInternship,
      extraMessage: application.extraMessage
    }
     this._http.post(this.apiUrlAdd,postData).toPromise().then((data:any) => {
      console.log(data);
      console.log(data.json);
    });
  }

  httpHeaders() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem('token')}`
    });
  }

  loadApplicationsByCandidate(username: string) {
    const params = new HttpParams()
      .set('username', username);

    return this._http.get<ApplicationDTO[]>(this.apiUrlAdd, { params, headers: this.httpHeaders() })
      .subscribe((res) => {
        this.applicationSubject.next(res);
        console.log(res);
      },
        error => console.log(error));
  }

  public getAllApplicationsByCandidate(username: string): Observable<ApplicationDTO[]> {
    this.loadApplicationsByCandidate(username);
    return this.applicationSubject.asObservable();
  }

}
