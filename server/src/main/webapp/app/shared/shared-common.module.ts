import { NgModule } from '@angular/core';

import { NotEndSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [NotEndSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [NotEndSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class NotEndSharedCommonModule {}
