package cn.free.spring.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author gaowenjin
 * @date 2020/11/11
 * @description:
 */
@NoRepositoryBean
public interface BaseRepository<T, E> extends PagingAndSortingRepository<T, E> {

    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();

}
