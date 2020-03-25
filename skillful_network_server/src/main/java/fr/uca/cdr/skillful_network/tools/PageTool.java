package fr.uca.cdr.skillful_network.tools;

import javax.swing.SortOrder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageTool {

	@NotNull(message = "le nombre d''entité doit être une donnée valide")
	@PositiveOrZero(message = "le nombre de d'entité doit être supérieur ou égale à zéro")
	private Integer size;
	@NotNull(message = "le nombre de pages doit être une donnée valide")
	@Positive(message = "le nombre de pages  doit être supérieur ou égale à zéro")
	private Integer page;

	private String field;
	private SortOrder sortOrder;

	final static int DEFAULT_NBR_ENTITIES = 10;
	final static int DEFAULT_NBR_PAGES = 0;

	public PageTool() {
	}

	public PageTool(
			@NotNull(message = "le nombre d''entité doit être une donnée valide") @PositiveOrZero(message = "le nombre de d'entité doit être supérieur ou égale à zéro") Integer size,
			@NotNull(message = "le nombre de pages doit être une donnée valide") @Positive(message = "le nombre de pages  doit être supérieur ou égale à zéro") Integer page,
			String field, SortOrder sortOrder) {
		this.size = size;
		this.page = page;
		this.field = field;
		this.sortOrder = sortOrder;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Pageable requestPage() {
		Pageable pageable;
		Integer.reverse(size);
		Integer.reverse(page);
	
		if (size == 0) {
			size = DEFAULT_NBR_ENTITIES;
		}
		if (page > 0) {
			pageable = PageRequest.of(page - 1, size, defineSort());
		} else {
			pageable = PageRequest.of(DEFAULT_NBR_PAGES, size, defineSort());
		}
		return pageable;
	}

	public Sort defineSort() {
		Sort sort = Sort.unsorted();
		if (sortOrder != null) {
			if (field.length() != 0) {
				if (sortOrder == SortOrder.valueOf("ASCENDING")) {
					sort = Sort.by(field).ascending();
				} else if (sortOrder == SortOrder.valueOf("DESCENDING")) {
					sort = Sort.by(field).descending();
				} else {
					Sort.by(field).and(Sort.unsorted());
				}
			}
		}
		return sort;
	}

	@Override
	public String toString() {
		return "PageTool [size=" + size + ", page=" + page + ", criteriaSort=" + "]";
	}

}
