export interface ITransaction {
    id?: any;
    amout?: string;
    userId?: any;
    userFirstName?: string;
    userLastName?: string;
    userEmail?: string;
    payDate?: Date;
}

export class Transaction implements ITransaction {
    constructor(
        public id?: any,
        public amout?: string,
        public userId?: any,
        public userFirstName?: string,
        public userLastName?: string,
        public userEmail?: string,
        public payDate?: Date
    ) {
        this.id = id ? id : null;
        this.amout = amout ? amout : null;
        this.userId = userId ? userId : null;
        this.userFirstName = userFirstName ? userFirstName : null;
        this.userLastName = userLastName ? userLastName : null;
        this.userEmail = userEmail ? userEmail : null;
        this.payDate = payDate ? payDate : null;
    }
}
