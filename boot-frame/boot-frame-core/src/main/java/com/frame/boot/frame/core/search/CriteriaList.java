package com.frame.boot.frame.core.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.frame.common.frame.base.enums.AndOr;
import org.springframework.data.jpa.domain.Specification;

import com.frame.boot.frame.core.exceptions.SearchException;

/**
 * 查询组
 * @author duancq
 * 2016年8月29日 下午2:55:05
 */
public class CriteriaList<T> implements Specification<T> {

	private List<Criteria<T>> criterias = new ArrayList<>();
	private AndOr conjunction;

	private CriteriaList(List<Criteria<T>> criterias, AndOr conjunction) {
		this.criterias = criterias;
		this.conjunction = conjunction;
	}

	@SafeVarargs
	public static <T> CriteriaList<T> and(Criteria<T>... criterias) {
		return new CriteriaList<T>(Arrays.asList(criterias), AndOr.AND);
	}

	public static <T> CriteriaList<T> and(List<Criteria<T>> criterias) {
		return new CriteriaList<T>(criterias, AndOr.AND);
	}

	@SafeVarargs
	public static <T> CriteriaList<T> or(Criteria<T>... criterias) {
		return new CriteriaList<T>(Arrays.asList(criterias), AndOr.OR);
	}

	public static <T> CriteriaList<T> or(List<Criteria<T>> criterias) {
		return new CriteriaList<T>(criterias, AndOr.OR);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		int size = criterias.size();
		Predicate[] predicates = new Predicate[size];
		for (int i = 0; i < size; i++) {
			predicates[i] = criterias.get(i).toPredicate(root, query, cb);
		}
		switch (conjunction) {
			case AND: return cb.and(predicates);
			case OR: return cb.or(predicates);
			default: throw new SearchException(SearchException.ERROR_CODE_CRITERIA_ERROR,
					String.format("AndOr not found. conjunction:%s", conjunction),
					SearchException.SHOW_MSG_CRITERIA_ERROR);
		}
	}

}
