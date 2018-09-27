import { Component, OnInit, OnDestroy } from '@angular/core';
import { TransactionServiceService } from 'app/admin/transaction-history/transaction-service.service';
import { Principal } from 'app/core';
import { JhiParseLinks, JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ITEMS_PER_PAGE } from 'app/shared';
import { HttpResponse } from '@angular/common/http';
import { Transaction } from 'app/admin/transaction-history/transaction.model';

@Component({
    selector: 'jhi-transaction-history',
    templateUrl: './transaction-history.component.html',
    styles: []
})
export class TransactionHistoryComponent implements OnInit, OnDestroy {
    currentAccount: any;
    transactions: Transaction[];
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private transactionService: TransactionServiceService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private parseLinks: JhiParseLinks,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private modalService: NgbModal
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.loadAll();
            // this.registerChange();
        });
    }

    ngOnDestroy() {
        this.routeData.unsubscribe();
    }

    registerChange() {
        // this.eventManager.subscribe('...', response => this.loadAll());
    }

    loadAll() {
        this.transactionService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<Transaction[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    trackIdentity(index, item: Transaction) {
        return item.id;
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/transaction-histories/admin-getall'], {
            queryParams: {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.transactions = data;
    }

    private onError(error) {
        this.alertService.error(error.error, error.message, null);
    }
}
