package fr.uca.cdr.skillful_network.tools;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageTool {

	private int numberOfEntities;
	private int pageIndex;

	public PageTool() {
	}

	public PageTool(int numberOfEntities, int page) {
		super();
		this.numberOfEntities = numberOfEntities;
		this.pageIndex = page;
	}

	public int getNumberOfEntities() {
		return numberOfEntities;
	}

	public void setNumberOfEntities(int numberOfEntities) {
		this.numberOfEntities = numberOfEntities;
	}

	public int getPage() {
		return pageIndex;
	}

	public void setPage(int page) {
		this.pageIndex = page;
	}

	public Optional<Pageable> requestPage() {
		Pageable pageable;
		if (pageIndex > 0) {
			pageable = PageRequest.of(pageIndex - 1, numberOfEntities);
		} else if (pageIndex == 0) {
			pageable = PageRequest.of(0, numberOfEntities);
		} else {
			pageable = null;
		}
		return pageable.toOptional();
	}
	
}
