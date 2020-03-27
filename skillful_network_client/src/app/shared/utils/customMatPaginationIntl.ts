import {MatPaginatorIntl} from '@angular/material/paginator';

const frenchRangeLabel = (page: number, pageSize: number, length: number) => {
  if (length == 0 || pageSize == 0) { return `0 à ${length}`; }
  length = Math.max(length, 0);
  const startIndex = page * pageSize;
  // If the start index exceeds the list length, do not try and fix the end index to the end.

  const endIndex = startIndex < length ?
    Math.min(startIndex + pageSize, length) :
    startIndex + pageSize;
  return `${startIndex + 1} - ${endIndex} à ${length}`;
}
export function getFrenchPaginatorIntl() {
  const paginatorIntl = new MatPaginatorIntl();
  paginatorIntl.itemsPerPageLabel = 'Nombres d\'entrées par page :';
  paginatorIntl.firstPageLabel = 'Première page';
  paginatorIntl.previousPageLabel = 'Page précédente';
  paginatorIntl.nextPageLabel = 'Page suivante';
  paginatorIntl.lastPageLabel = 'Dernière page';
  paginatorIntl.getRangeLabel = frenchRangeLabel;
  return paginatorIntl;
}