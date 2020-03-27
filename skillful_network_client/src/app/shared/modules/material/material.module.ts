import {MatListModule} from '@angular/material/list';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatMenuModule} from '@angular/material/menu';


​
const MATERIAL_MODULES = [
  MatInputModule,
  MatButtonModule,
  MatIconModule,
  MatToolbarModule,
  MatFormFieldModule,
  MatCardModule,
  MatBadgeModule,
  MatTabsModule,
  MatMenuModule,
  MatChipsModule,
  MatTooltipModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatCheckboxModule,
  MatAutocompleteModule,
  MatPaginatorModule,
  MatTableModule,
  MatSortModule,
  MatInputModule,
  MatListModule,
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ...MATERIAL_MODULES
  ],
  exports: [
    ...MATERIAL_MODULES
  ]
})
export class MaterialModule {
}
