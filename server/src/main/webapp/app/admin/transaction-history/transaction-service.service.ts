import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ITransaction } from 'app/admin/transaction-history/transaction.model';
import { Observable } from 'rxjs';
import { createRequestOption } from 'app/shared';

@Injectable({
    providedIn: 'root'
})
export class TransactionServiceService {
    private resourceUrl = SERVER_API_URL + 'api/transaction-histories/admin-getall';

    constructor(private http: HttpClient) {}

    query(req?: any): Observable<HttpResponse<ITransaction[]>> {
        const options = createRequestOption(req);
        return this.http.get<ITransaction[]>(this.resourceUrl, { params: options, observe: 'response' });
    }
}
