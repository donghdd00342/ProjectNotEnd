import { Route } from '@angular/router';

import { JhiMetricsMonitoringComponent } from './metrics.component';

export const metricsRoute: Route = {
    path: 'user-statistic',
    component: JhiMetricsMonitoringComponent,
    data: {
        pageTitle: 'User Statistic'
    }
};
