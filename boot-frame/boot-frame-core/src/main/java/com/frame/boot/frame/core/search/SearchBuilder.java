package com.frame.boot.frame.core.search;

import com.frame.boot.frame.utils.EmptyUtil;
import com.frame.common.frame.base.bean.PageBean;
import com.frame.common.frame.base.bean.SortBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * 查询构建器
 * @author duancq
 * 2016年8月29日 上午10:37:18
 */
public class SearchBuilder {

	/**
	 * 排序
	 * @param sortParam
	 * @return
	 */
	public static Sort sort(SortBean sortParam) {
		if (sortParam != null) {
			return new Sort(new Order(Direction.valueOf(sortParam.getSortType().name()), sortParam.getSortField()));
		} else {
			return null;
		}
	}

	/**
	 * 排序
	 * @param sortParams
	 * @return
	 */
	public static Sort sort(SortBean... sortParams) {
		if (EmptyUtil.notEmpty(sortParams)) {
			int len = sortParams.length;
			Order[] orders = new Order[len];
			for (int i = 0; i < len; i++) {
				orders[i] = new Order(Direction.valueOf(sortParams[i].getSortType().name()), sortParams[i].getSortField());
			}
			return new Sort(orders);
		} else {
			return null;
		}
	}

	/**
	 * 构建分页请求
	 * @param pageParam
	 * @return
	 */
	public static PageRequest pageRequest(PageBean pageParam) {
		if (pageParam == null) {
			pageParam = new PageBean();
		}
		return new PageRequest(pageParam.getJpaPage(), pageParam.getRows());
	}

	/**
	 * 构建分页请求
	 * @param pageParam
	 * @param sortParam
	 * @return
	 */
	public static PageRequest pageRequest(PageBean pageParam, SortBean sortParam) {
		if (pageParam == null) {
			pageParam = new PageBean();
		}
		Sort sort = sort(sortParam);
		if (sort == null) {
			return new PageRequest(pageParam.getJpaPage(), pageParam.getRows());
		} else {
			return new PageRequest(pageParam.getJpaPage(), pageParam.getRows(), sort);
		}
	}
}
