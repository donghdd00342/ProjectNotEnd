import { Route } from '@angular/router';

import { JhiMetricsMonitoringComponent } from './metrics.component';

export const metricsRoute: Route = {
    path: 'dashboard-metrics',
    component: JhiMetricsMonitoringComponent,
    data: {
        pageTitle: 'Application Metrics - Dashboard'
    }
};
