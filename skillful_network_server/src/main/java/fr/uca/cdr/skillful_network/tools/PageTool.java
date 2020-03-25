package fr.uca.cdr.skillful_network.tools;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageTool {
	
	@NotNull(message ="le nombre d''entité doit être une donnée valide")
	@PositiveOrZero(message ="le nombre de d'entité doit être supérieur ou égale à zéro")
	private Integer size;
	@NotNull(message ="le nombre de pages doit être une donnée valide")
	@Positive(message ="le nombre de pages  doit être supérieur ou égale à zéro")
	private Integer page;
	final static int DEFAULT_NBR_ENTITIES = 10;
	final static int DEFAULT_NBR_PAGES = 0;

	public PageTool() {
	}

	public PageTool(int size, int page) {
		this.size = size;
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Pageable requestPage() {
		Pageable pageable;
		Integer.reverse(size);
		Integer.reverse(page);
		
		if (size <= 0) {
			size = DEFAULT_NBR_ENTITIES;
		}
		if (page > 0) {
			pageable = PageRequest.of(page - 1, size);
		} else {
			pageable = PageRequest.of(DEFAULT_NBR_PAGES, size);
		}
		return pageable;
	}

}
